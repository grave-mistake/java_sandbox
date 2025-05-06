package org.example;

/*
Nested classes can be either static or non-static
To access a non-static inner class you need an instance of the outer class
To access a static inner class you only need the outer class itself
Nested interfaces are always static (implicitly or explicitly)
*/

class Outer {
    int outer_x = 100;
    void test() {
        Inner inner = new Inner();
        inner.display();
    }

    class Inner {
        int y = 10;
        static int z = 10;
        void display() {
            System.out.println("display: outer_x = " + outer_x);
        }
    }

    void showy() {
        //System.out.println(y);   // Can't do this, y is not accessible outside of Inner
        System.out.println(Inner.z);    // This is OK because z is static
    }
}
class InnerClassDemo {
    public static void main(String[] args) {
        var outer = new Outer();
        outer.test();
    }
}