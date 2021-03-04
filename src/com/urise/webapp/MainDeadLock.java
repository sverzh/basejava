package com.urise.webapp;

public class MainDeadLock {

    public static void main(String[] args) {
        Object o = new Object();
        Object o1 = new Object();
        new Thread(() -> {
            System.out.println("Исполняется поток " + Thread.currentThread().getName());
            deadlock(o, o1);
        }).start();
        new Thread(() -> {
            System.out.println("Исполняется поток " + Thread.currentThread().getName());
            deadlock(o1, o);
        }).start();
    }

    public static void deadlock(Object o, Object o1) {
        System.out.println("Поток " + Thread.currentThread().getName() + " пытается захватить объект o");
        synchronized (o) {
            System.out.println("Поток " + Thread.currentThread().getName() + " захватил объект O");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Поток " + Thread.currentThread().getName() + " пытается захватить объект o1");
            synchronized (o1) {
                System.out.println("Поток " + Thread.currentThread().getName() + " захватил объект o1");
            }
        }
    }
}




