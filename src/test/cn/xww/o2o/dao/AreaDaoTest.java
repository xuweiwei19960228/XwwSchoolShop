package cn.xww.o2o.dao;

import cn.xww.o2o.BaseTest;
import cn.xww.o2o.domain.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

//每次调BASETest，加载spring的配置文件
public class AreaDaoTest extends BaseTest {
    @Autowired
    //因为读取了配置文件，才能拿到AreaDao,@Autowired注解配置，虽然是个接口，但是mybatis自动生成了实现类，根据AreaDao.xml
    private AreaDao areaDao;
    @Test
    public void testQueryArea(){
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2,areaList.size());
    }


}
