package cn.spring.ssm.web.service.impl;

import cn.spring.ssm.web.dao.common.LocalMsgMapper;
import cn.spring.ssm.web.dao.common.UserMapper;
import cn.spring.ssm.common.enums.LocalMsgEnum;
import cn.spring.ssm.web.model.LocalMsgVO;
import cn.spring.ssm.web.model.User;
import cn.spring.ssm.framework.mq.AddIntegralMq;
import cn.spring.ssm.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.web.service.impl
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
    private LocalMsgMapper localMsgMapper;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Value("${spring.redis.user.list.key}")
    private String USER_LIST;

    @Value("${spring.redis.user.list.time}")
    private Integer USER_TIME;

    @Autowired
    private AddIntegralMq addIntegralMq;

    /**
     * 注册用户
     * 1：insert user表
     * 2：insert integral表（mq）
     * <p>
     * <p>
     * 基于mq实现分布式事务,保证最终一致性
     */
    @Override
    public void register(User user) throws Exception {
        Long data = save(user);
        addIntegralMq.distributeData(String.valueOf(data));
    }

    /**
     * 新增用户，并保存本地消息表
     *
     * @param user
     */
    @Transactional
    public Long save(User user) throws Exception {
        userMapper.insert(user);
        LocalMsgVO localMsgVO = new LocalMsgVO();
        localMsgVO.setMsg(user.getUserId().toString())
                .setStatus(LocalMsgEnum.INITIAL.toString())
                .setCreate_date(new Date());
        localMsgMapper.save(localMsgVO);
        return user.getUserId();
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
        Object obj = valueOperations.get(USER_LIST);
        if (null != obj) {
            return (List<User>) obj;
        } else {
            List<User> list = userMapper.selectAll();
            valueOperations.set(USER_LIST, list, USER_TIME, TimeUnit.SECONDS);
            return list;
        }
    }

}
