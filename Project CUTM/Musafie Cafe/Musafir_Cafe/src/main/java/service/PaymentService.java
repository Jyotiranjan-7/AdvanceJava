package service;

import dao.PaymentDAO;
import model.Payment;

public class PaymentService {

    private PaymentDAO paymentDAO;

    public PaymentService() {
        paymentDAO = new PaymentDAO();
    }

    // ==========================================
    // Process Payment
    // ==========================================
    public boolean processPayment(Payment payment) {

        if (payment == null) {

            System.out.println("Payment data cannot be null.");
            return false;
        }

        if (payment.getOrderId() <= 0) {

            System.out.println("Invalid Order ID.");
            return false;
        }

        if (payment.getAmount() <= 0) {

            System.out.println(
                    "Payment amount must be greater than 0."
            );

            return false;
        }

        if (payment.getPaymentMode() == null ||
                payment.getPaymentMode().trim().isEmpty()) {

            System.out.println(
                    "Payment mode cannot be empty."
            );

            return false;
        }

        return paymentDAO.makePayment(payment);
    }

    // ==========================================
    // View All Payments
    // ==========================================
    public void viewPayments() {

        paymentDAO.viewPayments();
    }

    // ==========================================
    // Search Payment
    // ==========================================
    public void searchPayment(int paymentId) {

        if (paymentId <= 0) {

            System.out.println("Invalid Payment ID.");
            return;
        }

        paymentDAO.searchPayment(paymentId);
    }
}