package com.wkk.sprinngdemo;

/**
 * @author kongweikun@163.com
 * @date 2023/4/7
 */
public class UpdateOrderCovert implements Convert<UpdateOrder> {

    @Override
    public OperateLogDO convert(UpdateOrder updateOrder) {
        OperateLogDO operateLogDO = new OperateLogDO();
        operateLogDO.setOrderId(updateOrder.getOrderId());
        return operateLogDO;
    }
}
