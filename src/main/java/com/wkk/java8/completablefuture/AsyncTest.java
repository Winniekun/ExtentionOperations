package com.wkk.java8.completablefuture;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.wkk.java8.completablefuture.Utils.print;

/**
 * CompletableFuture 单测
 * thenCompose 上一个异步的结果作为当前异步的参数，继续执行
 * thenCombine 并发执行 多个异步内容
 * thenAccept  异步执行结束，对返回结果的操作
 * allOf       所有的异步执行结束，再返回
 * anyOf       任何异步异异步执行结束，即可返回
 *
 * 案例
 * 通过远程调用多个商品的单价：
 * 1. 先远程调用获取商品的原价
 * 2. 之后再远程调用获取对应的折扣
 * 3. 之后返回打完折的价格
 *
 * 优势：
 * 执行比较耗时的操作时，尤其是那些依赖一个或者多个远程服务的操作，使用异步可以有效改善程序的性能，加快响应时间
 * CompletableFuture类还提供了异常管理的机制，能够有效的管理异步任务执行中的异常
 * 直接将同步方法封装到CompletableFuture中，即可通过异步的形式使用其结果
 * 如果异步任务之间互相独立，或者某一个结果是另一个结果的输入，则可以将这些异步combine或者compose
 * 可以为CompletableFuture设计一个回调函数，在Future执行完毕之后对执行结果进行额外操作
 * 可以有效选择所有异步操作执行结束或者任何一个执行结束就终止程序的运行
 *
 * @author weikunkun
 * @since 2022/8/11
 */
public class AsyncTest {

    private List<Shop> shops = Lists.newArrayList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy")
    );

    private ExecutorService executorService = Executors.newFixedThreadPool(
            Math.min(shops.size(), 100),
            r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            });

    private String product = "myPhone27S";


    @Test
    public void baseInfo() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void testFindPrices() {
        print((str) -> findPrices(str), product, "findPrices");
    }

    @Test
    public void testFindPriceParallel() {
        print((str) -> findPricesWithParallel(str), product, "findPricesWithParallel");
    }

    @Test
    public void testFindPriceyConcurrency() {
        print((str) -> findPriceWithConcurrency(str), product, "findPriceWithConcurrency");
    }

    @Test
    public void testFindPrice() {
        print((str) -> findPrices(str), product, "findPrices");
    }

    @Test
    public void testFindPriceRate() {
        print((str) -> findPricesWithRate(str), product, "findPricesWithRate");
    }

    @Test
    public void testFindPriceRateAsync() {
        print((str) -> findPricesWithRateAsync(str), product, "findPricesWithRateAsync");
    }

    @Test
    public void testFindPricesStreamWithAllOf() {
        long start = System.nanoTime();
        List<String> priceList = Lists.newArrayList();
        CompletableFuture[] futures = findPricesSteam(product)
                .map(f -> f.thenAccept(s -> {
                    System.out.println(s +
                            " (done in " + ((System.nanoTime() - start) / 1_000_000) + " ms)");
                    priceList.add(s);
                }))
                .toArray(size -> new CompletableFuture[size]);

        // 等待所有结果执行结束
        CompletableFuture.allOf(futures).join();
        System.out.println("All of shop have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " ms");
        System.out.println(priceList);
    }

    @Test
    public void testFindPricesStreamWithOneOf() {
        long start = System.nanoTime();
        List<String> priceList = Lists.newArrayList();
        CompletableFuture[] futures = findPricesSteam(product)
                .map(f -> f.thenAccept(s -> {
                    System.out.println(s +
                            " (done in " + ((System.nanoTime() - start) / 1_000_000) + " ms)");
                    priceList.add(s);
                }))
                .toArray(size -> new CompletableFuture[size]);

        // 等待任何一个结果执行结束
        CompletableFuture.anyOf(futures).join();
        System.out.println("All of shop have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " ms");
        System.out.println(priceList);
    }




    /**
     * 根据商品名获取商品原价
     *
     * @param product
     * @return
     */
    public List<String> findPricesWithString(String product) {
        return shops.stream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPriceNumber(product)))
                .collect(Collectors.toList());
    }

    /**
     * 并行执行
     *
     * @param product
     * @return
     */
    public List<String> findPricesWithParallel(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s prices is %.2f", shop.getName(), shop.getPriceNumber(product)))
                .collect(Collectors.toList());
    }

    /**
     * 并行执行
     *
     * @param product
     * @return
     */
    public List<String> findPriceWithConcurrency(String product) {
        List<CompletableFuture<String>> priceFuture = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPriceNumber(product),
                        executorService))
                .collect(Collectors.toList());
        // 所有结果结束之后，返回结果
        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * 根据商品名获取商品折扣之后的价格
     *
     * @param product
     * @return
     */
    public List<String> findPrices(String product) {
        return shops.stream()
                // 网络IO （模拟远程获取价格）
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                // 网络IO （模拟远程获取折扣）
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    /**
     * 根据商品名获取商品折扣之后的价格
     *
     * @param product
     * @return
     */
    public List<String> findPricesWithDiscountAsync(String product) {
        List<CompletableFuture<String>> priceFuture = shops.stream()
                // 异步执行 获取原价的IO请求
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executorService))
                .map(future -> future.thenApply((Quote::parse)))
                .map(future -> future
                        // 异步执行 获取折扣之后价格的IO请求
                        // thenCompose 对两个异步操作，将第一个异步的执行结果作为第二个异步操作的参数，并继续执行后续操作
                        .thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executorService)))
                .collect(Collectors.toList());

        // 等待所有的Future执行完毕，获取各自的值，并返回
        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public List<Double> findPricesWithRate(String product) {
        return shops.stream().map(shop -> shop.getPriceNumber(product)).map(price -> {
            double rate = ExchangeService.getRate(Money.EUR, Money.USD);
            return rate * price;
        }).collect(Collectors.toList());
    }

    /**
     * 根据不同汇率获取未折扣前价格
     * thenCombine使用
     *
     * @param product
     * @return
     */
    public List<Double> findPricesWithRateAsync(String product) {
        List<CompletableFuture<Double>> priceFuture = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceNumber(product), executorService)
                        // 两个线程同步工作
                        // 第一个线程获取价格
                        // 第二个线程获取该国家的汇率
                        .thenCombine(CompletableFuture.supplyAsync(() -> ExchangeService.getRate(Money.EUR, Money.USD), executorService)
                                , (price, rate) -> price * rate))
                .collect(Collectors.toList());

        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * 获取折扣价格的stream流
     *
     * @param product
     * @return
     */
    public Stream<CompletableFuture<String>> findPricesSteam(String product) {
        return shops.stream()
                // 获取原价 IO请求
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executorService))
                .map(future -> future.thenApply(Quote::parse))
                // 获取打完折的价格 IO请求
                .map(quoteCompletableFuture -> quoteCompletableFuture.thenCompose(quote ->
                        CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executorService)));
    }


}
