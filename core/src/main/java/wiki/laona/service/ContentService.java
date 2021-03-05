package wiki.laona.service;

import wiki.laona.core.pojo.ad.Content;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.entity.Result;

/**
 * @description: 广告服务器接口
 * @author: laona
 * @create: 2021-03-05 14:29
 **/
public interface ContentService {
    /**
     * 分页查询当前页面数据
     *
     * @param content 广告实体
     * @param page    当前页码
     * @param pageSize    当前行数
     * @return 广告信息列表
     */
    PageResult<Content> findPage(Content content, Integer page, Integer pageSize);

    /**
     * 添加广告信息
     * @param content 广告实体
     */
    void add(Content content);

    /**
     * 查找广告信息
     * @param id 广告id
     * @return 广告信息
     */
    Content findOne(Long id);

    /**
     * 更新广告信息
     * @param content 广告
     */
    void update(Content content);

    /**
     * 删除广告
     * @param ids 待删除广告列表
     */
    void delete(Long[] ids);
}
