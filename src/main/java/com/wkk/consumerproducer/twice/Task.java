package com.wkk.consumerproducer.twice;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author weikunkun
 * @since 2021/4/24
 */
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private int task;

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }
}
