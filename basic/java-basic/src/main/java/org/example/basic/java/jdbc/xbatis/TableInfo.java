package org.example.basic.java.jdbc.xbatis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author anyuan
 * @since 2021-07-21 14:39
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableInfo {

    /**
     * 实体类型
     */
    private Class<?> entityType;
    private String tableName;

    /**
     * 表主键ID 字段名
     */
    private String keyColumn;
    /**
     * 表主键ID 属性名
     */
    private String keyProperty;
    /**
     * 表主键ID 属性类型
     */
    private Class<?> keyType;
    private List<TableFieldInfo> fieldList;

}
