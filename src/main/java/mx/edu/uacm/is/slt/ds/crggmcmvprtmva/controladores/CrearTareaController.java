package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CrearTareaController  {

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
    private TableView<?> tableViewTareas;

    @FXML
    private TableColumn<?, ?> tlEstado;

    @FXML
    private TableColumn<?, ?> tlNombre;

    @FXML
    private TableColumn<?, ?> tlPausable;

    @FXML
    private TextField txtComportamientos;

    @FXML
    private TextField txtCondiciones;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecondiciones;

    @FXML
    void btnAgregar_OneClick(ActionEvent event) {

    }

    @FXML
    void btnModificar_OneClick(ActionEvent event) {

    }

    @FXML
    void btnVolver_OneClick(ActionEvent event) {

    }
    @FXML
    public void initialize() {
        cBoxEstado.getItems().addAll("Si", "No");
        cBoxEstado.setPromptText("Seleccionar");
    }



}
