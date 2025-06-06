package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.EnumEstado;
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
    public TextField txtComportamiento;

    @FXML
    public TextField txtModPost;

    @FXML
    public TextField txtModPrecon;

    private Tarea tareaActual;

    public String comportamiento;
    public String modificarPost;
    public String modificarPre;

    public void setComportamiento(String comportamiento) {
        this.comportamiento = comportamiento;
        txtComportamiento.setText(comportamiento);
    }

    public void setModificarPost(String modificarPost) {
        this.modificarPost = modificarPost;
        txtModPost.setText(modificarPost);
    }

    public void setModificarPre(String modificarPre) {
        this.modificarPre = modificarPre;
        txtModPrecon.setText(modificarPre);
    }



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
            if (tareaActual == null) {
                // Crear la tarea si no existe
                tareaActual = new Tarea(nombre, precondiciones, postcondiciones, instrucciones, pausable);
                System.out.println("Tarea creada y guardada exitosamente.");
            } else {
                // Actualizar los datos de la tarea sin volver a ejecutarla
                tareaActual.setNombre(nombre);
                tareaActual.setPrecondiciones(precondiciones);
                tareaActual.setPostcondiciones(postcondiciones);
                tareaActual.setInstrucciones(instrucciones);
                tareaActual.setPausable(pausable);
                System.out.println("Tarea actualizada exitosamente.");
            }

            // Mensaje de confirmación
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tarea Guardada");
            alert.setHeaderText(null);
            alert.setContentText("La tarea '" + nombre + "' ha sido guardada.");
            alert.showAndWait();

        } catch (Exception e) {
            mostrarAlerta("Ocurrió un error al guardar la tarea: " + e.getMessage());
        }
    }

    @FXML
    void EjecutarTarea(ActionEvent event) {
        if (tareaActual != null && tareaActual.getEstado() == EnumEstado.NO_EJECUTADA) {
            tareaActual.ejecutar();
            System.out.println("Tarea ejecutada exitosamente.");
        } else {
            mostrarAlerta("La tarea ya está en ejecución o no ha sido creada.");
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
