package com.mycompany.ejercicio2;

import java.util.Scanner;

/**
 *
 * @author dalto
 */
public class Ejercicio2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese la edad del estudiante: ");
        int edad = sc.nextInt();
        Estudiante estudiante = new Estudiante(nombre, edad);
        sc.nextLine();

        System.out.print("Ingrese el nombre de la materia: ");
        String nombreMateria = sc.nextLine();
        System.out.print("Ingrese la nota ACD (sobre 3.5): ");
        double acd = sc.nextDouble();
        System.out.print("Ingrese la nota APE (sobre 3.5): ");
        double ape = sc.nextDouble();
        System.out.print("Ingrese la nota AA (sobre 3.0): ");
        double aa = sc.nextDouble();

        Materia materia = new Materia(nombreMateria, acd, ape, aa);
        estudiante.asignarMateria(materia);
        estudiante.verificarAprobacion();
    }
}

class Estudiante {

    public String nombre;
    public int edad;
    public Materia materia;

    public Estudiante(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public void asignarMateria(Materia materia) {
        this.materia = materia;
    }

    public void verificarAprobacion() {
        if (materia == null) {
            System.out.println("El estudiante no tiene esta materia asignada.");
            return;
        }
        System.out.println("Estudiante: " + nombre + " - Edad: " + edad);
        System.out.println(materia);

        if (materia.determinarAprobado()) {
            System.out.println("Resultado: Aprobado");
        } else {
            System.out.println("Resultado: No Aprobo");
            System.out.println("El estudiante debe rendir examen de recuperación (mínimo 3.5/10).");
            double notaRec = materia.notaRecuperacion(3.5);
            System.out.println("Nota con recuperación: " + notaRec + "/10.0");
        }
    }
}

class Materia {

    public String nombre;
    public double acd;
    public double ape;
    public double aa;

    public Materia(String nombre, double acd, double ape, double aa) {
        this.nombre = nombre;
        this.acd = acd;
        this.ape = ape;
        this.aa = aa;
    }

    public double calcularNotaTotal() {
        return acd + ape + aa;
    }

    public boolean determinarAprobado() {
        double total = calcularNotaTotal();
        return (total / 10.0) >= 0.7;
    }

    public double notaRecuperacion(double examenRecuperacion) {
        double parcial = calcularNotaTotal() * 0.6;
        return parcial + examenRecuperacion;
    }

    @Override
    public String toString() {
        return "Materia: " + nombre
                + "\nACD: " + acd + "/3.5"
                + "\nAPE: " + ape + "/3.5"
                + "\nAA: " + aa + "/3.0"
                + "\nNota Total: " + calcularNotaTotal() + "/10.0";
    }
}
