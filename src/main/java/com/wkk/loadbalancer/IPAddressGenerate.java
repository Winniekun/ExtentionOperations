package com.wkk.loadbalancer;

import java.util.Random;

/**
 * @author weikunkun
 * @since 2021/8/15
 */
public class IPAddressGenerate {
    public String[] genIPAddress(int nums) {
        Random random = new Random();
        String[] ips = new String[nums];
        for (int i = 0; i < nums; i++) {
            ips[i] = String.valueOf(random.nextInt(256)) + "." + String.valueOf(random.nextInt(256)) + "."
                    + String.valueOf(random.nextInt(256)) + "." + String.valueOf(random.nextInt(256)) + ":"
                    + String.valueOf(random.nextInt(9999));
        }
        return ips;
    }
}
