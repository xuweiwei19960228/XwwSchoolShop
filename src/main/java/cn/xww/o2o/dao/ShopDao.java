package cn.xww.o2o.dao;

import cn.xww.o2o.domain.ProductCategory;
import cn.xww.o2o.domain.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    /**
     * 新增dianpu
     */
    int insertShop(Shop shop);
    //更新店铺信息
    int updateShop(Shop shop);
    //通过店铺id店铺
    Shop queryByShopId(long shopId);

    /**
     * 分页查询店铺，可输入的条件有：
     * 店铺名（模糊),店铺状态，店铺类别，区域id，owner
     *rowIndex:从第几行取数据
     * pageSize：取几行
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize);

    /**
     * 返回queryShopList总数
     * 查询店铺总数
     *
     * @param shopCondition
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);


}
