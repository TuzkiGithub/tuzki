package cn.spring.ssm.aspect.exception;

import cn.spring.ssm.enum_.ResultEnum;
import cn.spring.ssm.util.JSONResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.zz.web.exception
 * User: 25414
 * Date: 2019/9/3
 * Time: 10:59
 * Description:
 */
@Slf4j
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String errorHandler(Exception ex) throws JsonProcessingException {
        log.error("###ControllerAdvice### error message:{}", ex.getMessage());

        int count = 0; //只打印15行的错误堆栈
        for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
            log.error("###ControllerAdvice### {}", stackTraceElement.toString());
            if (count++ > 15) break;
        }
        return JSONResult.returnJSONString(ResultEnum.EXCEPTION.getCode(), ex.getMessage());
    }

}
