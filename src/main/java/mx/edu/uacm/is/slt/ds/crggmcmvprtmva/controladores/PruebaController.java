package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Tarea;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.EnumEstado;

import java.io.*;

public class PruebaController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<String> cbPausable;

    @FXML
    private Label lblTitulo;

    @FXML
    private TextArea txtAInstruciones;

    @FXML
    private TextField txtPost;

    @FXML
    private TextField txtPreco;

    public String nombre;
    public String comportamiento;
    public String modificarPost;
    public String modificarPre;

    public void setComportamiento(String comportamiento) {
        this.comportamiento = comportamiento;
        txtAInstruciones.setText(comportamiento);
    }

    public void setModificarPost(String modificarPost) {
        this.modificarPost = modificarPost;
        txtPost.setText(modificarPost);
    }

    public void setModificarPre(String modificarPre) {
        this.modificarPre = modificarPre;
        txtPreco.setText(modificarPre);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        lblTitulo.setText(nombre);
    }


    private Tarea tareaOriginal;

    private ObservableList<Tarea> listaTareas = FXCollections.observableArrayList();

    public void setTareaOriginal(Tarea tarea) {
        this.tareaOriginal = tarea;
        lblTitulo.setText(tarea.getNombre());
        txtPreco.setText(tarea.getPrecondiciones());
        txtPost.setText(tarea.getPostcondiciones());
        txtAInstruciones.setText(tarea.getInstrucciones());
        cbPausable.setValue(tarea.isPausable() ? "Si" : "No");
    }

    @FXML
    public void initialize() {
        cbPausable.getItems().addAll("Si", "No");
        cbPausable.setPromptText("Seleccionar");
    }

    @FXML
    void btnCancelar_OneClick(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnGuardar_OneClick(ActionEvent event) {
        if (txtAInstruciones.getText().isEmpty() || txtPost.getText().isEmpty() || txtPreco.getText().isEmpty() || cbPausable.getValue() == null) {
            mostrarAlerta("Todos los campos deben estar llenos.");
            return;
        }


        // Buscar y reemplazar la tarea original
        listaTareas.removeIf(t -> t.getNombre().equals(tareaOriginal.getNombre()));

        Tarea nueva = new Tarea(
                tareaOriginal.getNombre(),
                txtPreco.getText(),
                txtPost.getText(),
                txtAInstruciones.getText(),
                cbPausable.getValue().equals("Si")
                //tareaOriginal.getEstado() // mantener estado original
        );

        listaTareas.add(nueva);
        mostrarAlerta("Tarea modificada correctamente.");
        Stage stage = (Stage) btnGuardar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void borraIntru_OneCliked(MouseEvent event) {
        txtAInstruciones.clear();
    }

    @FXML
    void borraPost_OneCliked(MouseEvent event) {
        txtPost.clear();
    }

    @FXML
    void borraPre_OneClicked(MouseEvent event) {
        txtPreco.clear();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
