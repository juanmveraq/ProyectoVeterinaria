package model;

public class Especialidad {
    public static final int MEDICINA_INTERNA = 0;
    public static final int CIRUGIA = 1;
    public static final int DERMATOLOGIA = 2;

    private int tipo;

    public Especialidad(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() { return tipo; }
    public void setTipo(int tipo) { this.tipo = tipo; }

    public String getNombreEspecialidad() {
        switch (tipo) {
            case MEDICINA_INTERNA: return "Medicina Interna";
            case CIRUGIA: return "Cirugía";
            case DERMATOLOGIA: return "Dermatología";
            default: return "Desconocida";
        }
    }

    @Override
    public String toString() { return getNombreEspecialidad(); }
}