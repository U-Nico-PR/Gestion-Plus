package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.EnumEstado;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Operacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorDeOperacionesController {

    //List<Operacion> operaciones = new ArrayList<>();
    private static GestorDeOperacionesController instancia;
    private GestorDeOperacionesController() {
        // Constructor privado
    }
    public static GestorDeOperacionesController getInstancia() {
        if(instancia == null) {
            instancia = new GestorDeOperacionesController();
        }
        return instancia;
    }

    @FXML
    private Button acercaDe;

    @FXML
    private TableColumn<Operacion, String> colDetalle;

    @FXML
    private TableColumn<Operacion, EnumEstado> colEstado;

    @FXML
    private TableColumn<Operacion, String> colOperacion;

    @FXML
    private TableView<Operacion> tableOperacion;

    @FXML
    private Button crearOperacion;

    @FXML
    private Button eliminarOperacion;

    @FXML
    private Button salir;

    @FXML
    private Button tareas;

    private ObservableList<Operacion> operaciones = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar las columnas
        colDetalle.setCellValueFactory(new PropertyValueFactory<>("detalle"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colOperacion.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        // Agregar algunos datos de ejemplo
        operaciones.add(new Operacion("Hola"));
        operaciones.add(new Operacion("que"));
        operaciones.add(new Operacion("tal"));

        // Asignar datos a la tabla
        tableOperacion.setItems(operaciones);

        tableOperacion.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Detecta doble clic
                Operacion seleccionada = tableOperacion.getSelectionModel().getSelectedItem();
                if (seleccionada != null) {
                    mostrarEdicionOperaciones(seleccionada);
                }
            }
        });
    }

    private void mostrarEdicionOperaciones(Operacion operacion){
        try {
            FXMLLoader lodaer = new FXMLLoader(getClass().getResource("/mx/edu/uacm/is/slt/ds/crggmcmvprtmva/principal/EdicionOperaciones.fxml"));
            Parent root = lodaer.load();

            EdicionOperacionesController controller = lodaer.getController();
            controller.cargarDatos(operacion);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Acerca De");
            stage.show();
        } catch (IOException e){

        }
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
    void tareas_OnClick(ActionEvent event) {
        try {
            FXMLLoader lodaer = new FXMLLoader(getClass().getResource("/mx/edu/uacm/is/slt/ds/crggmcmvprtmva/principal/EdicionOperaciones.fxml"));
            Parent root = lodaer.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Crear operaciones");
            stage.show();
        } catch (IOException e){

        }
    }
}
