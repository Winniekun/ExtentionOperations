package com.wkk.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author kongwiki@163.com
 * @since 2020/10/23
 */
@Data
@Builder
@ToString
public class DemoBean {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    private String age;
    private Integer id;
}
