package cn.spring.ssm.service.impl;

import cn.spring.ssm.dao.common.OrgMapper;
import cn.spring.ssm.model.Org;
import cn.spring.ssm.util.DistributeLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.service.impl
 * User: 25414
 * Date: 2019/12/16
 * Time: 18:40
 * Description:
 */
@Service
public class OrgService {

    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Value("${ORG_TREE}")
    private String ORG_TREE;

    @Value("${ORG}")
    private String ORG;

    @Value("${DISTRIBUTE_LOCK}")
    private String DISTRIBUTE_LOCK;

    @Value("${spring.redis.user.list.time}")
    private Integer USER_TIME;

    @Value("${ORG_ICE_TIME}")
    private Integer ORG_ICE_TIME;

    @Value("${ORG_HOT_TIME}")
    private Integer ORG_HOT_TIME;

    @Value("${LOCK_TIME}")
    private Integer LOCK_TIME;

    static final Integer ZERO = 0;

    @Autowired
    private DistributeLock distributeLock;

    /**
     * 查询组织机构树形结构
     *
     * @return
     */
    public Object getOrgTree() throws Exception {
        //先从缓存中查询
        if (!StringUtils.isEmpty(valueOperations.get(ORG_TREE))) {
            return valueOperations.get(ORG_TREE);
        }

        Map<String, List<Org>> orgMap = null;
        List<Org> orgList = orgMapper.selectAll();

        if (!StringUtils.isEmpty(orgList)) {
            orgMap = new TreeMap<>();
            for (Org org : orgList) {
                if (!orgMap.containsKey(org.getPid())) {
                    orgMap.put(org.getPid(), new ArrayList<>());
                }
                orgMap.get(org.getPid()).add(org);
            }
            valueOperations.set(ORG_TREE, orgMap, USER_TIME, TimeUnit.SECONDS);
        }
        return orgMap;
    }


    /**
     * 根据id查询组织机构
     *
     * @param id 部门id
     * @return
     */
    public Object getOrgById(String id) throws Exception {
        //先从缓存中查询
        if (!StringUtils.isEmpty(valueOperations.get(ORG))) {
            return valueOperations.get(ORG);
        }

        Org org = orgMapper.getOrgById(id);

        /**
         * 为了防止缓存雪崩
         * 根据数据特点进行分类,并将失效时间分散化
         *
         * 考虑二级缓存，以空间换空间
         */
        Random random = new Random();
        int factor = random.nextInt(100);
        if (org.getType().equals(ZERO)) {
            valueOperations.set(ORG, org, ORG_HOT_TIME + factor, TimeUnit.SECONDS);
        } else {
            valueOperations.set(ORG, org, ORG_ICE_TIME + factor, TimeUnit.SECONDS);
        }
        return org;
    }


    /**
     * 将组织机构分类
     * 分类规则：组织id最后一位是偶数，热点数据
     */
    public void classifyOrg() {
        List<Org> orgs = orgMapper.selectAll();
        for (Org org : orgs) {
            String id = org.getId();
            String result = id.substring(id.length() - 1, id.length());
            if (Integer.parseInt(result) % 2 == ZERO) {
                org.setType(ZERO);
                orgMapper.updateTypeById(id, ZERO);
            }
        }
    }
}
