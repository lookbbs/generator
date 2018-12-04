package com.ydf.generator.config;

import java.util.concurrent.*;

/**
 * 线程管理类
 *
 * @author yuandongfei
 * @date 2018/11/9
 */
public class ThreadManager {

    private static ThreadPoolProxy instance;

    public static ThreadPoolProxy getInstance() {
        if (null == instance) {
            synchronized (ThreadManager.class) {
                if (null == instance) {
                    // 获取处理器数量
                    int cpuNum = Runtime.getRuntime().availableProcessors();
                    // 根据CPU的数量计算出合理的线程并发数
                    int threadNum = cpuNum * 2 + 1;
                    instance = new ThreadPoolProxy(threadNum - 1, threadNum, Integer.MAX_VALUE);
                }
            }
        }
        return instance;
    }

    public static class ThreadPoolProxy<T> {
        /**
         * 线程池
         */
        private ThreadPoolExecutor executor;
        /**
         * 核心线程数
         */
        private int corePoolSize;
        /**
         * 最大线程数
         */
        private int maximumPoolSize;
        /**
         * 闲置线程存活时间
         */
        private long keepAliveTime;

        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        public Future<T> submit(Callable<T> task) {
            if (null == task) {
                return null;
            }
            if (null == executor) {
                initThreadPoolExecutor();
            }
            return executor.submit(task);
        }

        public void execute(Runnable task) {
            if (null == task) {
                return;
            }
            if (null == executor) {
                initThreadPoolExecutor();
            }
            executor.execute(task);
        }

        /**
         * 初始化
         */
        private void initThreadPoolExecutor() {
            this.executor = new ThreadPoolExecutor(
                    corePoolSize,
                    maximumPoolSize,
                    keepAliveTime,
                    // 单位：毫秒
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingDeque<>(Integer.MAX_VALUE));
        }
    }
}