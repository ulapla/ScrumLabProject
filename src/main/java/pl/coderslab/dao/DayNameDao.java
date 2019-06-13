package pl.coderslab.dao;

import pl.coderslab.model.DayName;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {
    public static final String FIND_ALL_DAY_NAME_QUERY = "SELECT * FROM day_name;";
    private static final String READ_BY_PLAN_ID_QUERY = "SELECT name, day_name.display_order AS displayOrder, day_name.id AS id \n" +
            "FROM day_name \n" +
            "JOIN recipe_plan ON day_name.id = recipe_plan.day_name_id \n" +
            "WHERE plan_id = ? \n" +
            "GROUP BY name, day_name.display_order, day_name.id \n" +
            "ORDER BY day_name.display_order";

    public static List<DayName> findAll() {
        List<DayName> dayNameList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_DAY_NAME_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                DayName dayName = new DayName();
                dayName.setId(resultSet.getInt("id"));
                dayName.setName(resultSet.getString("name"));
                dayName.setDisplayOrder(resultSet.getInt("display_order"));
                dayNameList.add(dayName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayNameList;
    }

    public static List<DayName> readByPlanId(int planId) {
        List<DayName> list = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_PLAN_ID_QUERY);
            preparedStatement.setInt(1, planId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DayName tempDayName = new DayName();
                tempDayName.setId(resultSet.getInt("id"));
                tempDayName.setName(resultSet.getString("name"));
                tempDayName.setDisplayOrder(resultSet.getInt("displayOrder"));
                list.add(tempDayName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
