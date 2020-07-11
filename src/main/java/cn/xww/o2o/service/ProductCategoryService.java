package cn.xww.o2o.service;

import cn.xww.o2o.domain.ProductCategory;
import cn.xww.o2o.dto.ProductCategoryExecution;
import cn.xww.o2o.exceptions.ProductCategoryOperationException;


import java.util.List;

public interface ProductCategoryService {
    //查询某个店铺下所有商品
    List<ProductCategory> getProductCategoryList(long shopId);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;

    /**
     * 将此类别下的商品里的类别id置空，再删除该商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId)
        throws ProductCategoryOperationException;


}
