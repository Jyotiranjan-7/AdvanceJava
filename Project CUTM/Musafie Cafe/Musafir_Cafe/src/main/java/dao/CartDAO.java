package dao;

import config.DBConnection;
import model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDAO {

    private Connection con;

    public CartDAO() {
        con = DBConnection.getConnection();
    }

    // Add Item to Cart

    public boolean addToCart(Cart cart) {

        String sql = "INSERT INTO cart(customer_id,food_id,quantity,subtotal) VALUES(?,?,?,?)";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, cart.getCustomerId());
            ps.setInt(2, cart.getFoodId());
            ps.setInt(3, cart.getQuantity());
            ps.setDouble(4, cart.getSubtotal());

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Item Added To Cart Successfully.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // View Customer Cart

    public void viewCart(int customerId) {

        String sql = """
                SELECT c.cart_id,
                       f.food_name,
                       f.price,
                       c.quantity,
                       c.subtotal
                FROM cart c
                INNER JOIN food f
                ON c.food_id=f.food_id
                WHERE c.customer_id=?
                """;

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n================ YOUR CART ================");

            System.out.printf("%-8s %-20s %-10s %-10s %-10s\n",
                    "ID","Food","Price","Qty","Subtotal");

            while (rs.next()) {

                System.out.printf("%-8d %-20s %-10.2f %-10d %-10.2f\n",
                        rs.getInt("cart_id"),
                        rs.getString("food_name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getDouble("subtotal"));
            }

            System.out.println("===========================================\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    // Update Quantity

    public boolean updateQuantity(int cartId, int quantity, double subtotal) {

        String sql = "UPDATE cart SET quantity=?, subtotal=? WHERE cart_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, quantity);
            ps.setDouble(2, subtotal);
            ps.setInt(3, cartId);

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Cart Updated Successfully.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Remove Item

    public boolean removeItem(int cartId) {

        String sql = "DELETE FROM cart WHERE cart_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, cartId);

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Item Removed Successfully.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    // Get Cart Total
    public double getCartTotal(int customerId) {

        String sql = "SELECT SUM(subtotal) AS total FROM cart WHERE customer_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getDouble("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    public int getFoodIdFromCart(int cartId) {

        String sql = "SELECT food_id FROM cart WHERE cart_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, cartId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt("food_id");

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return 0;
    }
    // Clear Cart
    public boolean clearCart(int customerId) {

        String sql = "DELETE FROM cart WHERE customer_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, customerId);

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Cart Cleared Successfully.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
