package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Estado;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Operacion;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Tarea;

public class EdicionOperacionesController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnDetener;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnPausar;

    @FXML
    private Button btnRenaudar;

    // para obtener los datos del enum para la el ComboBox
    @FXML
    private ComboBox<Estado> cbEstado;

    // se establece los estados disponible para la tarea
    @FXML
    private TableColumn<Tarea, String> colDetalles;

    @FXML
    private TableColumn<Tarea, Estado> colEstado;

    @FXML
    private TableColumn<Tarea, String> colTarea;

    @FXML
    private Label lblNombre;

    @FXML
    private TableView<Tarea> tvEstados;
    // aqui termina

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtNombre;


    private Operacion operacionActual;
    private ObservableList<Tarea> tareasObservableList;
    private ObservableList<Estado> estadosObservableList;

    @FXML
    public void initialize() {
        // Configurar ComboBox de estados
        estadosObservableList = FXCollections.observableArrayList(Estado.values());
        cbEstado.setItems(estadosObservableList);

        // Configurar TableView
        colTarea.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colDetalles.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tareasObservableList = FXCollections.observableArrayList();
        tvEstados.setItems(tareasObservableList);
    }

    public void setOperacion(Operacion operacion) {
        this.operacionActual = operacion;
        actualizarVista();
    }

    private void actualizarVista() {
        if (operacionActual != null) {
            lblNombre.setText(operacionActual.getNombre());
            txtNombre.setText(operacionActual.getNombre());
            cbEstado.setValue(operacionActual.getEstado());

            tareasObservableList.clear();
            tareasObservableList.addAll(operacionActual.getTareas());
        }
    }



    @FXML
    void btnCancelar_OneClick(ActionEvent event) {
        // Cerrar la ventana
        btnCancelar.getScene().getWindow().hide();
    }

    @FXML
    void btnDetener_OneClick(ActionEvent event) {
        if (operacionActual != null) {
            operacionActual.detener();
            actualizarVista();
            mostrarAlerta("Operación detenida", "La operación ha sido detenida con éxito.");
        }

    }

    @FXML
    void btnGuardar_OneClick(ActionEvent event) {
        if (operacionActual != null) {
            operacionActual.setNombre(txtNombre.getText());
            operacionActual.setEstado(cbEstado.getValue());
            actualizarVista();
            mostrarAlerta("Cambios guardados", "Los cambios en la operación han sido guardados con éxito.");
        }
    }

    @FXML
    void btnPausar_OneClick(ActionEvent event) {
        if (operacionActual != null) {
            operacionActual.pausar();
            actualizarVista();
            mostrarAlerta("Operación pausada", "La operación ha sido pausada con éxito.");
        }
    }

    @FXML
    void btnRenaudar_OneClick(ActionEvent event) {
        if (operacionActual != null) {
            operacionActual.reanudar();
            actualizarVista();
            mostrarAlerta("Operación reanudada", "La operación ha sido reanudada con éxito.");
        }
    }
// funcion de mustrar alertas ante fallos
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}


