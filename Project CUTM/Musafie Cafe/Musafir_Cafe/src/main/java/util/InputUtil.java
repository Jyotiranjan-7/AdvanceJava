package util;

import java.util.Scanner;

public class InputUtil {

    private static final Scanner scanner = new Scanner(System.in);

    private InputUtil() {
        // Utility class - prevent object creation
    }

    public static String getString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static int getInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED +
                        "Please enter a valid number." +
                        ConsoleColors.RESET);
            }
        }
    }

    public static double getDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED +
                        "Please enter a valid amount." +
                        ConsoleColors.RESET);
            }
        }
    }

    public static long getLong(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED +
                        "Please enter a valid number." +
                        ConsoleColors.RESET);
            }
        }
    }

    public static boolean getBoolean(String message) {
        while (true) {
            String input = getString(message);

            if (input.equalsIgnoreCase("yes") ||
                    input.equalsIgnoreCase("y")) {
                return true;
            }

            if (input.equalsIgnoreCase("no") ||
                    input.equalsIgnoreCase("n")) {
                return false;
            }

            System.out.println(ConsoleColors.RED +
                    "Please enter yes/no." +
                    ConsoleColors.RESET);
        }
    }

    public static void closeScanner() {
        scanner.close();
    }
}
