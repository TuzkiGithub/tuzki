package cn.spring.ssm.model; /**
 * Created with IntelliJ IDEA.
 * Package: PACKAGE_NAME
 * cn.zz.model.User: 25414
 * Date: 2019/9/4
 * Time: 14:47
 * Description:
 */

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    @Column(name = "id")
    private Long userId;

    @Column(name = "realname")
    private String realName;

    private String username;

    private String password;

    private String phone;

    @Column(name = "e_mail")
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String birthday;

    private String gender;

    private String code;
}