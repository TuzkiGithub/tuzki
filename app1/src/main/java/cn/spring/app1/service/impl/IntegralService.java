package cn.spring.app1.service.impl;

import cn.spring.app1.mapper.IntegralMapper;
import cn.spring.app1.mapper.LocalMsgMapper;
import cn.spring.app1.model.Integral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.app1.service.impl
 * User: 25414
 * Date: 2020/1/10
 * Time: 14:49
 * Description:
 */
@Service
public class IntegralService {

    @Autowired
    private IntegralMapper integralMapper;

    @Autowired
    private LocalMsgMapper localMsgMapper;


    public void save(Integral integral) {
        integralMapper.insert(integral);
    }

    /**
     * 添加积分，并修改本地方法表状态
     */
    @Transactional
    public void saveWithLocalMsg(Integral integral, String status) {
        Integral record = integralMapper.selectByUserId(integral.getUser_id());
        if (null == record) {
            save(integral);
        }
        localMsgMapper.update(integral.getUser_id().toString(), status);
    }
}
