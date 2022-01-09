package com.wkk.limiter;

/**
 * @author weikunkun
 * @since 2021/7/31
 */
public interface RateLimiter {
    void acquire();
}
