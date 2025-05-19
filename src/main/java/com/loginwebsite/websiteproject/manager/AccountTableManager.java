package com.loginwebsite.websiteproject.manager;

import com.loginwebsite.websiteproject.controller.DataSourceController;
import com.loginwebsite.websiteproject.model.User;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.*;
import java.util.Properties;

public class AccountTableManager {

    private Properties props;
    private MysqlDataSource dataSource;
    public AccountTableManager() {
        DataSourceController dataSourceController = new DataSourceController();
        this.props = dataSourceController.getProperties();
        this.dataSource = dataSourceController.getDataSource(this.props);
    }

    public void insertAccountInfo(User theUser) throws SQLException {
        try (var connection = dataSource.getConnection(
                props.getProperty("user"),
                props.getProperty("password"));
             Statement statement = connection.createStatement();
        ) {

            String[] userInfo = new String[]{
                    theUser.getEmail(),
                    theUser.getUsername(),
                    theUser.getPassword(),
                    theUser.getFirstName(),
                    theUser.getLastName(),
                    theUser.getPhoneNumber()
            };

            String colValues = String.join("','", userInfo);
            String accountInsert = ("INSERT INTO movies.account (email, username, password, firstName, lastName, " +
                    "phoneNumber) VALUES ('%s')").formatted(colValues);

            System.out.println("accountInsert: " + accountInsert);

            statement.execute(accountInsert, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = statement.getGeneratedKeys();

            if (keys.next()) {
                int id = keys.getInt(1);
                theUser.setId(id);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public User getAccountProfileByQuery(String query) {
        User user = null;
        try {
            String username = this.props.getProperty("user");
            String password = this.props.getProperty("password");
            Connection connection = this.dataSource.getConnection(username, password);
            try {
                Statement statement = connection.createStatement();
                try {
                    ResultSet resultSet = statement.executeQuery(query);
                    if (resultSet.next()) {
                        user = new User();
                        user.setId(resultSet.getInt(1));
                        user.setFirstName(resultSet.getString(2));
                        user.setLastName(resultSet.getString(3));

                    } else {
                        // No user found
                    }
                } catch (Throwable var9) {
                    if (statement != null) {
                        try {
                            statement.close();
                        } catch (Throwable var8) {
                            var9.addSuppressed(var8);
                        }
                    }
                    throw var9;
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (Throwable var10) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Throwable var7) {
                        var10.addSuppressed(var7);
                    }
                }
                throw var10;
            }
            if (connection != null) {
                connection.close();
            }
            return user;
        } catch (SQLException var11) {
            throw new RuntimeException(var11);
        }
    }

    public void updateAccount(User user)throws SQLException{

        try (var connection = dataSource.getConnection(
                props.getProperty("user"),
                props.getProperty("password"));
             Statement statement = connection.createStatement();
        ) {
            String updatedAccount = "UPDATE movies.account SET email = '" + user.getEmail() + "', username = '" + user.getUsername()
                    + "', password = '" + user.getPassword() + "', firstName = '" + user.getFirstName()
                    + "', lastName = '" + user.getLastName() + "', phoneNumber = '" + user.getPhoneNumber() + "' WHERE id = " + user.getId();

            statement.execute(updatedAccount);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteUser(User user) throws SQLException {

        try (var connection = dataSource.getConnection(
                props.getProperty("user"),
                props.getProperty("password"));
             Statement statement = connection.createStatement();
        )
        {
            String deleteAccount = "DELETE FROM movies.account WHERE id='%s'".formatted(user.getId());
            statement.execute(deleteAccount);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
