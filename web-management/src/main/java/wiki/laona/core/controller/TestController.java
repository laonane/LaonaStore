package wiki.laona.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.laona.service.TestCore;

/**
 * @program: LaonaStore
 * @description: 测试控制器
 * @author: HuaiAnGG
 * @create: 2021-01-31 01:27
 **/
@RestController
public class TestController {

    @Reference
    private TestCore testCore;

    @RequestMapping("/getname")
    public String getName() {
        return testCore.getName();
    }
}
