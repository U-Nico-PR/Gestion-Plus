package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Operacion;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.SistemaOperaciones;

public class CreacionOperacionController {

    @FXML
    private TextField txtOperacion;
    @FXML
    private Button btnCrearOperacion;

    @FXML
    void crearOperacion_OnClick(ActionEvent event) {
        String nombreOperacion = txtOperacion.getText().trim();

        if (nombreOperacion.isEmpty()) {
            mostrarAlerta("Error", "El nombre de la operación es requerido.", Alert.AlertType.ERROR);
            return;
        }

        Operacion nuevaOperacion = new Operacion(nombreOperacion);
        SistemaOperaciones.getInstancia().agregarOperacion(nuevaOperacion);

        Stage stage = (Stage) btnCrearOperacion.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}