package model;
public class Cart {

    private int cartId;
    private int customerId;
    private int foodId;
    private int quantity;
    private double subtotal;

    public Cart() {
    }

    public Cart(int cartId, int customerId, int foodId, int quantity, double subtotal) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.foodId = foodId;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return foodId + " Qty=" + quantity + " Subtotal=" + subtotal;
    }
}
