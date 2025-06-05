package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.SistemaOperaciones;

public class CreacionOperacionController {

    @FXML
    private TextField txtNombreOperacion;

    @FXML
    private Button btnCrearOperacion;

    @FXML
    private void crearOperacion() {
        String nombreOperacion = txtNombreOperacion.getText().trim();

        if (nombreOperacion.isEmpty()) {
            mostrarAlerta("Error", "El nombre de la operación es requerido", Alert.AlertType.ERROR);
            return;
        }

        // Registrar la operación en el sistema
        SistemaOperaciones.getInstancia().crearOperacion(nombreOperacion);

        // Mostrar confirmación
        mostrarAlerta("Éxito", "Operación '" + nombreOperacion + "' creada correctamente", Alert.AlertType.INFORMATION);

        // Limpiar el campo
        txtNombreOperacion.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
