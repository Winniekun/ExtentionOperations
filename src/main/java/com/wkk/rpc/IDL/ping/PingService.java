package com.wkk.rpc.IDL.ping;

import lombok.Data;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/6/7
 */
public interface PingService {
    PingResponse ping(PingRequest request);
}
