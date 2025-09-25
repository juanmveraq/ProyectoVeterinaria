package Model;

public class Mascota {
    private String id;
    private String nombre;
    private String especie;
    private String raza;
    private String edad;
    private String color;
    private double peso;
    private String enfermedades;

    public Mascota(String id, String nombre, String especie, String raza, String edad, String color, double peso, String enfermedades) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.color = color;
        this.peso = peso;
        this.enfermedades = enfermedades;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaza() {
        return raza;
    }

    public String getEdad() {
        return edad;
    }

    public String getColor() {
        return color;
    }

    public double getPeso() {
        return peso;
    }

    public String getEnfermedades() {
        return enfermedades;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setEnfermedades(String enfermedades) {
        this.enfermedades = enfermedades;
    }
}
