package com.vichito.polimorfismovic.Models;

import java.util.ArrayList;
import java.util.Iterator;

public class SQLdata implements ICRUD{

    private ArrayList<Estudiante> estudiantes3 = new ArrayList<>();

    public SQLdata(){
    }


    public void saveEstudiante(Estudiante estudiante) {this.estudiantes3.add(estudiante);}



    public void updateEstudiante(Estudiante estudiante) {
        Iterator var2 = this.estudiantes3.iterator();

        Estudiante i;
        do {
            if (!var2.hasNext()) {
                return;
            }
            i= (Estudiante)var2.next();
        } while (i.getMatricula() != estudiante.getMatricula());

        i.setNombre(estudiante.getNombre());
        i.setApellido(estudiante.getApellido());

    }

        public void imprimirEstudiantes() {
            System.out.println("Estudintes en SQLdata");
            Iterator var1 = this.estudiantes3.iterator();

            while (var1.hasNext()){
                Estudiante o = (Estudiante) var1.next();
                System.out.println(o);
            }
        }


    public ArrayList<Estudiante> getEstudiante(){ return new ArrayList<>(estudiantes3);}

}
