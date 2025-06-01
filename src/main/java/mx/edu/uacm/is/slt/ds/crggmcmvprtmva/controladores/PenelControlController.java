package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PenelControlController {

    @FXML
    private Button btnAcerca;

    @FXML
    private Button btnOperaciones;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnTareas;

    @FXML
    private TableColumn<?, ?> colDetalles;

    @FXML
    private TableColumn<?, ?> colEstado;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private Label lblTitulo;

    @FXML
    private AnchorPane principal;

    @FXML
    private TableView<?> tableviewTareas;

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

    }

    @FXML
    void selecionar_MauseClicked(MouseEvent event) {

    }

}
