package com.springboot.tx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Version : 1.0.0

 * DateTime : 2019/3/2 0:39
 */
public class SingtonTest {
    private volatile static SingtonTest singtonTest;

    private SingtonTest() {
        System.out.print("测试");

    }

    public static SingtonTest getInstence() {
        if (singtonTest == null) {
            synchronized (SingtonTest.class) {
                if (singtonTest == null) {
                    singtonTest = new SingtonTest();
                }
            }
        }
        return singtonTest;
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i =0;i<100;i++) {
            Thread ta1 = new MyThread();
            service.execute(ta1);
        }


        service.shutdown();
    }


}
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +":"+ SingtonTest.getInstence().hashCode());
    }
}

