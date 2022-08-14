package com.wkk.loadbalancer;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author weikunkun
 * @since 2021/8/16
 */
@Slf4j
public class SortedMapWithoutVirtualNode implements Loadbalancer{
    private TreeMap<Integer, String> treeMapHash;

    @Override
    public void addServerNode(String serverNodeName) {
        int hash = new GetHashCode().getHashCode(serverNodeName);
        treeMapHash.put(hash, serverNodeName);
        log.info("服务器节点：{} 上线", serverNodeName);
    }

    @Override
    public void delServerNode(String serverNodeName) {
        int hash = new GetHashCode().getHashCode(serverNodeName);
        treeMapHash.remove(hash);
        log.info("服务器节点：{} 下线", serverNodeName);
    }

    @Override
    public String selectServerNode(String requestURL) {
        int hash = new GetHashCode().getHashCode(requestURL);
        // 向右寻找第一个 key
        Map.Entry<Integer, String> subEntry= treeMapHash.ceilingEntry(hash);
        // 设置成一个环，如果超过尾部，则取第一个点
        subEntry = subEntry == null ? treeMapHash.firstEntry() : subEntry;
        return subEntry.getValue();
    }

    // 构建 Hash 环
    public SortedMap<Integer, String> buildHash(TreeMap<Integer, String> treeMap) {
        this.treeMapHash = treeMap;
        return treeMapHash;
    }
}
