package com.vichito.polimorfismovic.Models;

import java.util.ArrayList;
import java.util.Iterator;

public class Oracledata implements ICRUD{



    private ArrayList<Estudiante> estudiantes2 = new ArrayList<>();

    public Oracledata(){
    }

    public void saveEstudiante(Estudiante estudiante) {this.estudiantes2.add(estudiante);}


    public void updateEstudiante(Estudiante estudiante) {
     Iterator var2 = this.estudiantes2.iterator();

     Estudiante a;
     do{
         if(!var2.hasNext()){
             return;
         }
         a = (Estudiante)var2.next();
     }  while (a.getMatricula() != estudiante.getMatricula());

     a.setNombre(estudiante.getNombre());
     a.setApellido(estudiante.getApellido());
    }

        public void imprimirEstudiantes() {
            System.out.println("Estudiantes en Oracledata ");
            Iterator var1 = this.estudiantes2.iterator();

            while (var1.hasNext()){
                Estudiante e = (Estudiante)var1.next();
                System.out.println(e);
            }
        }


    public ArrayList<Estudiante> getEstudiante(){ return new ArrayList<>(estudiantes2);}

}
