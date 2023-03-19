package com.wkk.java8.stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/6/8
 */
public class StreamTest {

    @Test
    public void demo1() {
        List<String> list = List.of("wkk,haoshuai", "kwk,haoshuai", "kkw", "jjj");

        List<String> k = list.stream()
                .filter(e -> e.contains("k"))
                .filter(e -> e.length() > 5)
                .collect(Collectors.toList());

        System.out.println(k);
    }

    // 一些 java8 stream的小练习
    // 给定一个数字列表，如何返回一个由每个数的平方构成的列表？给定【1，2，3，4，5】，返回【1，4，9，16，25】
    @Test
    public void test1() {
        Arrays.asList(1, 2, 3, 4, 5).stream().map(e -> e * e).forEach(System.out::println);
    }

    // 怎么用map和reduce方法数一数流中有多少个Employee？
    @Test
    public void test2() {
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 18, 9999.99),
                new Employee("李四", 50, 5555.99),
                new Employee("王五", 50, 6666.66),
                new Employee("赵六", 16, 3333.33),
                new Employee("田七", 8, 7777.77)
        );

        Optional<Integer> total = employees.stream()
                .map(e -> 1)
                .reduce(Integer::sum);
        System.out.println(total.get());

    }

    @Test
    public void testFilter() {
        List<String> list = Lists.newArrayList("Java", "Python", "Goland", "Shell");
        list.stream().filter(ele -> ele.length() < 5).forEach(System.out::println);
    }

    @Test
    public void testMaxSalary() {
        List<Person> peopleList = generatePersionList();
        Optional<Person> max = peopleList.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println("最大薪资：" + max.get().getName());
    }

    // 映射处理
    @Test
    public void testMap() {
        String[] strArr = {"abcd", "bcdd", "defde", "fTr"};
        // 全部转化为大写
        List<String> collect = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect);

        List<String> strList = Lists.newArrayList("a", "b", "c");
        strList.stream().map(String::toUpperCase).forEach(System.out::println);

        // 每个元素+1
        List<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        List<Integer> collect1 = intList.stream().map(value -> value + 1).collect(Collectors.toList());
        System.out.println(collect1);

        // 员工薪资全部+3000
        List<Person> peopleList = generatePersionList();
        List<Integer> collect2 = peopleList.stream().map(person -> person.getSalary() + 3000).collect(Collectors.toList());
        System.out.println(collect2);
    }

    @Test
    public void testFlatMap() {
        List<String> list = Arrays.asList("k,w,k", "a,b,c");
        //两个字符串，内部使用，分割，之后合并为一个新的字符数组
        list.stream().flatMap(s -> {
            String[] split = s.split(",");
            return Arrays.stream(split);
        }).forEach(System.out::println);
//        System.out.println("处理前集合：" + list);
//        System.out.println("处理后集合：" + newList);
    }

    @Test
    public void testPeek() {
        List<String> list = Lists.newArrayList("abandon", "boom", "com");
        list.stream().filter(ele -> ele.length() < 7).peek(s -> System.out.println("After filer value: " + s))
                .map(String::toUpperCase).peek(s -> System.out.println("After map value: " + s)).forEach(System.out::println);
    }

    @Test
    public void testLimit() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
        list.stream().limit(3).forEach(System.out::println);

    }

    @Test
    public void testReduce() {
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);

        // 求和1
        Optional<Integer> sum1 = list.stream().reduce((x, y) -> x + y);
        // 求和2
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        // 求和3
        Integer sum3 = list.stream().reduce(0, Integer::sum);

        // 求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);

        // 求最大值
        Optional<Integer> max1 = list.stream().reduce((x, y) -> x > y ? x : y);
        Integer max2 = list.stream().reduce(1, Integer::max);

        System.out.println("求和1：" + sum1.get());
        System.out.println("求和2：" + sum2.get());
        System.out.println("求和3：" + sum3);
        System.out.println("乘积：" + product);
        System.out.println("最大值1：" + max1);
        System.out.println("最大值2：" + max2);

        // 求所有员工工资合
        List<Person> personList = generatePersionList();
        Optional<Integer> salarySum = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("工资之和：" + salarySum);

        HashMap<@Nullable Object, @Nullable Object> objectObjectHashMap = Maps.newHashMap();

    }

    @Test
    public void testXOR() {
        int a = 2;
        System.out.println(0 ^ 110);

    }

    @Test
    public void testReduce1() {
        // 取长度最长的字符串
        Stream<String> stream = Stream.of("I", "am", "a", "programmer");
//      Optional<String> longest = stream.reduce((x, y) -> x.length() > y.length() ? x : y);
//      System.out.println(longest.get());
        Integer lengthSum = stream.reduce(0, // 初始值　// (1)
                (sum, str) -> sum + str.length(), // 累加器 // (2)
                (a, b) -> a + b); // 部分和拼接器，并行执行时才会用到 // (3)
        System.out.println(lengthSum);
//        int sum = stream.mapToInt(str -> str.length()).sum();
//        System.out.println(sum);
    }

    @Test
    public void testCollector() {
        List<String> list = Lists.newArrayList("I", "am", "a", "programmer");
        List<String> rest = Lists.newArrayList("I", "have", "a", "cat");
        Map<String, Boolean> map = list.stream().collect(Collectors.toMap(Function.identity(), ele -> contains(ele, rest)));
        System.out.println(map);
    }

    private boolean contains(String ele, List<String> rest) {
        return rest.contains(ele) ? true : false;
    }

    @Test
    public void testMap1() {
        Employee a = Employee.builder().age(1).name("abc").build();
        Employee b = Employee.builder().name("def").build();
        Employee c = Employee.builder().age(100).name("def").build();
        List<Employee> employeeList = Lists.newArrayList(a, b);
//        Map<String, Integer> map = employeeList.stream().collect(Collectors.toMap(Employee::getName, Employee::getAge));
//        System.out.println(map);
        HashMap<String, Integer> studentMap = employeeList.stream().collect(HashMap::new, (map, item) -> map.put(item.getName(), item.getAge()), HashMap::putAll);
//        Map<String, Integer> studentMap = employeeList.stream().collect(Collectors.toMap(Employee::getName, s -> Optional.ofNullable(s.getAge()).orElse(0), (key1, key2) -> key1));
        System.out.println(studentMap);

//        Map<String, List<Employee>> employeeMap = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        Map<String, List<String>> employeeMap = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.mapping(Employee::getName,  Collectors.toList())));
    }

    @Test
    public void testStream() {
        IntStream.range(1, 10)
                .peek(x -> System.out.print("\nA" + x))
                .peek(x -> System.out.print("B" + x))
                .limit(4)
                .forEach(x -> System.out.print("C" + x));
        IntStream.range(1, 10)
                .peek(x -> System.out.print("\nA" + x))
                .skip(6)
                .peek(x -> System.out.print("B" + x))
                .forEach(x -> System.out.print("C" + x));

    }

    @Test
    public void testJoin() {
        List<String> list = Lists.newArrayList("I", "am", "a", "programmer");
//        String joined = list.stream().collect(Collectors.joining());
//        String joined = list.stream().collect(Collectors.joining(","));
        String joined = list.stream().collect(Collectors.joining(",", "{", "}"));
        System.out.println(joined);
    }

    @Test
    public void testDemo() {

    }

    public int minimumOperations(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().filter(ele -> !Objects.equals(0, ele)).collect(Collectors.toSet());
        return set.size();
    }

    public static List<Person> generatePersionList() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Tom", 8900, 18, "male", "New York"));
        personList.add(new Person("Jack", 7000, 18, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 18, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 18, "female", "New York"));
        personList.add(new Person("Owen", 9500, 18, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 18, "female", "New York"));
        return personList;
    }

    public static class Person {
        private String name;  // 姓名
        private int salary; // 薪资
        private int age; // 年龄
        private String sex; //性别
        private String area;  // 地区

        // 构造方法
        public Person(String name, int salary, int age, String sex, String area) {
            this.name = name;
            this.salary = salary;
            this.age = age;
            this.sex = sex;
            this.area = area;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public int getSalary() {
            return salary;
        }

        public String getArea() {
            return area;
        }

        public String getSex() {
            return sex;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
