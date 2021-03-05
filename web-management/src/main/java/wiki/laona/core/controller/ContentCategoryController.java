package wiki.laona.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.laona.core.pojo.ad.ContentCategory;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.service.ContentCategoryService;

/**
 * @description: 广告分类 controller
 * @author: laona
 * @create: 2021-03-05 12:42
 **/
@RestController
@RequestMapping("/contentCategory")
public class ContentCategoryController {

    @Reference
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/search")
    public PageResult search(@RequestBody ContentCategory category, Integer page, Integer pageSize) {
        PageResult result = contentCategoryService.findPage(category, page, pageSize);
        return result;
    }
}
