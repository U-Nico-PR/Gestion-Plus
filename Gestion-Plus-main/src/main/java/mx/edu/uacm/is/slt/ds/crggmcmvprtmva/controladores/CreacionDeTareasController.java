package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
public class CreacionDeTareasController{

        @FXML
        private Button btnCrear;

        @FXML
        private ComboBox<?> cbxTipo;

        @FXML
        private TableView<?> jtTareas;

        @FXML
        private Label lblNombre;

        @FXML
        private Label lblPausable;

        @FXML
        private Label lblTitulo;

        @FXML
        private RadioButton rdbtnPausable;

        @FXML
        private TextField txtNombre;


}
