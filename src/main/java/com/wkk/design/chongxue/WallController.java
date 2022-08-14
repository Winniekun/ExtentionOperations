package com.wkk.design.chongxue;

import java.math.BigDecimal;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/7/13
 */
public class WallController {
    private WalletService walletService = new WalletService();

    public BigDecimal getBalance(Long walletId) {
        return new BigDecimal(0);
    }



}

