package com.wkk.jackson.core;

import lombok.Data;

import java.util.List;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/11/7
 */
@Data
public class Person {

    private String name;

    private Integer age;

    private Pet pet;

    private List<String> hobbies;
}
