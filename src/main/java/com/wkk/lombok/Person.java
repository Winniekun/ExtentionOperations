package com.wkk.lombok;

import lombok.Builder;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


/**
 * @author kongwiki@163.com
 * @since 2020/10/4
 */
@ToString
@Builder
@Slf4j
public class Person {
    private Integer id;
    private String name;
    private String age;

    public static void main(String[] args) {
        Person person = Person.builder().age("11").id(1).name("wkk").build();
        System.out.println(person);
        log.info("logger: {}", "fds");
    }
}
