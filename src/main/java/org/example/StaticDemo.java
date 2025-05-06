package org.example;

import static java.lang.Math.sqrt;  // Static import: lets us simply use "sqrt(...)" instead of "Math.sqrt(...)"
import static java.lang.Math.pow;

class StatA {
    static void greet() {
        System.out.println("Hello from StatA");
    }
}

class StatB extends StatA {
    // This HIDES StatA's greet(), not overrides it!
    static void greet() {
        System.out.println("Hello from StatB");
    }
}

public class StaticDemo {
    final static int a = 3;
    final static int b;
    static int c;
    final static String s = "Hello";
    static String t;

    static void meth(int x) {
        System.out.println("x = " + x);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);

        System.out.println("s = " + s);
        System.out.println("t = " + t);
    }

    static {
        System.out.println("Static block initialized.");
        b = a * 4;  // Magic!
    }

    public static void main(String[] args) {
        meth(42);
        System.out.println();

        StatA.greet();  // Hello from StatA
        StatB.greet();  // Hello from StatB
        StatA a = new StatB();
        a.greet();      // Hello from StatA (the reference type), not StatB (the actual object type)

        double side1 = 3.0, side2 = 4.0;
        double hypot = sqrt(pow(side1, 2) + pow(side2, 2));
        System.out.println("\nHypotenuse: " + hypot);
    }
}