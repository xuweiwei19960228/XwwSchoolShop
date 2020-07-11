package cn.xww.o2o.service.Impl;

import cn.xww.o2o.dao.PersonInfoDao;
import cn.xww.o2o.domain.PersonInfo;
import cn.xww.o2o.dto.PersonInfoExecution;
import cn.xww.o2o.enums.OperationStatusEnum;
import cn.xww.o2o.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class PersonInfoServiceImpl implements PersonInfoService {
	@Autowired
	private PersonInfoDao personInfoDao;


	public PersonInfo getPersonInfoById(Long userId) {
		return personInfoDao.queryPersonInfoById(userId);
	}

	public PersonInfoExecution insertPersonInfo(PersonInfo user) {

		// 设置默认信息
		user.setCreateTime(new Date());
		try {
			int effectedNum = personInfoDao.insertPersonInfo(user);
			if (effectedNum <= 0) {
				throw new RuntimeException("用户信息新增失败");
			} else {
				return new PersonInfoExecution(OperationStatusEnum.SUCCESS);
			}
		} catch (Exception e) {
			throw new RuntimeException("insertPersonInfo error:" + e.getMessage());
		}
	}


}
