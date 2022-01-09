package com.wkk.utils;

import com.wkk.DemoData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author weikunkun
 * @since 2021/12/29
 */
public class ExcelUtil {
    Random random = new Random(10000);

    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            DemoData demoData = new DemoData();
            demoData.setDate(new Date());
            demoData.setDoubleData(random.nextDouble());
            demoData.setString(UUID.randomUUID().toString());
            list.add(demoData);
        }
        return list;
    }
}
