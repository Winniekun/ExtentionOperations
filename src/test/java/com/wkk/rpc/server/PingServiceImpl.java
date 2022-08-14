package com.wkk.rpc.server;

import com.wkk.rpc.IDL.ping.PingRequest;
import com.wkk.rpc.IDL.ping.PingResponse;
import com.wkk.rpc.IDL.ping.PingService;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/6/7
 */
public class PingServiceImpl implements PingService {
    @Override
    public PingResponse ping(PingRequest request) {
        String name = request.getName();
        String retMsg = "pong: " + name;
        PingResponse pingResponse = new PingResponse(retMsg);
        return pingResponse;
    }
}
