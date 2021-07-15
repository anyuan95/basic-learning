package org.example.clazz;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author anyuan
 * @since 2021-06-09 15:52
 */
public class TestFieldInfo {

    static final int i = 0;
    static int j = 1;
    int k = -1;
    final int l = -2;

    Supplier<Integer> s = () -> -1;
    final Supplier<Integer> t = () -> -1;
    Supplier u = () -> -1;

    final List<Integer> list = Lists.newArrayList(1,2,3,4);

}
