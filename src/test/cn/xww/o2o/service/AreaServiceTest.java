package cn.xww.o2o.service;

import cn.xww.o2o.BaseTest;
import cn.xww.o2o.domain.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;

   @Test
    public void  testGetAreaList(){

       List<Area> areaList = areaService.getAreaList();
    }


}

