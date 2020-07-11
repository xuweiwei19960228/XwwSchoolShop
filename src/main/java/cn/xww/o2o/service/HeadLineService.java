package cn.xww.o2o.service;




import cn.xww.o2o.domain.HeadLine;

import java.io.IOException;
import java.util.List;

public interface HeadLineService {

	/**
	 * 根据传入的条件返回指定的头条列表
	 * 
	 * @param headLineCondition
	 * @return
	 * @throws IOException
	 */
	List<HeadLine> getHeadLineList(HeadLine headLineCondition);
}
