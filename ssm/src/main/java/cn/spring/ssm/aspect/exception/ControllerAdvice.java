package cn.spring.ssm.aspect.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
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
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String errorHandler(Exception ex) throws JsonProcessingException {
        return ex.getMessage();
//        return JSONResult.returnJSONString(ResultEnum.EXCEPTION.getCode(),ex.getMessage());
    }

}
