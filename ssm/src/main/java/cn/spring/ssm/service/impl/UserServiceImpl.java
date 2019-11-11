package cn.spring.ssm.service.impl;

import cn.spring.ssm.asyn.RegisterUserAsync;
import cn.spring.ssm.dao.common.UserMapper;
import cn.spring.ssm.model.User;
import cn.spring.ssm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.service.impl
 * User: 25414
 * Date: 2019/10/24
 * Time: 11:09
 * Description:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RegisterUserAsync registerUserAsync;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    private final static String USER_LIST = "USER_LIST";

    /**
     * 注册
     * 赠送积分
     * 通知用户
     */
    @Override
    public void register(User user) throws Exception {
        userMapper.insert(user);
        registerUserAsync.aSynRegister(user);
    }

    /**
     * 查询所有用户信息，并将其放入缓存
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<User> selectAll() throws Exception {
        log.info("UserRegister Service cache name = {}", USER_LIST);
        List<User> list = userMapper.selectAll();
        valueOperations.set(USER_LIST, list, 100, TimeUnit.SECONDS);
        return list;
    }


}
