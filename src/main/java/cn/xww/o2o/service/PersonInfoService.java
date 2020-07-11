package cn.xww.o2o.service;

import cn.xww.o2o.domain.PersonInfo;
import cn.xww.o2o.dto.PersonInfoExecution;


public interface PersonInfoService {

	/**
	 * 根据用户Id获取personInfo信息
	 * @param userId
	 * @return
	 */
	PersonInfo getPersonInfoById(Long userId);

	PersonInfoExecution insertPersonInfo(PersonInfo personInfo);

}
