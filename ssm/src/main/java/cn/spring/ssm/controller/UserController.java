package cn.spring.ssm.controller;

import cn.spring.ssm.enum_.ResultEnum;
import cn.spring.ssm.model.User;
import cn.spring.ssm.service.UserService;
import cn.spring.ssm.util.JSONResult;
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
     * 用户注册（注册、增加积分、日志输出）
     *
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerUser(User user) throws Exception {
        userService.register(user);
        return JSONResult.returnJSONString(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
    }


    /**
     * 查询用户列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET)
    public String selectAll() throws Exception {
        return JSONResult.returnJSONString(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), userService.selectAll());
    }

}
