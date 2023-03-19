package com.wkk.business;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author kongweikun@163.com
 * @date 2023/2/20
 */
public class TreeModule {

    public static
    List<Table> tableData = Arrays.asList(
            new Table(1, 0, "根节点"),
            new Table(2, 1, "子节点1"),
            new Table(3, 2, "子节点1.1"),
            new Table(4, 2, "子节点1.2"),
            new Table(5, 2, "子节点1.3"),
            new Table(6, 1, "子节点2"),
            new Table(7, 6, "子节点2.1"),
            new Table(8, 6, "子节点2.2"),
            new Table(9, 1, "子节点3"),
            new Table(10, 9, "子节点3.1")
    );

    @Test
    public void testView() {
        List<Tree> trees = TreeUtil.list2Tree(tableData);
        System.out.println(trees);
    }
}
