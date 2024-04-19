package com.vichito.polimorfismovic.Models;

import java.util.ArrayList;
import java.util.Iterator;

public class dBasedata implements ICRUD{

    private ArrayList<Estudiante> estudiantes1 = new ArrayList();

    public dBasedata(){
    }


    public void saveEstudiante(Estudiante estudiante) {this.estudiantes1.add(estudiante);}


        public void updateEstudiante(Estudiante estudiante) {
            Iterator var2 = this.estudiantes1.iterator();

            Estudiante e;
            do {
                if (!var2.hasNext()) {
                    return;
                }
                e = (Estudiante) var2.next();
            } while (e.getMatricula() != estudiante.getMatricula());

            e.setNombre(estudiante.getNombre());
            e.setApellido(estudiante.getApellido());
    }

       public void imprimirEstudiantes(){
           System.out.println("Estudiantes en dBasedata");
           Iterator var1 = this.estudiantes1.iterator();

           while (var1.hasNext()) {
               Estudiante a = (Estudiante) var1.next();
               System.out.println(a);
           }
       }

    public ArrayList<Estudiante> getEstudiante() { return new ArrayList<>(estudiantes1);}
}
