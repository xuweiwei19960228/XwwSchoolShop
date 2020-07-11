package cn.xww.o2o.service;

import cn.xww.o2o.domain.ShopCategory;

import java.util.List;

//根据查询条件返回shopcategory列表
public interface ShopCategoryService {
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
