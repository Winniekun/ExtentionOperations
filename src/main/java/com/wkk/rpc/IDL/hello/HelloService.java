package com.wkk.rpc.IDL.hello;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/6/3
 */
public interface HelloService {

    HelloResponse hello(HelloRequest request);

    HelloResponse hi(HelloRequest request);
}
