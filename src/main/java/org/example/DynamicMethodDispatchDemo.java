package org.example;

class DynA {
    void callme() {
        System.out.println("Inside A's callme method");
    }
}

class DynB extends DynA {
    // override callme()
    void callme() {
        System.out.println("Inside B's callme method");
    }
}

class DynC extends DynA {
    // override callme()
    void callme() {
        System.out.println("Inside C's callme method");
    }
}

public class DynamicMethodDispatchDemo {
    public static void main(String[] args) {
        DynA a = new DynA(); // object of type A
        DynB b = new DynB(); // object of type B
        DynC c = new DynC(); // object of type C

        DynA r; // obtain a reference of type A
        r = a; // r refers to an A object
        r.callme(); // calls A's version of callme
        r = b; // r refers to a B object
        r.callme(); // calls B's version of callme
        r = c; // r refers to a C object
        r.callme(); // calls C's version of callme
    }
}