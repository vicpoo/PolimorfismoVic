package com.vichito.polimorfismovic.Models;

import java.util.ArrayList;

public class dBasedata implements ICRUD{

    private ArrayList<Estudiante> estudiantes1 = new ArrayList();


    @Override
    public void saveEstudiante(Estudiante estudiante) {estudiantes1.add(estudiante);}


    @Override
        public void updateEstudiante(Estudiante estudiante) {
        for (Estudiante a : estudiantes1){
            if (a.getMatricula() == estudiante.getMatricula()){
                a.setNombre(estudiante.getNombre());
                a.setApellido(estudiante.getApellido());
                return;
            }
        }
    }

       public void ImprimirEstudiantes(){
           System.out.println("Estudiantes en dBasedata:");
            for (Estudiante o : estudiantes1){
                System.out.println(o);
            }
       }

    @Override
    public ArrayList<Estudiante> getEstudiante() { return new ArrayList<>(estudiantes1);}
}
