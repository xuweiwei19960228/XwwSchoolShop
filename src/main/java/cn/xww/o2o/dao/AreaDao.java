package cn.xww.o2o.dao;

import cn.xww.o2o.domain.Area;

import java.util.List;

public interface AreaDao {
    /**
     * 列出区域列表
     * @return areaList
     */
    List<Area> queryArea();
}
