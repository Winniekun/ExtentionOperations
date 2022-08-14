package com.wkk.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                new Employee("张三", 18 ,9999.99),
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
}
