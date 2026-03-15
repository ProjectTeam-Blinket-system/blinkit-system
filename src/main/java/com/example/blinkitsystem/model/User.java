package model;

public class User {

    private int userId;
    private String name;
    private String address;

    public User(int userId, String name, String address) {
        this.userId = userId;
        this.name = name;
        this.address = address;
    }

    public void login() {
        System.out.println(name + " logged in");
    }

    public void logout() {
        System.out.println(name + " logged out");
    }

    public String getName() {
        return name;
    }
}