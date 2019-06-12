package pl.coderslab.utils;

import pl.coderslab.model.DayName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//Stworzyłem nową klasę, aby w niej przechowywać metody potrzebne specjalnie dla mojego zadania.
public class AppScheduleDetailsUtils {

    private static final String Query1 = "SELECT name, day_name.display_order AS displayOrder \n" +
            "FROM day_name \n" +
            "JOIN recipe_plan ON day_name.id = recipe_plan.day_name_id \n" +
            "WHERE plan_id = ? \n" +
            "GROUP BY name, day_name.display_order \n" +
            "ORDER BY day_name.display_order";


    /*Ta metoda pozwala pobrać z bazy danych tylko te dni, w których użytkownik ma zaplanowane posiłki. Zakładam,
    że dało się to zrobić bez tworzenia specjalnego Query, ale na ten moment nie mam pomysłu jak to zrobić inaczej.
     */
    public static List<DayName> getUserDays(int planId) {
        List<DayName> list = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(Query1);
            preparedStatement.setInt(1, planId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DayName tempDayName = new DayName();
                tempDayName.setDisplayOrder(resultSet.getInt("displayOrder"));
                tempDayName.setName(resultSet.getString("name"));
                list.add(tempDayName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
