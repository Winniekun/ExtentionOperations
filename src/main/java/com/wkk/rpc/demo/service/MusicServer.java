package com.wkk.rpc.demo.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/6/11
 */
public class MusicServer {
    private int port = 5051;
    private Server server;


    public void start() throws IOException {
        server = ServerBuilder.forPort(port).addService(new MusicServiceImpl()).build();
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                MusicServer.this.stop();
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

}
