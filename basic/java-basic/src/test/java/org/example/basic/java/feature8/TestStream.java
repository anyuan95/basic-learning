package org.example.basic.java.feature8;

import cn.hutool.core.collection.ListUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.example.basic.java.feature8.model.Gender;
import org.example.basic.java.feature8.model.User;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author anyuan
 * @since 2021-07-22 11:00
 */
public class TestStream {

    @Test
    public void testToMap() {
        final ArrayList<User> users = ListUtil.toList(
                User.builder().id(1L).firstName("a").build(),
                User.builder().id(2L).firstName("b").build(),
                User.builder().id(2L).firstName("c").build()
        );
        System.out.println(users.stream().collect(Collectors.toMap(User::getId, User::getFirstName, (u1, u2) -> u1)));
        System.out.println(users.stream().collect(Collectors.toMap(User::getId, User::getFirstName, (u1, u2) -> u2)));

        System.out.println(users.stream().collect(Collectors.toMap(User::getId, User::getFirstName, (u1, u2) -> u2, HashMap::new)).getClass());
        System.out.println(users.stream().collect(Collectors.toMap(User::getId, User::getFirstName, (u1, u2) -> u2, LinkedHashMap::new)).getClass());

        // 参数1-supplier：用于生成使用的集合；参数2-accumulator：用于将流中的每个值添加到集合中；参数3-combiner：用于合并多个流（当前测试仅用于并行流情况下）
        final HashMap<Object, Object> collect = users.stream().collect(HashMap::new, (map, user) -> map.put(user.getId(), user.getFirstName()), HashMap::putAll);
        System.out.println(collect);
        final HashMap<Object, Object> collectParallel = users.parallelStream().collect(HashMap::new, (map, user) -> map.put(user.getId(), user.getFirstName()), HashMap::putAll);
        System.out.println(collectParallel);
    }

    @Test
    public void testStream() {
        final List<User> users = Lists.newArrayList(
                User.builder().id(1L).firstName("John").lastName("Smith").gender(Gender.MALE).build(),
                User.builder().id(2L).firstName("Jane").lastName("Smith").gender(Gender.FEMALE).build(),
                User.builder().id(3L).firstName("Hawking").lastName("Stephen").gender(Gender.MALE).build(),
                User.builder().id(4L).firstName("Tony").lastName("Stark").gender(Gender.MALE).build(),
                User.builder().id(5L).firstName("Rose").lastName("Stephen").gender(Gender.OTHER).build(),
                User.builder().id(6L).firstName("Arthur").lastName("Pandragon").gender(Gender.FEMALE).build()
        );

        // filter：根据条件过滤，流中只保留满足条件的项
        System.out.println(users.stream().filter(user -> Gender.MALE.equals(user.getGender())).collect(Collectors.toList()));
        // map：映射，将流中的每一项进行转换
        // distinct：去重，重复项只保留一个
        System.out.println(users.stream().map(User::getLastName).distinct().collect(Collectors.toList()));
        // skip：跳过前N项
        System.out.println(users.stream().skip(2L).collect(Collectors.toList()));
        // limit：与skip相对地，保留前N项
        System.out.println(users.stream().limit(2L).collect(Collectors.toList()));
        // sorted：根据传入的Comparator进行排序，默认是升序
        // 注意：若使用不带参数的sorted方法，则被排序对象必须实现了Comparable接口，否则会报错ClassCastException
        System.out.println(users.stream().sorted(Comparator.comparing(User::getFirstName)).collect(Collectors.toList()));
        // anyMatch/noneMatch/allMatch：存在匹配/全都不匹配/全都匹配，返回值为布尔类型
        System.out.println(users.stream().anyMatch(user -> "Smith".equals(user.getLastName())));
        System.out.println(users.stream().noneMatch(user -> "Strange".equals(user.getLastName())));
        System.out.println(users.stream().allMatch(user -> user.getLastName().length() > 4));
        // findAny：找到任意一个匹配的值
        System.out.println(users.parallelStream().filter(user -> "Stephen".equals(user.getLastName())).findAny().orElse(null));
        // findFirst：找到第一个匹配的值
        System.out.println(users.stream().filter(user -> "Stephen".equals(user.getLastName())).findFirst().orElse(null));
        // count：统计数量
        System.out.println(users.stream().count());
        System.out.println(users.stream().collect(Collectors.counting()));
        // reduce
        // flatMap TODO
    }

    @Test
    public void testCollectors() {
        final List<User> users = Lists.newArrayList(
                User.builder().id(1L).firstName("John").lastName("Smith").gender(Gender.MALE).age(25).build(),
                User.builder().id(2L).firstName("Jane").lastName("Smith").gender(Gender.FEMALE).age(17).build(),
                User.builder().id(3L).firstName("Hawking").lastName("Stephen").gender(Gender.MALE).age(28).build(),
                User.builder().id(4L).firstName("Tony").lastName("Stark").gender(Gender.MALE).age(33).build(),
                User.builder().id(5L).firstName("Rose").lastName("Stephen").gender(Gender.OTHER).age(41).build(),
                User.builder().id(6L).firstName("Arthur").lastName("Pandragon").gender(Gender.FEMALE).age(16).build()
        );
        // toSet
        final Set<User> userSet = users.stream().collect(Collectors.toSet());
        System.out.println(userSet.getClass());
        // toList
        final List<User> userList = users.stream().collect(Collectors.toList());
        System.out.println(userList.getClass());
        // toMap 双参数
        final Map<String, User> fullNameUserMap = users.stream()
                .collect(Collectors.toMap(user -> user.getFirstName() + "." + user.getLastName(), user -> user));
        System.out.println(fullNameUserMap);
        // toMap 三参数
        final Map<String, User> lastNameUserMap = users.stream()
                .collect(Collectors.toMap(User::getLastName, user -> user,
                        (u1, u2) -> u1.getFirstName().compareTo(u2.getFirstName()) > 0 ? u1 : u2));
        System.out.println(lastNameUserMap);
        // toMap 四参数
        final LinkedHashMap<Long, User> userIdUserLinkedHashMap = users.stream().collect(Collectors.toMap(User::getId, user -> user,
                (u1, u2) -> u1.getLastName().compareTo(u2.getLastName()) > 0 ? u1 : u2,
                LinkedHashMap::new));
        System.out.println(userIdUserLinkedHashMap);
        // collectingAndThen：使用传入的downstream（参数1）对流进行收集，收集完成后使用传入的finisher（参数2）进行最终处理
        final ImmutableList<User> userImmutableList = users.stream().collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
        System.out.println(userImmutableList);
        final List<String> fullNameList = users.stream().map(user -> user.getFirstName() + " " + user.getLastName()).collect(Collectors.toList());
        // joining：字符串连接操作
        // joining 无参数（等同于String.join("", elements)）
        System.out.println(fullNameList.stream().collect(Collectors.joining()));
        // joining 单参数（等同于String.join(delimiter, elements)）
        System.out.println(fullNameList.stream().collect(Collectors.joining(", ")));
        // joining 三参数，相比双参数多了prefix和suffix
        System.out.println(fullNameList.stream().collect(Collectors.joining(", ", "[", "]")));
        // counting：统计数量，collect(counting())等同于count()
        System.out.println(users.stream().collect(Collectors.counting()));
        // summarizingInt/summarizingDouble/summarizingLong：抽取流中的数值元素生成统计结果
        final IntSummaryStatistics ageSummaryStatistics = users.stream().collect(Collectors.summarizingInt(User::getAge));
        System.out.println(ageSummaryStatistics.getSum());
        System.out.println(ageSummaryStatistics.getAverage());
        System.out.println(ageSummaryStatistics.getCount());
        System.out.println(ageSummaryStatistics.getMax());
        System.out.println(ageSummaryStatistics.getMin());
        // averagingInt/averagingDouble/averagingLong：抽取流中的数值元素计算平均值
        System.out.println(users.stream().collect(Collectors.averagingInt(User::getAge)));
        // summingInt/summingDouble/summingLong：抽取流中的数值元素求和，collect(Collectors.summingInt(toXxxFunction)等同于mapToXxx(toXxxFunction).sum()
        System.out.println(users.stream().collect(Collectors.summingInt(User::getAge)));
        // maxBy：根据传入的comparator进行比较得出最大值。minBy同理。以下三句是等价的。
        System.out.println(users.stream().collect(Collectors.maxBy((u1, u2) -> u1.getAge() - u2.getAge())).get());
        System.out.println(users.stream().max((u1, u2) -> u1.getAge() - u2.getAge()).get());
        System.out.println(users.stream().max(Comparator.comparingInt(User::getAge)).get());
        // groupingBy：将元素进行分组，然后放在map中
        // groupingBy 单参数：根据classifier（参数1）计算得到map的key，流中同key的元素放在对应的List中
        final Map<Gender, List<User>> genderUserListMap = users.stream().collect(Collectors.groupingBy(User::getGender));
        System.out.println(genderUserListMap);
        // groupingBy 双参数：流中同key的元素通过downstream（参数2）进行收集处理后，作为map的value
        final Map<Gender, Set<User>> genderUserSetMap = users.stream().collect(Collectors.groupingBy(User::getGender, Collectors.toSet()));
        System.out.println(genderUserSetMap);
        // groupingBy 三参数：最终的map集合改为通过mapFactory（参数2）生成
        final LinkedHashMap<Gender, List<User>> genderUserListLinkedHashMap = users.stream()
                .collect(Collectors.groupingBy(User::getGender, LinkedHashMap::new, Collectors.toList()));
        System.out.println(genderUserListLinkedHashMap);
        // partitioningBy：一类特殊的分组器，只有true和false两个key。将满足predicate的内容放到true的list中，不满足的放到false的list中。
        // partitioningBy 单参数：predicate
        final Map<Boolean, List<User>> userListMapPartitionByAge = users.stream()
                .collect(Collectors.partitioningBy(user -> user.getAge() >= 25));
        System.out.println(userListMapPartitionByAge);
        // partitioningBy 双参数：使用downstream（参数2）生成value的容器
        final Map<Boolean, Set<User>> userSetMapPartitionByAge = users.stream()
                .collect(Collectors.partitioningBy(user -> user.getAge() >= 25, Collectors.toSet()));
        System.out.println(userSetMapPartitionByAge);
        // mapping：通过mapper对流中元素进行映射，然后通过downstream进行归纳。collect(mapping(mapper, downstream))等同于map(mapper).collect(downstream)
        final List<String> fullNameListx = users.stream()
                .collect(Collectors.mapping(user -> user.getFirstName() + "." + user.getLastName(), Collectors.toList()));
        System.out.println(fullNameListx);
    }

    @Test
    public void testStreamOthers() {
        final List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        ints.stream().reduce(Integer::sum).ifPresent(System.out::println);
        System.out.println(ints.stream().reduce(0, Integer::sum));
        System.out.println(ints.stream().mapToInt(Integer::intValue).sum());

//        combiner.apply(u, accumulator.apply(identity, t)) == accumulator.apply(u, t)
//        /* 本体 */ U identity, /* 对流中的每个元素进行累加 */ BiFunction<U, ? super T, U> accumulator,
//        /* 将多个线程的处理结果进行合并 */BinaryOperator<U> combiner

        // combiner 是用于合并多个线程的结果值。实际上只有多线程时才会使用到，但要注意处理数据的重复性。
        for (int i = 0; i < 10; i++) {
            System.out.println(ints.parallelStream().reduce(0, Integer::sum, Integer::sum));
        }
    }

    @Test
    public void testUnfinishedStream() {
        final List<User> users = Lists.newArrayList(User.builder().id(1L).firstName("John").lastName("Smith").gender(Gender.MALE).build());
        final Stream<User> userStream = users.stream().filter(user -> {
            user.setGender(Gender.FEMALE);
            return true;
        });
        System.out.println(users);
        System.out.println(userStream.count());
        System.out.println(users);
    }
}
