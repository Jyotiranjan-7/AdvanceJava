package dao;

import config.DBConnection;
import java.sql.*;

    public class OrderDAO {

        private Connection con;

        public OrderDAO() {
            con = DBConnection.getConnection();
        }

        // Place Order using Transaction

        public boolean placeOrder(int customerId) {

            String cartQuery =
                    "SELECT c.food_id, " +
                            "c.quantity, " +
                            "f.price, " +
                            "c.subtotal " +
                            "FROM cart c " +
                            "JOIN food f ON c.food_id = f.food_id " +
                            "WHERE c.customer_id = ?";

            String insertOrder =
                    "INSERT INTO orders(customer_id,total_amount,order_date,status) VALUES(?,?,NOW(),?)";

            String insertOrderItem =
                    "INSERT INTO order_item(order_id,food_id,quantity,price) VALUES(?,?,?,?)";

            String deleteCart =
                    "DELETE FROM cart WHERE customer_id=?";

            try {

                con.setAutoCommit(false);

                // Calculate Total Amount

                double total = 0;

                PreparedStatement cartPs = con.prepareStatement(cartQuery);
                cartPs.setInt(1, customerId);

                ResultSet cartRs = cartPs.executeQuery();

                while (cartRs.next()) {
                    total += cartRs.getDouble("subtotal");
                }

                if (total == 0) {
                    System.out.println("Cart is Empty.");
                    return false;
                }

                // Insert Order

                PreparedStatement orderPs =
                        con.prepareStatement(insertOrder,
                                Statement.RETURN_GENERATED_KEYS);

                orderPs.setInt(1, customerId);
                orderPs.setDouble(2, total);
                orderPs.setString(3, "PLACED");

                orderPs.executeUpdate();

                ResultSet key = orderPs.getGeneratedKeys();

                int orderId = 0;

                if (key.next()) {
                    orderId = key.getInt(1);
                }

                // Insert Order Items

                cartRs.beforeFirst();

                while (cartRs.next()) {

                    PreparedStatement itemPs =
                            con.prepareStatement(insertOrderItem);

                    itemPs.setInt(1, orderId);
                    itemPs.setInt(2, cartRs.getInt("food_id"));
                    itemPs.setInt(3, cartRs.getInt("quantity"));
                    itemPs.setDouble(4, cartRs.getDouble("price"));

                    itemPs.executeUpdate();
                }

                // Clear Cart

                PreparedStatement deletePs =
                        con.prepareStatement(deleteCart);

                deletePs.setInt(1, customerId);

                deletePs.executeUpdate();


                // Commit Transaction

                con.commit();

                System.out.println("--------------------------------");
                System.out.println("Order Placed Successfully");
                System.out.println("Order ID : " + orderId);
                System.out.println("Total Bill : ₹" + total);
                System.out.println("--------------------------------");

                return true;

            } catch (Exception e) {

                try {
                    con.rollback();
                    System.out.println("Transaction Failed.");
                    System.out.println("Rollback Successful.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                e.printStackTrace();
            }

            finally {

                try {
                    con.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            return false;
        }

        // View All Orders

        public void viewAllOrders() {

            String sql = "SELECT * FROM orders";

            try {

                PreparedStatement ps = con.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();

                System.out.println("\n================ ALL ORDERS ================");

                System.out.printf("%-10s %-12s %-15s %-22s %-15s\n",
                        "Order ID",
                        "Customer",
                        "Amount",
                        "Order Date",
                        "Status");

                while (rs.next()) {

                    System.out.printf("%-10d %-12d %-15.2f %-22s %-15s\n",
                            rs.getInt("order_id"),
                            rs.getInt("customer_id"),
                            rs.getDouble("total_amount"),
                            rs.getTimestamp("order_date"),
                            rs.getString("status"));
                }

                System.out.println("============================================");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        // View Customer Orders

        public void viewCustomerOrders(int customerId) {

            String sql = "SELECT * FROM orders WHERE customer_id=?";

            try {

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, customerId);

                ResultSet rs = ps.executeQuery();

                System.out.println("\n========== CUSTOMER ORDERS ==========");

                while (rs.next()) {

                    System.out.println("-------------------------------------");
                    System.out.println("Order ID      : " + rs.getInt("order_id"));
                    System.out.println("Total Amount  : ₹" + rs.getDouble("total_amount"));
                    System.out.println("Order Date    : " + rs.getTimestamp("order_date"));
                    System.out.println("Status        : " + rs.getString("status"));
                }

                System.out.println("-------------------------------------");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        // Search Order

        public void searchOrder(int orderId) {

            String sql = "SELECT * FROM orders WHERE order_id=?";

            try {

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, orderId);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    System.out.println("\n=========== ORDER DETAILS ===========");

                    System.out.println("Order ID      : " + rs.getInt("order_id"));
                    System.out.println("Customer ID   : " + rs.getInt("customer_id"));
                    System.out.println("Amount        : ₹" + rs.getDouble("total_amount"));
                    System.out.println("Order Date    : " + rs.getTimestamp("order_date"));
                    System.out.println("Status        : " + rs.getString("status"));

                    System.out.println("=====================================");

                } else {

                    System.out.println("Order Not Found.");

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        // Update Order Status

        public boolean updateOrderStatus(int orderId, String status) {

            String sql = "UPDATE orders SET status=? WHERE order_id=?";

            try {

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, status);

                ps.setInt(2, orderId);

                int row = ps.executeUpdate();

                if (row > 0) {

                    System.out.println("Order Status Updated.");

                    return true;
                }

            } catch (SQLException e) {

                e.printStackTrace();

            }

            return false;
        }

        // Delete Order

        public boolean deleteOrder(int orderId) {

            String sql = "DELETE FROM orders WHERE order_id=?";

            try {

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, orderId);

                int row = ps.executeUpdate();

                if (row > 0) {

                    System.out.println("Order Deleted Successfully.");

                    return true;
                }

            } catch (SQLException e) {

                e.printStackTrace();

            }

            return false;
        }

       // Get Order Total

        public double getOrderTotal(int orderId) {

            String sql = "SELECT total_amount FROM orders WHERE order_id=?";

            try {

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, orderId);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    return rs.getDouble("total_amount");

                }

            } catch (SQLException e) {

                e.printStackTrace();

            }

            return 0;
        }

    }
}
