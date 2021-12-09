package org.example.basic.java.feature8.model;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;

/**
 * @author anyuan
 * @date 2021-12-07 22:37
 */
@Data
public class ArrayBean {

    private Integer[] numbers;

    public static void main(String[] args) {
        final ArrayBean arrayBean = new ArrayBean();
        arrayBean.setNumbers(new Integer[]{1,2,3});
        System.out.println(arrayBean);
    }
}
