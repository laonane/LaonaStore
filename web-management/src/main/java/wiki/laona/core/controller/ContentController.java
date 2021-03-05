package wiki.laona.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.laona.core.pojo.ad.Content;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.entity.Result;
import wiki.laona.core.pojo.entity.ResultCode;
import wiki.laona.service.ContentService;

import java.util.Objects;

/**
 * @description: 广告控制器
 * @author: laona
 * @create: 2021-03-05 14:28
 **/
@RestController
@RequestMapping("/content")
public class ContentController {

    @Reference
    private ContentService contentService;

    @RequestMapping("/search")
    public PageResult<?> search(@RequestBody Content content, Integer page, Integer pageSize) {
        return contentService.findPage(content, page, pageSize);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Content content) {
        try {
            System.out.println("content=" + content);
            contentService.add(content);
            return new Result(ResultCode.SUCCESS, "保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAILED, "保存失败!");
        }
    }

    @RequestMapping("/findOne")
    public Result findOne(Long id) {
        Content content = contentService.findOne(id);
        if (Objects.nonNull(content)) {
            return new Result(ResultCode.SUCCESS, content);
        }
        return new Result(ResultCode.FAILED, "查找");
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Content content) {
        try {
            contentService.update(content);
            return new Result(ResultCode.SUCCESS, "修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAILED, "修改失败!");
        }
    }

    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            contentService.delete(ids);
            return new Result(ResultCode.SUCCESS, "删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAILED, "删除失败!");
        }
    }
}
