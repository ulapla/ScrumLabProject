package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/*
Problemy:
1. Muszę skonsultować się, w jaki sposób chcemy wyszukiwać plan. Czy tylko po id, czy może też po name.
2. Przy update, czy chcemy, aby data była edytowana?
3. Czy przy Plan wystarczy standardowy konstruktor, czy przygotować coś więcej?
 */
public class PlanDao {

        // ZAPYTANIA SQL
        private static final String CREATE_PLAN_QUERY = "INSERT INTO plan (name, description, created, admin_id) VALUES (?,?,?,?)";
        private static final String DELETE_PLAN_QUERY = "DELETE FROM plan WHERE id = ?;";
        private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan";
        private static final String READ_PLAN_QUERY = "SELECT * FROM plan WHERE id = ?";
        private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ?, created = ? WHERE id = ?";


        public Plan read(Integer planId) {
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



        public List<Plan> findAll() {
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



        public Plan create(Plan plan) {
            try (Connection connection = DbUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PLAN_QUERY,
                         PreparedStatement.RETURN_GENERATED_KEYS))
            {
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



        public void delete(Integer planId) {
            try (Connection connection = DbUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
                preparedStatement.setInt(1, planId);
                preparedStatement.executeUpdate();

                boolean deleted = preparedStatement.execute();
                if (!deleted) {
                    throw new NotFoundException("Plan not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        public void update(Plan plan) {
            try (Connection connection = DbUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
                preparedStatement.setInt(4, plan.getId());
                preparedStatement.setString(1, plan.getName());
                preparedStatement.setString(2, plan.getDescription());
                preparedStatement.setString(3, plan.getCreated());

                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }



}
