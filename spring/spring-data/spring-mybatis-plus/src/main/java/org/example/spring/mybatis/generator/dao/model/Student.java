package org.example.spring.mybatis.generator.dao.model;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.Data;

@Data
public class Student {
    private Integer id;

    private String name;

    private Integer age;

    private Integer classId;

    private String teachers;

    public Student(Integer id, String name, Integer age, Integer classId, String teachers) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.classId = classId;
        this.teachers = teachers;
    }

    public Student() {
        super();
    }

    public static Student.Builder builder() {
        return new Student.Builder();
    }

    public static class Builder {
        private Student obj;

        public Builder() {
            this.obj = new Student();
        }

        public Builder id(Integer id) {
            obj.setId(id);
            return this;
        }

        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        public Builder age(Integer age) {
            obj.setAge(age);
            return this;
        }

        public Builder classId(Integer classId) {
            obj.setClassId(classId);
            return this;
        }

        public Builder teachers(String teachers) {
            obj.setTeachers(teachers);
            return this;
        }

        public Student build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        name("name", "name", "VARCHAR", false),
        age("age", "age", "INTEGER", false),
        classId("class_id", "classId", "INTEGER", false),
        teachers("teachers", "teachers", "VARCHAR", false);

        private static final String BEGINNING_DELIMITER = "\"";

        private static final String ENDING_DELIMITER = "\"";

        private final String column;

        private final boolean isColumnNameDelimited;

        private final String javaProperty;

        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        public static Column[] all() {
            return Column.values();
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}