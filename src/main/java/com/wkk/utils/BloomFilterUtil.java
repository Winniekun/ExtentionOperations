package com.wkk.utils;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * @Time: 2020/7/10下午8:08
 * @Author: kongwiki
 * @Email: kongwiki@163.com
 */
public class BloomFilterUtil {

    public static List<Integer> generate(int maxSize, int maxValue){
        List<Integer> arr = new ArrayList<Integer>((int) ((maxSize + 1) * Math.random()));
        for (int i = 0; i < arr.size(); i++) {
           arr.add((int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random()));
        }
        return arr;

    }

    public static boolean isEqual(boolean[] arr1, boolean[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
