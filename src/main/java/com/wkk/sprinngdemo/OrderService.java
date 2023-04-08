package com.wkk.sprinngdemo;

import org.springframework.stereotype.Service;

/**
 * @author kongweikun@163.com
 * @date 2023/3/24
 */
@Service
public class OrderService {

    @RecordOperate(desc = "保存订单", convert = SaveOrderCovert.class)
    public Boolean saveOrder(SaveOrder saveOrder) {
        System.out.println("save order, orderId: " + saveOrder.getId());
        return true;
    }

    @RecordOperate(desc = "更新订单", convert = UpdateOrderCovert.class)
    public Boolean updateOrder(UpdateOrder updateOrder) {
        System.out.println("update order, orderId: " + updateOrder.getOrderId());
        return true;
    }

}
