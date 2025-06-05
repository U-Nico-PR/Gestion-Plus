package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.SistemaOperaciones;

import java.io.IOException;

public class CreacionOperacionController {

    @FXML
    private Button btnAgregarTarea;

    @FXML
    private Button btnCrearOperacion;

    @FXML
    private TextField txtOperacion;

    @FXML
    private TableView<?> jtOperacion;

    @FXML
    void agregarTarea_OnClick(ActionEvent event) {

    }

    @FXML
    void crearOperacion_OnClick(ActionEvent event) {
        String nombreOperacion = txtOperacion.getText().trim();

        if (nombreOperacion.isEmpty()) {
            mostrarAlerta("Error", "El nombre de la operación es requerido", Alert.AlertType.ERROR);
            return;
        }
        try {
            FXMLLoader lodaer = new FXMLLoader(getClass().getResource("/mx/edu/uacm/is/slt/ds/crggmcmvprtmva/principal/CreacionOperacion.fxml"));
            Parent root = lodaer.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Creacion De Operaciones");
            stage.show();
        } catch (IOException e) {

        }
        // Registrar la operación en el sistema
        SistemaOperaciones.getInstancia().crearOperacion(nombreOperacion);

        // Mostrar confirmación
        mostrarAlerta("Éxito", "Operación '" + nombreOperacion + "' creada correctamente", Alert.AlertType.INFORMATION);

        // Limpiar el campo
        txtOperacion.clear();

    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}
