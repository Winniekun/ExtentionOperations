package com.wkk.sprinngdemo;

/**
 * @author kongweikun@163.com
 * @date 2023/4/7
 */
public class SaveOrderCovert implements Convert<SaveOrder> {

    @Override
    public OperateLogDO convert(SaveOrder saveOrder) {
        OperateLogDO operateLogDO = new OperateLogDO();
        operateLogDO.setOrderId(saveOrder.getId());
        return operateLogDO;
    }
}
