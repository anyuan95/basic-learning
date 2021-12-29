package org.example.spring.mybatis.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.spring.mybatis.dao.mapper.StudentMapper;
import org.example.spring.mybatis.dao.model.Student;
import org.springframework.stereotype.Repository;

/**
 * @author anyuan
 * @since 2021-07-15 10:24
 */
@Repository("studentRepository")
public class StudentRepository extends ServiceImpl<StudentMapper, Student> {

}
