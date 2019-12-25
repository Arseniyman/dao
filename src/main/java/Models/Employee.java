package Models;

public class Employee {

    private int id;
    private String name;
    private short age;


    public Employee() {}

    public Employee(String name, short age) {
        this.name = name;
        this.age = age;
    }

    public Employee(int id, String name, short age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }
}
