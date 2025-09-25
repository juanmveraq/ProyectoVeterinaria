package     model;

import java.util.ArrayList;
import java.util.List;

public class Mascota {
    private String nombre;
    private String codigo;
    private String especie;
    private String raza;
    private byte edad;
    private String color;
    private double peso;

    private Propietario propietario;

    // Enfermedad mascota
    private List<String> afecciones = new ArrayList<>();

    public Mascota(String nombre, String codigo, String especie, String raza,
                   byte edad, String color, double peso, Propietario propietario) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.color = color;
        this.peso = peso;
        this.propietario = propietario;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public byte getEdad() { return edad; }
    public void setEdad(byte edad) { this.edad = edad; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public Propietario getPropietario() { return propietario; }
    public void setPropietario(Propietario propietario) { this.propietario = propietario; }

    public List<String> getAfecciones() { return afecciones; }
    public void agregarAfeccion(String afeccion) {
        if (afeccion != null && !afeccion.isBlank()) afecciones.add(afeccion.trim());
    }

    @Override
    public String toString() {
        return nombre + " (" + especie + ", " + raza + ", edad " + edad + " años, código " + codigo + ")";
    }
}