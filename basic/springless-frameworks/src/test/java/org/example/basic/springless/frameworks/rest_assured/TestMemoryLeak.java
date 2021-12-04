package org.example.basic.springless.frameworks.rest_assured;

import groovy.lang.GroovyShell;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;

/**
 * @author anyuan
 * @date 2021-12-03 14:10
 */
public class TestMemoryLeak {

    @Test
    public void test1() {
        String requestUrl = "http://baseapi.waimai.st.sankuai.com/api/provider/qa/v2/org/getOrgList?orgIds=102842";
        List list = null;
        Map resultMap = new HashMap();
        try {
            Response resonpse = given()
                    .when()
                    .get(requestUrl);
            if (resonpse != null) {
                JsonPath.from("{\"id\": \"" + UUID.randomUUID().toString() + "\"}").getUUID("id");
                Integer code = resonpse.path("code");
                if (code != null && code == 0) {
                    list = resonpse.path("data");
                }
            }
        } catch (Exception e) {
        }
        if (!CollectionUtils.isEmpty(list)) {
            resultMap = (Map) list.get(0);
        }
    }

    public static void test02() throws InterruptedException {
//        JSONAssertion
        for (int ci = 0; ci < 100000; ci++) {
            JsonPath.from("{\"id\": \"" + UUID.randomUUID().toString() + "\"}").getUUID("id");
            if (ci % 1000 == 0) {
                Thread.sleep(1000L);
            }
            Thread.sleep(1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        test02();
    }
}
