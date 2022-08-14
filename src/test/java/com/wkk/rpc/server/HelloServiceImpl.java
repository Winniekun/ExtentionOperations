package com.wkk.rpc.server;

import com.wkk.rpc.IDL.hello.HelloRequest;
import com.wkk.rpc.IDL.hello.HelloResponse;
import com.wkk.rpc.IDL.hello.HelloService;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/6/7
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public HelloResponse hello(HelloRequest request) {
        String name = request.getName();
        String retMsg = "hello: " + name;
        HelloResponse response = new HelloResponse(retMsg);
        return response;
    }

    @Override
    public HelloResponse hi(HelloRequest request) {
        String name = request.getName();
        String retMsg = "hi: " + name;
        HelloResponse response = new HelloResponse(retMsg);
        return response;
    }
}
