package cn.xww.o2o.controller.shopadmin;
/**
 * web-inf下的文件不能直接访问，通过路由解析转发到相应页面
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "shopadmin",method = {RequestMethod.GET})
public class ShopAdminController {
    @RequestMapping(value = "/shopoperation")
    public String shopOperation(){
//转发到店铺注册、编辑页面
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    //转发到店铺列表页面
    public String shopList(){
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanagement")
    public String shopManagement(){
        return "shop/shopmanagement";
    }


    @RequestMapping(value = "/productcategorymanagement",method = RequestMethod.GET)
    private String productManage(){
        return "shop/productcategorymanagement";
    }

    @RequestMapping(value = "/productoperation",method = RequestMethod.GET)
    private String productOperation(){
        //转发到商品添加/编辑页面
        return "shop/productoperation";
    }

    @RequestMapping(value = "/productmanagement",method = RequestMethod.GET)
    private String productManagement(){
        //转发到商品添加/编辑页面
        return "shop/productmanagement";
    }



}
