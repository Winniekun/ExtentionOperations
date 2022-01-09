package com.wkk.utils;

import com.alibaba.excel.EasyExcel;
import com.wkk.DemoData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author weikunkun
 * @since 2021/12/29
 */
public class ExcelUtilTest {
    Random random = new Random();

    @Test
    public void simpleWrite() {
        // 写法1
        String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

//        // 写法2
//        fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        // 这里 需要指定写用哪个class去写
//        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//        excelWriter.write(data(), writeSheet);
//        // 千万别忘记finish 会帮忙关闭流
//        excelWriter.finish();
    }

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