package com.wkk.rpc.core.rpc_protocol;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/6/7
 */
@Data
@Builder
public class RpcRequest implements Serializable {
    // 协议头部分
    private String header;
    // 协议体部分
    private byte[] body;
}
