package dao;

import config.DBConnection;
import model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAO {

    private Connection con;

    public PaymentDAO() {
        con = DBConnection.getConnection();
    }

    // Make Payment

    public boolean makePayment(Payment payment) {

        String sql = "INSERT INTO payment(order_id,amount,payment_mode,payment_status,payment_date) VALUES(?,?,?,?,NOW())";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, payment.getOrderId());
            ps.setDouble(2, payment.getAmount());
            ps.setString(3, payment.getPaymentMode());
            ps.setString(4, payment.getPaymentStatus());

            int row = ps.executeUpdate();

            if (row > 0) {

                System.out.println("Payment Successful.");

                return true;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }


    // View All Payments

    public void viewPayments() {

        String sql = "SELECT * FROM payment";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n============== PAYMENT DETAILS ==============");

            System.out.printf("%-10s %-10s %-12s %-15s %-15s %-20s\n",
                    "Pay ID",
                    "Order",
                    "Amount",
                    "Mode",
                    "Status",
                    "Date");

            while (rs.next()) {

                System.out.printf("%-10d %-10d %-12.2f %-15s %-15s %-20s\n",
                        rs.getInt("payment_id"),
                        rs.getInt("order_id"),
                        rs.getDouble("amount"),
                        rs.getString("payment_mode"),
                        rs.getString("payment_status"),
                        rs.getTimestamp("payment_date"));

            }

            System.out.println("=============================================");

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    // Search Payment

    public void searchPayment(int paymentId) {

        String sql = "SELECT * FROM payment WHERE payment_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, paymentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n========== PAYMENT DETAILS ==========");

                System.out.println("Payment ID     : " + rs.getInt("payment_id"));
                System.out.println("Order ID       : " + rs.getInt("order_id"));
                System.out.println("Amount         : ₹" + rs.getDouble("amount"));
                System.out.println("Payment Mode   : " + rs.getString("payment_mode"));
                System.out.println("Status         : " + rs.getString("payment_status"));
                System.out.println("Payment Date   : " + rs.getTimestamp("payment_date"));

                System.out.println("=====================================");

            } else {

                System.out.println("Payment Record Not Found.");

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }


    // Update Payment Status

    public boolean updatePaymentStatus(int paymentId, String status) {

        String sql = "UPDATE payment SET payment_status=? WHERE payment_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, paymentId);

            int row = ps.executeUpdate();

            if (row > 0) {

                System.out.println("Payment Status Updated.");

                return true;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    // Delete Payment

    public boolean deletePayment(int paymentId) {

        String sql = "DELETE FROM payment WHERE payment_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, paymentId);

            int row = ps.executeUpdate();

            if (row > 0) {

                System.out.println("Payment Deleted Successfully.");

                return true;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    // Get Payment Details

    public Payment getPayment(int paymentId) {

        String sql = "SELECT * FROM payment WHERE payment_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, paymentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Payment payment = new Payment();

                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setOrderId(rs.getInt("order_id"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentMode(rs.getString("payment_mode"));
                payment.setPaymentStatus(rs.getString("payment_status"));
                payment.setPaymentDate(rs.getTimestamp("payment_date").toLocalDateTime());

                return payment;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

}
