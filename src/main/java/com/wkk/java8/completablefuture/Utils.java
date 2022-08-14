package com.wkk.java8.completablefuture;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Function;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/8/11
 */
public class Utils {

    private static final Random random = new Random(0);

    private static final DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));


    /**
     * 延迟方法
     */
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 监测输出时间方法
     * @param function
     * @param input
     * @param methodName
     */
    public static <E, T> void print(Function<E, List<T>> function, E input, String methodName) {
        System.out.printf("################## %30s ##################" +
                "\n", methodName);
        long start = System.nanoTime();
        System.out.println(function.apply(input));
        long duration = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Done in " + duration + "ms");
        System.out.printf("################## %30s ##################" +
                "\n", methodName);
    }

    /**
     * 输出格式标准化
     * @param number
     * @return
     */
    public static double format(double number) {
        synchronized (formatter) {
            return Double.parseDouble(formatter.format(number));
        }
    }
}
