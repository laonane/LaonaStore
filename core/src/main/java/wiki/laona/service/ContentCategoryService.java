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

    PageResult findPage(ContentCategory category, Integer page, Integer rows);
}
