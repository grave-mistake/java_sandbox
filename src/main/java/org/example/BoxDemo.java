package org.example;

class Box implements Comparable<Box> {
    double width = 1;
    double height = 1;
    double depth = 1;

    Box(Box ob) {
        width = ob.width;
        height = ob.height;
        depth = ob.depth;
    }

    Box(double width, double height, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    Box() {
        // Empty constructor
    }

    Box(double side) {
        this(side, side, side);
    }

    double volume() {
        return width * height * depth;
    }

    @Override
    public String toString() {
        return width + " × " + height + " × " + depth;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Box box = (Box) obj;
        return this.width == box.width && this.height == box.height && this.depth == box.depth;
    }

    @Override
    public int compareTo(Box other) {
        return Double.compare(this.volume(), other.volume());
    }
}

class WeightedBox extends Box {
    double weight = 1;

    WeightedBox(WeightedBox ob) {
        super(ob);
        this.weight = ob.weight;
    }

    WeightedBox(double width, double height, double depth, double weight) {
        super(width, height, depth);
        this.weight = weight;
    }

    WeightedBox() {
        // Empty constructor
    }

    @Override
    public String toString() {
        return width + " × " + height + " × " + depth + ", m: " + weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        WeightedBox box = (WeightedBox) obj;
        return this.weight == box.weight;
    }

    WeightedBox(double side, double weight) {
        width = height = depth = side;
        this.weight = weight;
    }

    double density() {
        return weight / super.volume();
    }
}

class BoxDemo {
    public static void main(String[] args) {
        Box mybox1 = new Box(10, 20, 15);
        Box mybox2 = new Box(mybox1);
        Box mybox3 = new Box(5);
        Box mybox4 = new Box();

        System.out.println(mybox1);
        System.out.println(mybox2);
        System.out.println(mybox3);
        System.out.println(mybox4);

        System.out.println(mybox1.equals(new Box(10, 20, 15)));
        System.out.println(mybox1.equals(mybox2));
        System.out.println(mybox1.equals(mybox3));
        System.out.println(mybox1.equals(mybox4));

        WeightedBox myweightedbox1 = new WeightedBox(10, 20, 15, 10);
        WeightedBox myweightedbox2 = new WeightedBox(myweightedbox1);
        WeightedBox myweightedbox3 = new WeightedBox(5, 5);
        WeightedBox myweightedbox4 = new WeightedBox();
        double density;

        System.out.println(myweightedbox1);
        density = myweightedbox1.density();
        System.out.println("denisty is " + density);

        System.out.println(myweightedbox2);
        density = myweightedbox2.density();
        System.out.println("denisty is " + density);

        System.out.println(myweightedbox3);
        density = myweightedbox3.density();
        System.out.println("denisty is " + density);

        System.out.println(myweightedbox4);
        density = myweightedbox4.density();
        System.out.println("denisty is " + density);

        System.out.println(myweightedbox1.equals(new WeightedBox(10, 20, 15, 10)));
        System.out.println(myweightedbox1.equals(myweightedbox2));
        System.out.println(myweightedbox1.equals(myweightedbox3));
        System.out.println(myweightedbox1.equals(myweightedbox4));

        System.out.println(mybox1.equals(myweightedbox1));
        System.out.println(myweightedbox1.equals(mybox1));
        Box temp = new WeightedBox(myweightedbox1);
        System.out.println(mybox1.equals(temp));
        System.out.println(temp.equals(mybox1));

        System.out.println("Comparison result of mybox3 and mybox2: " + mybox3.compareTo(mybox2));
        System.out.println("Comparison result of mybox3 and mybox3: " + mybox3.compareTo(mybox3));
        System.out.println("Comparison result of mybox3 and mybox4: " + mybox3.compareTo(mybox4));
        System.out.println("Comparison result of mybox3 and myweightedbox1: " + mybox3.compareTo(myweightedbox1));
        System.out.println("Comparison result of mybox3 and myweightedbox3: " + mybox3.compareTo(myweightedbox3));
        System.out.println("Comparison result of mybox3 and myweightedbox4: " + mybox3.compareTo(myweightedbox4));
    }
}