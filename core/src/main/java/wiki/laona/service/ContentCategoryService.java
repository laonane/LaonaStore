package wiki.laona.service;

import com.alibaba.dubbo.config.annotation.Service;
import wiki.laona.core.pojo.ad.ContentCategory;
import wiki.laona.core.pojo.entity.PageResult;

/**
 * @description: 广告分类服务接口
 * @author: laona
 * @create: 2021-03-05 12:45
 **/
public interface ContentCategoryService {

    /**
     * 分页查询广告分类信息
     *
     * @param category
     * @param page
     * @param rows
     * @return
     */
    PageResult findPage(ContentCategory category, Integer page, Integer rows);

    /**
     * 添加分类信息
     *
     * @param category
     */
    void add(ContentCategory category);

    /**
     * 通过分类id 查找广告分类信息
     *
     * @param id 分类id
     * @return 分类信息
     */
    ContentCategory findOne(Long id);

    /**
     * 更新广告分类信息
     *
     * @param category 广告分类信息
     */
    void update(ContentCategory category);

    /**
     * 删除给定列表中的广告分类
     * @param ids 广告分类id列表
     */
    void delete(Long[] ids);
}
