package cn.xww.o2o.dao;

import cn.xww.o2o.BaseTest;
import cn.xww.o2o.domain.LocalAuth;
import cn.xww.o2o.domain.PersonInfo;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthDaoTest extends BaseTest{
    @Autowired
    private LocalAuthDao localAuthDao;
    private static final String username = "testusername";
    private static final String password = "testpassword";

    @Test
    public void testAInsertLocalAuth() throws Exception{
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
        int effectedNum = localAuthDao.insertLocalAuth(localAuth);
        assertEquals(1,effectedNum);

    }

    @Test
    public void testBQueryLocalByUserNameAndPwd() throws Exception{
        //按照账号和密码查询用户信息
        LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd(username,password);
        assertEquals("测试1",localAuth.getPersonInfo().getName());
    }

    @Test
    public void testEQueryLocalByUserName() throws Exception{
        //按照账号用户信息
        LocalAuth localAuth = localAuthDao.queryLocalByUserName(username);
        assertEquals("测试",localAuth.getPersonInfo().getName());
    }

    @Test
    public void testCQueryLocalByUserId() throws Exception{
        LocalAuth localAuth = localAuthDao.queryLocalByUserId(2L);
        assertEquals("测试1",localAuth.getPersonInfo().getName());
    }

    @Test
    public void testDUpdataLocalAuth() throws Exception{
        //修改密码
        Date now = new Date();
        int effectedNum = localAuthDao.updateLocalAuth(2L,username,password,"xww",now);
        assertEquals(1,effectedNum);
        LocalAuth localAuth = localAuthDao.queryLocalByUserId(2L);
        System.out.println("新密码：" + localAuth.getPassword());
    }
}
