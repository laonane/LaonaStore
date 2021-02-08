package wiki.laona.core.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wiki.laona.core.pojo.entity.Result;
import wiki.laona.core.pojo.entity.ResultCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 登录控制器
 * @author: laona
 * @create: 2021-02-08 15:46
 **/
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/getLoginName")
    @ResponseBody
    public Result getLoginName() {
        try {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            Map<String, String> map = new HashMap<>();
            map.put("username", name);
            return new Result(ResultCode.SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAILED);
        }
    }
}
