package cn.spring.ssm.web.service.impl;

import cn.spring.ssm.job.ReadFileThread;
import cn.spring.ssm.web.model.FileThreadVO;
import cn.spring.ssm.framework.mq.DataDistributeMq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.web.service.impl
 * User: 25414
 * Date: 2019/11/14
 * Time: 16:10
 * Description:
 */
@Service
@Slf4j
public class DistributeService {

    @Value("${file.thread.num}")
    private Integer threadNum; //线程数

    @Resource(name = "asyncServiceExecutor")
    private ThreadPoolTaskExecutor executor;

    @Autowired
    private DataDistributeMq dataDistributeMq;

    /**
     * 启用多个线程分段读取文件
     * <p>
     * PS:若文件行数小于线程数会造成线程浪费
     * 适用于读取一行一行的数据报文
     *
     * @return
     */
    public String uploadByThread(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return null;
        }
        InputStream is = file.getInputStream();
        List<FileThreadVO> threadVOS = new ArrayList<>(threadNum);
        //为线程分配读取行数
        Integer lines = getLineNum(is);     //文件总行数
        Integer line;                       //每个线程分配行数
        Integer start_line;                 //线程读取文件开始行数
        Integer end_line;                   //线程读取文件结束行数
        StringBuffer data = new StringBuffer();
        if (lines < threadNum) {
            for (int i = 1; i <= lines; i++) {
                FileThreadVO fileThreadVO = new FileThreadVO();
                start_line = end_line = i;
                InputStream stream = file.getInputStream();

                ReadFileThread readFileThread = new ReadFileThread(start_line, end_line, stream);
                fileThreadVO.setStart_line(start_line);
                fileThreadVO.setIs(stream);
                fileThreadVO.setEnd_line(end_line);
                fileThreadVO.setResult(executor.submit(readFileThread).get());
                threadVOS.add(fileThreadVO);
            }
        } else {
            for (int i = 1, tempLine = 0; i <= threadNum; i++, tempLine = ++end_line) {
                InputStream stream = file.getInputStream();
                FileThreadVO fileThreadVO = new FileThreadVO();
                Integer var1 = lines / threadNum;
                Integer var2 = lines % threadNum;
                line = (i == threadNum) ? (var2 == 0 ? var1 : var1 + var2) : var1;
                start_line = (i == 1) ? 1 : tempLine;
                end_line = (i == threadNum) ? lines : start_line + line - 1;

                ReadFileThread readFileThread = new ReadFileThread(start_line, end_line, stream);
                fileThreadVO.setStart_line(start_line);
                fileThreadVO.setIs(stream);
                fileThreadVO.setEnd_line(end_line);
                fileThreadVO.setResult(executor.submit(readFileThread).get());
                threadVOS.add(fileThreadVO);
            }
        }
        threadVOS.forEach(record -> data.append(record.getResult()).append("\r\n"));
        String mergeStr = data.toString().trim();
        boolean isComplete = isComplete(file, mergeStr);
        if (!isComplete) {
            log.error("###uploadByThread### 文件完整性校验失败！");
            throw new Exception("The file is incomplete！");
        } else {
            dataDistributeMq.distributeData(mergeStr);
            return mergeStr;
        }
    }

    /**
     * 获取文件行数
     *
     * @param is 输入流
     * @return
     * @throws IOException
     */
    private int getLineNum(InputStream is) throws IOException {
        int line = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        while (reader.readLine() != null) {
            line++;
        }
        reader.close();
        is.close();
        return line;
    }

    /**
     * 校验文件完整性
     *
     * @param file 文件
     * @param data 合并后的数据
     * @return
     */
    private boolean isComplete(MultipartFile file, String data) throws IOException {
        long originSize = file.getBytes().length;
        long resultSize = data.getBytes(Charset.forName("utf-8")).length;
        return StringUtils.equals(String.valueOf(originSize), String.valueOf(resultSize));
    }

}
