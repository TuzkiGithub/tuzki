package cn.spring.ssm.web.controller;

import cn.spring.ssm.common.enums.ResultEnum;
import cn.spring.ssm.web.service.impl.DistributeService;
import cn.spring.ssm.common.util.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.web.controller
 * User: 25414
 * Date: 2019/11/13
 * Time: 18:39
 * Description:多线程分段读取文件内容
 */
@RestController
@Slf4j
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private DistributeService distributeService;

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        String data = distributeService.uploadByThread(file);
        log.info("###UploadController###合并读取内容data = {}", data);
        return JSONResult.returnJSONString(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), data);
    }

}
