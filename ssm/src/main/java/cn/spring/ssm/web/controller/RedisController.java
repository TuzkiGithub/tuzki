package cn.spring.ssm.web.controller;

import cn.spring.ssm.common.enums.ResultEnum;
import cn.spring.ssm.common.util.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;


/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.web.controller
 * User: 25414
 * Date: 2019/11/1
 * Time: 8:15
 * Description:Redis基本操作
 */
@RestController
@Slf4j
public class RedisController {

    @Autowired
    private ValueOperations valueOperations;

    /**
     * Redis String add operator
     *
     * @param key
     * @param value
     */
    @RequestMapping(value = "str/add", method = RequestMethod.POST)
    public String str_add(@RequestParam("key") String key, @RequestParam("value") @RequestBody String value) throws Exception {
        valueOperations.set(key, value);
        return JSONResult.returnJSONString(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
    }


    /**
     * 从缓存中查询
     *
     * @param key
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "str/{key}", method = RequestMethod.GET)
    public String str_get(@PathVariable("key") String key) throws Exception {
        Object o = valueOperations.get(key);
        return JSONResult.getObjectMapper().writeValueAsString(o);
    }

}
