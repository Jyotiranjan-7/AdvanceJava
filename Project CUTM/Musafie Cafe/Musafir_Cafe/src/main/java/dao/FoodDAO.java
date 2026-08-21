package dao;
import com.musafircafe.config.DBConnection;
import com.musafircafe.model.Food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodDAO {

    private Connection con;

    public FoodDAO() {
        con = DBConnection.getConnection();
    }

    // Add Food Item

    public boolean addFood(Food food) {

        String sql = "INSERT INTO food(food_name,price) VALUES(?,?)";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, food.getFoodName());
            ps.setDouble(2, food.getPrice());

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Food Added Successfully.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // View All Food Items

    public void viewFoods() {

        String sql = "SELECT * FROM food";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n================ FOOD MENU ================");

            System.out.printf("%-10s %-25s %-10s\n",
                    "ID", "FOOD NAME", "PRICE");

            while (rs.next()) {

                System.out.printf("%-10d %-25s ₹%-10.2f\n",
                        rs.getInt("food_id"),
                        rs.getString("food_name"),
                        rs.getDouble("price"));
            }

            System.out.println("===========================================\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Search Food By ID

    public Food searchFood(int foodId) {

        String sql = "SELECT * FROM food WHERE food_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, foodId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Food food = new Food();

                food.setFoodId(rs.getInt("food_id"));
                food.setFoodName(rs.getString("food_name"));
                food.setPrice(rs.getDouble("price"));

                return food;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // Update Food

    public boolean updateFood(Food food) {

        String sql = "UPDATE food SET food_name=?, price=? WHERE food_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, food.getFoodName());
            ps.setDouble(2, food.getPrice());
            ps.setInt(3, food.getFoodId());

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Food Updated Successfully.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete Food

    public boolean deleteFood(int foodId) {

        String sql = "DELETE FROM food WHERE food_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, foodId);

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Food Deleted Successfully.");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // Check Food Exists

    public boolean foodExists(int foodId) {

        String sql = "SELECT * FROM food WHERE food_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, foodId);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get Food Price

    public double getFoodPrice(int foodId) {

        String sql = "SELECT price FROM food WHERE food_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, foodId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("price");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
