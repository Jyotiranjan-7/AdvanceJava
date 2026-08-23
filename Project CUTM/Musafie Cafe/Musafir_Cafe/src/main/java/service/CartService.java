package service;

import dao.CartDAO;
import dao.FoodDAO;
import model.Cart;

public class CartService {

    private CartDAO cartDAO;
    private FoodDAO foodDAO;

    public CartService() {
        cartDAO = new CartDAO();
        foodDAO = new FoodDAO();
    }

    // ==========================================
    // Add Food To Cart
    // ==========================================
    public boolean addToCart(Cart cart) {

        if (cart == null) {

            System.out.println("Cart data cannot be null.");
            return false;
        }

        if (cart.getCustomerId() <= 0) {

            System.out.println("Invalid Customer ID.");
            return false;
        }

        if (cart.getFoodId() <= 0) {

            System.out.println("Invalid Food ID.");
            return false;
        }

        if (cart.getQuantity() <= 0) {

            System.out.println(
                    "Quantity must be greater than 0."
            );

            return false;
        }

        // Calculate subtotal
        double price =
                foodDAO.getFoodPrice(cart.getFoodId());

        if (price <= 0) {

            System.out.println("Food Not Found.");
            return false;
        }

        double subtotal =
                price * cart.getQuantity();

        cart.setSubtotal(subtotal);

        return cartDAO.addToCart(cart);
    }

    // ==========================================
    // View Customer Cart
    // ==========================================
    public void viewCart(int customerId) {

        if (customerId <= 0) {

            System.out.println("Invalid Customer ID.");
            return;
        }

        cartDAO.viewCart(customerId);
    }

    // ==========================================
    // Update Cart Quantity
    // ==========================================
    public boolean updateQuantity(
            int cartId,
            int quantity) {

        if (cartId <= 0) {

            System.out.println("Invalid Cart ID.");
            return false;
        }

        if (quantity <= 0) {

            System.out.println(
                    "Quantity must be greater than 0."
            );

            return false;
        }

        // Get Food ID from Cart
        int foodId =
                cartDAO.getFoodIdFromCart(cartId);

        if (foodId <= 0) {

            System.out.println("Cart Item Not Found.");
            return false;
        }

        // Get current food price
        double price =
                foodDAO.getFoodPrice(foodId);

        if (price <= 0) {

            System.out.println("Food Not Found.");
            return false;
        }

        // Calculate new subtotal
        double subtotal =
                price * quantity;

        return cartDAO.updateQuantity(
                cartId,
                quantity,
                subtotal
        );
    }

    // ==========================================
    // Remove Item From Cart
    // ==========================================
    public boolean removeItem(int cartId) {

        if (cartId <= 0) {

            System.out.println("Invalid Cart ID.");
            return false;
        }

        return cartDAO.removeItem(cartId);
    }
}