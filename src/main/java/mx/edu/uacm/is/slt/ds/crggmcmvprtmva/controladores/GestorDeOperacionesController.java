package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class GestorDeOperacionesController {

    @FXML
    private Button acercaDe;

    @FXML
    private TableColumn<?, ?> colEstado;

    @FXML
    private TableColumn<?, ?> colOperacion;

    @FXML
    private Button crearOperacion;

    @FXML
    private Button eliminarOperacion;

    @FXML
    private Button salir;

    @FXML
    private Button tareas;

    private static GestorDeOperacionesController instancia;
    private GestorDeOperacionesController() {
        // Constructor privado
    }

    //patron de diseño singleton
    public static GestorDeOperacionesController getInstancia() {
        if(instancia == null) {
            instancia = new GestorDeOperacionesController();
        }
        return instancia;
    }

    @FXML
    void acercaDe_OnClick(ActionEvent event) {

        try {
            FXMLLoader lodaer = new FXMLLoader(getClass().getResource("/mx/edu/uacm/is/slt/ds/crggmcmvprtmva/principal/AcercaDe.fxml"));
            Parent root = lodaer.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Acerca De");
            stage.show();
        } catch (IOException e){

        }

    }

    @FXML
    void crearOperacion_OnClick(ActionEvent event) {

        try {
            FXMLLoader lodaer = new FXMLLoader(getClass().getResource("/mx/edu/uacm/is/slt/ds/crggmcmvprtmva/principal/CreacionOperacion.fxml"));
            Parent root = lodaer.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Crear operaciones");
            stage.show();
        } catch (IOException e){

        }

    }

    @FXML
    void eliminarOperacion_OnClick(ActionEvent event) {

    }

    @FXML
    void salir_OnClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void Operaciones_OnClick(ActionEvent event) {

        try {
            FXMLLoader lodaer = new FXMLLoader(getClass().getResource("/mx/edu/uacm/is/slt/ds/crggmcmvprtmva/principal/EdicionOperaciones.fxml"));
            Parent root = lodaer.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Crear Tarea");
            stage.show();
        } catch (IOException e){

        }

    }

}
