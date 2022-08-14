package com.wkk.rpc.core.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/6/7
 */
@Slf4j
public class RpcServer {

    private final ExecutorService threadPool;
    private final HashMap<String, Object> registerService;

    public RpcServer() {
        int corePoolSize = 5;
        int maximumPoolSize = 50;
        long keepAliveTime = 60;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        this.threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                workingQueue, threadFactory);
        this.registerService = new HashMap<>();
    }

    public void register(Object server) {
        registerService.put(server.getClass().getInterfaces()[0].getName(), server);
    }

    public void serve(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("server starting...");
            Socket handleSocket;

            while ((handleSocket = serverSocket.accept()) != null) {
                System.out.println("client connected, ip: " + handleSocket.getInetAddress());
                threadPool.execute(new RpcServerWorker(handleSocket, registerService));
            }
        } catch (IOException e) {
            log.error("server happened error", e);
        }

    }
}
