package wiki.laona.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.entity.Result;
import wiki.laona.core.pojo.entity.ResultCode;
import wiki.laona.core.pojo.entity.SpecificationEntity;
import wiki.laona.core.pojo.specification.Specification;
import wiki.laona.service.SpecificationService;

/**
 * @description: 规格管理 controller
 * @author: laona
 * @create: 2021-02-06 15:32
 **/
@RestController
@RequestMapping("/spec")
public class SpecController {

    @Reference
    private SpecificationService specificationService;

    @RequestMapping("/getSpecPageList")
    @ResponseBody
    public PageResult<Specification> getSpecPageList(
            Integer page,
            Integer pageSize,
            @RequestBody Specification specification) {
        return specificationService.findSpecPageList(page, pageSize, specification);
    }

    @RequestMapping("/getSpecById")
    @ResponseBody
    public Result getSpecById(Long id) {
        try {
            SpecificationEntity entity =  specificationService.findSpecById(id);
            System.out.println("entity = " + entity);
            return new Result(ResultCode.SUCCESS, entity);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.QUERY_FAILED);
        }
    }

    @RequestMapping("/saveSpecEntity")
    @ResponseBody
    public Result saveSpecEntity(@RequestBody SpecificationEntity entity) {
        try{
            specificationService.saveSpec(entity);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.SAVE_FAILED);
        }
    }

    @RequestMapping("/updateSpecEntity")
    @ResponseBody
    public Result updateSpecEntity(@RequestBody SpecificationEntity entity) {
        try{
            specificationService.updateSpecEntity(entity);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.UPDATE_FAILED);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteByIds(Long[] ids) {
        try {
            specificationService.deleteSpecByIds(ids);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.DELETE_FAILED);
        }
    }
}
