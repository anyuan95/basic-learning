package org.example.spring.mybatis.dao.repository;

import com.google.common.collect.Lists;
import org.example.spring.mybatis.dao.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

/**
 * @author anyuan
 * @since 2021-07-15 10:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@ComponentScan("org.example.spring.mybatis")
//@MybatisPlusTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testInsertBatch() {
        final ArrayList<Student> students = Lists.newArrayList(
                Student.builder().name("stu01").age(11).build(),
                Student.builder().name("stu02").age(12).build(),
                Student.builder().name("stu03").age(13).build(),
                Student.builder().name("stu04").age(14).build()
        );
        final boolean b = studentRepository.saveBatch(students);
        System.out.println(b);
    }
}
