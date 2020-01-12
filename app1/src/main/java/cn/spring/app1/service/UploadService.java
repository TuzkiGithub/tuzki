package cn.spring.app1.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.app1.service
 * User: 25414
 * Date: 2019/9/23
 * Time: 8:45
 * Description:
 */
public interface UploadService {

    /**
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
