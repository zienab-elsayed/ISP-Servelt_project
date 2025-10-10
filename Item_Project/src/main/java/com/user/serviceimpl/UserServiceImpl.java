 package com.user.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.user.model.User;
import com.user.service.UserService;

public class UserServiceImpl implements UserService {

    private DataSource datasource;

    public UserServiceImpl(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public boolean login(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            connection = datasource.getConnection();
            String query = "SELECT password FROM UserData WHERE username=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);

            resultset = statement.executeQuery();

            if (resultset.next()) {
                String DBpassword = resultset.getString("password");
                return DBpassword.equals(password);
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (resultset != null) resultset.close(); } catch (SQLException ignored) {}
            try { if (statement != null) statement.close(); } catch (SQLException ignored) {}
            try { if (connection != null) connection.close(); } catch (SQLException ignored) {}
        }
        return false;
    }

    @Override
    public boolean  signIn(User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = datasource.getConnection();
            String query = "INSERT INTO UserData (email, username, password) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());

            int rows = statement.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	
        	
        	try {
             if (statement != null) statement.close();
             if (connection != null) connection.close(); 
						
				} catch (SQLException e) {
					 
					e.printStackTrace();
				} 
             
        }

        return false;
    }
}
