package cn.xww.o2o.service;



import cn.xww.o2o.domain.Product;
import cn.xww.o2o.dto.ImageHolder;
import cn.xww.o2o.dto.ProductExecution;
import cn.xww.o2o.exceptions.ProductOperationException;

import java.util.List;

public interface ProductService {
	//查询商品列表并分页
	ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);


//	ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
//			throws ProductOperationException;

	ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws Exception;

	Product getProductById(long productId);

	ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
			throws Exception;
}
