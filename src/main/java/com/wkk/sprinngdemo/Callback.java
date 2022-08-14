package com.wkk.sprinngdemo;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/7/14
 */
public interface Callback {
    void success(String name, String price);

    void fail(String name);
}
