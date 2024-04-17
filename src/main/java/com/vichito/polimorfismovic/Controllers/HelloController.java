package com.vichito.polimorfismovic.Controllers;

import com.vichito.polimorfismovic.Models.*;
import com.vichito.polimorfismovic.Models.Estudiante;
import com.vichito.polimorfismovic.Models.ICRUD;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

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

    private ObservableList<Estudiante> listaEstudiante = FXCollections.observableArrayList();
    private ArrayList<ICRUD> baseDatos = new ArrayList<>();

    private dBasedata dBasedata;
    private Oracledata oracledata;
    private SQLdata sqLdata;

    @FXML
    public void initialize() {
        dBasedata = new dBasedata();
        oracledata = new Oracledata();
        sqLdata = new SQLdata();

        baseDatos.add(dBasedata);
        baseDatos.add(oracledata);
        baseDatos.add(sqLdata);

            MatriculaColumna.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMatricula()).asObject());
            NombreColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
            ApellidoColumna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
            EdadColumna.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEdad()).asObject());

        cargarDatos();
    }

        private void cargarDatos(){
            for (ICRUD baseDato : baseDatos) {
                listaEstudiante.addAll(baseDato.getEstudiante());
                }
            tablaView.setItems(listaEstudiante);
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
                listaEstudiante.add(estudiante);
                GuardarBase(estudiante);
                tablaView.refresh();
            }
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error al guardar");
            alert.showAndWait();
        }
    }

    @FXML
    void OnActionActualizarButton(ActionEvent event) {
        Estudiante seleccionar = tablaView.getSelectionModel().getSelectedItem();

        if (seleccionar != null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un estudiante");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("guardar-view.fxml"));
                Parent root = loader.load();

                guardarController controller = loader.getController();
                controller.initAtributes(seleccionar);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                Estudiante modifiedEstudiante = controller.getEstudiante();
                if (modifiedEstudiante != null){
                    updateData(seleccionar, modifiedEstudiante);
                    tablaView.refresh();
                }
            }catch (IOException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void OnClickSalirButton(MouseEvent event) {System.exit(1);}

    private void GuardarBase(Estudiante estudiante){
        for (ICRUD database : baseDatos) {
            database.saveEstudiante(estudiante);
        }
    }

        private void updateData(Estudiante modifiedEstudiante, Estudiante estudiante){
            dBasedata.updateEstudiante(estudiante);
            oracledata.updateEstudiante(estudiante);
            sqLdata.updateEstudiante(estudiante);

            dBasedata.imprimirEstudiantes();
            oracledata.imprimirEstudiantes();
            sqLdata.imprimirEstudiantes();
        }

}
