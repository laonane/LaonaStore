package wiki.laona.service;

import wiki.laona.core.pojo.entity.PageResult;
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

    /**
     * 分页查找品牌数据
     * @param page 当前页码
     * @param pageSize 每页数据
     */
    PageResult<Brand> findPageBrands(Integer page, Integer pageSize);

    /**
     * 添加品牌
     * @param brand 品牌
     */
    void addBrand(Brand brand);
}
