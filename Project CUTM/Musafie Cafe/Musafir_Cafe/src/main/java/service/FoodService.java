package service;

import dao.FoodDAO;
import model.Food;

public class FoodService {

    private FoodDAO foodDAO;

    public FoodService() {
        foodDAO = new FoodDAO();
    }

    // ==========================================
    // Add Food
    // ==========================================
    public boolean addFood(Food food) {

        if (food == null) {

            System.out.println("Food data cannot be null.");
            return false;
        }

        if (food.getFoodName() == null ||
                food.getFoodName().trim().isEmpty()) {

            System.out.println("Food name cannot be empty.");
            return false;
        }

        if (food.getPrice() <= 0) {

            System.out.println(
                    "Food price must be greater than 0."
            );

            return false;
        }

        return foodDAO.addFood(food);
    }

    // ==========================================
    // View All Food
    // ==========================================
    public void viewFoods() {

        foodDAO.viewFoods();
    }

    // ==========================================
    // Search Food By ID
    // ==========================================
    public Food searchFood(int foodId) {

        if (foodId <= 0) {

            System.out.println("Invalid Food ID.");
            return null;
        }

        Food food = foodDAO.searchFood(foodId);

        if (food == null) {

            System.out.println("Food Not Found.");
        }

        return food;
    }

    // ==========================================
    // Update Food
    // ==========================================
    public boolean updateFood(Food food) {

        if (food == null) {

            System.out.println("Food data cannot be null.");
            return false;
        }

        if (food.getFoodId() <= 0) {

            System.out.println("Invalid Food ID.");
            return false;
        }

        if (food.getFoodName() == null ||
                food.getFoodName().trim().isEmpty()) {

            System.out.println("Food name cannot be empty.");
            return false;
        }

        if (food.getPrice() <= 0) {

            System.out.println(
                    "Food price must be greater than 0."
            );

            return false;
        }

        return foodDAO.updateFood(food);
    }

    // ==========================================
    // Delete Food
    // ==========================================
    public boolean deleteFood(int foodId) {

        if (foodId <= 0) {

            System.out.println("Invalid Food ID.");
            return false;
        }

        if (!foodDAO.foodExists(foodId)) {

            System.out.println("Food Not Found.");
            return false;
        }

        return foodDAO.deleteFood(foodId);
    }

    // ==========================================
    // Check Food Exists
    // ==========================================
    public boolean foodExists(int foodId) {

        if (foodId <= 0) {

            return false;
        }

        return foodDAO.foodExists(foodId);
    }

    // ==========================================
    // Get Food Price
    // ==========================================
    public double getFoodPrice(int foodId) {

        if (foodId <= 0) {

            return 0;
        }

        return foodDAO.getFoodPrice(foodId);
    }
}