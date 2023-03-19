package com.wkk.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author kongweikun@163.com
 * @date 2023/2/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tree {
    private Integer id;

    private String name;

    private List<Tree> nodeList;

    public Tree(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
