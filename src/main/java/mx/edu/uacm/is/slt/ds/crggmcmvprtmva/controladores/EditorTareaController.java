package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Tarea;

public class EditorTareaController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Label lblComportamiento;

    @FXML
    private Label lblModificarPost;

    @FXML
    private Label lblModificarPre;

    @FXML
    private Label lblPausable;

    @FXML
    private Label lblTituloEditorTareas;

    @FXML
    private RadioButton rdbtnPausable;

    @FXML
    private TextField txtComportamiento;

    @FXML
    private TextField txtModPost;

    @FXML
    private TextField txtModPrecon;

    private Tarea tareaActual;


    @FXML
    void Cancelar(ActionEvent event) {
        limpiarCampos();
        System.out.println("Edicion cancelada.");
    }

    @FXML
    void Guardar_OnClick(ActionEvent event) {
        // Validar que los campos no estén vacíos
        if (txtComportamiento.getText().isEmpty() || txtModPost.getText().isEmpty() || txtModPrecon.getText().isEmpty()) {
            mostrarAlerta("Todos los campos deben estar llenos.");
            return;
        }

        // Capturar datos del formulario
        String nombre = lblTituloEditorTareas.getText();
        String precondiciones = txtModPrecon.getText();
        String postcondiciones = txtModPost.getText();
        String instrucciones = txtComportamiento.getText();
        boolean pausable = rdbtnPausable.isSelected();

        try {
            // Crear y almacenar la tarea
            tareaActual = new Tarea(nombre, precondiciones, postcondiciones, instrucciones, pausable);
            System.out.println("Tarea guardada exitosamente: " + tareaActual.getEstado());

            // Opcionalmente, podrías iniciar la tarea inmediatamente
            tareaActual.ejecutar();

            // Mostrar mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tarea Guardada");
            alert.setHeaderText(null);
            alert.setContentText("La tarea '" + nombre + "' ha sido guardada y ejecutada.");
            alert.showAndWait();

        } catch (Exception e) {
            mostrarAlerta("Ocurrio un error al guardar la tarea: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        txtModPrecon.clear();
        txtModPost.clear();
        txtComportamiento.clear();
        rdbtnPausable.setSelected(false);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Selección requerida");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
