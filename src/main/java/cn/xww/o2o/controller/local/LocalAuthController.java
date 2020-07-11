package cn.xww.o2o.controller.local;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.xww.o2o.domain.LocalAuth;
import cn.xww.o2o.domain.PersonInfo;
import cn.xww.o2o.dto.LocalAuthExecution;
import cn.xww.o2o.enums.LocalAuthStateEnum;
import cn.xww.o2o.enums.OperationStatusEnum;
import cn.xww.o2o.enums.PersonInfoStatusEnum;
import cn.xww.o2o.enums.PersonInfoTypeEnum;
import cn.xww.o2o.service.LocalAuthServices;
import cn.xww.o2o.service.PersonInfoService;
import cn.xww.o2o.util.CodeUtil;
import cn.xww.o2o.util.HttpServletRequestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @Description: 本地用户信息
 *
 * @author tyronchen
 * @date 2019年5月22日
 */
@Controller
@RequestMapping("/user")
public class LocalAuthController {

	@Autowired
	private LocalAuthServices localAuthService;
	@Autowired
	private PersonInfoService personInfoService;

	/**
	 * 注册账号
	 */
	@PostMapping(value = "/register")
	@ResponseBody
	public Map<String, Object> register(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 1、验证码校验
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误");
			return modelMap;
		}
		// 1、接收并转化相应参数
		String username = HttpServletRequestUtil.getString(request, "username");
		String password = HttpServletRequestUtil.getString(request, "password");
		if(localAuthService.getLocalAuthByUserNameAndPwd(username,password) == null){
//            LocalAuth localAuth = new LocalAuth();
//            localAuth.setUserName(username);
//            localAuth.setPassword(password);
//            LocalAuthExecution e = localAuthService.bindLocalAuth(localAuth);
			//新增一条平台信息
			LocalAuth localAuth = new LocalAuth();
			PersonInfo personInfo = new PersonInfo();
			personInfo.setUserId(2L);
			//给平台绑定用户信息
			localAuth.setPersonInfo(personInfo);
			//设置用户名和密码
			localAuth.setUserName(username);
			localAuth.setPassword(password);
			localAuth.setCreateTime(new Date());
			int effectedNum = localAuthService.insertLocalAuth(localAuth);
            modelMap.put("success", true);
        }else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户名和密码均不能为空");
            return modelMap;
        }
		// 判断
//		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
//			// 2、构建账号对象
//			LocalAuth localAuth = new LocalAuth();
//			localAuth.setUserName(username);
//			localAuth.setPassword(password);
//			LocalAuthExecution e = localAuthService.bindLocalAuth(localAuth);
//			// 3、操作成功，返回结果
//			if (e.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
//				// 同时创建用户信息
//				PersonInfo personInfo = new PersonInfo();
//				personInfo.setUserId(localAuth.getUserId());
//				personInfo.setName(username);
//				personInfo.setUserType(PersonInfoTypeEnum.CUSTOMER.getState());
//				personInfo.setEnableStatus(PersonInfoStatusEnum.ALLOW.getState());
//				personInfo.setCreateTime(new Date());
//				personInfoService.insertPersonInfo(personInfo);
//				modelMap.put("success", true);
//			}
//		} else {
//			modelMap.put("success", false);
//			modelMap.put("errMsg", "用户名和密码均不能为空");
//			return modelMap;

		return modelMap;
	}

	/**
	 * 检查注册的用户名是否已注册
	 */
	@PostMapping(value = "/checkusername")
	@ResponseBody
	public Map<String, Object> checkUsername(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String username = HttpServletRequestUtil.getString(request, "username");
		if (StringUtils.isNotBlank(username)) {
			LocalAuth localAuth = localAuthService.getLocalAuthByUserName(username);
			// 当前用户名的用户已存在
			if (localAuth != null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名已存在，请重新输入！");
			} else {
				modelMap.put("success", true);
			}
		}
		return modelMap;
	}

	/**
	 * 登录
	 */
	@PostMapping(value = "login")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
//		// 1、验证码，前端判断3次输入后需要验证码
//		boolean needVerify = HttpServletRequestUtil.getBoolean(request, "needVerify");
//		if (needVerify && !CodeUtil.checkVerifyCode(request)) {
//			modelMap.put("success", false);
//			modelMap.put("errMsg", OperationStatusEnum.VERIFYCODE_ERROR.getStateInfo());
//			return modelMap;
//		}

		// 2、获取参数
		String username = HttpServletRequestUtil.getString(request, "username");
		String password = HttpServletRequestUtil.getString(request, "password");
		String userType = HttpServletRequestUtil.getString(request, "userType");
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			LocalAuth localAuth = localAuthService.getLocalAuthByUserNameAndPwd(username, password);
			// 账号信息正确
			if (localAuth != null) {
				modelMap.put("success", true);
			} else {
				modelMap.put("用户名或密码错误", false);
			}
		}

			return modelMap;


	}

	/**
	 * 修改密码
	 */
	@PostMapping(value = "changepwd")
	@ResponseBody
	public Map<String, Object> changePwd(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 1、验证码
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", OperationStatusEnum.VERIFYCODE_ERROR.getStateInfo());
			return modelMap;
		}

		// 2、获取参数
		String username = HttpServletRequestUtil.getString(request, "username");
		String password = HttpServletRequestUtil.getString(request, "password");
		String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
		//Long userId = HttpServletRequestUtil.getString(request, "userId");
		//Date now = new Date();
		// 空值验证
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)
				&& StringUtils.isNotBlank(newPassword)) {
			// 修改密码
			LocalAuthExecution lae = localAuthService.modifyLocalAuth(1L,username, password, newPassword);
			// 操作成功
			if (lae.getState() == OperationStatusEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", lae.getStateInfo());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入有效信息");
		}
		return modelMap;
	}

	/**
	 * 退出登录
	 */
	@PostMapping(value = "logout")
	@ResponseBody
	public Map<String, Object> logout(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		request.getSession().setAttribute("user", null);
		request.getSession().setAttribute("shopList", null);
		request.getSession().setAttribute("currentShop", null);
		modelMap.put("success", true);
		return modelMap;
	}
}
