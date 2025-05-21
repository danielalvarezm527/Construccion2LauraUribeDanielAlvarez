package app.adapters.rest.dto;

public class UserRequest {
    private long document;
    private String name;
    private int age;
    private String userName;
    private String password;
    private String role;

    // Getters y setters
    public long getDocument() { return document; }
    public void setDocument(long document) { this.document = document; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
