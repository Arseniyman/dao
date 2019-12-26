package com.pri;

import static DAO.EmployeeDAO.getConn;
import static org.junit.Assert.*;

import DAO.EmployeeDAO;
import Models.Employee;
import org.junit.Before;
import org.junit.Test;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void connection() {
        try {
            Connection connection = getConn();
        } catch (SQLException e) {
            fail("Connection fail");
        }
    }

    @Test
    public void insertEmployeeTest() {

        String testName = "Mihail Portnov";
        short testAge = 66;

        try {
            Connection connection = getConn();
            String sql = "INSERT INTO employee(name, age) VALUES(?, ?)";
            PreparedStatement prepState = connection.prepareStatement(sql);
            prepState.setString(1, testName);
            prepState.setShort(2, testAge);
            prepState.executeUpdate();

            prepState.close();
            connection.close();

        } catch (SQLException e) {
            fail("Connection fail");
        }

        try {
            Connection connection1 = getConn();
            //String sql = "SELECT LAST_INSERT_ID()";  // Don`t work (Why?)
            String sql = "SELECT MAX(id) FROM employee";
            Statement statement = connection1.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int lastId = resultSet.getInt(1);
            resultSet.close();
            statement.close();
            connection1.close();

            EmployeeDAO employeeDAO = new EmployeeDAO();
            Employee employee = employeeDAO.getOne(lastId);

            assertEquals("Fail insert", testName, employee.getName());
            assertEquals("Fail insert", testAge, employee.getAge());

        } catch (SQLException e) {
            fail("Connection fail");
        }

    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
