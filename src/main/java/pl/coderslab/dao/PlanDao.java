package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlanDao {

    // ZAPYTANIA SQL
    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan (name, description, created, admin_id) VALUES (?,?,?,?)";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan WHERE id = ?;";
    private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan";
    private static final String READ_PLAN_QUERY = "SELECT * FROM plan WHERE id = ?";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ?, created = ?, admin_id = ? WHERE id = ?";
    private static final String FIND_LAST_PALN_WITH_DETAILS = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description\n" +
            "FROM `recipe_plan`\n" +
            "JOIN day_name on day_name.id=day_name_id\n" +
            "JOIN recipe on recipe.id=recipe_id WHERE\n" +
            "recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?)\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String FIND_ALL_PLAN_BY_ADMINID = "SELECT * FROM plan WHERE admin_ID = ?;";
    private static final String ADD_RECIPE_TO_PLAN = "INSERT INTO recipe_plan (recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?, ?, ?, ?, ?)";


    public static List<String[]> findLastPlan(Admin admin) {
        List<String[]> detailsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_LAST_PALN_WITH_DETAILS)
        ) {
            preparedStatement.setInt(1, admin.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String[] details = new String[4];
                    details[0] = resultSet.getString("day_name");
                    details[1] = resultSet.getString("meal_name");
                    details[2] = resultSet.getString("recipe_name");
                    details[3] = resultSet.getString("recipe_description");
                    detailsList.add(details);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return detailsList;
    }

    //Ta metoda zlicza, ile dany admin ma do siebie przypisanych planów.
    public static int userPlansQuantityCounter(int adminId) {
        int counter = 0;
        List<Plan> planList = findAll();
        Iterator<Plan> iterator = planList.iterator();
        while (iterator.hasNext()) {
            Plan tempPlan = iterator.next();
            if (tempPlan.getAdminId() == adminId) {
                counter++;
            }
        }
        return counter;
    }

    public static Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            preparedStatement.setInt(1, planId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    plan.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;

    }

    public static List<Plan> findAllByAdminID(int id) {
        List<Plan> listPlan = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLAN_BY_ADMINID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                planToAdd.setAdminId(resultSet.getInt("admin_id"));
                listPlan.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPlan;
    }


    public static List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             ResultSet resultSet = connection.createStatement().executeQuery(FIND_ALL_PLANS_QUERY)) {

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                planToAdd.setAdminId(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }


    public static Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, plan.getName());
            preparedStatement.setString(2, plan.getDescription());
            preparedStatement.setString(3, plan.getCreated());
            preparedStatement.setInt(4, plan.getAdminId());
            int result = preparedStatement.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void AddRecipeToPlan(int recipeId, String mealName, int displayOrder, int dayName, int planId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_RECIPE_TO_PLAN);
            preparedStatement.setInt(1, recipeId);
            preparedStatement.setString(2, mealName);
            preparedStatement.setInt(3, displayOrder);
            preparedStatement.setInt(4, dayName);
            preparedStatement.setInt(5, planId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
            preparedStatement.setInt(1, planId);
            int result = preparedStatement.executeUpdate();

            if (result != 1) {
                throw new NotFoundException("Plan not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            preparedStatement.setInt(5, plan.getId());
            preparedStatement.setString(1, plan.getName());
            preparedStatement.setString(2, plan.getDescription());
            preparedStatement.setString(3, plan.getCreated());
            preparedStatement.setInt(4, plan.getAdminId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
