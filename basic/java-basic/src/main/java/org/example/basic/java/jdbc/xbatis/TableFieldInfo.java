package org.example.basic.java.jdbc.xbatis;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.JdbcType;
import lombok.Data;
import org.example.basic.java.jdbc.annotations.TableField;

import java.lang.reflect.Field;

/**
 * @author anyuan
 * @since 2021-07-21 17:45
 */
@Data
public class TableFieldInfo {

    /**
     * 属性
     *
     * @since 3.3.1
     */
    private final Field field;
    /**
     * 字段名
     */
    private final String column;
    /**
     * 属性名
     */
    private final String property;
    /**
     * 属性类型
     */
    private final Class<?> propertyType;
    /**
     * JDBC类型
     *
     * @since 3.1.2
     */
    private JdbcType jdbcType;

    public TableFieldInfo(Field field, TableField tableField) {
        this.field = field;
        this.property = field.getName();
        this.propertyType = field.getType();
        String column = null;
        if (tableField != null) {
            column = tableField.value();
        }
        if (StrUtil.isBlank(column)) {
            column = StrUtil.toUnderlineCase(this.property);
        }
        this.column = column;
    }
}
