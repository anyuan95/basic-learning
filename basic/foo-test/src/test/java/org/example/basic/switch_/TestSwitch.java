package org.example.basic.switch_;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-05-28 10:48
 */
public class TestSwitch {

    public static void main(String[] args) {
        List<Integer> resourceModes = Lists.newArrayList(3, 4);

        List<Object> services = new ArrayList<>(); // 实际上这个需要从远程获取，很慢
        for (Integer mode : resourceModes) {
            switch (mode) {
                case 1:
                    System.out.println("do sth");
                    break;
                case 2:
                    System.out.println("do sth");
                    break;
                case 3:
                    System.out.println("do sth using services");
                    break;
                case 4:
                    System.out.println("do sth using services");
                    break;
                default:
                    break;
            }
        }
    }

}
