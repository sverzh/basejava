package com.urise.webapp;

public class MainDeadLock {

    public static void main(String[] args) {
        Object o = new Object();
        Object o1 = new Object();
        new Thread(() -> {
            try {
                doIT(o, o1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                doIT(o1, o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void doIT(Object o, Object o1) throws Exception {
        synchronized (o) {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10);
            synchronized (o1) {
            }
        }
    }
}




