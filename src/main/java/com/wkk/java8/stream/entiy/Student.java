package com.wkk.java8.stream.entiy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @author kongweikun@163.com
 * @date 2023/3/19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    /**
     * 班级
     */
    private String clazz;

    /**
     * 名字
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    /**
     * 爱好
     */
    private List<String>  hobbies;

    private String id;
    private LocalDate birthday;
    private double score;

    public Student(String id, String name, LocalDate birthday, Integer age, Double score) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.age = age;
        this.score = score;
    }
}
