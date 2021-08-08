package org.example.basic.springless.frameworks.graphql;

import org.junit.Test;
import org.mountcloud.graphql.GraphqlClient;
import org.mountcloud.graphql.request.query.DefaultGraphqlQuery;
import org.mountcloud.graphql.request.query.GraphqlQuery;
import org.mountcloud.graphql.request.result.ResultAttributtes;
import org.mountcloud.graphql.response.GraphqlResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author anyuan
 * @since 2021-08-08 11:49
 */
public class TestQuery {

    @Test
    public void testQueryLeetCode() throws IOException {
        String serverUrl = "https://leetcode-cn.com/graphql/";
        GraphqlClient graphqlClient = GraphqlClient.buildGraphqlClient(serverUrl);
        graphqlClient.setHttpHeaders(
                Collections.singletonMap("x-csrftoken", "tQKtoaQq3EpxiiZmW3bvXIXRtOvCxMZejwRiJZQ00ZkccmSPYXA4haM5yWZGt0mg"));
        String queryMethodName = "todayRecord";
        GraphqlQuery query = new DefaultGraphqlQuery(queryMethodName);
//        query.addParameter("id",1);
        final ResultAttributtes question = new ResultAttributtes("question");
        question.addResultAttributes("questionId", "questionFrontendId");
        query.addResultAttributes(question);
        GraphqlResponse response = graphqlClient.doQuery(query);
        //获取数据，数据为map类型
        Map result = response.getData();
        System.out.println(result);
    }

}