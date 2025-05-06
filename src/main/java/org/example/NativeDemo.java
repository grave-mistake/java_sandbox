package org.example;

public class NativeDemo {
    public static native void sayHello();

    static {
        String path = System.getProperty("user.dir") + "/native/NativeDemoSolution/x64/Release/NativeLib.dll";
        System.load(path);
    }

    public static void main(String[] args) {
        sayHello();
    }
}