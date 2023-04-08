package com.wkk.sprinngdemo;

/**
 * @author kongweikun@163.com
 * @date 2023/4/7
 */
public class OperateLogDO {

    private Long orderId;

    private String desc;

    private String result;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
