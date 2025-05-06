package org.example;

// One interface can extend another.
interface A {
    void meth1();
    void meth2();

    default void meth0() {
        System.out.println("Default meth0() in A. The original.");
    }
}

// B now includes meth1() and meth2() -- it adds meth3().
interface B extends A {
    void meth3();

    static void meth4() {
        System.out.println("Static meth4().");
    }

    default void meth0() {
        System.out.println("Default meth0() in B. Overrides the original.");
        System.out.println("But while we're here let's call the original version of meth0():");
        A.super.meth0();
    }
}

// This class must implement all of A and B
class MyClass implements B {
    public void meth0() {
        System.out.println("Custom override of meth0(). Overrides both the original and B's override.");
        System.out.println("But while we're here let's call the earlier version of meth0():");
        B.super.meth0();
        //A.super.meth0();  // This is not allowed for some reason
    }

    public void meth1() {
        System.out.println("Implement meth1().");
    }

    public void meth2() {
        System.out.println("Implement meth2().");
    }

    public void meth3() {
        System.out.println("Implement meth3().");
    }
}

// Define an integer stack interface.
interface IntStack {
    void push(int item); // store an item
    int pop(); // retrieve an item
}

// An implementation of IntStack that uses fixed storage.
class FixedStack implements IntStack {
    private int[] stck;
    private int tos;
    // allocate and initialize stack
    FixedStack(int size) {
        stck = new int[size];
        tos = -1;
    }
    // Push an item onto the stack
    public void push(int item) {
        if(tos==stck.length-1) // use length member
            System.out.println("Stack is full.");
        else
            stck[++tos] = item;
    }
    // Pop an item from the stack
    public int pop() {
        if(tos < 0) {
            System.out.println("Stack underflow.");
            return 0;
        }
        else
            return stck[tos--];
    }
}

// Implement a "growable" stack.
class DynStack implements IntStack {
    private int[] stck;
    private int tos;
    // allocate and initialize stack
    DynStack(int size) {
        stck = new int[size];
        tos = -1;
    }
    // Push an item onto the stack
    public void push(int item) {
        // if stack is full, allocate a larger stack
        if(tos==stck.length-1) {
            int[] temp = new int[stck.length * 2]; // double size
            for(int i=0; i<stck.length; i++) temp[i] = stck[i];
            stck = temp;
            stck[++tos] = item;
        }
        else
            stck[++tos] = item;
    }
    // Pop an item from the stack
    public int pop() {
        if(tos < 0) {
            System.out.println("Stack underflow.");
            return 0;
        }
        else
            return stck[tos--];
    }
}

// A functional interface is an interface that contains one abstract method and nothing else. It is used for lambdas.
interface FunctionalInterface {
    double getValue();
}

public class InterfaceDemo {
    public static void main(String[] args) {
        IntStack mystack; // create an interface reference variable
        DynStack ds = new DynStack(5);
        FixedStack fs = new FixedStack(8);

        mystack = ds; // load dynamic stack
        for(int i=0; i<12; i++) mystack.push(i);
        mystack = fs; // load fixed stack
        for(int i=0; i<8; i++) mystack.push(i);

        mystack = ds;
        System.out.println("Values in dynamic stack:");
        for(int i=0; i<12; i++)
            System.out.println(mystack.pop());
        mystack = fs;
        System.out.println("Values in fixed stack:");
        for(int i=0; i<8; i++)
            System.out.println(mystack.pop());
        System.out.println();

        MyClass ob = new MyClass();
        ob.meth0();
        ob.meth1();
        ob.meth2();
        ob.meth3();
        B.meth4();      // Static method
    }
}