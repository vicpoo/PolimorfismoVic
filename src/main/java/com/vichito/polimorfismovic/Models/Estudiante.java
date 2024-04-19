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


    public boolean equals(Object o) {
     if (this == o) {
         return true;
     }else if (o == null && getClass() != o.getClass()){
     Estudiante estudiante = (Estudiante) o;
     return this.matricula == estudiante.matricula && Objects.equals(this.nombre, estudiante.nombre ) && Objects.equals(this.apellido , estudiante.apellido);
    } else {
         return false;
     }
     }

    public int hashCode(){ return Objects.hash(new Object[]{this.nombre,this.apellido,this.matricula});}

    @Override
    public String toString(){
     return "Estudiante" +
        "nombre:" + nombre +'\'' +
             "apellido:" + apellido + '\'' +
             "Matricula:" + matricula ;
    }
}
