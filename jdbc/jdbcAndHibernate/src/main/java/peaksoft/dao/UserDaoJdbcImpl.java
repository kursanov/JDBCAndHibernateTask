package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {


    private  final Connection connection = Util.getConnection();

    public UserDaoJdbcImpl() {

    }

    public boolean createUsersTable() {
        int execute = 0;
        try {
            Statement statement = connection.createStatement();
            execute = statement.executeUpdate("""
                            create table if not exists users(
                            id serial primary key,
                            name varchar,
                            last_name varchar,
                            age int
                            )""");
            System.out.println("Tuzdu");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return execute != 0;





    }

    public void dropUsersTable() {
        String execute = "drop table if exists users";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(execute);
            System.out.println("success!");
            connection.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }



    }

    public void saveUser(String name, String lastName, int age) {
        String query = "insert into users(name,last_name,age)"+
                "values (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,age);
            preparedStatement.executeUpdate();
            System.out.println(name + " Kjshuldu!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }




    }

    public void removeUserById(long id) {
        String query = "delete from users where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setLong(1,id);
            int rowsAffected =  preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }

    public List<User> getAllUsers() {

        List<User> userList = new  ArrayList<>();

        String query = "select * from users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                userList.add(user);
            }



        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        String query = "delete from users";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}