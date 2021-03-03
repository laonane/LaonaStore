package wiki.laona.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wiki.laona.core.pojo.entity.Result;
import wiki.laona.core.pojo.entity.ResultCode;
import wiki.laona.core.pojo.template.TypeTemplate;
import wiki.laona.service.TypeTemplateService;

import java.util.List;
import java.util.Map;

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

    @RequestMapping("/getTempById")
    @ResponseBody
    public Result getTempById(Long id) {
        try {
            TypeTemplate typeTemplate = typeTemplateService.getTemplateById(id);
            return new Result(ResultCode.SUCCESS, typeTemplate);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.QUERY_FAILED);
        }
    }

    @RequestMapping("/findBySpecWithId")
    public Result findBySpecWithId(Long id) {
        System.out.println("id = " + id);
        try {
            List<Map> result = typeTemplateService.findBySpecWithId(id);
            System.out.println("result = " + result);
            return new Result(ResultCode.SUCCESS, result);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAILED, "查询失败");
        }
    }
}
