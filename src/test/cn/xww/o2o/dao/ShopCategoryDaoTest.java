package cn.xww.o2o.dao;

import cn.xww.o2o.BaseTest;
import cn.xww.o2o.domain.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class ShopCategoryDaoTest extends BaseTest {

	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Test
	public void testQueryShopCategory() {
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
		System.out.println(shopCategoryList.size());
	}
}
