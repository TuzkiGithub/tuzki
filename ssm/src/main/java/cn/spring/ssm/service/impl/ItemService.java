package cn.spring.ssm.service.impl;

import cn.spring.ssm.dao.common.ItemMapper;
import cn.spring.ssm.mq.DataDistributeMq;
import cn.spring.ssm.util.DistributeLock;
import cn.spring.ssm.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.service.impl
 * User: 25414
 * Date: 2019/12/27
 * Time: 9:14
 * Description:
 */
@Slf4j
@Service
public class ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${LOCK_TIME}")
    private Integer LOCK_TIME;

    @Value("${SECKILL_NUM}")
    private String SECKILL_NUM;

    @Value("${DISTRIBUTE_LOCK}")
    private String DISTRIBUTE_LOCK;

    @Autowired
    private DistributeLock distributeLock;

    @Autowired
    private DataDistributeMq dataDistributeMq;
    /**
     * 秒杀指定数量的商品
     *
     * @param id
     * @return
     */
    public int updateItemByRecord(String id) throws Exception {
        String value = UUID.randomUUID().toString() + id;
        if (!distributeLock.lock(DISTRIBUTE_LOCK, value, LOCK_TIME)) {
            log.info("###SECKILL ACTIVE### thread = {} is not get lock", Thread.currentThread().getName());
            return -1;
        }
        log.info("###SECKILL ACTIVE### thread = {} is get lock", Thread.currentThread().getName());
        Thread.sleep(1500);
        Integer num = Integer.parseInt((String) redisUtil.get(SECKILL_NUM));
        if (num > 0) {
            itemMapper.updateItemNumById(id);
            redisUtil.decr(SECKILL_NUM);
            distributeLock.unlock(DISTRIBUTE_LOCK, value);
            return 0;
        }
        distributeLock.unlock(DISTRIBUTE_LOCK, value);
        return -2;
    }

}
