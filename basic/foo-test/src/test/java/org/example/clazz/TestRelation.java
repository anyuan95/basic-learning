package org.example.clazz;

import org.reflections.Reflections;

import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author anyuan
 * @since 2021-05-11 17:06
 */
public class TestRelation {

    public static void main(String[] args) {
        final Class<LinkedList> linkedListClass = LinkedList.class;
        final Class<Collection> collectionClass = Collection.class;
        final Class<List> listClass = List.class;
        final Class<AbstractSequentialList> abstractSequentialListClass = AbstractSequentialList.class;
        final Class<AbstractList> abstractListClass = AbstractList.class;
        // A instanceOf B  判断A是否是B类的实例
        System.out.println(linkedListClass.isAssignableFrom(collectionClass));
        System.out.println(linkedListClass.isAssignableFrom(listClass));
        System.out.println(linkedListClass.isAssignableFrom(abstractSequentialListClass));
        System.out.println(linkedListClass.isAssignableFrom(abstractListClass));

        System.out.println(collectionClass.isAssignableFrom(linkedListClass));
        System.out.println(listClass.isAssignableFrom(linkedListClass));
        System.out.println(abstractSequentialListClass.isAssignableFrom(linkedListClass));
        System.out.println(abstractListClass.isAssignableFrom(linkedListClass));

        System.out.println(linkedListClass.isAssignableFrom(linkedListClass));

        System.out.println(Arrays.toString(linkedListClass.getClasses()));
        // 内部定义的所有类（不包括自身）
        System.out.println(Arrays.toString(linkedListClass.getDeclaredClasses()));
        // 实现的所有接口
        System.out.println(Arrays.toString(linkedListClass.getInterfaces()));
        System.out.println(linkedListClass.getDeclaringClass());
        System.out.println(linkedListClass.getEnclosingClass());
        // 直接父类
        final Class<? super LinkedList> superclass = linkedListClass.getSuperclass();
        System.out.println(superclass);
        // 获取某包下的、某类的所有子类
        Reflections reflections = new Reflections("java.util");
        final Set<Class<? extends AbstractCollection>> subTypes = reflections.getSubTypesOf(AbstractCollection.class);
        System.out.println(subTypes);

    }
}
