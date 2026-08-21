package model;
public class Food {

    private int foodId;
    private String foodName;
    private double price;
    private int categoryId;

    public Food() {
    }

    public Food(int foodId, String foodName, double price, int categoryId) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
        this.categoryId = categoryId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return foodId + "  " + foodName + "  ₹" + price;
    }
}
