import java.util.Scanner;

public class PhoneBook {

    public static void main(String[] args) {
        //Добавить считывание ввода пользователя в цикле
        System.out.println("Введите имя в формате ФИО");
        Scanner scanner = new Scanner(System.in);
        checkName(scanner.nextLine());
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        return true;
    }

    public static boolean checkName(String name) {
        String[] arrayName = name.trim().split(" ");
        if (arrayName.length == 3) {
            int famLen = arrayName[0].length();
            if (arrayName[0].charAt(famLen-1) == 'а') {
                arrayName[0] = arrayName[0].substring(0, famLen-1);
            }
            String famLastTwo = arrayName[0].substring(famLen - 2, famLen);
            switch (famLastTwo) {
                case "ов":
                case "ев":
                case "ин":
                case "ын":
                case "их":
                case "ыч":
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
        return "";
    }

    public static String formatPhoneNumber(String number) {
        return "";
    }

    public static void add(String[][] book, String name, String number) {
        //add logic
    }

    public static void list(String[][] book) {
        //print phone book
    }
}
