package cn.spring.ssm.job;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.job
 * User: 25414
 * Date: 2019/11/14
 * Time: 8:51
 * Description:分段读取文件
 */
@Slf4j
public class ReadFileThread implements Callable<String> {

    private Integer start_index;
    private Integer end_index;
    private InputStream is;

    public ReadFileThread(int start_index, int end_index, InputStream is) {
        this.start_index = start_index;
        this.end_index = end_index;
        this.is = is;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        LocalDateTime localDateTimeStart = LocalDateTime.now();
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
        int loc = 1;
        while (loc < start_index) {
            reader.readLine();
            loc++;
        }

        while (loc < end_index) {
            result.append(reader.readLine()).append("\r\n");
            loc++;
        }
        result.append(reader.readLine());
        String strResult = result.toString();
        reader.close();
        is.close();
        log.info("###ReadFileThread###FILE {} IS COMPLETE result = {} size = {}", Thread.currentThread().getName(), strResult, strResult
                .getBytes(Charset.forName("utf-8")).length);
        LocalDateTime localDateTimeEnd = LocalDateTime.now();
        Duration duration = Duration.between(localDateTimeStart, localDateTimeEnd);
        log.info("###ReadFileThread###FILE {} ,times = {}", Thread.currentThread().getName(), duration.toMillis());
        return strResult;
    }
}
