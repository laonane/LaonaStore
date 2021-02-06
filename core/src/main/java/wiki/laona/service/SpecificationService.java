package wiki.laona.service;

import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.entity.SpecificationEntity;
import wiki.laona.core.pojo.specification.Specification;

/**
 * @description: 规格服务接口
 * @author: laona
 * @create: 2021-02-06 15:36
 **/
public interface SpecificationService {

    /**
     * 获取规格分页数据
     * @param page 当前页码
     * @param pageSize 一页多少条数据
     * @param spec 规格实体
     * @return {@linkplain PageResult} 规格数据列表
     */
    PageResult<Specification> findSpecPageList(Integer page, Integer pageSize, Specification spec);

    /**
     * 保存规格
     * @param entity 规格
     */
    void saveSpec(SpecificationEntity entity);

    /**
     * 根据id批量删除规格
     * @param ids
     */
    void deleteSpecByIds(Long[] ids);

    /**
     * 根据id查询规格信息
     * @param id id
     * @return 规格信息
     */
    SpecificationEntity findSpecById(Long id);

    /**
     * 更新规格信息
     * @param entity 规格实体
     */
    void updateSpecEntity(SpecificationEntity entity);

}
