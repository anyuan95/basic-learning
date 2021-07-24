package org.example.spring.mybatis.dao.mapper;

import org.example.spring.mybatis.dao.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author anyuan
 * @since 2021-07-21 14:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MapperTest {

    @Autowired
    private StudentMapper studentMapper;
    @Test
    public void testSelectById() {
        final Student student = studentMapper.selectById(10);
        System.out.println(student);
    }
}
