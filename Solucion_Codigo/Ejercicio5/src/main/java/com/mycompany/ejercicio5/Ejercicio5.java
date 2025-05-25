package com.mycompany.ejercicio5;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.util.Random;

public class Ejercicio5 {

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner tcl = new Scanner(System.in);
        char opcion = 'S';
        String nombresEventos[] = {"Batalla", "Tratado de paz", "Reunion Diplomatica"};

        while (Character.toUpperCase(opcion) == 'S') {
            ArrayList<Pais> paises1 = new ArrayList<>(Arrays.asList(
                new Pais("Ecuador", false, 100, 10),
                new Pais("Venezuela", false, 100, 10),
                new Pais("Peru", false, 100, 10)
            ));

            ArrayList<Pais> paises2 = new ArrayList<>(Arrays.asList(
                new Pais("Rusia", true, 100, 60),
                new Pais("Ucrania", false, 100, 10),
                new Pais("China", true, 100, 10)
            ));

            ArrayList<Evento> eventos = new ArrayList<>(Arrays.asList(
                new Evento(nombresEventos[rand.nextInt(nombresEventos.length)],
                           LocalDate.of(2025, 5, 15), "Europa",
                           "Problemas de territorio", false, paises1),
                new Evento(nombresEventos[rand.nextInt(nombresEventos.length)],
                           LocalDate.of(2024, 5, 15), "Europa",
                           "Problemas politicos", true, paises2)
            ));

            Conflicto conflicto = new Conflicto("Reseteo", LocalDate.of(2025, 5, 15), 100, eventos);
            conflicto.actualizarEstado();
            System.out.println(conflicto);

            System.out.print("Volver a Simular? (S/N): ");
            opcion = tcl.next().toUpperCase().charAt(0);
        }
    }
}

class Conflicto {
    public String nombre;
    public LocalDate fechaInicio;
    public String estadoActual;
    public int totalPaisesMundo;
    public ArrayList<Evento> eventos;

    public Conflicto(String nombre, LocalDate fechaInicio, int totalPaisesMundo, ArrayList<Evento> eventos) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.totalPaisesMundo = totalPaisesMundo;
        this.eventos = eventos;
    }

    public void actualizarEstado() {
        int numeroPaisesEnBatalla = 0;

        for (Evento evento : eventos) {
            if (evento.nombre.equals("Batalla")) {
                numeroPaisesEnBatalla += evento.paises.size();
            }
        }

        double porcentajePaisesBatalla = (numeroPaisesEnBatalla * 100.0) / totalPaisesMundo;

        if (porcentajePaisesBatalla > 50) {
            estadoActual = "Guerra Mundial";
        } else if (porcentajePaisesBatalla > 30) {
            estadoActual = "Convocar a la ONU con caracter de URGENTE";
        } else {
            estadoActual = "Conflicto Localizado";
        }

        for (Evento evento : eventos) {
            if (evento.usoArmasNucleares) {
                for (Pais pais : evento.paises) {
                    if (pais.estadoDesarrollo) {
                        estadoActual = "Guerra Mundial";
                        return;
                    }
                }
            }
        }

        for (Evento evento : eventos) {
            for (Pais pais : evento.paises) {
                double porcentajeBajas = (pais.numeroBajas * 100.0) / pais.totalHabitantes;
                if (porcentajeBajas > 50) {
                    estadoActual = "Convocar a la ONU con caracter de URGENTE";
                    return;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre del Conflicto: ").append(nombre).append("\n");
        sb.append("Fecha de Inicio: ").append(fechaInicio).append("\n");
        sb.append("Estado Actual: ").append(estadoActual).append("\n");
        sb.append("Total de Paises del Mundo: ").append(totalPaisesMundo).append("\n\n");

        for (Evento evento : eventos) {
            sb.append("Evento\n").append(evento).append("\n");
        }

        return sb.toString();
    }
}

class Evento {
    public String nombre;
    public LocalDate fecha;
    public String ubicacion;
    public String descripcion;
    public boolean usoArmasNucleares;
    public ArrayList<Pais> paises;

    public Evento(String nombre, LocalDate fecha, String ubicacion,
                  String descripcion, boolean usoArmasNucleares, ArrayList<Pais> paises) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.usoArmasNucleares = usoArmasNucleares;
        this.paises = paises;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Fecha: ").append(fecha).append("\n");
        sb.append("Ubicacion: ").append(ubicacion).append("\n");
        sb.append("Descripcion: ").append(descripcion).append("\n");
        sb.append("Uso de Armas Nucleares: ").append(usoArmasNucleares).append("\n");

        for (Pais pais : paises) {
            sb.append(pais).append("\n");
        }

        return sb.toString();
    }
}

class Pais {
    public String nombre;
    public boolean estadoDesarrollo;
    public int totalHabitantes;
    public int numeroBajas;

    public Pais(String nombre, boolean estadoDesarrollo, int totalHabitantes, int numeroBajas) {
        this.nombre = nombre;
        this.estadoDesarrollo = estadoDesarrollo;
        this.totalHabitantes = totalHabitantes;
        this.numeroBajas = numeroBajas;
    }

    @Override
    public String toString() {
        return " Pais: " + nombre + "\n" +
               "    Desarrollo: " + estadoDesarrollo + "\n" +
               "    Habitantes: " + totalHabitantes + "\n" +
               "    Bajas: " + numeroBajas;
    }
}
