package app;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class Main {

    private static void precargarVeterinarios(Veterinaria vet) {
        vet.registrarVeterinario(new Veterinario(
                "Carlos Gómez", "VET-1001", "TP-11111", 6,
                new Especialidad(Especialidad.CIRUGIA), "3001112222", "carlos@vet.com"));
        vet.registrarVeterinario(new Veterinario(
                "María Torres", "VET-1002", "TP-22222", 8,
                new Especialidad(Especialidad.DERMATOLOGIA), "3003334444", "maria@vet.com"));
        vet.registrarVeterinario(new Veterinario(
                "Ana Ruiz", "VET-1003", "TP-33333", 4,
                new Especialidad(Especialidad.MEDICINA_INTERNA), "3005556666", "ana@vet.com"));
    }

    public static void main(String[] args) {
        // Clínica
        Veterinaria veterinaria = new Veterinaria(
                "Veterinaria Patitas Felices",
                "601-555-1234",
                "Calle 123 #45-67",
                "NIT 900123456-7"
        );
        precargarVeterinarios(veterinaria);

        // Registro Propietario
        JTextField nomProp = new JTextField();
        JTextField apeProp = new JTextField();
        JTextField idProp = new JTextField();
        JTextField telProp = new JTextField();
        JTextField dirProp = new JTextField();

        JPanel panelProp = new JPanel(new GridLayout(0, 2));
        panelProp.add(new JLabel("Nombres:")); panelProp.add(nomProp);
        panelProp.add(new JLabel("Apellidos:")); panelProp.add(apeProp);
        panelProp.add(new JLabel("Identificación:")); panelProp.add(idProp);
        panelProp.add(new JLabel("Teléfono:")); panelProp.add(telProp);
        panelProp.add(new JLabel("Dirección:")); panelProp.add(dirProp);

        JOptionPane.showConfirmDialog(null, panelProp, "Registro Propietario", JOptionPane.OK_CANCEL_OPTION);

        Propietario propietario = new Propietario(
                nomProp.getText(), apeProp.getText(), idProp.getText(), telProp.getText(), dirProp.getText()
        );
        veterinaria.registrarPropietario(propietario);

        // Registro Mascota
        JTextField nomMasc = new JTextField();
        JTextField codMasc = new JTextField();
        JTextField espMasc = new JTextField();
        JTextField razaMasc = new JTextField();
        JTextField edadMasc = new JTextField();
        JTextField colorMasc = new JTextField();
        JTextField pesoMasc = new JTextField();

        JPanel panelMasc = new JPanel(new GridLayout(0, 2));
        panelMasc.add(new JLabel("Nombre:")); panelMasc.add(nomMasc);
        panelMasc.add(new JLabel("Código:")); panelMasc.add(codMasc);
        panelMasc.add(new JLabel("Especie:")); panelMasc.add(espMasc);
        panelMasc.add(new JLabel("Raza:")); panelMasc.add(razaMasc);
        panelMasc.add(new JLabel("Edad (años):")); panelMasc.add(edadMasc);
        panelMasc.add(new JLabel("Color:")); panelMasc.add(colorMasc);
        panelMasc.add(new JLabel("Peso (kg):")); panelMasc.add(pesoMasc);

        JOptionPane.showConfirmDialog(null, panelMasc, "Registro Mascota", JOptionPane.OK_CANCEL_OPTION);

        Mascota mascota = new Mascota(
                nomMasc.getText(),
                codMasc.getText(),
                espMasc.getText(),
                razaMasc.getText(),
                Byte.parseByte(edadMasc.getText()),
                colorMasc.getText(),
                Double.parseDouble(pesoMasc.getText()),
                propietario
        );
        veterinaria.registrarMascota(mascota);

        // Agendar Cita
        JTextField codCita = new JTextField();
        JTextField fechaTxt = new JTextField("2025-09-01");
        JTextField horaTxt = new JTextField("10:00");
        JTextField motivo = new JTextField();
        JTextField duracionTxt = new JTextField("1");

        JPanel panelCita = new JPanel(new GridLayout(0, 2));
        panelCita.add(new JLabel("Código Cita:")); panelCita.add(codCita);
        panelCita.add(new JLabel("Fecha (AAAA-MM-DD):")); panelCita.add(fechaTxt);
        panelCita.add(new JLabel("Hora (HH:MM):")); panelCita.add(horaTxt);
        panelCita.add(new JLabel("Motivo:")); panelCita.add(motivo);
        panelCita.add(new JLabel("Duración (horas):")); panelCita.add(duracionTxt);

        JOptionPane.showConfirmDialog(null, panelCita, "Agendar Cita", JOptionPane.OK_CANCEL_OPTION);

        LocalDate fecha = LocalDate.parse(fechaTxt.getText());
        String hora = horaTxt.getText();
        double duracion = Double.parseDouble(duracionTxt.getText());

        // costo quemado por hora
        final double COSTO_POR_HORA = 20000.0;
        double costo = COSTO_POR_HORA * duracion;

        double observaciones = 0.0;
        String estado = "PROGRAMADA";

        // Veterinarios disponibles
        List<Veterinario> disponibles = veterinaria.veterinariosDisponibles(fecha, hora, duracion, null);
        if (disponibles.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay veterinarios disponibles en ese horario.");
            return;
        }

        // Mostrar lista detallada de veterinarios
        String[] opciones = disponibles.stream()
                .map(Veterinario::toString) // usa el toString con especialidad e ID
                .toArray(String[]::new);

        String sel = (String) JOptionPane.showInputDialog(null, "Seleccione veterinario:",
                "Veterinarios disponibles", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        Veterinario vetSel = disponibles.get(java.util.Arrays.asList(opciones).indexOf(sel));

        Cita cita = new Cita(codCita.getText(), fecha, costo, hora, motivo.getText(),
                observaciones, estado, duracion, vetSel, mascota);
        veterinaria.registrarCita(cita);

        // Resumen
        String resumen = """
                === RESUMEN ===
                Propietario: %s
                Mascota: %s
                Cita: %s | %s %s
                Veterinario:
                  - Nombre: %s
                  - ID: %s
                  - Tarjeta Profesional: %s
                  - Especialidad: %s
                  - Años de experiencia: %d
                  - Teléfono: %s
                  - Correo: %s
                Duración: %.1f h
                Total: $%.2f
                """.formatted(
                propietario, mascota,
                cita.getCodigo(), cita.getFecha(), cita.getHora(),
                vetSel.getNombres(),
                vetSel.getIdentificacion(),
                vetSel.getTarjetaProfesional(),
                vetSel.getEspecialidad().getNombreEspecialidad(),
                vetSel.getAniosExperiencia(),
                vetSel.getTelefono(),
                vetSel.getCorreo(),
                duracion, costo
        );

        JOptionPane.showMessageDialog(null, resumen);
    }
}