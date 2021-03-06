package wiki.laona.service;

import wiki.laona.core.pojo.ad.Content;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.entity.Result;

import java.util.List;

/**
 * @description: 广告服务器接口
 * @author: laona
 * @create: 2021-03-05 14:29
 **/
public interface ContentService {
    /**
     * 分页查询当前页面数据
     *
     * @param content  广告实体
     * @param page     当前页码
     * @param pageSize 当前行数
     * @return 广告信息列表
     */
    PageResult<Content> findPage(Content content, Integer page, Integer pageSize);

    /**
     * 添加广告信息
     *
     * @param content 广告实体
     */
    void add(Content content);

    /**
     * 查找广告信息
     *
     * @param id 广告id
     * @return 广告信息
     */
    Content findOne(Long id);

    /**
     * 更新广告信息
     *
     * @param content 广告
     */
    void update(Content content);

    /**
     * 删除广告
     *
     * @param ids 待删除广告列表
     */
    void delete(Long[] ids);

    /**
     * 根据 id 查找所有广告信息
     *
     * @param categoryId 分类id
     * @return 广告信息列表
     */
    List<Content> findByCategoryId(Long categoryId);

    /**
     * 在 redis 缓存中取出广告信息 -> banner 轮播图
     * <p>
     * 如果redis 中没有缓存数据（第一次加载）,则需要先请求数据
     *
     * @param categoryId 广告分类信息 id
     * @return 广告信息列表
     */
    List<Content> findCategoryFromRedisById(Long categoryId);
}
