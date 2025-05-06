package org.example;

abstract class CallMe {
    abstract void call(String msg);
}

class UnsyncedCallMe extends CallMe {
    void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("]");
    }
}

class SyncedCallMe extends CallMe {
    synchronized void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("]");
    }
}

class Caller implements Runnable {
    String msg;
    CallMe target;
    Thread t;
    boolean useSyncBlock;

    public Caller(CallMe target, String msg) {
        this.target = target;
        this.msg = msg;
        this.t = new Thread(this);
        this.useSyncBlock = false;
    }

    public Caller(CallMe target, String msg, boolean useSyncBlock) {
        this.target = target;
        this.msg = msg;
        this.t = new Thread(this);
        this.useSyncBlock = useSyncBlock;
    }

    public void run() {
        if (!useSyncBlock) {
            target.call(msg);
        } else {
            synchronized(target) {
                target.call(msg);
            }
        }
    }
}

public class SyncDemo {
    public static void main(String[] args) {
        CallMe target;
        boolean useSyncBlock = false;

        int select = 2;
        switch (select) {
            case 2:
                useSyncBlock = true;
            case 0:
                target = new UnsyncedCallMe();
                break;
            default:
                target = new SyncedCallMe();
                break;
        }

        Caller ob1 = new Caller(target, "Hello", useSyncBlock);
        Caller ob2 = new Caller(target, "Synchronized", useSyncBlock);
        Caller ob3 = new Caller(target, "World", useSyncBlock);

        ob1.t.start();
        ob2.t.start();
        ob3.t.start();

        try {
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        } catch(InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}
