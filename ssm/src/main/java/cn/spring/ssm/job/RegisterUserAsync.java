package cn.spring.ssm.job;

import cn.spring.ssm.web.dao.common.IntegralMapper;
import cn.spring.ssm.web.model.Integral;
import cn.spring.ssm.web.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.job
 * User: 25414
 * Date: 2019/10/28
 * Time: 14:06
 * Description:注册用户异步任务，增加积分
 */
@Slf4j
@Component
public class RegisterUserAsync {

    @Autowired
    private IntegralMapper integralMapper;

    @Async("asyncServiceExecutor")
    @Transactional
    public void aSynRegister(User user) throws Exception {
        Thread.sleep(3000);
        Integral record = new Integral();
        record.setIntegral(20)
                .setUser_id(user.getUserId());
        integralMapper.insert(record);
        log.info("user register userName = {}", user.getUsername());
    }
}
