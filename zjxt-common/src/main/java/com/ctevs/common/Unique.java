/**
 *
 */
package com.ctevs.common;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Description: 改进版<br/>
 *
 * @author haibing.xiao
 * @date: 2016年11月10日 下午6:30:01
 * @version 1.0
 * @since JDK 1.7
 */
public class Unique {

    static protected Logger logger = LoggerFactory.getLogger(Unique.class);
    /** 机器节点标识 */
    private Long workerId;

    /** 数据库节点标识 */
    private Long datacenterId;
    /** 上一毫秒时间戳 */
    private long lastTimestamp = -1L;
    /** 同一毫秒内做并发控制 */
    private long sequence = 0L;
    /** 最近时间戳基点 */
    private final static long twepoch = 1474454995157L;

    /** 机器标识位数 */
    private final static long workerIdBits = 5L;
    /** 数据库标志位数 */
    private final static long datacenterIdBits = 5L;
    /** 机器ID最大值(31) */
    public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
    /** 数据中心ID最大值(31) */
    public final static long maxDatacenterId = -1L ^ -1L << datacenterIdBits;
    /** 序列自增序列位数(10) */
    private final static long sequenceBits = 10L;
    /** 机器标识移位数(10) */
    private final static long workerIdShift = sequenceBits;
    /** 数据中心移位数(15) */
    private final static long datacenterIdShift = sequenceBits + datacenterIdBits;
    /** 毫秒时间戳移位数(20) */
    private final static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    /** 序列掩码 */
    public final static long sequenceMask = -1L ^ -1L << sequenceBits;

    public Unique() {
        this.datacenterId = getDatacenterId(maxDatacenterId);
        this.workerId = getMaxWorkerId(datacenterId, maxWorkerId);
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0",
                    maxDatacenterId));
        }
    }

    /** 同步生成下一个序列号 */
    public synchronized long nextId() {
        long timestamp = getCurMills();
        if (timestamp < this.lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    this.lastTimestamp - timestamp));
        }
        if (this.lastTimestamp == timestamp) {
            /** 当前毫秒则直接+1 */
            this.sequence = (this.sequence + 1) & sequenceMask;
            /** 当前毫秒计数器使用完则使用下一个毫秒 */
            if (this.sequence == 0) {
                timestamp = tilNextMillis(this.lastTimestamp);
            }
            
        } else {
            /** 非当前毫秒计数器清零 */
            this.sequence = new Random().nextInt(10);
        }
        this.lastTimestamp = timestamp;
        /** 移位、组合 */
        return ((timestamp - twepoch << timestampLeftShift)) | (this.datacenterId << datacenterIdShift)
                | (this.workerId << workerIdShift) | (this.sequence);
    }

    /** 得到下一个毫秒 */
    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = getCurMills();
        while (timestamp <= lastTimestamp) {
            timestamp = getCurMills();
        }
        return timestamp;
    }

    /** 获得当前毫秒 */
    private long getCurMills() {
        return System.currentTimeMillis();
    }

    /**
     * <p>
     * 获取 maxWorkerId
     * </p>
     */
    protected static long getMaxWorkerId(long datacenterId, long maxWorkerId) {
        long mpid = datacenterId;
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!name.isEmpty()) {
            mpid = mpid + Long.valueOf(name.split("@")[0]);
        }
        return (mpid & 0xffff)% (maxWorkerId + 1);
    }

    /**
     * <p>
     * 数据标识id部分
     * </p>
     */
    protected static long getDatacenterId(long maxDatacenterId) {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                id = id % (maxDatacenterId + 1);
            }
        } catch (Exception e) {
            System.out.println(" getDatacenterId: " + e.getMessage());
        }
        return id;
    }

    public static void main(String[] args) {

        Unique unique = new Unique();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println(unique.nextId());
                }
            }
        };
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            executor.execute(task);
        }
        executor.shutdown();
    }

}
