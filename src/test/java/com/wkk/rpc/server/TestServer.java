package com.wkk.rpc.server;

import com.wkk.rpc.core.server.RpcServer;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/6/7
 */
public class TestServer {
    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        HelloServiceImpl helloService = new HelloServiceImpl();
        rpcServer.register(helloService);

        rpcServer.serve(9000);
    }
}
