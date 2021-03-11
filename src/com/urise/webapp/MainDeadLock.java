package com.urise.webapp;

public class MainDeadLock {

    public static void main(String[] args) {
        Object o = new Object();
        Object o1 = new Object();
        new Thread(() -> {
            System.out.println("The thread " + Thread.currentThread().getName() + " is running");
            deadlock(o, o1);
        }).start();
        new Thread(() -> {
            System.out.println("The thread " + Thread.currentThread().getName() + " is running");
            deadlock(o1, o);
        }).start();
    }

    public static void deadlock(Object o, Object o1) {
        System.out.println("Thread " + Thread.currentThread().getName() + " try to capture object o");
        synchronized (o) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " try to capture object o1");
            synchronized (o1) {
            }
        }
    }
}




