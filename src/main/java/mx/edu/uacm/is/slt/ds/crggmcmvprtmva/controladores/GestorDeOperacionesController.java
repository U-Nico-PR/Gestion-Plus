package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Operacion;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.SistemaOperaciones;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.principal.HelloApplication;

import java.io.IOException;
import java.util.Optional;

public class GestorDeOperacionesController {

    @FXML
    private TableView<Operacion> tblOperaciones;
    @FXML
    private TableColumn<Operacion, String> colOperacion;
    @FXML
    private TableColumn<Operacion, String> colEstado;

    private SistemaOperaciones sistema;

    @FXML
    public void initialize() {
        sistema = SistemaOperaciones.getInstancia();
        colOperacion.setCellValueFactory(cellData -> cellData.getValue().estadoProperty());
        colEstado.setCellValueFactory(cellData -> cellData.getValue().estadoProperty());
        tblOperaciones.setItems(sistema.getOperaciones());
    }

    @FXML
    void acercaDe_OnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("AcercaDe.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Acerca De");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la ventana 'Acerca De'.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void crearOperacion_OnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("CreacionOperacion.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Crear Nueva Operación");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            tblOperaciones.refresh();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la ventana de creación.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void editarOperacion_OnClick(ActionEvent event) {
        Operacion seleccionada = tblOperaciones.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAlerta("Atención", "Por favor, seleccione una operación para editar.", Alert.AlertType.WARNING);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("EdicionOperaciones.fxml"));
            Parent root = loader.load();
            EdicionOperacionesController controller = loader.getController();
            controller.initData(seleccionada);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Editando Operación: " + seleccionada.getNombre());
            stage.setScene(new Scene(root));
            stage.showAndWait();
            tblOperaciones.refresh();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la ventana de edición.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void eliminarOperacion_OnClick(ActionEvent event) {
        Operacion seleccionada = tblOperaciones.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAlerta("Atención", "Por favor, seleccione una operación para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Eliminación");
        confirmacion.setHeaderText("¿Está seguro de que desea eliminar la operación '" + seleccionada.getNombre() + "'?");
        confirmacion.setContentText("Esta acción no se puede deshacer.");
        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            sistema.eliminarOperacion(seleccionada);
            tblOperaciones.refresh();
        }
    }

    @FXML
    void salir_OnClick(ActionEvent event) {
        Platform.exit();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}