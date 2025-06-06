package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.EnumEstado;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Tarea;
import javafx.scene.layout.AnchorPane;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.principal.HelloApplication;

import java.io.*;

public class CrearTareaController  {

    @FXML
    private AnchorPane principal;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnVolver;

    @FXML
    private ComboBox<String> cBoxEstado;

    @FXML
    private Label lblComportamineto;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblPausable;

    @FXML
    private Label lblPostcondiciones;

    @FXML
    private Label lblPrecondiciones;

    @FXML
    private TableView<Tarea> tableViewTareas;

    @FXML
    private TableColumn<Tarea, EnumEstado> tlEstado;

    @FXML
    private TableColumn<Tarea, String> tlNombre;

    @FXML
    private TableColumn<Tarea, String> tlPausable;

    @FXML
    private TextField txtComportamientos;

    @FXML
    private TextField txtCondiciones;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecondiciones;

    private ObservableList<Tarea> listaTareas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cBoxEstado.getItems().addAll("Si", "No");
        cBoxEstado.setPromptText("Seleccionar");

        // Configurar columnas
        tlNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tlPausable.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().isPausable() ? "Si" : "No"));
        tlEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        // Asegúrate de inicializar la tarea correctamente antes de usarla

        Tarea n = new Tarea("Nuevo", "Nada", "Nos", "Pocas", true);
        Tarea b = new Tarea("Hora", "Nada", "Nos", "Nada", true);
        Tarea a = new Tarea("Nada", "Nada", "Nos", "M", false);

        listaTareas.add(n);
        listaTareas.add(b);
        listaTareas.add(a);



        tableViewTareas.setItems(listaTareas);
    }


    @FXML
    void btnAgregar_OneClick(ActionEvent event) {
        if (verificarCasiilas()) {
            String nombre = txtNombre.getText();
            String precondiciones = txtPrecondiciones.getText();
            String postcondiciones = txtCondiciones.getText();
            String instrucciones = txtComportamientos.getText();
            String desicion = cBoxEstado.getValue();
            EnumEstado en = EnumEstado.NO_EJECUTADA;
            boolean d1 = verificarPausable(desicion);
            Tarea t1 = new Tarea(nombre, precondiciones, postcondiciones, instrucciones, d1);
            listaTareas.add(t1);
            mostrarAlerta("Tarea creada");
            limpiarCasillas();
        }
    }

    @FXML
    void btnModificar_OneClick(ActionEvent event) {
        //Falta Implementacion
        if (seleccionar()) {

        }else {
            mostrarAlerta("Debes Seleccionar Una Tarea");
        }


    }

    @FXML
    void btnVolver_OneClick(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GestorDeOperaciones.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) principal.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private void limpiarCasillas() {
        txtNombre.clear();
        txtPrecondiciones.clear();
        txtComportamientos.clear();
        txtCondiciones.clear();
        cBoxEstado.getSelectionModel().clearSelection();
        cBoxEstado.setPromptText("Seleccionar");
    }



    private boolean verificarCasiilas(){
        if (txtNombre.getText().isEmpty() || txtPrecondiciones.getText().isEmpty()
                || txtComportamientos.getText().isEmpty() || txtCondiciones.getText().isEmpty()) {
            mostrarAlerta("Verifique los campos");
            return false;
        } else if (cBoxEstado.getSelectionModel().isEmpty() || "Seleccionar".equals(cBoxEstado.getSelectionModel().getSelectedItem())) {
            mostrarAlerta("Seleccione Sí o No");
            return false;
        }
        return true;
    }

    private boolean verificarPausable(String desicion){
        if (cBoxEstado.getSelectionModel().getSelectedItem().equals("Si")){
            return true;
        }else {
            return false;
        }

    }
    private boolean seleccionar(){
        int fila = tableViewTareas.getSelectionModel().getSelectedIndex();
        if (fila == -1) {
            return false;
        }
        String nombreSeleccionado = tableViewTareas.getSelectionModel().getSelectedItem().getNombre();
        Tarea tareaSeleccionada = listaTareas.stream()
                .filter(tarea -> tarea.getNombre().equals(nombreSeleccionado))
                .findFirst()
                .orElse(null);

        if (tareaSeleccionada != null) {
            String nombre = tareaSeleccionada.getNombre();
            String precondiciones = tareaSeleccionada.getPrecondiciones();
            String postcondiciones = tareaSeleccionada.getPostcondiciones();
            String instrucciones = tareaSeleccionada.getInstrucciones();
            //Lleva los datos a la ventana de editar tarea
            try {
                FXMLLoader lodaer = new FXMLLoader(getClass().getResource("/mx/edu/uacm/is/slt/ds/crggmcmvprtmva/principal/Prueba.fxml"));
                Parent root = lodaer.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Editar Tareas");
                stage.show();
                PruebaController controller = lodaer.getController();
                controller.setNombre(nombre);
                controller.setModificarPre(precondiciones);
                controller.setModificarPost(postcondiciones);
                controller.setComportamiento(instrucciones);
                controller.setTareaOriginal(tareaSeleccionada);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("No se encontró la tarea.");
        }

        return true;
    }


}
