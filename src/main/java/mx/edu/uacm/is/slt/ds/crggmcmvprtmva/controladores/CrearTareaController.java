package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.EnumEstado;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos.Tarea;
import javafx.scene.layout.AnchorPane;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.principal.HelloApplication;

import java.io.*;

public class CrearTareaController  {

    @FXML
    private AnchorPane principal;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnVolver;

    @FXML
    private ComboBox<String> cBoxEstado;

    @FXML
    private Label lblComportamineto;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblPausable;

    @FXML
    private Label lblPostcondiciones;

    @FXML
    private Label lblPrecondiciones;

    @FXML
    private TableView<Tarea> tableViewTareas;

    @FXML
    private TableColumn<Tarea, EnumEstado> tlEstado;

    @FXML
    private TableColumn<Tarea, String> tlNombre;

    @FXML
    private TableColumn<Tarea, String> tlPausable;

    @FXML
    private TextField txtComportamientos;

    @FXML
    private TextField txtCondiciones;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecondiciones;

    private ObservableList<Tarea> listaTareas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cBoxEstado.getItems().addAll("Si", "No");
        cBoxEstado.setPromptText("Seleccionar");

        // Configurar columnas
        tlNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tlPausable.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().isPausable() ? "Sí" : "No"));
        tlEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));


        //tableViewTareas.setItems(listaTareas);
        verTareas();
    }


    @FXML
    void btnAgregar_OneClick(ActionEvent event) {
        if (verificarCasiilas()) {
            String nombre = txtNombre.getText();
            String precondiciones = txtPrecondiciones.getText();
            String postcondiciones = txtComportamientos.getText();
            String instrucciones = txtCondiciones.getText();
            String desicion = cBoxEstado.getValue();
            boolean d1 = verificarPausable(desicion);
            Tarea t1 = new Tarea(nombre, precondiciones, postcondiciones, instrucciones, d1);
            listaTareas.add(t1);
            añadirTarea(t1);
            mostrarAlerta("Tarea creada");
            limpiarCasillas();
        }
    }

    @FXML
    void btnModificar_OneClick(ActionEvent event) {
        //Falta Implementacion
        if (seleccionar()) {

        }else {
            mostrarAlerta("Debes Seleccionar Una Tarea");
        }


    }

    @FXML
    void btnVolver_OneClick(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GestorDeOperaciones.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) principal.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private void limpiarCasillas() {
        txtNombre.clear();
        txtPrecondiciones.clear();
        txtComportamientos.clear();
        txtCondiciones.clear();
        cBoxEstado.getSelectionModel().clearSelection();
        cBoxEstado.setPromptText("Seleccionar");
    }



    private boolean verificarCasiilas(){
        if (txtNombre.getText().isEmpty() || txtPrecondiciones.getText().isEmpty()
                || txtComportamientos.getText().isEmpty() || txtCondiciones.getText().isEmpty()) {
            mostrarAlerta("Verifique los campos");
            return false;
        } else if (cBoxEstado.getSelectionModel().isEmpty() || "Seleccionar".equals(cBoxEstado.getSelectionModel().getSelectedItem())) {
            mostrarAlerta("Seleccione Sí o No");
            return false;
        }
        return true;
    }

    private boolean verificarPausable(String desicion){
        if (cBoxEstado.getSelectionModel().getSelectedItem().equals("Si")){
            return true;
        }else {
            return false;
        }

    }
    private boolean seleccionar(){
        int fila = tableViewTareas.getSelectionModel().getSelectedIndex();
        if (fila == -1) {
            return false;
        }
        String nombreSeleccionado = tableViewTareas.getSelectionModel().getSelectedItem().getNombre();
        Tarea tareaSeleccionada = listaTareas.stream()
                .filter(tarea -> tarea.getNombre().equals(nombreSeleccionado))
                .findFirst()
                .orElse(null);

        if (tareaSeleccionada != null) {
            String nombre = tareaSeleccionada.getNombre();
            String precondiciones = tareaSeleccionada.getPrecondiciones();
            String postcondiciones = tareaSeleccionada.getPostcondiciones();
            String instrucciones = tareaSeleccionada.getInstrucciones();
            //Lleva los datos a la ventana de editar tarea
            try {
                FXMLLoader lodaer = new FXMLLoader(getClass().getResource("/mx/edu/uacm/is/slt/ds/crggmcmvprtmva/principal/EditorTareas.fxml"));
                Parent root = lodaer.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Editar Tareas");
                stage.show();
                EditorTareaController controller = lodaer.getController();
                controller.setModificarPre(precondiciones);
                controller.setModificarPost(postcondiciones);
                controller.setComportamiento(instrucciones);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("No se encontró la tarea.");
        }

        return true;
    }
    //Agrega las Tareas al archivo txt
    public void añadirTarea(Tarea tarea) {
        try (BufferedWriter escribir = new BufferedWriter(new FileWriter("Tareas.txt", true))) {
            escribir.write("Nombre: " + tarea.getNombre() + "\n");
            escribir.write("Precondiciones: " + tarea.getPrecondiciones() + "\n");
            escribir.write("Postcondiciones: " + tarea.getPostcondiciones() + "\n");
            escribir.write("Instrucciones: " + tarea.getInstrucciones() + "\n");
            escribir.write("Pausable: " + (tarea.isPausable() ? "Si" : "No") + "\n");
            escribir.write("Estado: " + tarea.getEstado() + "\n");
            escribir.write("----------------------------------------\n");
        } catch (IOException e) {
            System.out.println("Hubo un error al guardar la tarea: " + e.getMessage());
        }
    }

    //Ver Tareas del archivo

    public void verTareas() {
        listaTareas.clear(); // Limpiar la lista antes de cargar nuevos datos
        try (BufferedReader reader = new BufferedReader(new FileReader("Tareas.txt"))) {
            String linea;
            String nombre= "";
            String precondiciones ="";
            String postcondiciones="";
            String instrucciones="";
            boolean pausable = false;
            EnumEstado estado = null;

            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Nombre: ")) {
                    nombre = linea.substring(8);
                } else if (linea.startsWith("Precondiciones: ")) {
                    precondiciones = linea.substring(15);
                } else if (linea.startsWith("Postcondiciones: ")) {
                    postcondiciones = linea.substring(16);
                } else if (linea.startsWith("Instrucciones: ")) {
                    instrucciones = linea.substring(14);
                } else if (linea.startsWith("Pausable: ")) {
                    pausable = linea.substring(9).equals("Si");
                } else if (linea.startsWith("Estado: ")) {
                    estado = EnumEstado.valueOf(linea.substring(8)); // Convertir texto a Enum
                } else if (linea.equals("----------------------------------------")) {
                    // Cuando encuentra la separación, agrega la tarea a la lista
                    listaTareas.add(new Tarea(nombre, precondiciones, postcondiciones, instrucciones, pausable));
                }
            }
            //Actualiza la tabla cada vez que se agreguen mas
            tableViewTareas.setItems(listaTareas);
            System.out.println("Tareas cargadas correctamente.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }



}
