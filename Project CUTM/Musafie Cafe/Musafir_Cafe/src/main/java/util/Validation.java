package util;

public class Validation {

    private Validation() {
        // Utility class - prevent object creation
    }

    public static boolean isValidName(String name) {
        return name != null &&
                !name.trim().isEmpty() &&
                name.matches("[a-zA-Z ]+");
    }

    public static boolean isValidEmail(String email) {
        return email != null &&
                email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean isValidPhone(String phone) {
        return phone != null &&
                phone.matches("[6-9][0-9]{9}");
    }

    public static boolean isValidPassword(String password) {
        return password != null &&
                password.length() >= 6;
    }

    public static boolean isValidPrice(double price) {
        return price > 0;
    }

    public static boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }

    public static boolean isValidId(int id) {
        return id > 0;
    }

    public static boolean isNotEmpty(String value) {
        return value != null &&
                !value.trim().isEmpty();
    }
}
