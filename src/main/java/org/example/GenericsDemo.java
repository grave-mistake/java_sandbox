package org.example;

class Gen1<T> {
    T ob;

    Gen1(T o) {
        ob = o;
    }

    T getOb() {
        return ob;
    }

    void showType() {
        System.out.println("Type of T is " + ob.getClass().getName());
    }
}

class Gen2<T1, T2> {
    T1 ob1;
    T2 ob2;

    Gen2(T1 o1, T2 o2) {
        ob1 = o1;
        ob2 = o2;
    }

    void showTypes() {
        System.out.println("Type of T1 is " + ob1.getClass().getName());
        System.out.println("Type of T2 is " + ob2.getClass().getName());
    }

    T1 getOb1() {
        return ob1;
    }

    T2 getOb2() {
        return ob2;
    }
}

// Limit types to those that match the given class (or any of its subclasses)
class Gen3<T extends Number>{
    T[] nums;

    Gen3(T[] o) {
        nums = o;
    }

    double average() {
        double sum = 0.0;
        for (T num : nums) {
            sum += num.doubleValue();
        }
        return sum / nums.length;
    }

    // Wildcard argument demo
    boolean isSameAvg(Gen3<?> ob) {
        return average() == ob.average();
    }
}

// Limit types to those that implement the given interface
class Gen4<T extends IntStack>{
    int topElement;

    Gen4(T o) {
        topElement = o.pop();
    }

    int getTopElement() {
        return topElement;
    }
}

// Limit types to those that match the given class (or any of its subclasses) AND implement the given interface
class Gen5<T extends DynStack & IntStack>{
    int topElement;

    Gen5(T o) {
        topElement = o.pop();
    }

    int getTopElement() {
        return topElement;
    }
}

class NonGenericClassWithAGenericConstructor {
    private double val;

    <T extends Number> NonGenericClassWithAGenericConstructor(T arg) {
        val = arg.doubleValue();
    }

    void showVal() {
        System.out.println("val: " + val);
    }
}

interface MinMax<T extends Comparable<T>> {
    T min();
    T max();
}

class Gen6<T extends Comparable<T>> implements MinMax<T> {
    T[] vals;

    Gen6(T[] o) {
        vals = o;
    }

    public T min() {
        T v = vals[0];
        for (int i = 1; i < vals.length; i++) {
            if (vals[i].compareTo(v) < 0) {
                v = vals[i];
            }
        }
        return v;
    }

    public T max() {
        T v = vals[0];
        for (int i = 1; i < vals.length; i++) {
            if (vals[i].compareTo(v) > 0) {
                v = vals[i];
            }
        }
        return v;
    }
}

public class GenericsDemo {
    public static void main(String[] args) {
        Gen1<Integer> ig1 = new Gen1<Integer>(16);
        ig1.showType();
        System.out.println("value: " + ig1.getOb() + "\n");

        Gen1<String> sg1 = new Gen1<String>("Generics Test");
        sg1.showType();
        System.out.println("value: " + sg1.getOb() + "\n");

        Gen1<WeightedBox> bg1 = new Gen1<WeightedBox>(new WeightedBox(5, 5, 10, 17));
        bg1.showType();
        System.out.println("value: " + bg1.getOb());
        System.out.println("Sum of box dimensions: " + sumOfDimensions(bg1));
        System.out.println("Total surface area: " + surfaceArea(bg1) + "\n");

        Gen2<Integer, Integer> ig2 = new Gen2<Integer, Integer>(23, 42);
        ig2.showTypes();
        System.out.println("values: " + ig2.getOb1() + ", " + ig2.getOb2() + "\n");

        Gen2<Boolean, String> sg2 = new Gen2<Boolean, String>(true, "Bee");
        sg2.showTypes();
        System.out.println("values: " + sg2.getOb1() + ", " + sg2.getOb2() + "\n");

        Gen2<Box, Character> bg2 = new Gen2<Box, Character>(new Box(3), 'f');
        bg2.showTypes();
        System.out.println("values: " + bg2.getOb1() + ", " + bg2.getOb2() + "\n");

        Gen3<Integer> ig3 = new Gen3<Integer>(new Integer[] {1, 2, 3, 4, 5});
        System.out.println("ig3 average is " + ig3.average());

        Gen3<Double> dg3 = new Gen3<Double>(new Double[] {0.5, 2.0, 3.0, 4.5, 5.0});
        System.out.println("dg3 average is " + dg3.average());
        System.out.println("ig3 and dg3 have same average: " + dg3.isSameAvg(ig3) + "\n");

        // This won't work because Gen3<> is only for types that extend the class Number!
        // Gen3<String> sg3 = new Gen3<String>(new String[] {"1", "2", "3", "4", "5"});
        // System.out.println("sg3 average is " + sg3.average());

        FixedStack fs = new FixedStack(5);
        for (int i = 0; i < 5; i++) {
            fs.push((int) (10 + Math.random() * 10));
        }
        Gen4<FixedStack> ig4 = new Gen4<>(fs);
        System.out.println("Top element of ig4 is " + ig4.getTopElement());

        DynStack ds = new DynStack(5);
        for (int i = 0; i < 5; i++) {
            ds.push((int) (10 + Math.random() * 10));
        }
        Gen5<DynStack> ig5 = new Gen5<>(ds);
        System.out.println("Top element of ig5 is " + ig5.getTopElement() + "\n");

        Integer[] nums = { 1, 2, 3, 4, 5 };
        if(isIn(2, nums)) {
            System.out.println("2 is in nums");
        }
        if(!isIn(7, nums)) {
            System.out.println("7 is not in nums");
        }

        String[] strs = { "one", "two", "three", "four", "five" };
        if(isIn("two", strs)) {
            System.out.println("two is in strs");
        }
        if(!isIn("seven", strs)) {
            System.out.println("seven is not in strs");
        }
        System.out.println();

        var test1 = new NonGenericClassWithAGenericConstructor(100);
        var test2 = new NonGenericClassWithAGenericConstructor(123.5F);
        test1.showVal();
        test2.showVal();
        System.out.println();

        Gen6<Integer> ig6 = new Gen6<Integer>(new Integer[] {3, 6, 2, 8, 6});
        Gen6<Character> cg6 = new Gen6<Character>(new Character[] {'b', 'r', 'p', 'w'});
        Gen6<Box> bg6 = new Gen6<>(new Box[] {new Box(3), new Box(2), new Box(1,2,3)});

        System.out.println("Max value in ig6: " + ig6.max());
        System.out.println("Min value in ig6: " + ig6.min());
        System.out.println("Max value in cg6: " + cg6.max());
        System.out.println("Min value in cg6: " + cg6.min());
        System.out.println("Max value in bg6: " + bg6.max());
        System.out.println("Min value in bg6: " + bg6.min() + "\n");
    }

    static double sumOfDimensions(Gen1<? extends Box> gb) {
        Box b = gb.getOb();
        return b.width + b.height + b.depth;
    }

    static double surfaceArea(Gen1<? super WeightedBox> gb) {
        Box b = (Box) gb.getOb();
        return 2 * ((b.width * b.depth) + (b.height * b.depth) + (b.width * b.height));
    }

    // Determine if an object is in an array.
    static <T extends Comparable<T>, V extends T> boolean isIn(T x, V[] y) {
        for (V v : y) {
            if (x.equals(v)) {
                return true;
            }
        }
        return false;
    }
}
