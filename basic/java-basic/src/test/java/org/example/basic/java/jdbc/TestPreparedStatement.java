//package org.example.basic.java.jdbc;
//
//import com.google.common.collect.Lists;
//import org.example.spring.mybatis.dao.model.Student;
//import org.junit.Test;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Arrays;
//
///**
// * @author anyuan
// * @since 2021-07-15 15:03
// */
//public class TestPreparedStatement {
//
//    private static final String driverClassName = "com.mysql.cj.jdbc.Driver";
//    private static final String url = "jdbc:mysql://139.198.28.158:13306/learn?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai";
//    private static final String username = "root";
//    private static final String password = "Mxl951213+";
//
//    private Connection getConnection() throws ClassNotFoundException, SQLException {
//        Class.forName(driverClassName);
//        return DriverManager.getConnection(url, username, password);
//    }
//
//    @Test
//    public void testBatch() throws ClassNotFoundException, SQLException {
//        final ArrayList<Student> students = Lists.newArrayList(
//                Student.builder().name("stu01").age(11).build(),
//                Student.builder().name("stu02").age(12).build(),
//                Student.builder().name("stu03").age(13).build(),
//                Student.builder().name("stu04").age(14).build()
//        );
//        final Connection connection = getConnection();
//        final String sql = "INSERT INTO t_student (name, age) VALUES (?, ?)";
//        final PreparedStatement pstmt = connection.prepareStatement(sql);
//        int[] count = null;
//        for (Student student : students) {
//            pstmt.setString(1, student.getName());
//            pstmt.setString(2, student.getAge().toString());
//            pstmt.addBatch();
//        }
//        if (pstmt != null) {
//            count = pstmt.executeBatch();
//            pstmt.close();
//            connection.close();
//        }
//        System.out.println(Arrays.toString(count));
//    }
//}
