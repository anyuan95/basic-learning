package org.example.basic.java.jdbc.xbatis;

import cn.hutool.core.util.StrUtil;
import org.example.basic.java.jdbc.annotations.TableField;
import org.example.basic.java.jdbc.annotations.TableId;
import org.example.basic.java.jdbc.annotations.TableName;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author anyuan
 * @since 2021-07-21 14:40
 */
public class TableInfoHelper {

    private static final Map<Class<?>, TableInfo> TABLE_INFO_CACHE = new ConcurrentHashMap<>();

    public synchronized static TableInfo initTableInfo(Class<?> entityClazz) {
        TableInfo tableInfo = TABLE_INFO_CACHE.get(entityClazz);
        if (tableInfo != null) {
            return tableInfo;
        }
        tableInfo = new TableInfo();
        initTableName(entityClazz, tableInfo);
        initTableFields(entityClazz, tableInfo);
        tableInfo.setEntityType(entityClazz);

        TABLE_INFO_CACHE.put(entityClazz, tableInfo);
        return tableInfo;
    }

    private static void initTableName(Class<?> entityClazz, TableInfo tableInfo) {
        final TableName annotation = entityClazz.getAnnotation(TableName.class);
        if (annotation != null) {
            tableInfo.setTableName(annotation.value());
        } else {
            tableInfo.setTableName(StrUtil.toUnderlineCase(entityClazz.getSimpleName()));
        }
    }

    private static void initTableFields(Class<?> entityClazz, TableInfo tableInfo) {
        final List<Field> superFields = new ArrayList<>();
        Class superClazz = entityClazz;
        while ((superClazz = superClazz.getSuperclass()) != null) {
            Collections.addAll(superFields, superClazz.getDeclaredFields());
        }
        final Field[] declaredFields = entityClazz.getDeclaredFields();
        final LinkedHashMap<String, Field> fieldMap = Arrays.stream(declaredFields)
                .collect(Collectors.toMap(Field::getName, Function.identity(),
                        (u, v) -> {
                            throw new IllegalStateException(String.format("Duplicate key %s", u));
                        },
                        LinkedHashMap::new));
        superFields.stream()
                .filter(field -> !fieldMap.containsKey(field.getName()))
                .forEach(field -> fieldMap.put(field.getName(), field));

        final List<Field> fieldList = fieldMap.values().stream()
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .filter(field -> !Modifier.isTransient(field.getModifiers()))
                .filter(field -> {
                    final TableField tableField = field.getAnnotation(TableField.class);
                    return tableField == null || tableField.exist();
                })
                .collect(Collectors.toList());

        tableInfo.setFieldList(fieldList.stream().map(field ->
                new TableFieldInfo(field, field.getAnnotation(TableField.class))
        ).collect(Collectors.toList()));

        /* set PK */
        final Field keyField = fieldList.stream()
                .filter(field -> field.isAnnotationPresent(TableId.class))
                .findFirst().orElseThrow(() -> new IllegalStateException(""));
        tableInfo.setKeyType(keyField.getType());
        tableInfo.setKeyColumn(StrUtil.isBlank(keyField.getAnnotation(TableId.class).value())
                ? StrUtil.toUnderlineCase(keyField.getName())
                : keyField.getAnnotation(TableId.class).value());
        tableInfo.setKeyProperty(keyField.getName());
    }
}
