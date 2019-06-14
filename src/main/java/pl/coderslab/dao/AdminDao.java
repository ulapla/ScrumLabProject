package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;

import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    // ZAPYTANIA SQL
    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name,last_name,email,password,superadmin,enable) VALUES (?,?,?,?,?,?);";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins WHERE id = ?;";
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins;";
    private static final String READ_ADMIN_QUERY = "SELECT * FROM admins WHERE id = ?;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name = ? , last_name = ?, email = ?, password = ?, superadmin = ?, enable = ? WHERE	id = ?;";
    private static final String FIND_ADMIN_BY_EMAIL = "SELECT * FROM admins WHERE email = ?;";


    /**
     * Get admin by id
     *
     * @param adminId
     * @return
     */
    public static Admin read(Integer adminId) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMIN_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                   admin.setFirstName(resultSet.getString("first_name"));
                   admin.setLastName(resultSet.getString("last_name"));
                   admin.setEmail(resultSet.getString("email"));
                   admin.setPassword(resultSet.getString("password"));
                   admin.setSuperadmin(resultSet.getInt("superadmin"));
                   admin.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;

    }

    public static Admin read(String email) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ADMIN_BY_EMAIL)
        ) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                    admin.setSuperadmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;

    }

    private static boolean isEmailExist(String email){
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ADMIN_BY_EMAIL)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();// zwraca false gdy nie znajdzie emaila w bazie danych i true jak znajdzie


        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Return all admins
     *
     * @return
     */
    public static List<Admin> findAll() {
        List<Admin> adminsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setSuperadmin(resultSet.getInt("superadmin"));
                admin.setEnable(resultSet.getInt("enable"));
                adminsList.add(admin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminsList;

    }

    /**
     * Create admin
     *
     * @param admin
     * @return
     *
     * Jesli email tworzonego admina jest już w bazie zwróci null
     */
    public static Admin create(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_ADMIN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)){
            if (!isEmailExist(admin.getEmail())){
                insertStm.setString(1, admin.getFirstName());
                insertStm.setString(2, admin.getLastName());
                insertStm.setString(3, admin.getEmail());
                insertStm.setString(4, BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()));//szyfrowanie hasła przy zapisie do bazy
                insertStm.setInt(5, admin.getSuperadmin());
                insertStm.setInt(6, admin.getEnable());

                int result = insertStm.executeUpdate();

                if (result != 1) {
                    throw new RuntimeException("Execute update returned " + result);
                }

                try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                    if (generatedKeys.first()) {
                        admin.setId(generatedKeys.getInt(1));
                        return admin;
                    } else {
                        throw new RuntimeException("Generated key was not found");
                    }
                }
            }
        } catch (Exception e) {
                    e.printStackTrace();
                }

            return null;
        }




    /**
     * Remove admin by id
     *
     * @param adminId
     */
    public static void delete(Integer adminId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_QUERY)) {
            statement.setInt(1, adminId);
            int i = statement.executeUpdate();

            if (i != 1) {
                throw new NotFoundException("Admin not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Update admin
     *
     * @param admin
     */
    public static void update(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_QUERY)) {

            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, read(admin.getId()).getEmail());//przypisze email z bazy - nie ma możliwości zmiany emaila



            if(!admin.getPassword().equals(read(admin.getId()).getPassword()) ) { //jesli wprowadzono nowe hasło (obecne jest inne niż to w bazie) zaszyfruj
                statement.setString(4, BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()));//szyfrujemy hasło przy zapisie do bazy danych
            }
            else { //w innym wypadku jest już zaszyfrowane
                statement.setString(4, admin.getPassword());
            }

            statement.setInt(5, admin.getSuperadmin());
            statement.setInt(6, admin.getEnable());
            statement.setInt(7, admin.getId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    // Uwaga! metoda zwraca null gdy wpisane hasło jest niepoprawne
    public static Admin checkPassword(String email, String password) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ADMIN_BY_EMAIL)
        ) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                    admin.setSuperadmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));

                }
                if (BCrypt.checkpw(password, admin.getPassword())) { //sprawdzanie hasła
                    //System.out.println("pssword maches");
                    return admin;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       // System.out.println("password does not match");
        return null;
    }



    public static boolean changePassword(Admin admin, String oldPassword, String newPassword) { //admin musi być pobrany z bazy by jego stare hasło było z
        if (BCrypt.checkpw(oldPassword, read(admin.getId()).getPassword())) { //sprawdzanie hasła
            //System.out.println("pssword maches");
            admin.setPassword(newPassword);
            update(admin);
            return true;
        }
        else {
            return false; //podano błędne stare hasło, więc nie zostało zmienione
        }
    }

}

