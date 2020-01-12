package cn.spring.ssm.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.model
 * User: 25414
 * Date: 2020/1/10
 * Time: 9:26
 * Description:
 */
@Data
@Accessors(chain = true)
public class LocalMsgVO {
    private Integer id;
    private String msg;
    private String status;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_date;
}
