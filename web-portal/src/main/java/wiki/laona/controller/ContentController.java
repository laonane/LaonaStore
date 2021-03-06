package wiki.laona.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.laona.core.pojo.ad.Content;
import wiki.laona.core.pojo.entity.Result;
import wiki.laona.core.pojo.entity.ResultCode;
import wiki.laona.service.ContentService;

import java.util.List;

/**
 * @description: 广告 controller
 * @author: laona
 * @create: 2021-03-05 23:34
 **/
@RestController
@RequestMapping("/content")
public class ContentController {

    @Reference
    private ContentService contentService;

    @RequestMapping("/findByCategoryId")
    public Result findByCategoryId(Long categoryId) {
        try {
            List<Content> contentList = contentService.findCategoryFromRedisById(categoryId);
            return new Result(ResultCode.SUCCESS, contentList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAILED, null);
        }
    }
}
