package cn.spring.ssm.controller;

import cn.spring.ssm.service.UserService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.controller
 * User: 25414
 * Date: 2019/9/5
 * Time: 16:49
 * Description:
 */
@RestController
@Slf4j
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 获得全部用户数据
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getUserAll(){
        return JSONObject.toJSONString(userService.getUserAll());
    }
}
