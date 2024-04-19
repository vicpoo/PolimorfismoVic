package com.vichito.polimorfismovic.Controllers;

import com.vichito.polimorfismovic.Models.SQLdata;
import com.vichito.polimorfismovic.Models.Oracledata;
import com.vichito.polimorfismovic.Models.dBasedata;

import com.vichito.polimorfismovic.Models.ICRUD;
import com.vichito.polimorfismovic.Models.Estudiante;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;

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

    public guardarController(){
    }

    public void initialize() {
        this.basesDatos = new ArrayList<>();
        this.basesDatos.add(new dBasedata());
        this.basesDatos.add(new Oracledata());
        this.basesDatos.add(new SQLdata());
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

            Iterator var6 = this.basesDatos.iterator();

            ICRUD baseDato;

            while (var6.hasNext()) {
                baseDato = (ICRUD) var6.next();
                if (baseDato.getEstudiante().contains(nuevoEstudiante)) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                if (this.estudiante != null) {
                    this.estudiante.setNombre(nombre);
                    this.estudiante.setApellido(apellido);
                    this.estudiante.setMatricula(matricula);
                    this.estudiante.setEdad(edad);
                    var6 = this.basesDatos.iterator();

                    while (var6.hasNext()) {
                        baseDato = (ICRUD) var6.next();
                        baseDato.saveEstudiante(this.estudiante);
                    }
                } else {
                    this.estudiante = nuevoEstudiante;
                    var6 = this.basesDatos.iterator();

                    while (var6.hasNext()) {
                        baseDato = (ICRUD) var6.next();
                        baseDato.saveEstudiante(this.estudiante);
                    }
                }
                this.cerrar();
            } else {
        this.mostrarAlerta("Estudiante ya existe");
            }
        } catch (NumberFormatException var10) {
                    this.mostrarAlerta("La matricula debe de ser entero");
        }
    }


    private void cerrar() {
        Stage stage = (Stage) this.AgregarButton.getScene().getWindow();
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
