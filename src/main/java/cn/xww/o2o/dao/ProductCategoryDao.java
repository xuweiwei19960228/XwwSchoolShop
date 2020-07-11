package cn.xww.o2o.dao;

import cn.xww.o2o.domain.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
    /**
     * 通过shopid查询店铺商品类别
     * @param shopId
     * @return
     */

    List<ProductCategory> queryProductCategoryList(long shopId);
    /**
     * 批量增加商品类别
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);


    /**
     * 删除指定商品，两个参数用注解找
     * @param productCategoryId
     * @param shopId
     * @return
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId,@Param("shopId") long shopId);

}
