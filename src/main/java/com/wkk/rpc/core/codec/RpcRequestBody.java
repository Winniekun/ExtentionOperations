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
public class RpcRequestBody implements Serializable {
    // 接口名称
    private String interfaceName;
    // 方法名
    private String methodName;
    // 参数
    private Object[] parameters;
    // 参数类型
    private Class<?>[] paramTypes;
}
