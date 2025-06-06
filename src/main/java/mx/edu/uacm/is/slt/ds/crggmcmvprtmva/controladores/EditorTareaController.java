package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Tarea;

public class EditorTareaController {

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
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    private Tarea tareaActual;
    private boolean esNuevaTarea = true;

    // Método para inicializar los datos de una tarea existente
    public void initData(Tarea tarea) {
        this.tareaActual = tarea;
        this.esNuevaTarea = false;

        txtNombre.setText(tarea.getNombre());
        txtPrecondiciones.setText(tarea.getPrecondiciones());
        txtPostcondiciones.setText(tarea.getPostcondiciones());
        txtComportamiento.setText(tarea.getInstrucciones());
        cBoxPausable.setValue(tarea.isPausable() ? "Si" : "No");
    }


    @FXML
    public void initialize() {
        cBoxPausable.getItems().addAll("Si", "No");
    }

    @FXML
    void guardar_OnClick(ActionEvent event) {
        if (!validarCampos()) {
            return;
        }

        // Si es una tarea nueva, la creamos. Si no, la actualizamos.
        if (esNuevaTarea) {
            // Esta lógica ahora está en CrearTareaController para mayor claridad.
            // Este controlador se enfocará en la edición.
        } else {
            // Actualizamos el objeto Tarea existente
            tareaActual.setNombre(txtNombre.getText());
            tareaActual.setPrecondiciones(txtPrecondiciones.getText());
            tareaActual.setPostcondiciones(txtPostcondiciones.getText());
            tareaActual.setInstrucciones(txtComportamiento.getText());
            tareaActual.setPausable(cBoxPausable.getValue().equals("Si"));

            mostrarAlerta("Éxito", "Tarea actualizada correctamente.", Alert.AlertType.INFORMATION);
        }

        cerrarVentana();
    }

    @FXML
    void cancelar_OnClick(ActionEvent event) {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnGuardar.getScene().getWindow();
        stage.close();
    }


    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty() ||
                txtPrecondiciones.getText().trim().isEmpty() ||
                txtPostcondiciones.getText().trim().isEmpty() ||
                cBoxPausable.getValue() == null) {
            mostrarAlerta("Error de validación", "Todos los campos son obligatorios.", Alert.AlertType.WARNING);
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