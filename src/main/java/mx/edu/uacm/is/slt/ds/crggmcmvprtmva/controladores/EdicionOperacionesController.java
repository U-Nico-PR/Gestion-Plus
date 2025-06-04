package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.EnumEstado;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Operacion;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Tarea;

import java.util.List;

public class EdicionOperacionesController {

    // Atributos definidos en la documentación
    private Operacion operacionActual;
    private List<Tarea> tareas;
    private EnumEstado estado;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnDetener;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnPausar;

    @FXML
    private Button btnRenaudar;

    @FXML
    private TableColumn<?, ?> colDetalles;

    @FXML
    private TableColumn<?, ?> colEstado;

    @FXML
    private TableColumn<?, ?> colTarea;

    @FXML
    private TableView<?> tvEstados;

    // Este es el Método crearTarea
    @FXML
    void btnCrear_OneClick(ActionEvent event) {

    }

    @FXML
    void btnDetener_OneClick(ActionEvent event) {

    }

    @FXML
    void btnEliminar_OneClick(ActionEvent event) {

    }

    @FXML
    void btnModificar_OneClick(ActionEvent event) {

    }

    @FXML
    void btnPausar_OneClick(ActionEvent event) {

    }

    @FXML
    void btnRenaudar_OneClick(ActionEvent event) {

    }

}
