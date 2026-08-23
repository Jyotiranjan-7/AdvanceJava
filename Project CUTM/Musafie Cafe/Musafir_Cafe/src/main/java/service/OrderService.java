package service;

import dao.OrderDAO;
import model.Order;

public class OrderService {

    private OrderDAO orderDAO;

    public OrderService() {
        orderDAO = new OrderDAO();
    }

    // ==========================================
    // Place Order
    // ==========================================
    public boolean placeOrder(int customerId) {

        if (customerId <= 0) {

            System.out.println("Invalid Customer ID.");
            return false;
        }

        return orderDAO.placeOrder(customerId);
    }

    // ==========================================
    // Search Order By ID
    // ==========================================
    public void searchOrder(int orderId) {

        if (orderId <= 0) {

            System.out.println("Invalid Order ID.");
            return;
        }

        orderDAO.searchOrder(orderId);
    }

    // ==========================================
    // View All Orders
    // ==========================================
    public void viewAllOrders() {

        orderDAO.viewAllOrders();
    }

    // ==========================================
    // View Customer Orders
    // ==========================================
    public void viewCustomerOrders(int customerId) {

        if (customerId <= 0) {

            System.out.println("Invalid Customer ID.");
            return;
        }

        orderDAO.viewCustomerOrders(customerId);
    }

    // ==========================================
    // Update Order Status
    // ==========================================
    public boolean updateOrderStatus(
            int orderId,
            String status) {

        if (orderId <= 0) {

            System.out.println("Invalid Order ID.");
            return false;
        }

        if (status == null ||
                status.trim().isEmpty()) {

            System.out.println(
                    "Order Status cannot be empty."
            );

            return false;
        }

        return orderDAO.updateOrderStatus(
                orderId,
                status
        );
    }

    // ==========================================
    // Delete Order
    // ==========================================
    public boolean deleteOrder(int orderId) {

        if (orderId <= 0) {

            System.out.println("Invalid Order ID.");
            return false;
        }

        return orderDAO.deleteOrder(orderId);
    }

    // ==========================================
    // Get Order Total
    // ==========================================
    public double getOrderTotal(int orderId) {

        if (orderId <= 0) {

            System.out.println("Invalid Order ID.");
            return 0;
        }

        return orderDAO.getOrderTotal(orderId);
    }

    // ==========================================
    // Cancel Order
    // ==========================================
    public boolean cancelOrder(int orderId) {

        if (orderId <= 0) {

            System.out.println("Invalid Order ID.");
            return false;
        }

        return orderDAO.updateOrderStatus(
                orderId,
                "CANCELLED"
        );
    }
}