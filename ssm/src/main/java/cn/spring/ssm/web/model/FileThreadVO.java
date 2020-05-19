package cn.spring.ssm.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.web.model
 * User: 25414
 * Date: 2019/11/14
 * Time: 16:45
 * Description:多线程读取文件实体类
 */
@Data
@Accessors(chain = true)
public class FileThreadVO {
    private InputStream is;
    private Integer start_line;
    private Integer end_line;
    private String result;
}
