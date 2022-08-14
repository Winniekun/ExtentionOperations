package com.wkk.rpc.core.codec;

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
public class RpcResponseBody implements Serializable {
    // 返回信息
    private Object retObject;
}
