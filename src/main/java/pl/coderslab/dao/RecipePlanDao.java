package pl.coderslab.dao;

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

}
