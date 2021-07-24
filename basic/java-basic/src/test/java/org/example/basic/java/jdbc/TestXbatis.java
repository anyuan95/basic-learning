package org.example.basic.java.jdbc;

import org.example.basic.java.jdbc.entity.sakila.Actor;
import org.example.basic.java.jdbc.mapper.ActorMapper;
import org.junit.Test;

/**
 * @author anyuan
 * @since 2021-07-21 22:26
 */
public class TestXbatis {

    @Test
    public void testSelectById() throws Exception {
        Integer id = 1;
        final ActorMapper actorMapper = new ActorMapper();
        final Actor actor = actorMapper.selectById(id);
        System.out.println(actor);
    }
}
