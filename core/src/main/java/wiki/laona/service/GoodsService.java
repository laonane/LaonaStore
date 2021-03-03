package wiki.laona.service;

import wiki.laona.core.pojo.entity.GoodsEntity;

/**
 * @description: goods 服务接口
 * @author: laona
 * @create: 2021-03-03 15:29
 **/
public interface GoodsService {
    /**
     * 添加商品
     *
     * @param goodsEntity 商品实体
     */
    void add(GoodsEntity goodsEntity);
}
