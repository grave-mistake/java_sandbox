package org.example;

record simpleRecord(String firstName, String lastName, int ID) {}

record recordWithCons(String firstName, String lastName, int ID) {
    /* // Custom canonical constructor (full form)
    public recordWithCons(String firstName, String lastName, int ID) {
        this.firstName = firstName;
        this.lastName = lastName.toUpperCase();
        this.ID = ID;
    }*/

    // Custom canonical constructor (short form) (there can be only one canonical constructor)
    public recordWithCons {
        lastName = lastName.toUpperCase();
    }

    // Non-canonical constructor - must call a canonical one
    public recordWithCons(String firstName, String middleName, String lastName, int ID) {
        this(firstName, middleName + " " + lastName, ID);
    }

    // Custom getter
    String fullName() {
        return firstName + " " + lastName;
    }
}

public class NewFeaturesDemo {
    public static void main(String[] args) {

        // Compact case lists
        char c = (char) (33 + Math.random() * (127 - 33));
        switch (c) {
            case '1', '2', '3', '4', '5', '6', '7', '8', '9', '0':
                System.out.println(c + " is a digit");
                break;
            case '.', ',', '!', '$', '&', '?', ':', ';', '+':
                System.out.println(c + " is a particular symbol");
                break;
            default:
                System.out.println(c + " is some kind of symbol");
                break;
        }

        // Switch with ->
        switch (c) {
            case '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' -> {
                System.out.println("This case uses a block of code");
                System.out.println("Bottom text");
            }
            case '.', ',', '!', '$', '&', '?', ':', ';', '+' -> System.out.println("This case doesn't use a block of code");
            default -> {
                System.out.println("This case also uses a block of code");
                System.out.println("Your ad here");
            }
        }

        // Switch that returns a value
        int x = (int) (Math.random() * 10);
        int y = switch (x) {
            case 1, 2, 3, 4, 5:
                yield 1;
            case 6, 7, 8, 9, 0:
                yield 2;
            default:
                yield 0;
        };

        // Switch with -> that returns a value
        int z = switch (x) {
            case 1, 3, 5, 7, 9 -> 1;
            case 2, 4, 6, 8, 0 -> {
                if (Math.random() < 0.5) {
                    yield 2;
                }
                yield 3;
            }
            default -> 0;
        };
        System.out.println(x + " " + y + " " + z + "\n");

        // Text blocks
        String textblock = """
                Beginning with JDK 15, Java provides support for text blocks. A text block is a new kind of
                  literal that is comprised of a sequence of characters that can occupy more than one line. A text
                    block reduces the tedium programmers often face when creating long string literals because
                  newline characters can be used in a text block without the need for the \\n escape sequence.
                Furthermore, tab and double quote characters can also be entered directly, without using an
                  escape sequence, and the indentation of a multiline string can be preserved. Although text
                    blocks may, at first, seem to be a relatively small addition to Java, they may well become one
                  of the most popular features.
                Bottom Text
                """;
        System.out.println(textblock);

        textblock = """
                Putting '\\' at the end of the line \
                will remove the line break
                Also you can "quote" things here without a need for '\\'""";
        System.out.println(textblock + "\n");

        // Records
        simpleRecord employee1 = new simpleRecord("John", "Smith", 23);
        System.out.println("Employee #" + employee1.ID() + " is " + employee1.firstName() + " " + employee1.lastName());

        recordWithCons employee2 = new recordWithCons("Jane", "Smith", 25);
        System.out.println("Employee #" + employee2.ID() + " is " + employee2.firstName() + " " + employee2.lastName());
        employee2 = new recordWithCons("Sonic", "the", "Hedgehog", 28);
        System.out.println("Employee #" + employee2.ID() + " is " + employee2.firstName() + " " + employee2.lastName());
        System.out.println("Full name: " + employee2.fullName() + "\n");

        // Patterned instanceof
        Object object = (Math.random() < 0.5) ? Integer.valueOf(x) : new Box(3);
        if (object instanceof Integer integerObject) {
            System.out.println("Object refers to an integer: " + integerObject);
        } else {
            System.out.println("Object refers to a box: " + object);
        }

        if ((object instanceof Integer integerObject) && (integerObject > 3)) {
            System.out.println("Object refers to an integer that is larger than 3");
        } else {
            System.out.println("Nothing to say");
        }

        // Patterned switch (we're going into uncharted territory here)
        switch (object) {
            case Integer integerObject -> System.out.println("Object refers to an integer: " + integerObject);
            case Box boxObject -> System.out.println("Object refers to a box: " + boxObject);
            default -> System.out.println("Nothing to say");
        }
    }
}
