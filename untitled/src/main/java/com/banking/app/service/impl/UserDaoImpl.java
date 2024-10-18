package com.banking.app.service.impl;

import com.banking.app.entity.User;
import com.banking.app.service.UserDao;
import com.banking.app.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private final Connection connection;

    public UserDaoImpl() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void register(String username, String password) {
        String query = "insert into user (username, password) values (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error code for adding " + e);
        }
    }

    @Override
    public User login(String username, String password) {
        String query = "SELECT * FROM user where username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error code for getting " + e);
        }
        return null;
    }



}
