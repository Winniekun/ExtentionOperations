package com.wkk.loadbalancer;

/**
 * @author weikunkun
 * @since 2021/6/4
 */
public interface Loadbalancer {
    // 添加服务器节点
    public void addServerNode(String serverNodeName);

    // 删除服务器节点
    public void delServerNode(String serverNodeName);

    // 选择服务器节点
    public String selectServerNode(String requestURL);
}
