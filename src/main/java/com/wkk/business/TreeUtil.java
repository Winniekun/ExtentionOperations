package com.wkk.business;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kongweikun@163.com
 * @date 2023/2/20
 */
public class TreeUtil {

    public static List<Tree> list2Tree(List<Table> beans) {
        List<Tree> result = new ArrayList<>();
        if(!beans.isEmpty()){
            for(Table p : beans){
                if(p.getParentId() == null || p.getParentId() == 0) {
                    Tree vo = new Tree(p.getId(),p.getName());
                    vo.setNodeList(recurrence(beans, p));
                    result.add(vo);
                }
            }
        }
        return result;
    }

    /**
     *  递归函数
     * @param list
     * @param vo
     */
    private static List<Tree> recurrence(List<Table> list, Table vo){
        List<Tree> subNodes = new ArrayList<>();
        for(Table d : list){
            if (vo.getId().equals(d.getParentId())) {
                Tree sub = new Tree(d.getId(), d.getName());
                sub.setNodeList(recurrence(list, d));
                subNodes.add(sub);
            }
        }
        return subNodes;
    }

}
