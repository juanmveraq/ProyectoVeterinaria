package Model;

import java.util.ArrayList;

public class Veterinaria {
    private String ubicacion;
    private String nombre;
    private int nit;
    private ArrayList <Mascota> listaMascotas;

    public Veterinaria (String ubicacion, String nombre, int nit) {
        this.ubicacion = ubicacion;
        this.nombre = nombre;
        this.nit = nit;
        this.listaMascotas = new ArrayList();
    }

    public ArrayList getListaMascotas() {
        return listaMascotas;
    }

    public void setListaMascotas(ArrayList<Mascota> listaMascotas) {
        this.listaMascotas = listaMascotas;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    /**
     * This method allows you to register a new pet. If the pet ID already exist you will get an error.
     * @param newMascota
     * @return
     */
    public String registrarMasccota(Mascota newMascota){
        String resultado = "";

        Mascota mascotaEncontrado = null;

        mascotaEncontrado = buscarMascota(Mascota.getid);

        if (mascotaEncontrado == null){
            resultado = "Se ha encontrado una mascota con éste ID, porfavor use otro ID o ésta mascota ya está registrada";
        }else{
            resultado = "Se ha registrado la mascota correctamente.";
        }
        return resultado;
    }

    /**
     * This method allow you to find a mascot by their ID.
     * @param id
     * @return
     */
    public Mascota buscarMascota(String id){
        Mascota resultado = null;
        for(int i = 0; i < listaMascotas.size(); i++){
           Mascota mascotaAuxiliar = listaMascotas.get(i);
           if(mascotaAuxiliar.equals(id)){

           }
        }
        return resultado;
    }

}

