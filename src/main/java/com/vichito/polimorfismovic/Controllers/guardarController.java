package com.vichito.polimorfismovic.Controllers;

import com.vichito.polimorfismovic.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class guardarController {

    @FXML
    private Button AgregarButton;

    @FXML
    private TextField ApellidoTxt;

    @FXML
    private TextField EdadTxt;

    @FXML
    private TextField MatriculaTxt;

    @FXML
    private TextField NombreTxt;


    private Estudiante estudiante;
    private ArrayList<ICRUD> basesDatos;

    public void initialize() {
        basesDatos = new ArrayList<>();
        basesDatos.add(new dBasedata());
        basesDatos.add(new Oracledata());
        basesDatos.add(new SQLdata());
    }

    public void initAtributos(Estudiante estudiante) {
        this.estudiante = estudiante;

        if (estudiante != null) {
            ApellidoTxt.setText(estudiante.getApellido());
            NombreTxt.setText(estudiante.getNombre());
            EdadTxt.setText(String.valueOf(estudiante.getEdad()));
            MatriculaTxt.setText(String.valueOf(estudiante.getMatricula()));
        }
    }


    @FXML
    void OnActionAgregarButton(ActionEvent event) {
        try {
            String nombre = NombreTxt.getText();
            String apellido = ApellidoTxt.getText();
            int matricula = Integer.parseInt(MatriculaTxt.getText());
            int edad = Integer.parseInt(EdadTxt.getText());

            Estudiante nuevoEstudiante = new Estudiante(nombre, apellido, matricula, edad);

            boolean existe = false;

            for (ICRUD database : basesDatos) {
                if (database.getEstudiante().contains(nuevoEstudiante)) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                if (estudiante != null) {
                    estudiante.setNombre(nombre);
                    estudiante.setEdad(edad);
                    estudiante.setMatricula(matricula);
                    estudiante.setApellido(apellido);
                    for (ICRUD database : basesDatos) {
                        database.saveEstudiante(estudiante);
                    }
                } else {
                    estudiante = nuevoEstudiante;
                    for (ICRUD database : basesDatos) {
                        database.saveEstudiante(estudiante);
                    }
                }
                cerrar();
            } else {
                mostrarAlerta("El Estudiante ya existe en alguna base de datos");
            }
        } catch (NumberFormatException e){
            mostrarAlerta("La matricula debe de ser un numero entero");
        }
    }

    private void cerrar() {
        Stage stage = (Stage) AgregarButton.getScene().getWindow();
        stage.close();
    }

        public void initAtributes(Estudiante estudiante){this.estudiante = estudiante;}

        public Estudiante getEstudiante(){return estudiante;}

    private void mostrarAlerta(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();

    }
}
