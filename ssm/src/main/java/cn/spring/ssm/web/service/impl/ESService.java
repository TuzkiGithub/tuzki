package cn.spring.ssm.web.service.impl;

import cn.spring.ssm.configure.es.ESClient;
import cn.spring.ssm.web.dao.es.ESMapper;
import cn.spring.ssm.common.util.JSONResult;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.web.service.impl
 * User: 25414
 * Date: 2020/3/13
 * Time: 9:27
 * Description:
 */
@Service
public class ESService {
    @Autowired
    private ESMapper esMapper;

    @Autowired
    private ESClient esClient;

    /**
     * ES7一个索引对应一个文档，即固定值："_doc"
     */
    private static final String DOC = "_doc";

    /**
     * 同步MYSQL数据库 ==> ES
     *
     * @param table 表名
     * @param index 索引
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void syncData2ES(String table, String index) throws Exception {
        List<Map<Object, Object>> list = esMapper.getEntryList(table);
        RestHighLevelClient restHighLevelClient = esClient.esClient();
        BulkRequest bulkRequest = new BulkRequest();

        assert list != null;
        for (Map map : list) {
            String id = getFirstOrNull(map).toString();
            String org_json = JSONResult.getObjectMapper().writeValueAsString(map);
            //批量更新或插入
            IndexRequest indexRequest = new IndexRequest(index, DOC, id).source(org_json, XContentType.JSON).opType(DocWriteRequest.OpType.INDEX);
            bulkRequest.add(indexRequest);
        }
        //同步执行
        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

    }

    /**
     * 获取map中第一个元素
     *
     * @param map
     * @return
     */
    private Object getFirstOrNull(Map<Object, Object> map) {
        Object obj = null;
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            obj = entry.getValue();
            if (obj != null) {
                break;
            }
        }
        return obj;
    }
}
