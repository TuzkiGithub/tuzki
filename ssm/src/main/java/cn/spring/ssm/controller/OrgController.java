package cn.spring.ssm.controller;

import cn.spring.ssm.enum_.ResultEnum;
import cn.spring.ssm.service.impl.OrgService;
import cn.spring.ssm.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.controller
 * User: 25414
 * Date: 2019/12/16
 * Time: 19:11
 * Description:
 */
@RestController
@RequestMapping("org")
public class OrgController {

    @Autowired
    private OrgService orgService;

    @RequestMapping("/{id}")
    public String getOrgById(@PathVariable("id") String id) throws Exception {
        Object result = orgService.getOrgById(id);
        if (Objects.equals(result, -1)) {
            return JSONResult.returnJSONString(ResultEnum.SUCCESS.getCode(), "current user is not get lock!");
        } else {
            return JSONResult.returnJSONString(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), result);
        }
    }

    /**
     * 查询树形结构
     *
     * @return
     */
    @RequestMapping("tree")
    public String getOrgTreeByRootId() throws Exception {
        return JSONResult.returnJSONString(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), orgService.getOrgTree());
    }


    /**
     * 分类组织数据
     *
     * @return
     */
    @RequestMapping(value = "classify", method = RequestMethod.POST)
    public String classifyOrg() throws Exception {
        orgService.classifyOrg();
        return JSONResult.returnJSONString(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
    }


}
