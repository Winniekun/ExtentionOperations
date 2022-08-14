package com.wkk.limiter;

import lombok.Data;

/**
 * @author weikunkun
 * @since 2021/7/31
 */
@Data
public class TimeStampHolder{
    private long timeStamp;

    public TimeStampHolder() {
        this(System.currentTimeMillis());
    }

    public TimeStampHolder(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
