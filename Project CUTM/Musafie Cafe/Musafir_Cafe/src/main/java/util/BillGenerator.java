package util;

import model.Order;
import model.OrderItem;

import java.util.List;

public class BillGenerator {

    public static void generateBill(
            Order order,
            List<OrderItem> orderItems) {

        System.out.println("\n==============================================");
        System.out.println("              MUSAFIR CAFE");
        System.out.println("              CUSTOMER BILL");
        System.out.println("==============================================");

        System.out.println("Order ID    : " + order.getOrderId());
        System.out.println("Customer ID : " + order.getCustomerId());

        System.out.println("----------------------------------------------");

        System.out.printf(
                "%-15s %-10s %-12s%n",
                "Food ID",
                "Quantity",
                "Amount"
        );

        System.out.println("----------------------------------------------");

        double total = 0;

        for (OrderItem item : orderItems) {

            double amount =
                    item.getQuantity() * item.getPrice();

            total += amount;

            System.out.printf(
                    "%-15d %-10d ₹%-10.2f%n",
                    item.getFoodId(),
                    item.getQuantity(),
                    amount
            );
        }

        System.out.println("----------------------------------------------");

        System.out.printf(
                "%-30s ₹%.2f%n",
                "Total Amount:",
                total
        );

        System.out.println("==============================================");
        System.out.println("          Thank You! Visit Again!");
        System.out.println("==============================================");
    }
}