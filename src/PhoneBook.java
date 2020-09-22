import java.util.Arrays;
import java.util.Scanner;

public class PhoneBook {
    static int stringNum = 0;

    public static void main(String[] args) {
        //Добавить считывание ввода пользователя в цикле
        Scanner scanner = new Scanner(System.in);

        int columnNum = 2;
        String[][] book = new String[stringNum][columnNum];
        String name = "";
        String number = "";
        boolean isCheckName = false;
        boolean isCheckNumber = false;
        String answerEnd = "";
        String answer = "";
        do {
            System.out.println("Желаете ввести имя или номер телефона? " +
                    "Введите да если хотите ввести Имя и нет, если номер");
            answer = scanner.nextLine();

            if (answer.toLowerCase() == "да") {
                System.out.println("Введите имя:");
                while (!isCheckName) {
                    name = scanner.nextLine();
                    isCheckName = checkName(name);
                    if (!isCheckName) System.out.println("Введите корректное имя!");
                }
                for (int i = 0; i < stringNum; i++) {
                    if (book[i][0] == name) {
                        System.out.println("Это имя есть в справочнике!" + name + ": " + book[i][1]);
                    } else if (i == stringNum - 1) {
                        System.out.println("Такого имени нет в справочнике! Введите номер телефона");
                        while (!isCheckNumber) {
                            number = scanner.nextLine();
                            isCheckNumber = checkPhoneNumber(number);
                            if (!isCheckNumber) System.out.println("Введите корректный номер телефона!");
                        }
                        add(book, name, number);
/*
                        stringNum += 1;
                        book = Arrays.copyOf(book, stringNum);
                        book[stringNum - 1][0] = name;
                        book[stringNum - 1][1] = number;
*/
                    }
                }

            } else {
                System.out.println("Введите номер:");
                while (!isCheckNumber) {
                    number = scanner.nextLine();
                    isCheckNumber = checkPhoneNumber(number);
                    if (!isCheckNumber) System.out.println("Введите корректный номер телефона!");
                }
                for (int i = 0; i < stringNum; i++) {
                    if (book[i][1] == number) {
                        System.out.println("Этот номер есть в справочнике!" + book[i][0] + ": " + number);
                    } else if (i == stringNum - 1) {
                        System.out.println("Такого номера нет в справочнике! Введите имя");
                        while (!isCheckName) {
                            name = scanner.nextLine();
                            isCheckName = checkName(name);
                            if (!isCheckName) System.out.println("Введите корректное имя!");
                        }


                        add(book, name, number);
/*
                        book = Arrays.copyOf(book, stringNum);
                        book[stringNum - 1][0] = name;
                        book[stringNum - 1][1] = number;
*/
                    }
                }


            }

            System.out.println("Хотите продолжить? Да/Нет");
            answerEnd = scanner.nextLine();

        } while (answerEnd.toLowerCase() == "нет");


    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        String cleanTelNum = phoneNumber.replaceAll("[^0-9]", "");
        int numLength = cleanTelNum.length();
        if (numLength == 11) {
            return true;
        } else return false;
    }

    public static boolean checkName(String name) {
        String[] arrayName = name.trim().split(" ");
        if (arrayName.length == 3) {
            int famLen = arrayName[0].length();
            if (arrayName[0].charAt(famLen-1) == 'а') {
                arrayName[0] = arrayName[0].substring(0, famLen-1);
                famLen = arrayName[0].length();
            }
            String famLastTwo = arrayName[0].substring(famLen - 2, famLen);
            switch (famLastTwo) {
                case "ов":
                case "ев":
                case "ин":
                case "ын":
                case "их":
                case "ых":
                    return true;
            }
            String famLastFore = arrayName[0].substring(famLen - 4, famLen);
            switch (famLastFore) {
                case "ский":
                case "ская":
                case "цкий":
                case "цкая":
                    return true;
            }
        }
        return false;
    }

    public static String formatName(String name) {
        String[] words = name.trim().split(" ");
        String result = "";
        for (int i = 0; i < words.length; i++) {
            char firstChar = words[i].charAt(0);
            if (!Character.isUpperCase(firstChar)) {
                result += Character.toUpperCase(firstChar) + words[i].substring(1) + " ";
            } else {
                result += words[i] + " ";
            }
        }
        return result;
    }

    public static String formatPhoneNumber(String number) {
        number = number.replaceAll("[^0-9]", "");
        number = "+7" + number.substring(1, 4) + " " + number.substring(4, 7)
                + " " + number.substring(7, 9) + " " + number.substring(9, 11);

        return number;
    }

    public static void add(String[][] book, String name, String number) {
        stringNum += 1;
        book = Arrays.copyOf(book, stringNum);
        book[stringNum - 1][0] = name;
        book[stringNum - 1][1] = number;


        //add logic
    }

    public static void list(String[][] book) {
        for (int i = 0; i < book.length; i++) {
            System.out.println(book[i][0] + ": " + book[i][1]);
        }
        //print phone book
    }
}
