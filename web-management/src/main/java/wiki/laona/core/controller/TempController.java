package wiki.laona.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.entity.Result;
import wiki.laona.core.pojo.entity.ResultCode;
import wiki.laona.core.pojo.template.TypeTemplate;
import wiki.laona.service.TypeTemplateService;

import java.util.List;

/**
 * @description: 模板 controller
 * @author: laona
 * @create: 2021-02-06 22:14
 **/
@RestController
@RequestMapping("/temp")
public class TempController {

    @Reference
    private TypeTemplateService typeTemplateService;

    @RequestMapping("/getTempPageList")
    @ResponseBody
    public PageResult<TypeTemplate> getTempPageList(Integer page, Integer pageSize, @RequestBody TypeTemplate typeTemplate) {
        return typeTemplateService.findTemplatePageList(page, pageSize, typeTemplate);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteByIds(Long[] ids) {
        try {
            typeTemplateService.deleteTempByIds(ids);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.DELETE_FAILED);
        }
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result addTypeTemplate(@RequestBody TypeTemplate typeTemplate) {
        try {
            typeTemplateService.addTypeTemplate(typeTemplate);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.DELETE_FAILED);
        }
    }

    @RequestMapping("/getTempById")
    @ResponseBody
    public Result getTempById(Long id) {
        try {
            TypeTemplate typeTemplate = typeTemplateService.getTemplateById(id);
            return new Result(ResultCode.SUCCESS, typeTemplate);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.QUERY_FAILED);
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result updateTypeTemplate(@RequestBody TypeTemplate typeTemplate) {
        System.out.println("typeTemplate = " + typeTemplate);
        try {
            typeTemplateService.updateTypeTemplate(typeTemplate);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.DELETE_FAILED);
        }
    }

}
