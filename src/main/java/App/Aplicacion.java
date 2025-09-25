import Model.Mascota;
public static void main(String[] args) {
    String id = "01";
    String nombre = "Thanatos";
    String especie = "Gato";
    String raza = "Rayado";
    String edad = "8 meses";
    String color = "Negro, gris, blanco y amarillo";
    double peso = 1.6;
    String enfermedades = "Ninguna";

    Mascota mascota1 = new Mascota(id, nombre, especie, raza, edad, color, peso, enfermedades);

}
