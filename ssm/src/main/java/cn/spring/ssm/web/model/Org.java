package cn.spring.ssm.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.web.model
 * User: 25414
 * Date: 2019/12/16
 * Time: 18:33
 * Description:
 */
@Data
@Accessors(chain = true)
@Table(name = "vum_org")
public class Org {
    private String id;
    private String pid;
    private String code;
    private String name;
    private String label;
    private Integer type;
}
