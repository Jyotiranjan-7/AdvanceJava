package dao;

import config.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private Connection con;

    public OrderDAO() {
        con = DBConnection.getConnection();
    }

    // =========================================================
    // Place Order Using Transaction + Batch Processing
    // =========================================================
    public boolean placeOrder(int customerId) {

        String cartQuery =
                "SELECT c.food_id, c.quantity, f.price, c.subtotal " +
                        "FROM cart c " +
                        "JOIN food f ON c.food_id = f.food_id " +
                        "WHERE c.customer_id = ?";

        String insertOrder =
                "INSERT INTO orders " +
                        "(customer_id, total_amount, order_date, status) " +
                        "VALUES (?, ?, NOW(), ?)";

        String insertOrderItem =
                "INSERT INTO order_item " +
                        "(order_id, food_id, quantity, price) " +
                        "VALUES (?, ?, ?, ?)";

        String deleteCart =
                "DELETE FROM cart WHERE customer_id = ?";

        boolean oldAutoCommit = true;

        try {

            oldAutoCommit = con.getAutoCommit();

            // Start Transaction
            con.setAutoCommit(false);

            // =================================================
            // Step 1: Read Cart Items
            // =================================================

            List<CartItemData> cartItems = new ArrayList<>();

            double total = 0;

            try (PreparedStatement cartPs =
                         con.prepareStatement(cartQuery)) {

                cartPs.setInt(1, customerId);

                try (ResultSet rs = cartPs.executeQuery()) {

                    while (rs.next()) {

                        int foodId = rs.getInt("food_id");
                        int quantity = rs.getInt("quantity");
                        double price = rs.getDouble("price");
                        double subtotal = rs.getDouble("subtotal");

                        cartItems.add(
                                new CartItemData(
                                        foodId,
                                        quantity,
                                        price,
                                        subtotal
                                )
                        );

                        total += subtotal;
                    }
                }
            }

            // =================================================
            // Step 2: Check Empty Cart
            // =================================================

            if (cartItems.isEmpty()) {

                System.out.println("Cart is Empty.");

                con.rollback();

                return false;
            }

            // =================================================
            // Step 3: Insert Order
            // =================================================

            int orderId;

            try (PreparedStatement orderPs =
                         con.prepareStatement(
                                 insertOrder,
                                 Statement.RETURN_GENERATED_KEYS)) {

                orderPs.setInt(1, customerId);
                orderPs.setDouble(2, total);
                orderPs.setString(3, "PLACED");

                orderPs.executeUpdate();

                try (ResultSet keyRs =
                             orderPs.getGeneratedKeys()) {

                    if (keyRs.next()) {

                        orderId = keyRs.getInt(1);

                    } else {

                        throw new SQLException(
                                "Unable to generate Order ID."
                        );
                    }
                }
            }

            // =================================================
            // Step 4: Insert Order Items Using Batch
            // =================================================

            try (PreparedStatement itemPs =
                         con.prepareStatement(insertOrderItem)) {

                for (CartItemData item : cartItems) {

                    itemPs.setInt(1, orderId);
                    itemPs.setInt(2, item.foodId);
                    itemPs.setInt(3, item.quantity);
                    itemPs.setDouble(4, item.price);

                    itemPs.addBatch();
                }

                itemPs.executeBatch();
            }

            // =================================================
            // Step 5: Clear Customer Cart
            // =================================================

            try (PreparedStatement deletePs =
                         con.prepareStatement(deleteCart)) {

                deletePs.setInt(1, customerId);

                deletePs.executeUpdate();
            }

            // =================================================
            // Step 6: Commit Transaction
            // =================================================

            con.commit();

            System.out.println("\n====================================");
            System.out.println("       ORDER PLACED SUCCESSFULLY");
            System.out.println("====================================");
            System.out.println("Order ID    : " + orderId);
            System.out.println("Total Bill  : ₹" + total);
            System.out.println("Status      : PLACED");
            System.out.println("====================================");

            return true;

        } catch (SQLException e) {

            // =================================================
            // Rollback Transaction
            // =================================================

            try {

                con.rollback();

                System.out.println("\nTransaction Failed.");
                System.out.println("Rollback Successful.");

            } catch (SQLException rollbackException) {

                System.out.println("Rollback Failed.");
                rollbackException.printStackTrace();
            }

            e.printStackTrace();

            return false;

        } finally {

            // =================================================
            // Restore Auto Commit
            // =================================================

            try {

                con.setAutoCommit(oldAutoCommit);

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    // =========================================================
    // View All Orders
    // =========================================================
    public void viewAllOrders() {

        String sql =
                "SELECT * FROM orders ORDER BY order_id DESC";

        try (PreparedStatement ps =
                     con.prepareStatement(sql);

             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n================ ALL ORDERS ================");

            System.out.printf(
                    "%-10s %-12s %-15s %-22s %-15s%n",
                    "Order ID",
                    "Customer",
                    "Amount",
                    "Order Date",
                    "Status"
            );

            System.out.println(
                    "---------------------------------------------------------------"
            );

            while (rs.next()) {

                System.out.printf(
                        "%-10d %-12d %-15.2f %-22s %-15s%n",
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getDouble("total_amount"),
                        rs.getTimestamp("order_date"),
                        rs.getString("status")
                );
            }

            System.out.println(
                    "==============================================================="
            );

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // View Customer Orders
    // =========================================================
    public void viewCustomerOrders(int customerId) {

        String sql =
                "SELECT * FROM orders " +
                        "WHERE customer_id = ? " +
                        "ORDER BY order_id DESC";

        try (PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {

                System.out.println("\n========== CUSTOMER ORDERS ==========");

                boolean found = false;

                while (rs.next()) {

                    found = true;

                    System.out.println("-------------------------------------");
                    System.out.println(
                            "Order ID     : " +
                                    rs.getInt("order_id")
                    );

                    System.out.println(
                            "Total Amount : ₹" +
                                    rs.getDouble("total_amount")
                    );

                    System.out.println(
                            "Order Date   : " +
                                    rs.getTimestamp("order_date")
                    );

                    System.out.println(
                            "Status       : " +
                                    rs.getString("status")
                    );
                }

                if (!found) {

                    System.out.println("No Orders Found.");
                }

                System.out.println("-------------------------------------");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // Search Order
    // =========================================================
    public void searchOrder(int orderId) {

        String sql =
                "SELECT * FROM orders WHERE order_id = ?";

        try (PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    System.out.println("\n=========== ORDER DETAILS ===========");

                    System.out.println(
                            "Order ID     : " +
                                    rs.getInt("order_id")
                    );

                    System.out.println(
                            "Customer ID  : " +
                                    rs.getInt("customer_id")
                    );

                    System.out.println(
                            "Amount       : ₹" +
                                    rs.getDouble("total_amount")
                    );

                    System.out.println(
                            "Order Date   : " +
                                    rs.getTimestamp("order_date")
                    );

                    System.out.println(
                            "Status       : " +
                                    rs.getString("status")
                    );

                    System.out.println(
                            "====================================="
                    );

                } else {

                    System.out.println("Order Not Found.");
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // Update Order Status
    // =========================================================
    public boolean updateOrderStatus(
            int orderId,
            String status) {

        String sql =
                "UPDATE orders SET status = ? " +
                        "WHERE order_id = ?";

        try (PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);

            int row = ps.executeUpdate();

            if (row > 0) {

                System.out.println(
                        "Order Status Updated Successfully."
                );

                return true;
            }

            System.out.println("Order Not Found.");

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    // =========================================================
    // Delete Order
    // =========================================================
    public boolean deleteOrder(int orderId) {

        String deleteItems =
                "DELETE FROM order_item WHERE order_id = ?";

        String deleteOrder =
                "DELETE FROM orders WHERE order_id = ?";

        try {

            con.setAutoCommit(false);

            // First delete order items
            // because order_item references orders

            try (PreparedStatement itemPs =
                         con.prepareStatement(deleteItems)) {

                itemPs.setInt(1, orderId);
                itemPs.executeUpdate();
            }

            // Then delete order

            try (PreparedStatement orderPs =
                         con.prepareStatement(deleteOrder)) {

                orderPs.setInt(1, orderId);

                int row = orderPs.executeUpdate();

                if (row > 0) {

                    con.commit();

                    System.out.println(
                            "Order Deleted Successfully."
                    );

                    return true;

                } else {

                    con.rollback();

                    System.out.println(
                            "Order Not Found."
                    );
                }
            }

        } catch (SQLException e) {

            try {

                con.rollback();

            } catch (SQLException rollbackException) {

                rollbackException.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {

                con.setAutoCommit(true);

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return false;
    }

    // =========================================================
    // Get Order Total
    // =========================================================
    public double getOrderTotal(int orderId) {

        String sql =
                "SELECT total_amount " +
                        "FROM orders " +
                        "WHERE order_id = ?";

        try (PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return rs.getDouble("total_amount");
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 0;
    }

    // =========================================================
    // Check Whether Order Exists
    // =========================================================
    public boolean orderExists(int orderId) {

        String sql =
                "SELECT order_id " +
                        "FROM orders " +
                        "WHERE order_id = ?";

        try (PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {

                return rs.next();
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    // =========================================================
    // Inner Class For Temporary Cart Data
    // =========================================================
    private static class CartItemData {

        private int foodId;
        private int quantity;
        private double price;
        private double subtotal;

        public CartItemData(
                int foodId,
                int quantity,
                double price,
                double subtotal) {

            this.foodId = foodId;
            this.quantity = quantity;
            this.price = price;
            this.subtotal = subtotal;
        }
    }
}