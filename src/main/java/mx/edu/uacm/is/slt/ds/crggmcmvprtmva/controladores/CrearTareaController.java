package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Operacion;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Tarea;

public class CrearTareaController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrecondiciones;
    @FXML
    private TextField txtPostcondiciones;
    @FXML
    private TextArea txtComportamiento;
    @FXML
    private ComboBox<String> cBoxPausable;
    @FXML
    private Button btnAgregar;

    private Operacion operacionPadre;

    public void initData(Operacion operacion) {
        this.operacionPadre = operacion;
    }

    @FXML
    public void initialize() {
        cBoxPausable.getItems().addAll("Si", "No");
        cBoxPausable.setValue("Si");
    }

    @FXML
    void btnAgregar_OnClick(ActionEvent event) {
        if (!validarCampos()) {
            return;
        }

        String nombre = txtNombre.getText();
        String precondiciones = txtPrecondiciones.getText();
        String postcondiciones = txtPostcondiciones.getText();
        String instrucciones = txtComportamiento.getText();
        boolean pausable = cBoxPausable.getValue().equals("Si");

        Tarea nuevaTarea = new Tarea(nombre, precondiciones, postcondiciones, instrucciones, pausable);

        if (operacionPadre != null) {
            operacionPadre.agregarTarea(nuevaTarea);
        }

        Stage stage = (Stage) btnAgregar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnCancelar_OnClick(ActionEvent event) {
        Stage stage = (Stage) btnAgregar.getScene().getWindow();
        stage.close();
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty() ||
                txtPrecondiciones.getText().trim().isEmpty() ||
                txtPostcondiciones.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "Los campos Nombre, Precondiciones y Postcondiciones no pueden estar vacíos.", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}