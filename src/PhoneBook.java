import java.util.Scanner;

public class PhoneBook {

    public static void main(String[] args) {
        //Добавить считывание ввода пользователя в цикле
        System.out.println("Введите имя в формате ФИО");
        Scanner scanner = new Scanner(System.in);
        String name = "";
        boolean isCheckName = false;
        while (!isCheckName) {
            name = scanner.nextLine();
            isCheckName = checkName(name);
            if (!isCheckName) System.out.println("Введите корректное имя!");
        }
        System.out.println(formatName(name));

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

        //add logic
    }

    public static void list(String[][] book) {
        for (int i = 0; i < book.length; i++) {
            System.out.println(book[i][0] + ": " + book[i][1]);
        }
        //print phone book
    }
}
