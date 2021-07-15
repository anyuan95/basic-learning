package org.example.spring.mybatis.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.spring.mybatis.dao.model.Student;
import org.springframework.stereotype.Repository;

/**
 * @author anyuan
 * @since 2021-07-15 10:16
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {
}
