package wiki.laona.service;

import wiki.laona.core.pojo.good.Brand;

import java.util.List;

/**
 * @description: 品牌服务
 * @author: laona
 * @create: 2021-02-03 16:34
 **/
public interface BrandService {

    /**
     * 获取所有品牌
     * @return 品牌信息列表
     */
    List<Brand> findAllBrands();
}
