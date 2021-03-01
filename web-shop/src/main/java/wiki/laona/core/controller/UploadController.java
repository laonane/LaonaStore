package wiki.laona.core.controller;

import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import wiki.laona.core.pojo.entity.Result;
import wiki.laona.core.pojo.entity.ResultCode;
import wiki.laona.utils.FastDFSClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 上传文件 controller
 * @author: laona
 * @create: 2021-02-28 21:02
 **/
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Value("${FILE_SERVER_URL}")
    private String FILE_SERVER_URL;

    @RequestMapping("/uploadFile")
    public Result uploadFile(MultipartFile file) throws Exception {
        try {
            // 创建一个 FastDFS 客户端
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:fastdfs/fdfs_client.conf");
            String serverPath = fastDFSClient.uploadFile(file.getBytes(), file.getOriginalFilename(), file.getSize());
            String path = FILE_SERVER_URL + serverPath;
            return new Result(ResultCode.SUCCESS, path);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAILED, "上传文件失败，请查看log");
        }
    }

    @RequestMapping("/uploadImage")
    public Map uploadImage(MultipartFile upfile) throws Exception {
        try {
            FastDFSClient fastDFS = new FastDFSClient("classpath:fastdfs/fdfs_client.conf");
            //上传文件返回文件保存的路径和文件名
            String path = fastDFS.uploadFile(upfile.getBytes(), upfile.getOriginalFilename(), upfile.getSize());
            //拼接上服务器的地址返回给前端
            String url = FILE_SERVER_URL + path;
            Map<String, Object> result = new HashMap<>();
            result.put("state", "SUCCESS");
            result.put("url", url);
            result.put("title", upfile.getOriginalFilename());
            result.put("original", upfile.getOriginalFilename());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/deleteImage")
    public Result deleteImage(String url){
        try {
            FastDFSClient fastDFS = new FastDFSClient("classpath:fastdfs/fdfs_client.conf");
            String path = url.substring(FILE_SERVER_URL.length());
            System.out.println("path = " + path);
            Integer result = fastDFS.deleteFile(path);
            if (result == 0) {
                return new Result(ResultCode.SUCCESS);
            }else {
                return new Result(ResultCode.FAILED, "删除失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAILED, "删除失败");
        }
    }
}
