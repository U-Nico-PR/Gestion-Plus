package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

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
import javafx.stage.Stage;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.EnumEstado;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Operacion;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Tarea;

import java.io.IOException;
import java.util.Collections;

public class EdicionOperacionesController {

    // Atributos definidos en la documentación
    private Operacion operacionActual = new Operacion("Opc1");
    private ObservableList<Tarea> tareas = FXCollections.observableArrayList();
    private EnumEstado estado;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnDetener;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnPausar;

    @FXML
    private Button btnReanudar;

    @FXML
    private TableColumn<?, ?> colDetalles;

    @FXML
    private TableColumn<?, ?> colEstado;

    @FXML
    private TableColumn<?, ?> colTarea;

    @FXML
    private TableView<Tarea> tbEstados;

    // Este es el Método crearTarea
    @FXML
    void btnCrear_OneClick(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mx/edu/uacm/is/slt/ds/crggmcmvprtmva/principal/creacionDeTareas.fxml"));
            Parent root = loader.load();
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Lista de Libros");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            ((Stage) btnCrear.getScene().getWindow()).close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Metodo para Detener la tarea
    @FXML
    void btnDetener_OneClick(ActionEvent event) {
        Tarea tareaSeleccionada = tbEstados.getSelectionModel().getSelectedItem();
        if (tareaSeleccionada != null) {
            tareaSeleccionada.detener();
            actualizarTabla();
        } else {
            mostrarAlerta("Debes seleccionar una tarea antes de detenerla.");
        }
    }
    //Metodo para eliminar la tarea
    @FXML
    void btnEliminar_OneClick(ActionEvent event) {
        Tarea tareaSeleccionada = tbEstados.getSelectionModel().getSelectedItem();
        if (tareaSeleccionada != null) {
            operacionActual.eliminarTarea(tareaSeleccionada);
            tareas.remove(tareaSeleccionada);
            actualizarTabla();
        } else {
            mostrarAlerta("Debes seleccionar una tarea antes de eliminarla.");
        }
    }
    //Metodo para modificar el orden de las tareas
    @FXML
    void btnModificar_OneClick(ActionEvent event) {
        int selectedIndex = tbEstados.getSelectionModel().getSelectedIndex();

        // Verificar si hay una tarea seleccionada y que no sea la primera (para mover arriba)
        if (selectedIndex > 0) {
            Collections.swap(tareas, selectedIndex, selectedIndex - 1);
            actualizarTabla();
            tbEstados.getSelectionModel().select(selectedIndex - 1);
        } else {
            mostrarAlerta("Modificación inválida");
        }
    }
    //Metodo para pausar la tarea
    @FXML
    void btnPausar_OneClick(ActionEvent event) {
        Tarea tareaSeleccionada = tbEstados.getSelectionModel().getSelectedItem();
        if (tareaSeleccionada != null) {
            tareaSeleccionada.pausar();
            actualizarTabla();
        } else {
            mostrarAlerta("Debes seleccionar una tarea antes de pausarla.");
        }
    }
    //Metodo para reanudar la tarea
    @FXML
    void btnReanudar_OneClick(ActionEvent event) {
        Tarea tareaSeleccionada = tbEstados.getSelectionModel().getSelectedItem();
        if (tareaSeleccionada != null) {
            tareaSeleccionada.reanudar();
            actualizarTabla();
        } else {
            mostrarAlerta("Debes seleccionar una tarea antes de reanudarla.");
        }
    }

    @FXML
    void btnEditar_OneClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mx/edu/uacm/is/slt/ds/crggmcmvprtmva/principal/editorTareas.fxml"));
            Parent root = loader.load();
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Lista de Libros");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            ((Stage) btnCrear.getScene().getWindow()).close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarTabla(){
        tbEstados.setItems(tareas);
        tbEstados.refresh();
    }
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Selección requerida");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
