package wiki.laona.service;

import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.template.TypeTemplate;

import java.util.List;
import java.util.Map;

/**
 * @description: 类型模板 service
 * @author: laona
 * @create: 2021-02-06 22:17
 **/
public interface TypeTemplateService {

    /**
     * 分页查询所有类型模板数据
     * @param page 当前页码
     * @param pageSize 每页最大数目
     * @param template 类型模板实体
     * @return 类型模板列表
     */
    PageResult<TypeTemplate> findTemplatePageList(Integer page, Integer pageSize, TypeTemplate template);

    /**
     * 根据id批量删除模板数据
     * @param ids ids
     */
    void deleteTempByIds(Long[] ids);

    /**
     * 添加一条记录
     * @param typeTemplate
     */
    void addTypeTemplate(TypeTemplate typeTemplate);

    /**
     * 获取
     * @param id
     * @return
     */
    TypeTemplate getTemplateById(Long id);

    /**
     * 更新 typeTemplate
     * @param typeTemplate
     */
    void updateTypeTemplate(TypeTemplate typeTemplate);

    /**
     * 根据 id 查询商品规格
     * @param id 分类id
     * @return 商品规格
     */
    List<Map> findBySpecWithId(Long id);
}
