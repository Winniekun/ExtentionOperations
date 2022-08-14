package com.wkk.loadbalancer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @author weikunkun
 * @since 2021/8/14
 */
public class Balancer {
    public static List<String> servers = Arrays.asList("192.168.0.1", "192.168.0.2", "192.168.0.3", "192.168.0.4",
            "192.168.0.5");
    public static Map<String, Integer> serverMap = new HashMap<String, Integer>();
    public static int pos = 0;
    static {
        serverMap.put("192.168.0.1", 1);
        serverMap.put("192.168.0.2", 1);
        serverMap.put("192.168.0.3", 4);
        serverMap.put("192.168.0.4", 3);
        serverMap.put("192.168.0.5", 3);
        serverMap.put("192.168.0.6", 2);
    }

    /**
     * 轮询
     * @return
     */
    public static String getPollingServer() {
        String server = null;
        if (pos >= servers.size()) {
            pos = 0;
        }
        server = servers.get(pos);
        pos++;
        return server;
    }

    /**
     * 随机
     * @return
     */
    public static String getRandomServer() {
        Random random = new Random();
        int nextPos = random.nextInt(servers.size());
        return servers.get(nextPos);
    }

    /**
     * 源地址hash法
     * @param ip
     * @return
     */
    public static String getSourceAddressHashServer(String ip) {
        int hashCode = ip.hashCode();
        int pos = hashCode % servers.size();
        return servers.get(pos);
    }

    /**
     * 加权轮询
     * 就是根据某种加权的方式，将权重大的节点 有更多的机会出来请求
     * @return
     */
    public static String getWeightRandomServer() {
        Set<String> keySet = serverMap.keySet();
        Iterator<String> itr = keySet.iterator();
        List<String> servers = new ArrayList<>();

        while (itr.hasNext()) {
            String cur = itr.next();
            Integer weight = serverMap.get(cur);
            for (int i = 0; i < weight; i++) {
                servers.add(cur);
            }
        }

        String server = null;
        if (pos >= servers.size()) {
            pos = 0;
        }
        server = servers.get(pos);
        pos++;
        return server;
    }
}
