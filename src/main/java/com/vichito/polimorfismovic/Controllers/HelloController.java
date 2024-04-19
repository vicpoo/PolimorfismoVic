package com.vichito.polimorfismovic.Controllers;

import com.vichito.polimorfismovic.Models.SQLdata;
import com.vichito.polimorfismovic.Models.Oracledata;
import com.vichito.polimorfismovic.Models.dBasedata;

import com.vichito.polimorfismovic.Models.ICRUD;
import com.vichito.polimorfismovic.Models.Estudiante;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class HelloController {

    @FXML
    private Button ActualizarButton;

    @FXML
    private Button AgregarButton;

    @FXML
    private TableView<Estudiante> tablaView;

    @FXML
    private TableColumn<Estudiante, String> ApellidoColumna;

    @FXML
    private TableColumn<Estudiante, Integer> EdadColumna;

    @FXML
   private TableColumn<Estudiante, Integer> MatriculaColumna;

    @FXML
    private Button SalirButton;

    @FXML
    private TableColumn<Estudiante, String> NombreColumna;

    private ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();

    private ArrayList<ICRUD> baseDatos = new ArrayList<>();

    private dBasedata dbasedata;

    private Oracledata oracledata;

    private SQLdata sqLdata;


    public HelloController(){
    }

    @FXML
    public void initialize() {
        this.dbasedata = new dBasedata();
        this.oracledata = new Oracledata();
        this.sqLdata = new SQLdata();

        this.baseDatos.add(this.dbasedata);
        this.baseDatos.add(this.oracledata);
        this.baseDatos.add(this.sqLdata);

            MatriculaColumna.setCellValueFactory((cellData) -> {
                return (new SimpleIntegerProperty(((Estudiante)cellData.getValue()).getMatricula())).asObject();
            });

            NombreColumna.setCellValueFactory((cellData) -> {
                return (new SimpleStringProperty(((Estudiante)cellData.getValue()).getNombre()));
            });

            ApellidoColumna.setCellValueFactory((cellData) -> {
                return (new SimpleStringProperty(((Estudiante)cellData.getValue()).getApellido()));
            });
            EdadColumna.setCellValueFactory((cellData) ->
            { return (new SimpleIntegerProperty(((Estudiante)cellData.getValue()).getEdad()).asObject());
            });

        cargarDatos();
    }

        private void cargarDatos(){
            Iterator var1 = this.baseDatos.iterator();

            while(var1.hasNext()){
                ICRUD baseDato = (ICRUD)var1.next();
                this.listaEstudiantes.addAll(baseDato.getEstudiante());
            }

            this.tablaView.setItems(this.listaEstudiantes);
        }

    @FXML
    void OnActionAgregarButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/vichito/polimorfismovic/guardar-view.fxml"));
            Parent root = loader.load();

            guardarController controller = loader.getController();
            controller.initAtributes(null);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            Estudiante estudiante = controller.getEstudiante();
            if (estudiante != null){
                listaEstudiantes.add(estudiante);
                GuardarBase(estudiante);
                tablaView.refresh();
            }
        }catch (IOException var7){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText((String)null);
            alert.setTitle("Error");
            alert.setContentText("Error al guardar");
            alert.showAndWait();
        }
    }

    @FXML
    void OnActionActualizarButton(ActionEvent event) {
        Estudiante seleccionar = (Estudiante) this.tablaView.getSelectionModel().getSelectedItem();

        if (seleccionar == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText((String) null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un estudiante");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/vichito/PolimorfismoVic/guardar-view.fxml"));
                Parent root = (Parent) loader.load();

                guardarController controller = (guardarController) loader.getController();
                controller.initAtributes(seleccionar);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                Estudiante modifiedEstudiante = controller.getEstudiante();
                if (modifiedEstudiante != null){
                    this.updateData(seleccionar, modifiedEstudiante);
                    this.tablaView.refresh();
                }
            }catch (IOException var10){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText((String) null);
                alert.setTitle("Error");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void OnClickSalirButton(MouseEvent event) {System.exit(1);}

    private void GuardarBase(Estudiante estudiante){
        Iterator var2 = this.baseDatos.iterator();

        while (var2.hasNext()){
            ICRUD database = (ICRUD)var2.next();
            database.saveEstudiante(estudiante);
        }
    }

        private void updateData(Estudiante modifiedEstudiante, Estudiante estudiante){
            this.sqLdata.updateEstudiante(estudiante);
            this.oracledata.updateEstudiante(estudiante);
            this.dbasedata.updateEstudiante(estudiante);

            this.dbasedata.imprimirEstudiantes();
            this.sqLdata.imprimirEstudiantes();
            this.oracledata.imprimirEstudiantes();
        }

}
