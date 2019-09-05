package cn.spring.ssm.aspect.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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

    private static final Integer CODE = -10001;
    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", CODE);
        map.put("msg", ex.getMessage());
        return map;
    }

}
