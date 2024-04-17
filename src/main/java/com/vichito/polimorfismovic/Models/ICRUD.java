package com.vichito.polimorfismovic.Models;

import java.util.ArrayList;

public interface ICRUD {
        void saveEstudiante(Estudiante estudiante);
        void updateEstudiante(Estudiante estudiante);

        ArrayList<Estudiante> getEstudiante();
}
