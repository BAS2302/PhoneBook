import java.util.Arrays;
import java.util.Scanner;

public class PhoneBook {

    public static void main(String[] args) {
        //Добавить считывание ввода пользователя в цикле
        Scanner scanner = new Scanner(System.in);
        String[][] book = new String[0][2];
        String name = "";
        String number = "";
        boolean isCheckName = false;
        boolean isCheckNumber = false;
        String answerEnd = "";

        do {
            name = "";
            number = "";
            System.out.println("Введите имя или номер телефона!");
            String enter = scanner.nextLine();
            String checkEnter = enter.trim().replaceAll("[^0-9]", "");

            if (checkEnter.length() < 1) {
                name = enter;
                isCheckName = checkName(name);
                while (!isCheckName) {
                    if (!isCheckName) System.out.println("Введите корректное имя!");
                    name = scanner.nextLine();
                    isCheckName = checkName(name);
                }
                isCheckName = false;
                name = formatName(name);
            } else {
                number = enter;
                isCheckNumber = checkPhoneNumber(number);
                while (!isCheckNumber) {
                    if (!isCheckNumber) System.out.println("Введите корректный номер телефона!");
                    number = scanner.nextLine();
                    isCheckNumber = checkPhoneNumber(number);
                }
                isCheckNumber = false;
                number = formatPhoneNumber(number);
            }
            if (book.length == 0) {
                if (name.length() > 1) {
                    System.out.println("Такого имени нет в справочнике");
                    System.out.println("Введите номер");
                    while (!isCheckNumber) {
                        number = scanner.nextLine();
                        isCheckNumber = checkPhoneNumber(number);
                        if (!isCheckNumber) System.out.println("Введите корректный номер телефона!");
                    }
                    isCheckNumber = false;
                    number = formatPhoneNumber(number);
                    book = new String[][]{{name,number}};
                    System.out.println("Контакт добавлен в справочник");
                    System.out.println(book[0][0] + ": " + book[0][1]);
                } else {
                    System.out.println("Такого номера нет в справочнике");
                    System.out.println("Введите имя");
                    while (!isCheckName) {
                        name = scanner.nextLine();
                        isCheckName = checkName(name);
                        if (!isCheckName) System.out.println("Введите корректное имя!");
                    }
                    isCheckName = false;
                    name = formatName(name);
                    book = new String[][]{{name,number}};
                    System.out.println("Контакт добавлен в справочник");
                    System.out.println(book[0][0] + ": " + book[0][1]);
                }
            } else {
                if (name.length() > 1) {
                    int bookLen = book.length;
                    for (int i = 0; i < bookLen; i++) {
                        if (book[i][0].equals(name)) {
                            System.out.println("Такое имя есть в справочнике");
                            System.out.println(book[i][0] + ": " + book[i][1]);
                            break;
                        } else {
                            if (i == bookLen - 1) {
                                System.out.println("Такого имени нет в справочнике");
                                System.out.println("Введите номер");
                                while (!isCheckNumber) {
                                    number = scanner.nextLine();
                                    isCheckNumber = checkPhoneNumber(number);
                                    if (!isCheckNumber) System.out.println("Введите корректный номер телефона!");
                                }
                                isCheckNumber = false;
                                number = formatPhoneNumber(number);
                                book = add(book, name, number);
                                System.out.println("Контакт добавлен в справочник");
                                System.out.println(book[book.length - 1][0] + ": " + book[book.length - 1][1]);
                            }
                        }
                    }
                } else {
                    int bookLen = book.length;
                    for (int i = 0; i < bookLen; i++) {
                        if (book[i][1].equals(number)) {
                            System.out.println("Такой номер есть в справочнике");
                            System.out.println(book[i][0] + ": " + book[i][1]);
                            break;
                        } else {
                            if (i == bookLen - 1) {
                                System.out.println("Такого номера нет в справочнике");
                                System.out.println("Введите имя");
                                while (!isCheckName) {
                                    name = scanner.nextLine();
                                    isCheckName = checkName(name);
                                    if (!isCheckName) System.out.println("Введите корректное имя!");
                                }
                                isCheckName = false;
                                name = formatName(name);
                                book = add(book, name, number);
                                System.out.println("Контакт добавлен в справочник");
                                System.out.println(book[book.length - 1][0] + ": " + book[book.length - 1][1]);
                            }
                        }
                    }
                }
            }
            System.out.println("Хотите продолжить? Да/Нет");
            answerEnd = scanner.nextLine();

        } while (answerEnd.toLowerCase().equals("да"));
        list(book);
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        String cleanTelNum = phoneNumber.trim().replaceAll("[^0-9]", "");
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
        number = "+7" + " " + number.substring(1, 4) + " " + number.substring(4, 7)
                + " " + number.substring(7, 9) + " " + number.substring(9, 11);

        return number;
    }

    public static String[][] add(String[][] book, String name, String number) {
        book = addLength(book);
        book[book.length - 1][0] = name;
        book[book.length - 1][1] = number;
        return book;
        //add logic
    }

    public static String[][] addLength(String[][] book) {
        int arrayLen = book.length;

        String[][] copBook = new String[arrayLen][2];
        for (int i = 0; i < arrayLen; i++) {
            copBook[i][0] = book[i][0];
            copBook[i][1] = book[i][1];
        }
        arrayLen += 1;
        book = new String[arrayLen][2];
        for (int i = 0; i < arrayLen - 1; i++) {
            book[i][0] = copBook[i][0];
            book[i][1] = copBook[i][1];
        }
        return book;
    }

    public static void list(String[][] book) {
        for (int i = 0; i < book.length - 1; i++) {
            for (int j = i + 1; j < book.length; j++) {
                for (int k = 0; k < book[i][0].length(); k++) {
                    if (book[i][0].charAt(k) < book[j][0].charAt(k)) {
                        break;
                    } else {
                        if (book[i][0].charAt(k) == book[j][0].charAt(k)) {
                        } else {
                            String bookFirst = book[i][0];
                            String bookSecond = book[i][1];
                            book[i][0] = book[j][0];
                            book[i][1] = book[j][1];
                            book[j][0] = bookFirst;
                            book[j][1] = bookSecond;
                            break;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < book.length; i++) {
            System.out.println(book[i][0] + ": " + book[i][1]);
        }
        //print phone book
    }
}
