package com.hexaware.dao;

import com.hexaware.entities.Policy;
import com.hexaware.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsuranceServiceImpl implements IPolicyService {

    // Static variable for holding the database connection
    private static Connection connection;

    // Constructor to initialize the connection
    public InsuranceServiceImpl() {
        // Assign the connection by invoking the getConnection() method in DBConnection class
        connection = DBConnection.getConnection();
    }

    @Override
    public boolean createPolicy(Policy policy) {
        try {
            String query = "INSERT INTO Policy (policy_number, policy_type, coverage_amount, premium_amount, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, policy.getPolicyNumber());
            statement.setString(2, policy.getPolicyType());
            statement.setDouble(3, policy.getCoverageAmount());
            statement.setDouble(4, policy.getPremiumAmount());
            statement.setDate(5, new java.sql.Date(policy.getStartDate().getTime()));
            statement.setDate(6, new java.sql.Date(policy.getEndDate().getTime()));

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Policy getPolicy(int policyId) {
        Policy policy = null;

        try {
            String sql = "SELECT * FROM Policy WHERE policy_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, policyId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                policy = mapResultSetToPolicy(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return policy;
    }

    @Override
    public List<Policy> getAllPolicies() {
        List<Policy> policies = new ArrayList<>();

        try {
            String query = "SELECT * FROM Policy";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Policy policy = mapResultSetToPolicy(resultSet);
                policies.add(policy);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return policies;
    }

    @Override
    public boolean updatePolicy(Policy policy) {
        try {
            String query = "UPDATE Policy SET policy_number = ?, policy_type = ?, coverage_amount = ?, " +
                    "premium_amount = ?, start_date = ?, end_date = ? WHERE policy_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, policy.getPolicyNumber());
            statement.setString(2, policy.getPolicyType());
            statement.setDouble(3, policy.getCoverageAmount());
            statement.setDouble(4, policy.getPremiumAmount());
            statement.setDate(5, new java.sql.Date(policy.getStartDate().getTime()));
            statement.setDate(6, new java.sql.Date(policy.getEndDate().getTime()));
            statement.setInt(7, policy.getPolicyId());

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePolicy(int policyId) {
        try {
            String query = "DELETE FROM Policy WHERE policy_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, policyId);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to map ResultSet to Policy object
    private Policy mapResultSetToPolicy(ResultSet resultSet) throws SQLException {
        return new Policy(
                resultSet.getInt("policy_id"),
                resultSet.getString("policy_number"),
                resultSet.getString("policy_type"),
                resultSet.getDouble("coverage_amount"),
                resultSet.getDouble("premium_amount"),
                resultSet.getDate("start_date"),
                resultSet.getDate("end_date")
        );
    }
}
