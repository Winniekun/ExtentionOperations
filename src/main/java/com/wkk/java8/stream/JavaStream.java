package com.wkk.java8.stream;

import com.google.common.collect.Lists;
import com.wkk.java8.stream.entiy.Dish;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.wkk.java8.stream.entiy.Dish.menu;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/8/5
 */
@Slf4j
public class JavaStream {

    /**
     * flatMap将一个流中的每个值都换成一个流，之后再把所有的流连接起来
     */
    @Test
    public void testFlat() {
        ArrayList<String> words = Lists.newArrayList("Hello", "World");
        List<String[]> collect = words.stream().map(word -> word.split("")).collect(Collectors.toList());
        for (String[] strings : collect) {
            Arrays.stream(strings).forEach(System.out::println);
        }
        words.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
    }

    /**
     * 生成数对
     */
    @Test
    public void testFlagMap() {
        List<Integer> numbers1 = Lists.newArrayList(1, 2, 3);
        List<Integer> numbers2 = Lists.newArrayList(3, 4);

        numbers1.stream()
                .flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(Collectors.toList());
    }

    @Test
    public void testDishes() {
        Long howManyDishes = menu.stream().collect(Collectors.counting());
        log.info("how many dishes: {}", howManyDishes);
    }

    @Test
    public void testJoin() {
        String collect = menu.stream().map(Dish::getName).collect(joining(", "));
        log.info("join: {}", collect);
    }

    @Test
    public void testReducing() {
        // 热量总和
        int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j)).intValue();
        log.info("total calories: {}", totalCalories);

        // 热量最大值
        Optional<Dish> collect = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        log.info("max value: {}", collect.get());
    }

    /**
     * 分区
     */
    @Test
    public void testPartitioningBy() {
        Map<Boolean, List<Dish>> collect = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        log.info("info: {}", collect);
    }

    /**
     * 分类
     */
    @Test
    public void testClassify() {
        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> collect = menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
                            else return Dish.CaloricLevel.FAT;
                        }))
        );

        System.out.println(collect);
    }

    @Test
    public void testClassifyII() {
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        log.info("dishesByType: {}", dishesByType);
    }

}
