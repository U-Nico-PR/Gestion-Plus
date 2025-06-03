package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PanelControlController {

    @FXML
    private Button btnAcerca;

    @FXML
    private Button btnOperaciones;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnTareas;

    @FXML
    private TableColumn<?, ?> colDetalles;

    @FXML
    private TableColumn<?, ?> colEstado;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private Label lblTitulo;

    @FXML
    private AnchorPane principal;

    @FXML
    private TableView<?> tableviewTareas;

    @FXML
    void btnAcerca_OneClick(ActionEvent event) {

    }

    @FXML
    void btnOperaciones_OneClick(ActionEvent event) {

    }

    @FXML
    void btnSalir_OneClick(ActionEvent event) {
        System.exit(0);

    }

    @FXML
    void btnTareas_OneClick(ActionEvent event) {

    }

    @FXML
    void selecionar_MauseClicked(MouseEvent event) {

    }

}
