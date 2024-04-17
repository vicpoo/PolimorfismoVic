package com.vichito.polimorfismovic.Models;

import java.util.ArrayList;

public class Oracledata implements ICRUD{

        private ArrayList<Estudiante> estudiantes2 = new ArrayList<>();

     @Override
    public void saveEstudiante(Estudiante estudiante) {estudiantes2.add(estudiante);}

    @Override
    public void updateEstudiante(Estudiante estudiante) {
        for (Estudiante e : estudiantes2) {
            if (e.getMatricula() == estudiante.getMatricula()) {
                e.setNombre(estudiante.getNombre());
                e.setApellido(estudiante.getApellido());
                return;
            }
        }
    }

        public void imprimirEstudiantes() {
            System.out.println("Estudiantes en Oracledata: ");
            for (Estudiante o : estudiantes2) {
                System.out.println(o);
            }
        }

      @Override
    public ArrayList<Estudiante> getEstudiante(){ return new ArrayList<>(estudiantes2);}

}
