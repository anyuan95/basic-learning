package org.example.basic.java.basic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author anyuan
 * @date 2022-04-20 20:38
 */
public class TestJson {

    @Test
    public void testParseJson() throws JsonProcessingException {
        String content = "{\n" +
                "\t\"name\": null,\n" +
                "\t\"id\": 1\n" +
                "}";
        final HashMap map = new ObjectMapper().readValue(content, HashMap.class);
        System.out.println(map);
    }
}
