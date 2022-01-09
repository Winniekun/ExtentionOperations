package com.wkk.loadbalancer;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author weikunkun
 * @since 2021/8/16
 */
public class Analysis {
    public double analysis(Map<String, String> map) {
        Iterator<Map.Entry<String, String>> itr = map.entrySet().iterator();
        // 计数 map <Server, Count>
        ConcurrentHashMap<String, Integer> countMap = new ConcurrentHashMap<>();
        while (itr.hasNext()) {
            Map.Entry<String, String> next = itr.next();
            String server = next.getValue();
            countMap.put(server, countMap.getOrDefault(server, 0) + 1);
        }

        Collection<Integer> values = countMap.values();
        Iterator<Integer> valItr = values.iterator();
        int count = 0;
        int[] res = new int[values.size()];
        while (valItr.hasNext()) {
            res[count] = valItr.next();
        }
        return variance(res);
    }

    // 求方差
    private static double variance(int[] arr) {
        int m = arr.length;
        double sum = 0;
        sum = Arrays.stream(arr).sum();
        double dAve = sum / m;
        double dVar = 0;
        for (int i = 0; i < m; i++) {
            dVar += (arr[i] - dAve) * (arr[i] - dAve);
        }
        return dVar / m;
    }
}
