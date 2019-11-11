package cn.spring.ssm.service;

import cn.spring.ssm.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.service
 * User: 25414
 * Date: 2019/10/24
 * Time: 11:08
 * Description:
 */
public interface UserService {

    /**
     * 注册
     * 赠送积分
     * 通知用户
     */
    void register(User user) throws Exception;

    List<User> selectAll() throws Exception;
}
