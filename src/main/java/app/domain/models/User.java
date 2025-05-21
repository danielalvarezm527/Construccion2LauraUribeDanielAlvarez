package app.domain.models;

public class User {
    private long userId; // <-- Agregado
    private long document;
    private String name;
    private int age;
    private String userName;
    private String password;
    private String role;

    // Constructor vacío
    public User() {}

    // Constructor completo (incluyendo userId)
    public User(long userId, long document, String name, int age, String userName, String password, String role) {
        this.userId = userId;
        this.document = document;
        this.name = name;
        this.age = age;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    // Constructor específico para el adapter MedicalRecord (según error reportado)
    public User(long userId, String name, int age, String userName, String password, long document, String role) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.userName = userName;
        this.password = password;
        this.document = document;
        this.role = role;
    }

    // Constructor completo (opcional)
    public User(long document, String name, int age, String userName, String password, String role) {
        this.document = document;
        this.name = name;
        this.age = age;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    // Métodos para userId
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

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


