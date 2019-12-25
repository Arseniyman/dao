package Models;

public class Task {

    private int id;
    private String name;
    private boolean isDone;
    private int employeeId;

    public Task(String name, boolean isDone, int employeeId) {
        this.name = name;
        this.isDone = isDone;
        this.employeeId = employeeId;
    }

    public Task(int id, String name, boolean isDone, int employeeId) {
        this.id = id;
        this.name = name;
        this.isDone = isDone;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
