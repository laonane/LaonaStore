package wiki.laona.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import wiki.laona.core.pojo.entity.Result;
import wiki.laona.core.pojo.entity.ResultCode;

/**
 * @description: 上传文件 controller
 * @author: laona
 * @create: 2021-02-28 21:02
 **/
@RestController
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping("/uploadFile")
    public Result uploadFile(MultipartFile file) throws Exception {
        System.out.println("file = " + file);
        return new Result(ResultCode.SUCCESS, "测试==上传成功");
    }
}
