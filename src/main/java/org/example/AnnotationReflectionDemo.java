package org.example;

import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyMethAnno {
    String str() default "Unimportant Method";
    int value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MyClassAnno {
    int value();
}

@MyClassAnno(1800)
class Dummy {
    final static int x = 10;
    int y;

    Dummy(int y) {
        this.y = y;
    }

    @MyMethAnno(str = "Certified Cool Method", value = 100500)
    public static void printRandom() {
        int local = (int) (x * Math.random());
        System.out.println("Random number: " + local);
    }

    @MyMethAnno(33)
    public static void printHello() {
        System.out.println("Hello");
    }
}

class FortKnox {
    private final int topSecret = 42;
}

public class AnnotationReflectionDemo {
    public static void main(String[] args) {
        var a = MyClassAnno.class;
        var aa = a.getAnnotations();
        System.out.println("All annotations for MyClassAnno:");
        for (var anno : aa) {
            System.out.println("> " + anno);
        }
        System.out.println();

        var c = Dummy.class;
        var ca = c.getAnnotation(MyClassAnno.class);
        System.out.println("Details of MyClassAnno for class Dummy: value = " + ca.value());
        System.out.println("All fields of class Dummy:");
        for (var f : c.getDeclaredFields()) {
            System.out.println("> " + f);
        }
        System.out.println("All constructors of class Dummy:");
        for (var m : c.getDeclaredConstructors()) {
            System.out.println("> " + m);
        }
        System.out.println("All methods of class Dummy:");
        for (var m : c.getDeclaredMethods()) {
            System.out.println("> " + m);
            var ma = m.getAnnotation(MyMethAnno.class);
            System.out.println(">> Details of MyMethAnno for " + m.getName() + "(): str = \"" + ma.str() + "\", value = " + ma.value());
        }
        System.out.println();

        try {
            FortKnox fort = new FortKnox();
            Field f = fort.getClass().getDeclaredField("topSecret");
            f.setAccessible(true);
            System.out.println("Retrieved top secret value: " + f.get(fort));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
