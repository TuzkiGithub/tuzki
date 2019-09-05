package cn.spring.ssm.service;

import cn.spring.ssm.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.service
 * User: 25414
 * Date: 2019/9/5
 * Time: 16:46
 * Description:
 */
public interface UserService {
    List<User> getUserAll();
}
