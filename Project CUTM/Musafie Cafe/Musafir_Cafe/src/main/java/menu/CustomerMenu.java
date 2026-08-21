package menu;
import dao.CartDAO;
import dao.CustomerDAO;
import dao.FoodDAO;
import dao.OrderDAO;
import dao.PaymentDAO;
import model.Cart;
import model.Customer;
import model.Food;
import model.Payment;

import java.util.Scanner;

public class CustomerMenu {

    private Scanner sc = new Scanner(System.in);

    private CustomerDAO customerDAO = new CustomerDAO();
    private FoodDAO foodDAO = new FoodDAO();
    private CartDAO cartDAO = new CartDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private PaymentDAO paymentDAO = new PaymentDAO();

    private int customerId;

    //==========================================
    // Customer Home
    //==========================================
    public void customerHome() {

        int choice;

        do {

            System.out.println("\n==================================");
            System.out.println("        CUSTOMER MENU");
            System.out.println("==================================");
            System.out.println("1. New Customer Registration");
            System.out.println("2. Existing Customer");
            System.out.println("3. Back");
            System.out.println("==================================");

            System.out.print("Enter Choice : ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    registerCustomer();
                    break;

                case 2:
                    existingCustomer();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid Choice.");

            }

        } while (true);

    }

    //==========================================
    // Register Customer
    //==========================================
    private void registerCustomer() {

        sc.nextLine();

        Customer customer = new Customer();

        System.out.print("Enter Customer Name : ");
        customer.setCustomerName(sc.nextLine());

        System.out.print("Enter Mobile Number : ");
        customer.setPhone(sc.nextLine());

        customerDAO.addCustomer(customer);

        System.out.println("Registration Successful.");
        System.out.println("Please Login Using Customer ID.");

    }

    //==========================================
    // Existing Customer
    //==========================================
    private void existingCustomer() {

        System.out.print("Enter Customer ID : ");

        customerId = sc.nextInt();

        if (customerDAO.customerExists(customerId)) {

            customerDashboard();

        } else {

            System.out.println("Customer Not Found.");

        }

    }

    //==========================================
    // Customer Dashboard
    //==========================================
    private void customerDashboard() {

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println("       CUSTOMER DASHBOARD");
            System.out.println("=================================");
            System.out.println("1. View Food Menu");
            System.out.println("2. Add To Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Update Cart");
            System.out.println("5. Remove Item");
            System.out.println("6. Place Order");
            System.out.println("7. Payment");
            System.out.println("8. Order History");
            System.out.println("9. Logout");
            System.out.println("=================================");

            System.out.print("Enter Choice : ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    foodDAO.viewFoods();
                    break;

                case 2:
                    addToCart();
                    break;

                case 3:
                    cartDAO.viewCart(customerId);
                    break;

                case 4:
                    updateCart();
                    break;

                case 5:
                    removeItem();
                    break;

                case 6:
                    placeOrder();
                    break;

                case 7:
                    payment();
                    break;

                case 8:
                    orderDAO.viewCustomerOrders(customerId);
                    break;

                case 9:
                    System.out.println("Logged Out Successfully.");
                    break;

                default:
                    System.out.println("Invalid Choice.");

            }

        } while (choice != 9);

    }

    //==========================================
    // Add To Cart
    //==========================================
    private void addToCart() {

        foodDAO.viewFoods();

        System.out.print("Enter Food ID : ");
        int foodId = sc.nextInt();

        Food food = foodDAO.searchFood(foodId);

        if (food == null) {

            System.out.println("Food Not Found.");
            return;

        }

        System.out.print("Enter Quantity : ");
        int quantity = sc.nextInt();

        double subtotal = food.getPrice() * quantity;

        Cart cart = new Cart();

        cart.setCustomerId(customerId);
        cart.setFoodId(foodId);
        cart.setQuantity(quantity);
        cart.setSubtotal(subtotal);

        cartDAO.addToCart(cart);

    }
    //==========================================
    // Update Cart
    //==========================================
    private void updateCart() {
        private void updateCart() {

            cartDAO.viewCart(customerId);

            System.out.print("Enter Cart ID : ");
            int cartId = sc.nextInt();

            System.out.print("Enter New Quantity : ");
            int quantity = sc.nextInt();

            int foodId = cartDAO.getFoodIdFromCart(cartId);

            double price = foodDAO.getFoodPrice(foodId);

            double subtotal = quantity * price;

            if (cartDAO.updateQuantity(cartId, quantity, subtotal)) {

                System.out.println("Cart Updated Successfully.");

            } else {

                System.out.println("Unable To Update Cart.");

            }
        }

    //==========================================
    // Remove Item From Cart
    //==========================================
    private void removeItem() {

        cartDAO.viewCart(customerId);

        System.out.print("Enter Cart ID : ");

        int cartId = sc.nextInt();

        boolean status = cartDAO.removeItem(cartId);

        if (status) {

            System.out.println("Item Removed Successfully.");

        } else {

            System.out.println("Unable to Remove Item.");

        }

    }

    //==========================================
    // Place Order
    //==========================================
    private void placeOrder() {

        boolean status = orderDAO.placeOrder(customerId);

        if (status) {

            System.out.println("Order Placed Successfully.");

        } else {

            System.out.println("Order Failed.");

        }

    }

    //==========================================
    // Payment
    //==========================================
    private void payment() {

        System.out.print("Enter Order ID : ");
        int orderId = sc.nextInt();

        double amount = orderDAO.getOrderTotal(orderId);

        if (amount == 0) {

            System.out.println("Invalid Order ID.");
            return;

        }

        System.out.println("Total Amount : ₹" + amount);

        System.out.println("\nPayment Mode");
        System.out.println("1. Cash");
        System.out.println("2. UPI");
        System.out.println("3. Card");

        System.out.print("Choose Payment Mode : ");
        int choice = sc.nextInt();

        String paymentMode;

        switch (choice) {

            case 1:
                paymentMode = "Cash";
                break;

            case 2:
                paymentMode = "UPI";
                break;

            case 3:
                paymentMode = "Card";
                break;

            default:
                System.out.println("Invalid Payment Mode.");
                return;

        }

        Payment payment = new Payment();

        payment.setOrderId(orderId);
        payment.setAmount(amount);
        payment.setPaymentMode(paymentMode);
        payment.setPaymentStatus("SUCCESS");

        boolean status = paymentDAO.makePayment(payment);

        if (status) {

            System.out.println("Payment Successful.");

        } else {

            System.out.println("Payment Failed.");

        }

    }
}

