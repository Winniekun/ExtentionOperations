package com.wkk.loadbalancer;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author weikunkun
 * @since 2021/8/16
 */
@Slf4j
public class SortedMapWithVirtualNodeTest {
    private static SortedMapWithVirtualNode sortedMapWithVirtualNode = new SortedMapWithVirtualNode();

    // Hash 环
    private static SortedMap<Integer, String> treeMapHash;
    // 服务器总数
    private static final int SERVERS_NUM = 100;
    // 每台服务器需要设置的虚拟节点数
    private static final int VIRTUAL_NODES = 10;

    // 待加入 Hash 环的服务器列表
    private static ArrayList<String> serverList = new ArrayList<>();

    private static void init() {
        // 构造服务器数据
        for (int i = 0; i < SERVERS_NUM; i++) {
            String s = new StringBuilder().append("192.168.0.").append(String.valueOf(i)).toString();
            serverList.add(s);
        }
        // 构建 Hash 环
        treeMapHash = sortedMapWithVirtualNode.buildHash(new TreeMap<Integer, String>());
        // 将服务器的虚拟节点添加到 Hash 环中
        for (String s : serverList) {
            for (int i = 0; i < VIRTUAL_NODES; i++) {
                String VNNode = s + "&&VN" + String.valueOf(i);
                sortedMapWithVirtualNode.addServerNode(VNNode);
            }
        }
    }

    public static void main(String[] args) {
        init();
        // <节点，服务器>
        HashMap<String, String> map = new HashMap<>();
        // 请求节点
        String[] nodes = new IPAddressGenerate().genIPAddress(10000);
        // <节点，服务器>
        for (int i = 0; i < nodes.length; i++) {
            // 选择服务器
            String serverIP = sortedMapWithVirtualNode.selectServerNode(nodes[i]);
            // 记录服务器信息
            map.put(nodes[i], serverIP);
        }

        log.info("虚拟节点,初始方差: " + new Analysis().analysis(map));
    }

}