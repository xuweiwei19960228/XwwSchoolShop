package cn.xww.o2o.service;

import cn.xww.o2o.domain.Area;
import cn.xww.o2o.domain.Shop;
import cn.xww.o2o.dto.ImageHolder;
import cn.xww.o2o.dto.ShopExecution;
import cn.xww.o2o.exceptions.ShopOperationException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface ShopService {
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws RuntimeException;
    //通过店铺id获取店铺信息
    Shop getByShopId(long shopId);

    //更新店铺信息，包括图片
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

    /**
     * 根据shopCondition分页返回商店列表数据
     */
    public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);

}
