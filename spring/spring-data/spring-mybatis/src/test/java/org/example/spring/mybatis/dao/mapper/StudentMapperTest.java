package org.example.spring.mybatis.dao.mapper;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.mybatis.dao.model.Student;
import org.example.spring.mybatis.dao.model.StudentExample;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author anyuan
 * @date 2022-01-11 21:13
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
class StudentMapperTest {

    @Resource
    private StudentMapper studentMapper;

    @Test
    void countByExample() {
        Assertions.assertEquals(studentMapper.countByExample(
                new StudentExample()
                        .createCriteria()
                        .andAgeBetween(11, 12)
                        .andClassIdLessThan(10)
                        .example()), 5);
    }

    @Test
    void deleteByExample() {
        Assertions.assertEquals(studentMapper.deleteByExample(
                new StudentExample()
                        .createCriteria()
                        .andAgeBetween(13, 14)
                        .example()), 8);
    }

    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void insert() {
    }

    @Test
    void insertSelective() {
    }

    @Test
    void selectOneByExample() {
    }

    @Test
    void selectOneByExampleSelective() {
    }

    @Test
    void selectByExampleSelective() {
        System.out.println(studentMapper.selectByExampleSelective(
                new StudentExample()
                        .createCriteria()
                        .andAgeBetween(12, 13)
                        .andClassIdEqualTo(10)
                        .example(),
                Student.Column.name, Student.Column.age));

        System.out.println(studentMapper.selectByExampleSelective(
                new StudentExample()
                        .distinct(true)
                        .limit(10, 10)
                        .orderBy(Student.Column.id.asc()),
                Student.Column.id, Student.Column.classId));

        System.out.println(studentMapper.selectByExampleSelective(new StudentExample()
                .createCriteria()
                .andAgeBetween(12, 13)
                .when(Math.random() > 0.5,
                        criteria -> criteria.andClassIdLessThan(10),
                        criteria -> criteria.andClassIdGreaterThanOrEqualTo(11))
                .example()
                .limit(0, 10)
                .distinct(true)
                .orderBy(Student.Column.id.asc(), Student.Column.age.desc())));
    }

    @Test
    void selectByExample() {

    }

    @Test
    void selectByPrimaryKeySelective() {
    }

    @Test
    void selectByPrimaryKey() {
    }

    @Test
    void updateByExampleSelective() {
    }

    @Test
    void updateByExample() {
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }

    @Test
    void batchInsert() {
    }

    @Test
    void batchInsertSelective() {
    }
}