package org.example.spring.testing.testng.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.example.spring.testing.testng.common.exception.AnyThrow;

import java.io.IOException;
import java.util.List;

/**
 * jackson序列化及反序列化工具类
 *
 * @author anyuan
 * @date 2019-08-10 17:33
 */
@Slf4j
public final class JsonUtils {

    private JsonUtils() {
    }

    private static ObjectMapper objectMapper() {
        return SpringUtil.getBean(ObjectMapper.class);
    }

    public static String toJson(Object object) {
        return toJson(objectMapper(), object);
    }

    public static String toJson(@NonNull ObjectMapper objectMapper, Object object) {
        if (null != object) {
            try {
                return objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                log.error("Json serialize failed，reason：[{}]", e.getMessage());
                AnyThrow.throwUnchecked(e);
            }
        }
        return null;
    }

    public static <T> T toObject(String json, @NonNull Class<T> targetClass) {
        return toObject(objectMapper(), json, targetClass);
    }

    public static <T> T toObject(@NonNull ObjectMapper objectMapper, String json, @NonNull Class<T> targetClass) {
        if (StrUtil.isNotBlank(json)) {
            try {
                return objectMapper.readValue(json, targetClass);
            } catch (IOException e) {
                log.error("Json deserialize failed，reason：[{}]", e.getMessage());
                AnyThrow.throwUnchecked(e);
            }
        }
        return null;
    }

    public static <T> T toObject(String json, @NonNull TypeReference<T> typeReference) {
        return toObject(objectMapper(), json, typeReference);
    }

    public static <T> T toObject(@NonNull ObjectMapper objectMapper, String json, @NonNull TypeReference<T> typeReference) {
        if (StrUtil.isNotBlank(json)) {
            try {
                return objectMapper.readValue(json, typeReference);
            } catch (IOException e) {
                log.error("Json deserialize failed，reason：[{}]", e.getMessage());
                AnyThrow.throwUnchecked(e);
            }
        }
        return null;
    }

    public static <T> List<T> toListObject(String json, @NonNull Class<T> targetClass) {
        return toListObject(objectMapper(), json, targetClass);
    }

    public static <T> List<T> toListObject(@NonNull ObjectMapper objectMapper, String json, @NonNull Class<T> targetClass) {
        if (StrUtil.isNotBlank(json)) {
            CollectionLikeType listType = objectMapper.getTypeFactory().constructCollectionLikeType(List.class, targetClass);
            try {
                return objectMapper.readValue(json, listType);
            } catch (IOException e) {
                log.error("Json deserialize failed，reason：[{}]", e.getMessage());
                AnyThrow.throwUnchecked(e);
            }
        }
        return null;
    }

    public static JsonNode toJsonNode(String json) {
        return toJsonNode(objectMapper(), json);
    }

    public static JsonNode toJsonNode(@NonNull ObjectMapper objectMapper, String json) {
        if (StrUtil.isNotBlank(json)) {
            try {
                return objectMapper.readTree(json);
            } catch (IOException e) {
                log.error("Json deserialize failed，reason：[{}]", e.getMessage());
                AnyThrow.throwUnchecked(e);
            }
        }
        return null;
    }
}
