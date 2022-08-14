package com.wkk.rpc.IDL.ping;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/6/7
 */
@Data
@AllArgsConstructor
public class PingRequest implements Serializable {
    private String name;
}
