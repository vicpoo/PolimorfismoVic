package com.vichito.polimorfismovic.Models;

import java.util.ArrayList;

public class SQLdata implements ICRUD{

    private ArrayList<Estudiante> estudiantes3 = new ArrayList<>();

    @Override
        public void saveEstudiante(Estudiante estudiante) {estudiantes3.add(estudiante);}


    @Override
    public void updateEstudiante(Estudiante estudiante) {
        for (Estudiante e : estudiantes3){
            if (e.getMatricula() == estudiante.getMatricula()){
                e.setNombre(estudiante.getNombre());
                e.setApellido(estudiante.getApellido());
                return;
            }
        }
    }

        public void printEstudiantes() {
            System.out.println("Estudiantes en SQLdata:");
            for (Estudiante o : estudiantes3){
                System.out.println(o);
            }
        }


        @Override
    public ArrayList<Estudiante> getEstudiante(){ return new ArrayList<>(estudiantes3);}

}
