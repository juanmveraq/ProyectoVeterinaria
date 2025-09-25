package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Veterinaria {
    private String nombre;
    private String telefono;
    private String direccion;
    private String nit;

    private List<Mascota> listaMascotas = new ArrayList<>();
    private List<Propietario> listaPropietarios = new ArrayList<>();
    private List<Veterinario> listaVeterinarios = new ArrayList<>();
    private List<Cita> listaCitas = new ArrayList<>();

    public Veterinaria(String nombre, String telefono, String direccion, String nit) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.nit = nit;
    }

    // Mascotas
    public boolean registrarMascota(Mascota mascota) {
        if (buscarMascotaPorCodigo(mascota.getCodigo()) != null) {
            System.out.println("La mascota con código " + mascota.getCodigo() + " ya existe.");
            return false;
        }
        // Asegurar propietario registrado
        if (buscarPropietarioPorId(mascota.getPropietario().getIdentificacion()) == null) {
            registrarPropietario(mascota.getPropietario());
        }
        listaMascotas.add(mascota);
        return true;
    }

    public Mascota buscarMascotaPorCodigo(String codigo) {
        for (Mascota m : listaMascotas) if (m.getCodigo().equals(codigo)) return m;
        return null;
    }

    public boolean eliminarMascota(String codigo) {
        Mascota m = buscarMascotaPorCodigo(codigo);
        return m != null && listaMascotas.remove(m);
    }

    public List<Mascota> getListaMascotas() { return listaMascotas; }

    // Propietarios
    public boolean registrarPropietario(Propietario propietario) {
        if (buscarPropietarioPorId(propietario.getIdentificacion()) != null) {
            System.out.println("El propietario con ID " + propietario.getIdentificacion() + " ya existe.");
            return false;
        }
        listaPropietarios.add(propietario);
        return true;
    }

    public Propietario buscarPropietarioPorId(String identificacion) {
        for (Propietario p : listaPropietarios)
            if (p.getIdentificacion().equals(identificacion)) return p;
        return null;
    }

    public List<Propietario> getListaPropietarios() { return listaPropietarios; }

    // Veterinarios
    public boolean registrarVeterinario(Veterinario veterinario) {
        if (buscarVeterinarioPorId(veterinario.getIdentificacion()) != null) {
            System.out.println("El veterinario con ID " + veterinario.getIdentificacion() + " ya existe.");
            return false;
        }
        listaVeterinarios.add(veterinario);
        return true;
    }

    public Veterinario buscarVeterinarioPorId(String identificacion) {
        for (Veterinario v : listaVeterinarios)
            if (v.getIdentificacion().equals(identificacion)) return v;
        return null;
    }

    public List<Veterinario> getListaVeterinarios() { return listaVeterinarios; }

    public List<Veterinario> veterinariosPorEspecialidad(int tipoEspecialidad) {
        return listaVeterinarios.stream()
                .filter(v -> v.getEspecialidad().getTipo() == tipoEspecialidad)
                .collect(Collectors.toList());
    }

    // Citas y disponibilidad
    public boolean estaDisponible(Veterinario vet, LocalDate fecha, String hora, double duracionHoras) {
        int ini = aMinutos(hora);
        int fin = ini + (int)Math.round(duracionHoras * 60);

        for (Cita c : listaCitas) {
            if (!c.getVeterinario().getIdentificacion().equals(vet.getIdentificacion())) continue;
            if (!c.getFecha().equals(fecha)) continue;

            int iniC = aMinutos(c.getHora());
            int finC = iniC + (int)Math.round(c.getDuracion() * 60);

            boolean solapan = ini < finC && iniC < fin;
            if (solapan) return false;
        }
        return true;
    }

    public boolean registrarCita(Cita cita) {
        // validar existencia de entidades
        if (buscarMascotaPorCodigo(cita.getMascota().getCodigo()) == null) {
            System.out.println("La mascota no está registrada.");
            return false;
        }
        Propietario prop = cita.getMascota().getPropietario();
        if (buscarPropietarioPorId(prop.getIdentificacion()) == null) {
            System.out.println("El propietario no está registrado.");
            return false;
        }
        if (buscarVeterinarioPorId(cita.getVeterinario().getIdentificacion()) == null) {
            System.out.println("El veterinario no está registrado.");
            return false;
        }
        // disponibilidad
        if (!estaDisponible(cita.getVeterinario(), cita.getFecha(), cita.getHora(), cita.getDuracion())) {
            System.out.println("El veterinario ya tiene una cita en ese horario.");
            return false;
        }
        // codigo ya existe
        if (buscarCitaPorCodigo(cita.getCodigo()) != null) {
            System.out.println("Ya existe una cita con código " + cita.getCodigo());
            return false;
        }
        listaCitas.add(cita);
        return true;
    }

    public boolean cancelarCita(String codigoCita) {
        Cita c = buscarCitaPorCodigo(codigoCita);
        return c != null && listaCitas.remove(c);
    }

    public boolean reprogramarCita(String codigoCita, LocalDate nuevaFecha, String nuevaHora,
                                   double nuevaDuracion, Veterinario nuevoVet) {
        Cita c = buscarCitaPorCodigo(codigoCita);
        if (c == null) return false;

        Veterinario vet = (nuevoVet != null) ? nuevoVet : c.getVeterinario();
        double dur = (nuevaDuracion > 0) ? nuevaDuracion : c.getDuracion();
        String h = (nuevaHora != null) ? nuevaHora : c.getHora();
        LocalDate f = (nuevaFecha != null) ? nuevaFecha : c.getFecha();

        if (!estaDisponible(vet, f, h, dur)) return false;

        c.setVeterinario(vet);
        c.setFecha(f);
        c.setHora(h);
        c.setDuracion(dur);
        return true;
    }

    public Cita buscarCitaPorCodigo(String codigo) {
        for (Cita c : listaCitas) if (c.getCodigo().equals(codigo)) return c;
        return null;
    }

    public List<Cita> buscarCitasPorVeterinario(String idVet) {
        return listaCitas.stream()
                .filter(c -> c.getVeterinario().getIdentificacion().equals(idVet))
                .collect(Collectors.toList());
    }

    public List<Cita> buscarCitasPorPropietario(String idProp) {
        return listaCitas.stream()
                .filter(c -> c.getMascota().getPropietario().getIdentificacion().equals(idProp))
                .collect(Collectors.toList());
    }

    public List<Cita> buscarCitasPorFecha(LocalDate fecha) {
        return listaCitas.stream().filter(c -> c.getFecha().equals(fecha)).collect(Collectors.toList());
    }

    public List<Veterinario> veterinariosDisponibles(LocalDate fecha, String hora, double duracionHoras, Integer tipoEsp) {
        return listaVeterinarios.stream()
                .filter(v -> tipoEsp == null || v.getEspecialidad().getTipo() == tipoEsp)
                .filter(v -> estaDisponible(v, fecha, hora, duracionHoras))
                .collect(Collectors.toList());
    }

    public double ingresosPorFecha(LocalDate fecha) {
        return listaCitas.stream()
                .filter(c -> c.getFecha().equals(fecha))
                .mapToDouble(Cita::getCosto)
                .sum();
    }

    private int aMinutos(String hhmm) {
        String[] p = hhmm.split(":");
        int h = Integer.parseInt(p[0]);
        int m = Integer.parseInt(p[1]);
        return h * 60 + m;
    }

    // Getters y Setters de la clínica
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }

    public List<Cita> getListaCitas() { return listaCitas; }
}