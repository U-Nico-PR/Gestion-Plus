package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AcercaDeController {
    @FXML private Button btnCerrar;

    @FXML
    private void btnCerrar_OneClick() {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
}