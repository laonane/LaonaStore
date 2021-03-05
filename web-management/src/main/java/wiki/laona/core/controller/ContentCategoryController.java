package wiki.laona.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.laona.core.pojo.ad.ContentCategory;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.entity.Result;
import wiki.laona.core.pojo.entity.ResultCode;
import wiki.laona.service.ContentCategoryService;

import java.util.Objects;

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

    @RequestMapping("/add")
    public Result add(@RequestBody ContentCategory category) {
        try {
            contentCategoryService.add(category);
            return new Result(ResultCode.SUCCESS, "保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAILED, "保存失败!");
        }
    }

    @RequestMapping("/findOne")
    public Result findOne(Long id) {
        ContentCategory category = contentCategoryService.findOne(id);
        if (Objects.nonNull(category)) {
            return new Result(ResultCode.SUCCESS, category);
        } else {
            return new Result(ResultCode.FAILED);
        }
    }

    @RequestMapping("/update")
    public Result update(@RequestBody ContentCategory category) {
        try {
            contentCategoryService.update(category);
            return new Result(ResultCode.SUCCESS, "修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAILED, "修改失败!");
        }
    }

    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            contentCategoryService.delete(ids);
            return new Result(ResultCode.SUCCESS, "删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAILED, "删除失败!");
        }
    }
}
