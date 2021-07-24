package org.example.basic.java.jdbc;

import org.example.basic.java.jdbc.mapper.ActorMapper;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author anyuan
 * @since 2021-07-21 14:29
 */
public class TestReflect {

    public static void main(String[] args) {
        final Class<ActorMapper> clazz = ActorMapper.class;
        final Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);
        System.out.println(genericSuperclass.getTypeName());
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType superclass = (ParameterizedType) genericSuperclass;
            /* 泛型 */
            System.out.println(superclass.getRawType());
            System.out.println(superclass.getOwnerType());
            System.out.println(superclass.getTypeName());
        }
    }

    @Test
    public void testExtend() {
        final ActorMapper actorMapper = new ActorMapper();
        actorMapper.analyze();
    }
}
