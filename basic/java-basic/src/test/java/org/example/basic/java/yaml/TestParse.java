package org.example.basic.java.yaml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.assertj.core.util.Lists;
import org.yaml.snakeyaml.Yaml;

import java.util.List;

/**
 * @author anyuan
 * @date 2022-04-26 20:45
 */
public class TestParse {

    public static void main(String[] args) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        System.out.println(objectMapper.writeValueAsString(Lists.newArrayList(Lists.newArrayList(Lists.newArrayList(1, 2, 3),
                Lists.newArrayList(Lists.newArrayList(4, 5, 6))))));

        String content = "- - 1\n" +
                "  - 2\n" +
                "  - 3\n" +
                "- - 4\n" +
                "  - 5\n" +
                "  - 6";
        final List<List<Integer>> lists = objectMapper.readValue(content, new TypeReference<List<List<Integer>>>() {
        });
        System.out.println(lists);

    }
}
