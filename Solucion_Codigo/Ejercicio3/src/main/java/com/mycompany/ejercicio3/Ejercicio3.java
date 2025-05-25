package com.mycompany.ejercicio3;

import java.util.ArrayList;

/**
 *
 * @author dalto
 */
public class Ejercicio3 {

   public static void main(String[] args) {
        ArrayList<Departamento> listaDepartamentos = new ArrayList<>();
        Empresa empresaTemp = new Empresa();
        Departamento departamentoTemp = new Departamento();

        departamentoTemp.ingresarDepartamento("Ventas", 7, 400000, listaDepartamentos);
        departamentoTemp.ingresarDepartamento("RRHH", 15, 528900, listaDepartamentos);
        departamentoTemp.ingresarDepartamento("Administrativo", 8, 1000000, listaDepartamentos);

        empresaTemp.comprobarCategoria(listaDepartamentos);

        departamentoTemp.imprimirDepartamentos(listaDepartamentos);
    }
}

class Empresa {

    public String nombre;
    public int RUC;
    public String direccionAsignada;

    public Empresa() {
    }

    public Empresa(String nombre, int RUC, String direccionAsignada) {
        this.nombre = nombre;
        this.RUC = RUC;
        this.direccionAsignada = direccionAsignada;
    }

    public void comprobarCategoria(ArrayList<Departamento> listaDepartamentos) {
        for (Departamento departamento1 : listaDepartamentos) {
            if (departamento1.numeroEmpleados > 20 && departamento1.produccionAnual > 1000000) {
                departamento1.categoriaMerecida = 'A';
            } else if (departamento1.numeroEmpleados >= 20 && departamento1.produccionAnual == 1000000) {
                departamento1.categoriaMerecida = 'B';
            } else if (departamento1.numeroEmpleados >= 10 && departamento1.produccionAnual >= 500000) {
                departamento1.categoriaMerecida = 'C';
            } else {
                departamento1.categoriaMerecida = 'D';
            }
        }

    }

    @Override
    public String toString() {
        return "Empresa{"
                + "\nNombre: " + nombre
                + "\nRUC: " + RUC
                + "\nDireccion asignada: " + direccionAsignada
                + "\n}\n";
    }
}

class Departamento {

    public String nombre;
    public int numeroEmpleados;
    public double produccionAnual;
    public char categoriaMerecida = 'D';

    public Departamento() {
    }

    public Departamento(String nombre, int numeroEmpleados, double produccionAnual, char categoriaMerecida) {
        this.nombre = nombre;
        this.numeroEmpleados = numeroEmpleados;
        this.produccionAnual = produccionAnual;
        this.categoriaMerecida = categoriaMerecida;
    }

    public void ingresarDepartamento(String nombre, int numeroEmpleados, double produccionAnual, ArrayList<Departamento> listaDepartamentos) {
        listaDepartamentos.add(new Departamento(nombre, numeroEmpleados, produccionAnual, categoriaMerecida));
    }

    public void imprimirDepartamentos(ArrayList<Departamento> listaDepartamentos) {
        for (Departamento listaDepartamento : listaDepartamentos) {
            System.out.println(listaDepartamento.toString());
        }
    }

    @Override
    public String toString() {
        return "Departamento{"
                + "\nNombre:" + nombre
                + "\nNumero empleados:" + numeroEmpleados
                + "\nProduccion anual:" + produccionAnual
                + "\nCategoria merecida:" + categoriaMerecida
                + "\n}\n";
    }

}

