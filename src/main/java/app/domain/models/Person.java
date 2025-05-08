package app.domain.models;

public class Person {
    private long document;
    private String name;
    private int age;
    private String role;

    public Person() {}

    public Person(long document, String name, int age, String role) {
        this.document = document;
        this.name = name;
        this.age = age;
        this.role = role;
    }

    public long getDocument() { return document; }
    public void setDocument(long document) { this.document = document; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

