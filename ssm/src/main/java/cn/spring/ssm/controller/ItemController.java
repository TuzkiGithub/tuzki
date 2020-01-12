package cn.spring.ssm.controller;

import cn.spring.ssm.enum_.ResultEnum;
import cn.spring.ssm.service.impl.ItemService;
import cn.spring.ssm.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.controller
 * User: 25414
 * Date: 2019/12/27
 * Time: 9:22
 * Description:
 */
@RequestMapping("item")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateItemByRecord(String id) throws Exception {
        int result = itemService.updateItemByRecord(id);
        if (result == 0) {
            return JSONResult.returnJSONString(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
        } else if (result == -1) {
            return JSONResult.returnJSONString(ResultEnum.SUCCESS.getCode(),"系统繁忙");
        } else {
            return JSONResult.returnJSONString(ResultEnum.SUCCESS.getCode(), "库存不足");
        }

    }
}
