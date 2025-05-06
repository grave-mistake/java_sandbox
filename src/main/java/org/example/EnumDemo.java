package org.example;

import javax.crypto.SealedObject;

enum Season {
    Winter, Spring, Summer, Fall
}

enum Month {
    January(31), February(28), March(31), April(30), May(31), June(30),
    July(31), August(31), September(30), October(31), November(30), December(31);

    private int days;

    Month(int days) {
        this.days = days;
    }

    int getDays() {
        return this.days;
    }
}

public class EnumDemo {
    public static void main(String[] args)
    {
        for (Season s : Season.values()) {
            System.out.println(s);
        }

        Season s = Season.valueOf("Summer");
        System.out.println("\nChosen season: " + s + "\n");

        for (Month m : Month.values()) {
            System.out.println((m.ordinal() + 1) + ". " + m + " has " + m.getDays() + " days");
        }

        System.out.println("\nMarch has " + Month.valueOf("March").getDays() + " days");
        System.out.println("April has " + Month.April.getDays() + " days\n");

        Month m1 = Month.April;
        Month m2 = Month.September;
        Month m3 = Month.April;
        if(m1.compareTo(m2) < 0)
            System.out.println(m1 + " comes before " + m2);
        if(m1.compareTo(m2) > 0)
            System.out.println(m2 + " comes before " + m1);
        if(m1.compareTo(m3) == 0)
            System.out.println(m1 + " equals " + m3);
        if(m1.equals(m2))
            System.out.println("Error!");
        if(m1.equals(m3))
            System.out.println(m1 + " equals " + m3);
        if(m1 == m3)
            System.out.println(m1 + " == " + m3);
    }
}
