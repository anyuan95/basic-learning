package org.example.basic.java.basic;

/**
 * @author anyuan
 * @date 2021-12-02 10:34
 */
public class TestProperty {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.vm.name"));
        boolean USE_CLASSVALUE = Boolean.parseBoolean(System.getProperty("groovy.use.classvalue", "IBM J9 VM".equals(System.getProperty("java.vm.name"))?"true":"false"));
        System.out.println(USE_CLASSVALUE);
    }
}
