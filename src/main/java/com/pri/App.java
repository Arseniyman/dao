package com.pri;

import DAO.EmployeeDAO;
import DAO.TaskDAO;
import Models.Employee;
import Models.Task;

import java.util.ArrayList;

public class App
{
    public static void main( String[] args )
    {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ArrayList<Employee> employees = employeeDAO.getAll();

        TaskDAO taskDAO = new TaskDAO();
        ArrayList<Task> tasks = taskDAO.getAll();

        for (Employee emp : employees) {
            for (Task tsk : tasks) {
                if (emp.getId() == tsk.getEmployeeId() && tsk.isDone()) {
                    System.out.println(emp.getName() + " made " + tsk.getName());
                }
            }
        }
    }
}
