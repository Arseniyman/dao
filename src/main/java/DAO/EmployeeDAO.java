package DAO;

import Models.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements DAO {
    @Override
    public Employee getOne(int id) {
        Employee employee = null;
        try(Connection connection = getConn()) {
            String sql = "SELECT * FROM employee WHERE id = ?";
            PreparedStatement prepState = connection.prepareStatement(sql);
            prepState.setInt(1, id);
            ResultSet resultSet = prepState.executeQuery();
            if (resultSet.next()) {
                int idEmp = resultSet.getInt(1);
                String name = resultSet.getString(2);
                short age = resultSet.getShort(3);
                employee = new Employee(idEmp, name, age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public ArrayList<Employee> getAll() {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try(Connection connection = getConn()) {
            String sql = "SELECT * FROM employee";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                short age = resultSet.getShort(3);
                Employee employee = new Employee(id, name, age);
                employees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void insert(Object o) {
        Employee employee = (Employee)o;
        try(Connection connection = getConn()) {
            String sql = "INSERT INTO employee (name, age) VALUES(?, ?)";
            PreparedStatement prepState = connection.prepareStatement(sql);
            prepState.setString(1, employee.getName());
            prepState.setShort(2, employee.getAge());
            prepState.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object o) {
        Employee employee = (Employee)o;
        try(Connection connection = getConn()) {
            String sql = "UPDATE employee SET name = ?, age = ? WHERE id = ?";
            PreparedStatement prepState = connection.prepareStatement(sql);
            prepState.setString(1, employee.getName());
            prepState.setShort(2, employee.getAge());
            prepState.setInt(3, employee.getId());
            prepState.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object o) {
        int id = ((Employee)o).getId();
        try(Connection connection = getConn()) {
            String sql = "DELETE FROM employee WHERE id = ?";
            PreparedStatement prepState = connection.prepareStatement(sql);
            prepState.setInt(1, id);
            prepState.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConn() throws SQLException {
        return (DriverManager.getConnection("jdbc:mariadb://localhost:3306/company?user=root&password=root"));
    }
}
