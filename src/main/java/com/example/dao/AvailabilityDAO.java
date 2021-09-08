package com.example.dao;

import java.sql.*;

public class AvailabilityDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/product";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql@123";
    private static AvailabilityDAO inst = null;
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;
    private static final String Query = "SELECT a.availability from availability a join product p on a.product_id = p.id join location l on a.location_id = l.id where l.city_name = ? and p.name = ?";

    private AvailabilityDAO(){}

    public static AvailabilityDAO getInstance() {
        if (inst == null) {
            inst = new AvailabilityDAO();
        }
        return inst;
    }
    public Integer checkAvailability (String cityName, String productName) {
        Integer availability = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(Query);
            statement.setString(1, cityName);
            statement.setString(2, productName);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                availability = resultSet.getInt("availability");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return availability;
    }
}
