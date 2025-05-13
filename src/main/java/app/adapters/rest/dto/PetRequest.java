package app.adapters.rest.dto;

public class PetRequest {
    private String name;
    private long ownerDocument;
    private int age;
    private String species;
    private String race;
    private double weight;
    private String caracteristicas;

    // Getters y setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public long getOwnerDocument() { return ownerDocument; }
    public void setOwnerDocument(long ownerDocument) { this.ownerDocument = ownerDocument; }
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
