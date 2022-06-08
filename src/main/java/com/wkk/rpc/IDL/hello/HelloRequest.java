package com.wkk.rpc.IDL.hello;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/6/3
 */
@Data
@AllArgsConstructor
public class HelloRequest implements Serializable {
    private String name;
}
