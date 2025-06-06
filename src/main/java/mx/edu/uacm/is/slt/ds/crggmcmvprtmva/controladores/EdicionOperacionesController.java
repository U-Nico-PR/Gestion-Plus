package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.EnumEstado;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Operacion;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Tarea;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.principal.HelloApplication;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class EdicionOperacionesController {

    private Operacion operacionActual;

    @FXML
    private TableView<Tarea> tblTareas;
    @FXML
    private TableColumn<Tarea, String> colTarea;
    @FXML
    private TableColumn<Tarea, String> colPausable;
    @FXML
    private TableColumn<Tarea, EnumEstado> colEstadoTarea;

    public void initData(Operacion operacion) {
        this.operacionActual = operacion;
        tblTareas.setItems(operacion.getTareas());
    }

    @FXML
    public void initialize() {
        colTarea.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEstadoTarea.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colPausable.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().isPausable() ? "Si" : "No")
        );
    }

    @FXML
    void ejecutar_OnClick(ActionEvent event) {
        if (operacionActual != null) {
            operacionActual.ejecutar();
            tblTareas.refresh();
        }
    }

    @FXML
    void pausar_OnClick(ActionEvent event) {
        if (operacionActual != null) {
            operacionActual.pausar();
            tblTareas.refresh();
        }
    }

    @FXML
    void reanudar_OnClick(ActionEvent event) {
        if (operacionActual != null) {
            operacionActual.reanudar();
            tblTareas.refresh();
        }
    }

    @FXML
    void detener_OnClick(ActionEvent event) {
        if (operacionActual != null) {
            operacionActual.detener();
            tblTareas.refresh();
        }
    }

    @FXML
    void crearTarea_OnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("CrearTarea.fxml"));
            Parent root = loader.load();
            CrearTareaController controller = loader.getController();
            controller.initData(this.operacionActual);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Crear Nueva Tarea para: " + operacionActual.getNombre());
            stage.setScene(new Scene(root));
            stage.showAndWait();
            tblTareas.refresh();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo abrir el editor de tareas.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    void eliminarTarea_OnClick(ActionEvent event) {
        Tarea seleccionada = tblTareas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar Eliminación");
            confirmacion.setHeaderText("¿Eliminar la tarea '" + seleccionada.getNombre() + "'?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                operacionActual.eliminarTarea(seleccionada);
                tblTareas.refresh();
            }
        } else {
            mostrarAlerta("Atención", "Debe seleccionar una tarea para eliminar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void moverArriba_OnClick(ActionEvent event) {
        int selectedIndex = tblTareas.getSelectionModel().getSelectedIndex();
        if (selectedIndex > 0) {
            Collections.swap(operacionActual.getTareas(), selectedIndex, selectedIndex - 1);
            tblTareas.getSelectionModel().select(selectedIndex - 1);
        }
    }

    @FXML
    void moverAbajo_OnClick(ActionEvent event) {
        int selectedIndex = tblTareas.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1 && selectedIndex < operacionActual.getTareas().size() - 1) {
            Collections.swap(operacionActual.getTareas(), selectedIndex, selectedIndex + 1);
            tblTareas.getSelectionModel().select(selectedIndex + 1);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}