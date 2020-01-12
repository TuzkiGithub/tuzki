package cn.spring.app2.controller;

import cn.spring.app2.util.HttpUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.app2.controller
 * User: 25414
 * Date: 2019/9/23
 * Time: 14:15
 * Description:
 */
@RestController
public class UploadController {

    private final static String url = "http://127.0.0.1:8010/api/upload";


    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        try {
            return HttpUtils.uploadFileByHTTP(file, url, null);
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }
}
