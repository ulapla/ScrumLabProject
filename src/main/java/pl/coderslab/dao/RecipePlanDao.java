package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipePlanDao {

    private static final String READ_BY_PLAN_ID_QUERY = "SELECT * FROM recipe_plan\n" +
            "WHERE recipe_plan.plan_id = ?\n" +
            "ORDER BY display_order";

    private static final String FIND_ALL_PLAN_RECIPE_BY_RECIPE_ID = "SELECT * FROM scrumlab.recipe_plan WHERE recipe_id = ?";

    private static final String DELETE_RECIPEPLAN_QUERY = "DELETE FROM recipe_plan WHERE id = ?";
    private static final String FIND_ALL_PLAN_RECIPE_BY_PLAN_ID = "SELECT * FROM recipe_plan WHERE plan_id = ?;";

    public static void delete(int recipePlanId) {
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RECIPEPLAN_QUERY);
            preparedStatement.setInt(1, recipePlanId);
            int result = preparedStatement.executeUpdate();

            if (result != 1) {
                throw new NotFoundException("Plan not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public static List<RecipePlan> findAllPlanRecipeByPlanId(int planId) {
        List<RecipePlan> list = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_PLAN_RECIPE_BY_PLAN_ID);
            preparedStatement.setInt(1, planId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RecipePlan recipePlan = new RecipePlan();
                recipePlan.setId(resultSet.getInt("id"));
                recipePlan.setRecipeId(resultSet.getInt("recipe_id"));
                recipePlan.setMealName(resultSet.getString("meal_name"));
                recipePlan.setDisplayOrder(resultSet.getInt("display_order"));
                recipePlan.setDayNameId(resultSet.getInt("day_name_id"));
                recipePlan.setPlanId(planId);
                list.add(recipePlan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static List<RecipePlan> readByPlanId(int planId) {
        List<RecipePlan> list = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_PLAN_ID_QUERY);
            preparedStatement.setInt(1, planId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RecipePlan recipePlan = new RecipePlan();
                recipePlan.setId(resultSet.getInt("id"));
                recipePlan.setRecipeId(resultSet.getInt("recipe_id"));
                recipePlan.setMealName(resultSet.getString("meal_name"));
                recipePlan.setDisplayOrder(resultSet.getInt("display_order"));
                recipePlan.setDayNameId(resultSet.getInt("day_name_id"));
                recipePlan.setPlanId(planId);
                list.add(recipePlan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<RecipePlan> findAllPlanRecipeByRecipeId(int recipeId) {
        List<RecipePlan> list = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_PLAN_RECIPE_BY_RECIPE_ID);
            preparedStatement.setInt(1, recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next()) {
                RecipePlan recipePlan = new RecipePlan();
                recipePlan.setId(resultSet.getInt("id"));
                recipePlan.setRecipeId(resultSet.getInt("recipe_id"));
                recipePlan.setMealName(resultSet.getString("meal_name"));
                recipePlan.setDisplayOrder(resultSet.getInt("display_order"));
                recipePlan.setDayNameId(resultSet.getInt("day_name_id"));
                recipePlan.setPlanId(resultSet.getInt("plan_id"));
                list.add(recipePlan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
