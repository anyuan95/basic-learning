package org.example.json;

import com.alibaba.fastjson.JSONPath;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * @author anyuan
 * @date 2021-11-24 14:36
 */
public class TestJson {

    @Test
    public void testEDouble() throws JsonProcessingException {
        Double d = 4.3122033E7;
        String dStr = "4.3122033E7";
        final ObjectMapper objectMapper = new ObjectMapper();
        final Double aDouble = objectMapper.readValue(dStr, Double.class);
        System.out.println(aDouble);
        final NumberFormat numberInstance = DecimalFormat.getNumberInstance(Locale.getDefault());
        System.out.println(numberInstance.format(aDouble));
    }

    @Test
    public void testParseJson() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"id\":1,\"$name\":\"anyuan02\",\"$date\":\"2022-09-08T18:35:53.977Z\"}";
        final User user = objectMapper.readValue(json, User.class);
        System.out.println(user);
    }

}
