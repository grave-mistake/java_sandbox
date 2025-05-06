package org.example;

interface NumericTest {
    boolean test(int n);
}

interface NumericTest2 {
    boolean test(int n, int d);
}

interface NumericFunc {
    int func(int n);
}

interface StringFunc {
    String func(String n);
}

interface SomeFunc<T> {
    T func(T t);
}

interface UniversalFunc<T, V> {
    V func(T t);
}

interface DoubleNumericArrayFunc {
    double func(double[] n) throws EmptyArrayException;
}

class EmptyArrayException extends Exception {
    EmptyArrayException() {
        super("Array Empty");
    }
}

class MyAwesomeOps {
    static String staticMethodThatReversesString(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            result.append(str.charAt(i));
        }
        return result.toString();
    }

    String nonStaticMethodThatTurnsStringUpperCase(String str) {
        return str.toUpperCase();
    }

    String nonStaticMethodThatTurnsItsObjectUpperCase() {
        return this.toString().toUpperCase();
    }

    static <T> int staticMethodThatCountsChars(T value) {
        System.out.println("Value received: " + value.toString());
        return value.toString().length();
    }

    static <T> String staticMethodThatTurnsAnythingUpperCase(T value) {
        System.out.println("Value received: " + value.toString());
        return value.toString().toUpperCase();
    }
}

public class LambdaDemo {
    // This method has a functional interface as the type of its first parameter. Thus, it can be passed a reference
    // to any instance of that interface, including the instance created by a lambda expression.
    static String stringOp(StringFunc f, String s) {
        return f.func(s);
    }

    static <T, V> V someOp(UniversalFunc<T, V> f, T t) {
        return f.func(t);
    }

    public static void main(String[] args) throws EmptyArrayException {
        // Create a reference to a functional interface instance
        FunctionalInterface myNum;
        // Here, the lambda expression is simply a constant expression.
        // When it is assigned to myNum, a class instance is constructed in which the lambda expression implements
        // the sole abstract method in MyNumber.
        myNum = () -> 123.45;
        // Call the abstract method, which is implemented by the previously assigned lambda expression
        System.out.println(myNum.getValue());

        myNum = () -> Math.random() * 100;
        System.out.println("A random value: " + myNum.getValue());
        System.out.println("Another random value: " + myNum.getValue() + "\n");

        // A lambda expression that tests if a number is even.
        NumericTest isEven = (n) -> (n % 2) == 0;
        if (isEven.test(10)) {
            System.out.println("10 is even");
        }
        if (!isEven.test(9)) {
            System.out.println("9 is not even");
        }
        // Now, use a lambda expression that tests if a number is non-negative.
        NumericTest isNonNeg;
        isNonNeg = (n) -> n >= 0;
        isNonNeg = (int n) -> n >= 0; // You can explicitly specify the type if you want
        if (isNonNeg.test(1)) {
            System.out.println("1 is non-negative");
        }
        if (!isNonNeg.test(-1)) {
            System.out.println("-1 is negative");
        }
        System.out.println();

        // This lambda expression determines if one number is a factor of another.
        NumericTest2 isFactor = (n, d) -> (n % d) == 0;
        if (isFactor.test(10, 2)) {
            System.out.println("2 is a factor of 10");
        }
        if (!isFactor.test(10, 3)) {
            System.out.println("3 is not a factor of 10");
        }
        System.out.println();

        // This block lambda computes the factorial of an int value.
        NumericFunc fact = (n) -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result = i * result;
            }
            return result;
        };
        System.out.println("The factorial of 3 is " + fact.func(3));
        System.out.println("The factorial of 5 is " + fact.func(5));
        System.out.println();

        // Use a String-based version of SomeFunc.
        SomeFunc<String> rev = (str) -> {
            StringBuilder result = new StringBuilder();
            for (int i = str.length() - 1; i >= 0; i--) {
                result.append(str.charAt(i));
            }
            return result.toString();
        };
        System.out.println("Lambda reversed is " + rev.func("Lambda"));
        System.out.println("Expression reversed is " + rev.func("Expression"));
        // Now, use an Integer-based version of SomeFunc.
        SomeFunc<Integer> factorial = (n) -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result = i * result;
            }
            return result;
        };
        System.out.println("The factorial of 3 is " + factorial.func(3));
        System.out.println("The factorial of 5 is " + factorial.func(5) + "\n");

        String inStr = "Lambdas add power to Java";
        String outStr;
        System.out.println("Here is input string: " + inStr);
        // Here, a simple expression lambda that uppercases a string is passed to stringOp().
        outStr = stringOp((str) -> str.toUpperCase(), inStr);
        System.out.println("The string in uppercase: " + outStr);
        // This passes a block lambda that removes spaces.
        outStr = stringOp((str) -> {
            StringBuilder result = new StringBuilder();
            int i;
            for (i = 0; i < str.length(); i++) {
                if (str.charAt(i) != ' ') {
                    result.append(str.charAt(i));
                }
            }
            return result.toString();
        }, inStr);
        System.out.println("The string with spaces removed: " + outStr);

        // Of course, it is also possible to pass a StringFunc instance created by an earlier lambda expression.
        // For example, after this declaration executes, reverse refers to an instance of StringFunc.
        StringFunc reverse = (str) -> {
            StringBuilder result = new StringBuilder();
            for (int i = str.length() - 1; i >= 0; i--) {
                result.append(str.charAt(i));
            }
            return result.toString();
        };
        // Now, reverse can be passed as the first parameter to stringOp() since it refers to a StringFunc object.
        System.out.println("The string reversed: " + stringOp(reverse, inStr) + "\n");

        double[] values  = { 1.0, 2.0, 3.0, 4.0 };
        // This block lambda computes the average of an array of doubles.
        DoubleNumericArrayFunc average = (n) -> {
            double sum = 0;
            if (n.length == 0) {
                throw new EmptyArrayException();
            }
            for (double v : n) {
                sum += v;
            }
            return sum / n.length;
        };
        System.out.println("The average is " + average.func(values));
        //System.out.println("The average is " + average.func(new double[0]));  // This would cause an exception
        System.out.println();

        // A local variable MUST be final or effectively final to be used inside a lambda!
        int num = 10;   // num is effectively final if it is never modified again
        NumericFunc tempLambda = (n) ->  {
            // This use of num is OK. It does not modify num.
            int v = Integer.valueOf(num) + n;
            // num++;   // However, this would be illegal, because it attempts to modify the value of num.
            return v;
        };
        // num = 9;    // This would also be illegal, because it would remove the effectively final status from num!
        System.out.println(num + "\n");

        inStr = "Lambdas add power to Java";
        // A reference to a static method (as long as it has a compatible signature) can be used in place of a lambda:
        outStr = stringOp(MyAwesomeOps::staticMethodThatReversesString, inStr);
        System.out.println("Original string: " + inStr);
        System.out.println("String reversed: " + outStr);
        // Works with non-static methods too:
        var MyAwesomeOpsInstance = new MyAwesomeOps();
        outStr = stringOp(MyAwesomeOpsInstance::nonStaticMethodThatTurnsStringUpperCase, inStr);
        System.out.println("String in uppercase: " + outStr);
        // You can even use an non-static method with the class itself instead of a specific instance!
        // In this case, a specific instance will need to be passed as the first argument to the method you're passing
        outStr = someOp(MyAwesomeOps::nonStaticMethodThatTurnsItsObjectUpperCase, MyAwesomeOpsInstance);
        System.out.println("Object in uppercase: " + outStr + "\n");

        double g1 = 23.42;
        System.out.println("String representation has " + someOp(MyAwesomeOps::staticMethodThatCountsChars, g1) + " characters");
        System.out.println("Converted to uppercase: " + someOp(MyAwesomeOps::staticMethodThatTurnsAnythingUpperCase, g1) + "\n");
        String g2 = "Lamb Da";
        System.out.println("String representation has " + someOp(MyAwesomeOps::staticMethodThatCountsChars, g2) + " characters");
        System.out.println("Converted to uppercase: " + someOp(MyAwesomeOps::staticMethodThatTurnsAnythingUpperCase, g2) + "\n");
        WeightedBox g3 = new WeightedBox(4, 8, 15, 16);
        System.out.println("String representation has " + someOp(MyAwesomeOps::staticMethodThatCountsChars, g3) + " characters");
        System.out.println("Converted to uppercase: " + someOp(MyAwesomeOps::staticMethodThatTurnsAnythingUpperCase, g3) + "\n");

        UniversalFunc<Double, Box> boxCon = Box::new;
        Box coolBox = boxCon.func(25.0);
        System.out.println("Box instance created using a method reference: " + coolBox.toString());
        UniversalFunc<Box, Gen1<Box>> genCon = Gen1<Box>::new;
        Gen1<Box> coolGenBox = genCon.func(g3);
        System.out.println("Gen1<Box> instance created using a method reference: " + coolGenBox.getOb().toString() + "\n");

        // This block lambda computes the factorial of an int value. This time, Function is the functional interface.
        java.util.function.Function<Integer, Integer> myFactorial = (n) -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result = i * result;
            }
            return result;
        };
        System.out.println("The factorial of 3 is " + myFactorial.apply(3));
        System.out.println("The factorial of 5 is " + myFactorial.apply(5));
    }
}
