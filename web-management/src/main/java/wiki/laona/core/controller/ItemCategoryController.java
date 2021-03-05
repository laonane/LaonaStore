package wiki.laona.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.laona.core.pojo.entity.Result;
import wiki.laona.core.pojo.entity.ResultCode;
import wiki.laona.core.pojo.item.ItemCat;
import wiki.laona.service.ItemCategoryService;

import java.util.List;

/**
 * @description: 条目控制器
 * @author: laona
 * @create: 2021-02-12 15:03
 **/
@RestController
@RequestMapping("/itemCate")
public class ItemCategoryController {

    @Reference
    private ItemCategoryService itemCategoryService;

    @RequestMapping("/getItemCategoryList")
    public Result getItemCatList(Long parentId) {
        List<ItemCat> itemCatList = itemCategoryService.findItemPageList(parentId);
        return new Result(ResultCode.SUCCESS, itemCatList);
    }
}
