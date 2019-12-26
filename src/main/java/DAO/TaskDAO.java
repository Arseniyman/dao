package DAO;

import Models.Employee;
import Models.Task;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements DAO {
    @Override
    public Task getOne(int id) {

        Task task = null;
        Connection connection = null;
        PreparedStatement prepState = null;
        ResultSet resultSet = null;

        try {
            connection = getConn();
            String sql = "SELECT * FROM task WHERE id = ?";
            prepState = connection.prepareStatement(sql);
            prepState.setInt(1, id);
            resultSet = prepState.executeQuery();
            if (resultSet.next()) {
                int idTask = resultSet.getInt(1);
                String name = resultSet.getString(2);
                boolean isDone = resultSet.getBoolean(3);
                int employeeId = resultSet.getInt(4);
                task = new Task(idTask, name, isDone, employeeId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                prepState.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return task;
    }

    @Override
    public ArrayList<Task> getAll() {

        ArrayList<Task> tasks = new ArrayList<Task>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConn();
            String sql = "SELECT * FROM task";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                boolean isDone = resultSet.getBoolean(3);
                int employeeId = resultSet.getInt(4);
                Task task = new Task(id, name, isDone, employeeId);
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tasks;
    }

    @Override
    public void insert(Object o) {

        Task task = (Task) o;
        Connection connection = null;
        PreparedStatement prepState = null;

        try {
            connection = getConn();
            String sql = "INSERT INTO task (name, is_done, employee_id) VALUES(?, ?, ?)";
            prepState = connection.prepareStatement(sql);
            prepState.setString(1, task.getName());
            prepState.setBoolean(2, task.isDone());
            prepState.setInt(3, task.getEmployeeId());
            prepState.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepState.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Object o) {

        Task task = (Task)o;
        Connection connection = null;
        PreparedStatement prepState = null;

        try {
            connection = getConn();
            String sql = "UPDATE task SET name = ?, is_done = ?, employee_id = ? WHERE id = ?";
            prepState = connection.prepareStatement(sql);
            prepState.setString(1, task.getName());
            prepState.setBoolean(2, task.isDone());
            prepState.setInt(3, task.getEmployeeId());
            prepState.setInt(4, task.getId());
            prepState.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepState.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {

        Connection connection = null;
        PreparedStatement prepState = null;

        try {
            connection = getConn();
            String sql = "DELETE FROM task WHERE id = ?";
            prepState = connection.prepareStatement(sql);
            prepState.setInt(1, id);
            prepState.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepState.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConn() throws SQLException {
        return (DriverManager.getConnection("jdbc:mariadb://localhost:3306/company?user=root&password=root"));
    }
}
