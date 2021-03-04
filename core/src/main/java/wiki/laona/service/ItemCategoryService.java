package wiki.laona.service;

import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.item.ItemCat;

import java.util.List;

/**
 * @description: 条目服务层接口
 * @author: laona
 * @create: 2021-02-12 15:05
 **/
public interface ItemCategoryService {

    /**
     * 查找条目分页数据
     * @param parentId 父类id
     * @return 条目分页数据
     */
    List<ItemCat> findItemPageList(Long parentId);

    /**
     * 根据 id 查询分类
     * @param id 模板id
     * @return 分类
     */
    ItemCat findItemCategoryById(Long id);


    /**
     * 查询所有分类信息
     * @return 分类信息列表
     */
    List<ItemCat> getAllCategoryList();

}
