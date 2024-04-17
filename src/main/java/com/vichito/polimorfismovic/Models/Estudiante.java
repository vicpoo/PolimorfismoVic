package com.vichito.polimorfismovic.Models;

import java.util.Objects;

public class Estudiante {

    private String nombre;
    private String apellido;
    private int matricula;
    private int edad;


    private ICRUD crud;

    public Estudiante(String nombre, String apellido, int matricula, int edad) {
       this.nombre = nombre;
       this.apellido = apellido;
       this.matricula = matricula;
       this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }


    @Override
    public boolean equals(Object o) {
     if (this == o) return true;
     if (o == null || getClass() != o.getClass()) return false;
     Estudiante estudiante = (Estudiante) o;
     return matricula == estudiante.matricula && Objects.equals(nombre, estudiante.nombre ) && Objects.equals(apellido , estudiante.apellido);
    }

    @Override
    public int hashCode(){ return Objects.hash(nombre,apellido,matricula);}

    @Override
    public String toString(){
     return "Estudiante" +
        "nombre:" + nombre +'\'' +
             "apellido:" + apellido + '\'' +
             "Matricula:" + matricula ;
    }
}
