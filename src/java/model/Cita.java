package model;

import java.time.LocalDate;

public class Cita {
    private String codigo;
    private LocalDate fecha;
    private double costo;
    private String hora;       // HH:mm
    private String motivo;
    private double observaciones; // según tu UML: double
    private String estado;
    private double duracion;   // horas (ej: 0.5 = 30 min)

    private Veterinario veterinario;
    private Mascota mascota;

    public Cita(String codigo, LocalDate fecha, double costo, String hora, String motivo,
                double observaciones, String estado, double duracion,
                Veterinario veterinario, Mascota mascota) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.costo = costo;
        this.hora = hora;
        this.motivo = motivo;
        this.observaciones = observaciones;
        this.estado = estado;
        this.duracion = duracion;
        this.veterinario = veterinario;
        this.mascota = mascota;
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public double getObservaciones() { return observaciones; }
    public void setObservaciones(double observaciones) { this.observaciones = observaciones; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public double getDuracion() { return duracion; }
    public void setDuracion(double duracion) { this.duracion = duracion; }

    public Veterinario getVeterinario() { return veterinario; }
    public void setVeterinario(Veterinario veterinario) { this.veterinario = veterinario; }

    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }
}