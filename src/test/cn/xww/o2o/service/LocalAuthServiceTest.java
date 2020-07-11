package cn.xww.o2o.service;

import cn.xww.o2o.BaseTest;
import cn.xww.o2o.domain.LocalAuth;
import cn.xww.o2o.domain.PersonInfo;
import cn.xww.o2o.dto.LocalAuthExecution;
import cn.xww.o2o.enums.WechatAuthStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthServiceTest extends BaseTest {
    @Autowired
    private  LocalAuthServices localAuthServices;

    @Test
    public void testABindLocalAuth(){
        //注册绑定账号
        //平台信息
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        String username = "testusername";
        String password = "testpassword";
        personInfo.setUserId(1L);
        localAuth.setPersonInfo(personInfo);
        //设置账号
        localAuth.setUserName(username);
        localAuth.setPassword(password);
        //绑定账号
        LocalAuthExecution localAuthExecution = localAuthServices.bindLocalAuth(localAuth);
        assertEquals(WechatAuthStateEnum.SUCCESS.getState(),localAuthExecution.getState());
        //找
        localAuth = localAuthServices.getLocalAuthByUserId(personInfo.getUserId());
        //打印用户名和账号密码看是否相符
        System.out.println("用户昵称" + localAuth.getPersonInfo().getName());
        System.out.println("平台账号密码" + localAuth.getPassword());

    }

}
