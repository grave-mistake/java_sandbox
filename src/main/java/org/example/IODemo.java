package org.example;

import java.io.*;

public class IODemo {
    public static void main(String[] args) throws IOException
    {
        int select = 7;
        switch (select) {
            case 0:
                readChars();
                break;
            case 1:
                readLine();
                break;
            case 2:
                writeByte();
                break;
            case 3:
                printWriter();
                break;
            case 4:
                showFile();
                break;
            case 5:
                copyFile();
                break;
            case 6:
                showFileImproved();
                break;
            default:
                copyFileImproved();
                break;
        }
    }

    static void readChars() throws IOException {
        char c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, System.console().charset()));
        System.out.println("Enter characters, 'q' to quit.");
        do {
            c = (char) br.read();
            System.out.println(c);
        } while(c != 'q');
    }

    static void readLine() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, System.console().charset()));
        String[] str = new String[100];

        System.out.println("Enter lines of text.");
        System.out.println("Enter 'stop' to quit.");
        for(int i=0; i<100; i++) {
            str[i] = br.readLine();
            if(str[i].equals("stop")) break;
        }

        System.out.println("\nHere is your file:");
        for(int i=0; i<100; i++) {
            if(str[i].equals("stop")) break;
            System.out.println(str[i]);
        }
    }

    static void writeByte() {
        int b;
        b = 'A';
        System.out.write(b);
        System.out.write('\n');
    }

    static void printWriter() {
        PrintWriter pw = new PrintWriter(System.out, true);
        pw.println("This is a string");
        int i = -7;
        pw.println(i);
        double d = 4.5e-7;
        pw.println(d);
        pw.close();
    }

    static void showFile() {
        int i;
        String file = "Bee Movie.txt";
        FileInputStream fin = null;

        try {
            // Attempt to open the file.
            fin = new FileInputStream(file);
            // Read characters until EOF is encountered.
            do {
                i = fin.read();
                if(i != -1) System.out.print((char) i);
            } while(i != -1);
        } catch(FileNotFoundException e) {
            System.out.println("Cannot Open File");
        } catch(IOException e) {
            System.out.println("Error Reading File");
        } finally {
            // Close the file.
            try {
                if(fin != null) fin.close();
            } catch(IOException e) {
                System.out.println("Error Closing File");
            }
        }
    }

    static void showFileImproved() {
        int i;
        String file = "Bee Movie.txt";

        try(var fin = new FileInputStream(file)) {
            do {
                i = fin.read();
                if(i != -1) System.out.print((char) i);
            } while(i != -1);
        } catch(FileNotFoundException e) {
            System.out.println("Cannot Open File");
        } catch(IOException e) {
            System.out.println("An I/O Error Occurred");
        }
    }

    static void copyFile() {
        int i;
        String inputFile = "Bee Movie.txt";
        String outputFile = "The Sacred Texts.txt";
        FileInputStream fin = null;
        FileOutputStream fout = null;

        try {
            fin = new FileInputStream(inputFile);
            fout = new FileOutputStream(outputFile);
            do {
                i = fin.read();
                if(i != -1) fout.write(i);
            } while(i != -1);
        } catch(IOException e) {
            System.out.println("I/O Error: " + e);
        } finally {
            try {
                if(fin != null) fin.close();
            } catch(IOException e2) {
                System.out.println("Error Closing Input File");
            }
            try {
                if(fout != null) fout.close();
            } catch(IOException e2) {
                System.out.println("Error Closing Output File");
            }
        }
    }

    static void copyFileImproved() {
        int i;
        String inputFile = "Bee Movie.txt";
        String outputFile = "The Sacred Texts.txt";

        try (var fin = new FileInputStream(inputFile); var fout = new FileOutputStream(outputFile)) {
            do {
                i = fin.read();
                if(i != -1) fout.write(i);
            } while(i != -1);
        } catch(IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }
}
