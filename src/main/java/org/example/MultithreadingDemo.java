package org.example;

class MyExtendingThread extends Thread {
    MyExtendingThread() {
        super("Demo MyExtendingThread");
        System.out.println("Child thread (e): " + this);
    }

    public void run() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Child thread (e): " + i);
                Thread.sleep(40);
            }
        } catch (InterruptedException e) {
            System.out.println("Child thread (e) interrupted.");
        }
        System.out.println("Exiting child thread (e).");
    }
}

class MyRunnableThread implements Runnable {
    Thread t;
    MyRunnableThread() {
        t = new Thread(this, "Demo MyRunnableThread");
        System.out.println("Child thread (r): " + t);
    }

    public void run() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Child thread (r): " + i);
                Thread.sleep(40);
            }
        } catch (InterruptedException e) {
            System.out.println("Child thread (r) interrupted.");
        }
        System.out.println("Exiting child thread (r).");
    }
}

class MyImprovedRunnableThread implements Runnable {
    String name;
    Thread t;
    MyImprovedRunnableThread(String threadname) {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
    }
    // This is the entry point for thread.
    public void run() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(name + "Interrupted");
        }
        System.out.println(name + " exiting.");
    }
}

public class MultithreadingDemo {
    public static void main(String[] args) {
        int select = 3;
        switch (select) {
            case 0:
                mainThreadDemo();
                break;
            case 1:
                extendingThreadDemo();
                break;
            case 2:
                runnableThreadDemo();
                break;
            case 3:
                multipleThreadsDemo();
                break;
        }
    }

    private static void multipleThreadsDemo() {
        MyImprovedRunnableThread nt1 = new MyImprovedRunnableThread("Alpha");
        MyImprovedRunnableThread nt2 = new MyImprovedRunnableThread("Beta");
        MyImprovedRunnableThread nt3 = new MyImprovedRunnableThread("Gamma");

        nt1.t.start();
        nt2.t.start();
        nt3.t.start();

        System.out.println("Is thread Alpha alive: " + nt1.t.isAlive());
        System.out.println("Is thread Beta alive: " + nt2.t.isAlive());
        System.out.println("Is thread Gamma alive: " + nt3.t.isAlive());

        try {
            System.out.println("Waiting for threads to finish.");
            nt1.t.join();
            nt2.t.join();
            nt3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }

        System.out.println("Is thread Alpha alive: " + nt1.t.isAlive());
        System.out.println("Is thread Beta alive: " + nt2.t.isAlive());
        System.out.println("Is thread Gamma alive: " + nt3.t.isAlive());
        System.out.println("Main thread exiting.");
    }

    private static void extendingThreadDemo() {
        MyExtendingThread et = new MyExtendingThread();
        et.start();
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Main thread: " + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Main thread exiting.");
    }


    private static void runnableThreadDemo() {
        MyRunnableThread rt = new MyRunnableThread();
        rt.t.start();
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Main thread: " + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Main thread exiting.");
    }

    private static void mainThreadDemo() {
        Thread t = Thread.currentThread();
        System.out.println("Current thread: " + t);
        t.setName("Bread");
        System.out.println("After name change: " + t + "\n");
    }
}
