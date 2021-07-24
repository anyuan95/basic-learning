package org.example.basic.java.jdbc.mapper;

import cn.hutool.core.util.StrUtil;
import org.example.basic.java.jdbc.annotations.TableName;
import org.example.basic.java.jdbc.util.DBUtil;
import org.example.basic.java.jdbc.xbatis.TableFieldInfo;
import org.example.basic.java.jdbc.xbatis.TableInfo;
import org.example.basic.java.jdbc.xbatis.TableInfoHelper;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author anyuan
 * @since 2021-07-21 13:53
 */
public abstract class BaseMapper<T> {

    public TableInfo analyze() {
        final Class<? extends BaseMapper> clazz = this.getClass();
        final ParameterizedType superClazz = (ParameterizedType) clazz.getGenericSuperclass();
        final Type[] actualTypeArguments = superClazz.getActualTypeArguments();
        final Class entityClazz = (Class) actualTypeArguments[0];
        final TableInfo tableInfo = TableInfoHelper.initTableInfo(entityClazz);
        return tableInfo;
    }

    public T selectById(Serializable id) throws Exception {
        final TableInfo tableInfo = analyze();
        final Connection connection = DBUtil.buildConnection();
        final List<TableFieldInfo> fieldList = tableInfo.getFieldList();
        final String columnList = fieldList.stream().map(TableFieldInfo::getColumn).collect(Collectors.joining(", "));
        final String selectByIdSqlTemplate = "SELECT %s FROM %s WHERE %s = ? %s";
        final String pstmtSql = String.format(selectByIdSqlTemplate, columnList, tableInfo.getTableName(), tableInfo.getKeyColumn(), StrUtil.EMPTY);
        final PreparedStatement pstmt = connection.prepareStatement(pstmtSql);
        pstmt.setObject(1, id);
        final ResultSet resultSet = pstmt.executeQuery();
        T entity = null;
        while (resultSet.next()) {
            final Class<T> entityType = (Class<T>) tableInfo.getEntityType();
            entity = entityType.newInstance();
            for (int i = 0; i < fieldList.size(); i++) {
                final Field field = fieldList.get(i).getField();
                final boolean accessible = field.isAccessible();
                if (!accessible) {
                    field.setAccessible(true);
                }
                field.set(entity, resultSet.getObject(i+1));
                field.setAccessible(accessible);
            }
        }
        return entity;
    }

}
