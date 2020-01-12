package cn.spring.app1.service.impl;

import cn.spring.app1.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.app1.service.impl
 * User: 25414
 * Date: 2019/9/23
 * Time: 8:46
 * Description:
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    private final static String path = "F:/IDEA/tuzki_learn/app1/src/main/resources/upload/";

    /**
     * @param file
     * @return
     */
    @Override
    public String upload(MultipartFile file) {
        if (file.isEmpty()) {
            return "-1";
        }

        try {
            String filename = file.getOriginalFilename();
            log.info("###UploadService### filename = {}", filename);
            String filePath = path + filename;
            File desFile = new File(filePath);
            file.transferTo(desFile);
            return "0";
        } catch (Exception e) {
            log.error("###UploadService### upload failed");
            return "-1";
        }

    }


}
