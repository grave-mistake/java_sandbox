package org.example;

class CustomException extends Exception {
    private int detail;
    CustomException(int a) {
        detail = a;
    }
    public String toString() {
        return "CustomException[" + detail + "]";
    }
}

public class ExceptionDemo {
    class ThrowDemo {
        static void demoproc() {
            try {
                throw new NullPointerException("demo");
            } catch (NullPointerException e) {
                System.out.println("Caught inside demoproc.");
                throw e; // rethrow the exception
            }
        }

        private static void throwDemo() {
            System.out.println("> throwDemo():");
            try {
                demoproc();
            } catch (NullPointerException e) {
                System.out.println("Recaught: " + e);
            }
        }
    }

    class ThrowsDemo {
        static void throwOne() throws IllegalAccessException {
            System.out.println("Inside throwOne.");
            throw new IllegalAccessException("demo");
        }

        private static void throwsDemo() {
            System.out.println("> throwsDemo():");
            try {
                throwOne();
            } catch (IllegalAccessException e) {
                System.out.println("Caught " + e);
            }
        }
    }

    class FinallyDemo {
        // Throw an exception out of the method.
        static void procA() {
            try {
                System.out.println("inside procA");
                throw new RuntimeException("demo");
            } finally {
                System.out.println("procA's finally");
            }
        }
        // Return from within a try block.
        static void procB() {
            try {
                System.out.println("inside procB");
                return;
            } finally {
                System.out.println("procB's finally");
            }
        }
        // Execute a try block normally.
        static void procC() {
            try {
                System.out.println("inside procC");
            } finally {
                System.out.println("procC's finally");
            }
        }
        public static void finallyDemo() {
            System.out.println("> finallyDemo():");
            try {
                procA();
            } catch (Exception e) {
                System.out.println("Exception caught");
            }
            procB();
            procC();
        }
    }

    class CustomExceptionDemo {
        static void compute(int a) throws CustomException {
            System.out.println("Called compute(" + a + ")");
            if(a > 10)
                throw new CustomException(a);
            System.out.println("Normal exit");
        }

        public static void exceptionDemo() {
            System.out.println("> exceptionDemo():");
            try {
                compute(1);
                compute(20);
            } catch (CustomException e) {
                System.out.println("Caught " + e);
            }
        }
    }

    public static void main(String[] args) {
        ThrowDemo.throwDemo();
        ThrowsDemo.throwsDemo();
        FinallyDemo.finallyDemo();
        CustomExceptionDemo.exceptionDemo();
    }
}