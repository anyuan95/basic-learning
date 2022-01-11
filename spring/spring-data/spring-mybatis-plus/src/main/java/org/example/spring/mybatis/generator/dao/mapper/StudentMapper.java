package org.example.spring.mybatis.generator.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.spring.mybatis.generator.dao.model.Student;
import org.example.spring.mybatis.generator.dao.model.StudentExample;

public interface StudentMapper {
    long countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectOneByExample(StudentExample example);

    Student selectOneByExampleSelective(@Param("example") StudentExample example, @Param("selective") Student.Column ... selective);

    List<Student> selectByExampleSelective(@Param("example") StudentExample example, @Param("selective") Student.Column ... selective);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") Student.Column ... selective);

    Student selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    int batchInsert(@Param("list") List<Student> list);

    int batchInsertSelective(@Param("list") List<Student> list, @Param("selective") Student.Column ... selective);
}