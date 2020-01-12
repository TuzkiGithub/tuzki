package cn.spring.ssm.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.util
 * User: 25414
 * Date: 2019/10/28
 * Time: 15:13
 * Description:
 */
@Component
public class ThreadPoolUtils {

    private int corePoolSize = 10;
    private int maximumPoolSize = 30;
    private long keepAliveTime = 10;
    private TimeUnit unit = TimeUnit.SECONDS;
    private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);

    private ThreadPoolExecutor executor;

    public ThreadPoolUtils() {
        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    public Future submit(Runnable runnable) {
        return executor.submit(runnable);
    }

}
