package org.example.spring.mybatis.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @author anyuan
 * @since 2021-07-15 10:17
 */
@Data
@Builder
@TableName("t_student")
public class Student {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private Integer classId;

}
