package cn.spring.ssm.configure.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.configure.thread
 * User: 25414
 * Date: 2019/11/13
 * Time: 9:06
 * Description:自定义拒绝策略
 */
@Component
@Slf4j
public class MRejectedExecutionHandler implements RejectedExecutionHandler {
    /**
     * Method that may be invoked by a {@link ThreadPoolExecutor} when
     * {@link ThreadPoolExecutor#execute execute} cannot accept a
     * task.  This may occur when no more threads or queue slots are
     * available because their bounds would be exceeded, or upon
     * shutdown of the Executor.
     *
     * @param r        the runnable task requested to be executed
     * @param executor the executor attempting to execute this task
     */
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
            log.warn("===RejectedExecutionHandler ===");
            executor.getQueue().put(r);
        } catch (InterruptedException e) {
            log.error("===rejectedExecutorHandler===wait input queue error!");
        }
    }
}
