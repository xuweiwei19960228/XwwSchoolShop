package cn.xww.o2o.service.Impl;


import java.util.List;


import cn.xww.o2o.dao.ShopCategoryDao;
import cn.xww.o2o.domain.ShopCategory;
import cn.xww.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

	@Autowired
	private ShopCategoryDao shopCategoryDao;


	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
		return shopCategoryDao.queryShopCategory(shopCategoryCondition);
	}
};
