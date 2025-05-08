package app.domain.models;

public class Pet {
    private long petId;
    private String name;
    private Person owner;
    private int age;
    private String species;
    private String race;
    private double weight;
    private String caracteristicas;

    // Constructor vacío
    public Pet() {}

    // Constructor completo (opcional)
    public Pet(long petId, String name, Person owner, int age, String species, String race, double weight, String caracteristicas) {
        this.petId = petId;
        this.name = name;
        this.owner = owner;
        this.age = age;
        this.species = species;
        this.race = race;
        this.weight = weight;
        this.caracteristicas = caracteristicas;
    }

    // Constructor completo (nuevo, para los adapters)
    public Pet(String name, Person owner, int age, long petId, String species, String race, double weight, String caracteristicas) {
        this.name = name;
        this.owner = owner;
        this.age = age;
        this.petId = petId;
        this.species = species;
        this.race = race;
        this.weight = weight;
        this.caracteristicas = caracteristicas;
    }

    // Getters y setters
    public long getPetId() { return petId; }
    public void setPetId(long petId) { this.petId = petId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Person getOwner() { return owner; }
    public void setOwner(Person owner) { this.owner = owner; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getRace() { return race; }
    public void setRace(String race) { this.race = race; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public String getCaracteristicas() { return caracteristicas; }
    public void setCaracteristicas(String caracteristicas) { this.caracteristicas = caracteristicas; }
}



