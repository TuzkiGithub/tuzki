package cn.spring.ssm.job;

import cn.spring.ssm.dao.integral.IntegralMapper;
import cn.spring.ssm.model.Integral;
import cn.spring.ssm.model.User;
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
 * Description:
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
