package com.vichito.polimorfismovic.Models;

public class Politecnica {

    private ICRUD crud;

    public Politecnica(ICRUD crud) { this.crud = crud; }

    public void saveEstudiante(int matricula, String nombre , String apellido , int edad){
        Estudiante estudiante = new Estudiante(nombre,apellido,edad,matricula);
        crud.saveEstudiante(estudiante);
    }

    public void updateEstudiante(String nombre , String apellido,int matricula, int edad){
        Estudiante estudiante = new Estudiante(nombre,apellido,matricula,edad);
        crud.updateEstudiante(estudiante);
    }

}
