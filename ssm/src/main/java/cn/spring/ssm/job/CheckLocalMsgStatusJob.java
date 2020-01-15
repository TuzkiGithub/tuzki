package cn.spring.ssm.job;

import cn.spring.ssm.dao.common.LocalMsgMapper;
import cn.spring.ssm.enum_.LocalMsgEnum;
import cn.spring.ssm.model.LocalMsgVO;
import cn.spring.ssm.mq.AddIntegralMq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.job
 * User: 25414
 * Date: 2020/1/13
 * Time: 17:41
 * Description:检查本地消息表状态
 */
@Slf4j
@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
public class CheckLocalMsgStatusJob {

    @Autowired
    private LocalMsgMapper localMsgMapper;

    @Autowired
    private AddIntegralMq addIntegralMq;


    @Scheduled(fixedDelay = 10000)  //间隔10秒
    @Async("asyncServiceExecutor")
    public void checkLocalMsgStatus() throws Exception {
        List<LocalMsgVO> localMsgVOList = localMsgMapper.selectAll();
        if (!CollectionUtils.isEmpty(localMsgVOList)) {
            localMsgVOList = localMsgVOList.stream()
                    .filter((LocalMsgVO localMsgVO) -> !StringUtils.equals(String.valueOf(LocalMsgEnum.FINAL), localMsgVO.getStatus()))
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(localMsgVOList)) {
                localMsgVOList.forEach((LocalMsgVO localMsgVO) -> {
                    try {
                        addIntegralMq.distributeData(localMsgVO.getMsg());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

            }
        }


    }
}
