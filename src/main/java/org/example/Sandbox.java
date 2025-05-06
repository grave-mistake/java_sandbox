package org.example;

public class Sandbox {
    public static void main(String[] args) {
        sayHello();
        lightspeed(543446);
        chars();
        funWIthNumbers();
        conversion();
        arrays();
        pyramid();
        basicMath();
        byteShift();
        byteUShift();
        switchShowcase();
        stringSwitch();
        midpoint();
        loops();
        IWantToBreakFree();

        fact(1);
        fact(2);
        fact(3);
        fact(4);
        fact(5);
        fact(6);
    }

    public static void sayHello() {
        System.out.println("Hello,\sWorld!");
    }

    public static void lightspeed(int days) {
        int lightspeed;
        long seconds;
        long distance;
        lightspeed = 300000; // approximate speed of light
        seconds = (long) days * 24 * 60 * 60; // convert to seconds
        distance = lightspeed * seconds; // compute distance
        System.out.print("In " + days);
        System.out.print(" days light will travel about ");
        System.out.println(distance + " km.\n");
    }

    public static void chars() {
        char c = 'X';
        System.out.println("c = '" + c + "', c + 1 = '" + ++c + "'");
        char l = 'l';
        System.out.println("The code of '" + l + "' is " + (int) l + "\n");
    }

    public static void funWIthNumbers() {
        int a = 0b1001_0100_0101_0001; // 37969
        int b = a * a * a;
        long c = a * a * a;
        long d = (long) a * a * a;
        var e = a * a * a;
        var f = (long) a * a * a;
        System.out.println("                    int a = " + a);
        System.out.println("        int b = a * a * a = " + b + " (Incorrect)");
        System.out.println("       long c = a * a * a = " + c + " (Incorrect)");
        System.out.println("long d = (long) a * a * a = " + d);
        System.out.println("        var e = a * a * a = " + e + " (Incorrect)");
        System.out.println(" var f = (long) a * a * a = " + f + "\n");

        int x = 17;
        int y = 5;
        System.out.println("          int x = " + x);
        System.out.println("          int y = " + y);
        System.out.println("          x / y = " + x / y);
        System.out.println("  (float) x / y = " + (float) x / y);
        System.out.println("  x / (float) y = " + x / (float) y);
        System.out.println("(float) (x / y) = " + (float) (x / y) + "\n");
    }

    public static void conversion() {
        byte b;
        int i = 260;
        double d = 260.5;
        System.out.println("Conversion of int to byte:");
        b = (byte) i;
        System.out.println(i + " becomes " + b);
        System.out.println("Conversion of double to int:");
        i = (int) d;
        System.out.println(d + " becomes " + i);
        System.out.println("Conversion of double to byte:");
        b = (byte) d;
        System.out.println(d + " becomes " + b);
    }

    public static void arrays() {
        int[] a1 = new int[3];
        a1[0] = 4;
        a1[1] = 8;
        a1[2] = 15;
        System.out.println("a1[1] = " + a1[1]);

        int[] a2 = {16, 23, 42};
        System.out.println("a2[1] = " + a2[1] + "\n");
    }

    public static void pyramid() {
        int[][] pyr = new int[4][];
        pyr[0] = new int[1];
        pyr[1] = new int[2];
        pyr[2] = new int[3];
        pyr[3] = new int[4];
        int i, j, k = 0;

        for(i = 0; i < 4; i++)
            for(j = 0; j < i + 1; j++) {
                pyr[i][j] = k;
                k++;
            }

        for(i = 0; i < 4; i++) {
            for(j = 0; j < i + 1; j++)
                System.out.print(pyr[i][j] + " ");
            System.out.println();
        }
        System.out.println("Length of pyr: " + pyr.length);
        System.out.println();
    }

    public static void basicMath() {
        // arithmetic using integers
        System.out.println("Integer Arithmetic:");
        int a = 1 + 1;
        int b = a * 3;
        int c = b / 4;
        int d = c - a;
        int e = -d;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("d = " + d);
        System.out.println("e = " + e);
        // arithmetic using doubles
        System.out.println("Floating Point Arithmetic:");
        double da = 1 + 1;
        double db = da * 3;
        double dc = db / 4;
        double dd = dc - a;
        double de = -dd;
        System.out.println("da = " + da);
        System.out.println("db = " + db);
        System.out.println("dc = " + dc);
        System.out.println("dd = " + dd);
        System.out.println("de = " + de + "\n");
    }

    public static void byteShift() {
        byte a = 64, b;
        int i = a << 2;
        b = (byte) (a << 2);
        System.out.println("Original value of a: " + a);
        System.out.println("int i = a << 2: " + i + " (Correct)");
        System.out.println("b = (byte) (a << 2): " + b + " (Overflows back to 0)\n");
    }

    static public void byteUShift() {
        char[] hex = {
                '0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };
        byte b = (byte) 0xf1;
        byte c = (byte) (b >> 4);
        byte d = (byte) (b >>> 4);
        byte e = (byte) ((b & 0xff) >> 4);
        System.out.println("              b = 0x" + hex[(b >> 4) & 0x0f] + hex[b & 0x0f]);
        System.out.println("         b >> 4 = 0x" + hex[(c >> 4) & 0x0f] + hex[c & 0x0f]);
        System.out.println("        b >>> 4 = 0x" + hex[(d >> 4) & 0x0f] + hex[d & 0x0f]);
        System.out.println("(b & 0xff) >> 4 = 0x" + hex[(e >> 4) & 0x0f] + hex[e & 0x0f] + "\n");
    }

    public static void switchShowcase() {
        for (int i = 0; i < 12; i++) {
            switch (i) {
                case 0:
                case 1:
                case 2:
                    System.out.println(i + " is less than 3, but also");
                case 3:
                case 4:
                    System.out.println(i + " is less than 5");
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    System.out.println(i + " is less than 10");
                    break;
                default:
                    System.out.println(i + " is equal to or more than 10");
            }
        }
        System.out.println();
    }

    public static void stringSwitch() {
        String str = "two";
        switch(str) {
            case "one":
                System.out.println("one");
                break;
            case "two":
                System.out.println("two");
                break;
            case "three":
                System.out.println("three");
                break;
            default:
                System.out.println("no match");
                break;
        }
        System.out.println();
    }

    public static void midpoint() {
        int i, j;
        i = 100;
        j = 200;
        // find midpoint between i and j
        while(++i < --j); // no body in this loop
        System.out.println("Midpoint is " + i);
        System.out.println();
    }

    public static void loops() {
        int a, b;
        for(a=1, b=4; a<b; a++, b--) {
            System.out.println("a = " + a);
            System.out.println("b = " + b);
        }

        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int sum = 0;
        // use for-each style for to display and sum the values
        for(int x : nums) {
            System.out.println("Value is: " + x);
            sum += x;
        }
        System.out.println("Summation: " + sum);
        System.out.println();
    }

    public static void IWantToBreakFree() {
        boolean t = true;
        first: {
            second: {
                third: {
                    System.out.println("Before the break.");
                    if(t) break second; // break out of second block
                    System.out.println("This won't execute");
                }
                System.out.println("This won't execute");
            }
            System.out.println("This is after second block.");
        }
        System.out.println();
    }

    public static int fact(int n) {
        int result;
        if (n == 1) {
            System.out.println("1! = " + 1);
            return 1;
        }
        result = fact(n - 1) * n;
        System.out.println(n + "! = " + (n - 1) + "! * " + n + " = " + result);
        return result;
    }
}