package cn.xww.o2o.dao;

import cn.xww.o2o.BaseTest;
import cn.xww.o2o.domain.Area;
import cn.xww.o2o.domain.PersonInfo;
import cn.xww.o2o.domain.Shop;
import cn.xww.o2o.domain.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ShopDaoTest extends BaseTest{
    @Autowired
    private ShopDao shopDao;
    @Test
    @Ignore
    public void testInsertShop(){
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);

        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");

        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(28L);
        shop.setShopDesc("测试描述12345");
        shop.setShopAddr("测试地址1234");
        shop.setLastEditTime(new Date());


        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void testQueryByShopId(){
        long shopId = 1;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println("areaName:" + shop.getArea().getAreaName());
        System.out.println("areaId:" + shop.getArea().getAreaId());
    }

    @Test
    public void testQueryShopList(){
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shopCondition.setOwner(owner);
        List<Shop> shopList = shopDao.queryShopList(shopCondition,0,5);
        System.out.println("店铺列表大小" + shopList.size());
    }

    @Test
    public void testQueryShopListAndCount(){
        Shop shopCondition = new Shop();
        ShopCategory childCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(5L);
        childCategory.setParent(parentCategory);
        shopCondition.setShopCategory(childCategory);
        List<Shop> shopList = shopDao.queryShopList(shopCondition,0,5);
        int count = shopDao.queryShopCount(shopCondition);
        System.out.println("店铺列表大小：" + shopList.size());
        System.out.println("店铺总数:" + count);
//        PersonInfo owner = new PersonInfo();
//        owner.setUserId(1L);
//        shopCondition.setOwner(owner);
//        ShopCategory sc = new ShopCategory();
//        sc.setShopCategoryId(2L);
//        shopCondition.setShopCategory(sc);
//        int count = shopDao.queryShopCount(shopCondition);
//
//        System.out.println("店铺总数" + count);
    }





}
