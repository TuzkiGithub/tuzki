package cn.spring.ssm.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.web.model
 * User: 25414
 * Date: 2019/9/11
 * Time: 17:49
 * Description:
 */
@Data
@Accessors(chain = true)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    @Column(name = "id")
    private Integer id;

    private Integer age;

    private String name;

}
