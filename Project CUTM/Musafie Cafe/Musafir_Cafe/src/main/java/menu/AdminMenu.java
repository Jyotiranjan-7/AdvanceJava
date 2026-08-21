package menu;

import dao.AdminDAO;
import dao.CustomerDAO;
import dao.FoodDAO;
import dao.OrderDAO;
import dao.PaymentDAO;
import model.Food;

import java.util.Scanner;

public class AdminMenu {

    private Scanner sc = new Scanner(System.in);

    private AdminDAO adminDAO = new AdminDAO();
    private FoodDAO foodDAO = new FoodDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private PaymentDAO paymentDAO = new PaymentDAO();

    //=================================
    // Admin Login
    //=================================
    public void login(String username, String password) {

        boolean status = adminDAO.adminLogin(username, password);

        if (status) {

            System.out.println("\nLogin Successful.");

            adminDashboard();

        } else {

            System.out.println("\nInvalid Username or Password.");

        }

    }

    //=================================
    // Dashboard
    //=================================
    public void adminDashboard() {

        int choice;

        do {

            System.out.println("\n===================================");
            System.out.println("          ADMIN DASHBOARD");
            System.out.println("===================================");
            System.out.println("1. Add Food");
            System.out.println("2. View Food");
            System.out.println("3. Update Food");
            System.out.println("4. Delete Food");
            System.out.println("5. View Customers");
            System.out.println("6. View Orders");
            System.out.println("7. Update Order Status");
            System.out.println("8. View Payments");
            System.out.println("9. Logout");
            System.out.println("===================================");

            System.out.print("Enter Choice : ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addFood();
                    break;

                case 2:
                    foodDAO.viewFoods();
                    break;

                case 3:
                    updateFood();
                    break;

                case 4:
                    deleteFood();
                    break;

                case 5:
                    customerDAO.viewCustomers();
                    break;

                case 6:
                    orderDAO.viewAllOrders();
                    break;

                case 7:
                    updateOrderStatus();
                    break;

                case 8:
                    paymentDAO.viewPayments();
                    break;

                case 9:
                    System.out.println("Logged Out Successfully.");
                    break;

                default:
                    System.out.println("Invalid Choice.");

            }

        } while (choice != 9);

    }

    //=================================
    // Add Food
    //=================================
    private void addFood() {

        sc.nextLine();

        System.out.print("Food Name : ");
        String name = sc.nextLine();

        System.out.print("Price : ");
        double price = sc.nextDouble();

        Food food = new Food();

        food.setFoodName(name);
        food.setPrice(price);

        foodDAO.addFood(food);

    }

    //=================================
    // Update Food
    //=================================
    private void updateFood() {

        System.out.print("Enter Food ID : ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("New Food Name : ");
        String name = sc.nextLine();

        System.out.print("New Price : ");
        double price = sc.nextDouble();

        Food food = new Food();

        food.setFoodId(id);
        food.setFoodName(name);
        food.setPrice(price);

        foodDAO.updateFood(food);

    }

    //=================================
    // Delete Food
    //=================================
    private void deleteFood() {

        System.out.print("Enter Food ID : ");

        int id = sc.nextInt();

        foodDAO.deleteFood(id);

    }
    //=================================
    // Update Order Status
    //=================================
    private void updateOrderStatus() {

        System.out.print("Enter Order ID : ");
        int orderId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter New Status : ");
        String status = sc.nextLine();

        boolean updated = orderDAO.updateOrderStatus(orderId, status);

        if (updated) {
            System.out.println("Order Status Updated Successfully.");
        } else {
            System.out.println("Unable to Update Order Status.");
        }

    }

    //=================================
    // Search Customer
    //=================================
    private void searchCustomer() {

        System.out.print("Enter Customer ID : ");
        int customerId = sc.nextInt();

        if (customerDAO.searchCustomer(customerId) != null) {

            System.out.println("\n========== CUSTOMER DETAILS ==========");

            System.out.println("Customer ID   : "
                    + customerDAO.searchCustomer(customerId).getCustomerId());

            System.out.println("Customer Name : "
                    + customerDAO.searchCustomer(customerId).getCustomerName());

            System.out.println("Phone Number  : "
                    + customerDAO.searchCustomer(customerId).getPhone());

        } else {

            System.out.println("Customer Not Found.");

        }

    }

    //=================================
    // Search Food
    //=================================
    private void searchFood() {

        System.out.print("Enter Food ID : ");

        int foodId = sc.nextInt();

        Food food = foodDAO.searchFood(foodId);

        if (food != null) {

            System.out.println("\n========== FOOD DETAILS ==========");

            System.out.println("Food ID   : " + food.getFoodId());
            System.out.println("Food Name : " + food.getFoodName());
            System.out.println("Price     : ₹" + food.getPrice());

        } else {

            System.out.println("Food Not Found.");

        }

    }

    //=================================
    // Search Order
    //=================================
    private void searchOrder() {

        System.out.print("Enter Order ID : ");

        int orderId = sc.nextInt();

        orderDAO.searchOrder(orderId);

    }

    //=================================
    // Search Payment
    //=================================
    private void searchPayment() {

        System.out.print("Enter Payment ID : ");

        int paymentId = sc.nextInt();

        paymentDAO.searchPayment(paymentId);

    }
}
