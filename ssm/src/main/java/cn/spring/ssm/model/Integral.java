package cn.spring.ssm.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.mq.model
 * User: 25414
 * Date: 2019/10/9
 * Time: 18:06
 * Description:
 */
@Data
@Accessors(chain = true)
@Table(name = "integral")
public class Integral implements Serializable {
    private Long id;
    private Integer integral;
    private Long user_id;
}
