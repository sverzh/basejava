package com.urise.webapp.Util;

public class MainDeadLock {
    private final String name;

    public MainDeadLock(String field) {
        this.name = field;
    }

    public static void main(String[] args) {
        final MainDeadLock MainDeadLock = new MainDeadLock("First");
        final MainDeadLock MainDeadLock2 = new MainDeadLock("Second");
        new Thread(() -> {
            try {
                MainDeadLock.doIT(MainDeadLock2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                MainDeadLock2.doIT(MainDeadLock);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public String getName() {
        return name;
    }

    public synchronized void doIT(MainDeadLock deadLock) throws Exception {
        Thread.sleep(10);
        System.out.println("1 - " + this.name + "-" + deadLock.getName());
        deadLock.doIt2(this);
    }

    public synchronized void doIt2(MainDeadLock deadLock) throws Exception {
        System.out.println("2 - " + this.name + "-" + deadLock.getName());
        deadLock.doIt2(this);
    }
}

