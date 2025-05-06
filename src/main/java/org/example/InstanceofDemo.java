class A1 {
    int i, j;
}
class B1 {
    int i, j;
}
class C1 extends A1 {
    int k;
}
class D1 extends A1 {
    int k;
}
class InstanceofDemo {
    public static void main(String[] args) {
        A1 a = new A1();
        B1 b = new B1();
        C1 c = new C1();
        D1 d = new D1();
        if(a instanceof A1)
            System.out.println("a is instance of A1");
        if(b instanceof B1)
            System.out.println("b is instance of B1");
        if(c instanceof C1)
            System.out.println("c is instance of C1");
        if(c instanceof A1)
            System.out.println("c can be cast to A1");
        if(a instanceof C1)
            System.out.println("a can be cast to C1");
        System.out.println();
        // compare types of derived types
        A1 ob;
        ob = d; // A1 reference to d
        System.out.println("ob now refers to d");
        if(ob instanceof D1)
            System.out.println("ob is instance of D1");
        System.out.println();
        ob = c; // A1 reference to c
        System.out.println("ob now refers to c");
        if(ob instanceof D1)
            System.out.println("ob can be cast to D1");
        else
            System.out.println("ob cannot be cast to D1");
        if(ob instanceof A1)
            System.out.println("ob can be cast to A1");
        System.out.println();
        // all objects can be cast to Object
        if(a instanceof Object)
            System.out.println("a may be cast to Object");
        if(b instanceof Object)
            System.out.println("b may be cast to Object");
        if(c instanceof Object)
            System.out.println("c may be cast to Object");
        if(d instanceof Object)
            System.out.println("d may be cast to Object");
    }
}