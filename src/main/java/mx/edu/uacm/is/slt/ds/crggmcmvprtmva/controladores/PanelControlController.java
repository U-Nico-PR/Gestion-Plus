package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Estado;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Operacion;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Tarea;

import java.io.IOException;

public class PanelControlController {

    @FXML
    private Button btnAcerca;

    @FXML
    private Button btnOperaciones;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnTareas;

    // toma datos segun la variable
    @FXML
    private TableColumn<Operacion, String> colDetalles;

    @FXML
    private TableColumn<Operacion, Estado> colEstado;

    @FXML
    private TableColumn<Operacion, String> colNombre;
    // termina aqui
    @FXML
    private Label lblTitulo;

    @FXML
    private AnchorPane principal;

    @FXML
    private TableView<Operacion> tableviewTareas; // ver tareas con Operacion

    private ObservableList<Operacion> operacionesObservableList;

    @FXML
    public void initialize() {
        // Configurar TableView
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colDetalles.setCellValueFactory(new PropertyValueFactory<>("identificador"));

       operacionesObservableList = FXCollections.observableArrayList();
       tableviewTareas.setItems(operacionesObservableList);
    }

    @FXML
    void btnAcerca_OneClick(ActionEvent event) {

        try {
            FXMLLoader lodaer = new FXMLLoader(getClass().getResource("/mx/edu/uacm/is/slt/ds/crggmcmvprtmva/principal/AcercaDe.fxml"));
            Parent root = lodaer.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Acerca del Sistema");
            stage.show();
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo cargar la ventana 'Acerca De'");
            alert.setContentText("El archivo FXML no se encontró o está corrupto.");
            alert.showAndWait();
        }

    }

    @FXML
    void btnOperaciones_OneClick(ActionEvent event) {
        try {
            FXMLLoader lodaer = new FXMLLoader(getClass().getResource("/mx/edu/uacm/is/slt/ds/crggmcmvprtmva/principal/EdicionOperaciones.fxml"));
            Parent root = lodaer.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Operaciones Tarea");
            stage.show();
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo cargar la ventana 'Operaciones'");
            alert.setContentText("El archivo FXML no se encontró o está corrupto.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnSalir_OneClick(ActionEvent event) {
        System.exit(0);

    }

    @FXML
    void btnTareas_OneClick(ActionEvent event) {
    // aqui la implemetacion y vista de la tarea para crear y editar
    }

    @FXML
    void selecionar_MauseClicked(MouseEvent event) {
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
