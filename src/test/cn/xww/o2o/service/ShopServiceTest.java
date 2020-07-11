package cn.xww.o2o.service;

import cn.xww.o2o.BaseTest;
import cn.xww.o2o.domain.Area;
import cn.xww.o2o.domain.PersonInfo;
import cn.xww.o2o.domain.Shop;
import cn.xww.o2o.domain.ShopCategory;
import cn.xww.o2o.dto.ImageHolder;
import cn.xww.o2o.dto.ShopExecution;
import cn.xww.o2o.enums.ShopStateEnum;
import cn.xww.o2o.exceptions.ShopOperationException;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resources;
import java.io.*;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;


    @Test
    @Ignore
    public void testAddShop() throws IOException {
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
        shop.setShopName("测试店铺6");
        shop.setShopDesc("test6");
        shop.setShopAddr("test6");
        shop.setPhone("test6");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("E:\\TestImage\\test.jpg");
        InputStream is = new FileInputStream(shopImg);
        //shopService.addShop(shop,is,shopImg.getName());
        ImageHolder imageHolder = new ImageHolder(shopImg.getName(),is);
        ShopExecution se = shopService.addShop(shop,imageHolder);
        assertEquals(ShopStateEnum.CHECK.getState(),se.getState());



    }

    @Test
    @Ignore

    public void testModifyShop() throws ShopOperationException, FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("修改后的店铺名称xww");


        //修改的店铺图片
        File shopImg = new File("E:\\upload\\item\\shop\\test.jpg");
        InputStream is = new FileInputStream(shopImg);
        ImageHolder imageHolder =new ImageHolder(shopImg.getName(),is);
        ShopExecution shopExecution = shopService.modifyShop(shop,imageHolder);
        System.out.println("新的图片地址为：" + shopExecution.getShop().getShopImg());
    }

    @Test
    public void testGetShopList(){
        Shop shopCondition = new Shop();
        Area area = new Area();
        area.setAreaId(2);
        shopCondition.setArea(area);
        ShopExecution se = shopService.getShopList(shopCondition,2,2);
        System.out.println("店铺列表数：" + se.getShopList().size());
        System.out.println("店铺总数" + se.getCount());

    }











}
