package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SistemaOperaciones {
    private static SistemaOperaciones instancia;
    private final ObservableList<Operacion> operaciones;

    private SistemaOperaciones() {
        operaciones = FXCollections.observableArrayList();

        // Datos de ejemplo para demostración inicial
        Operacion op1 = new Operacion("Proceso de Facturación");
        Tarea t1 = new Tarea("Recibir Pedido", "Pedido del cliente", "Pedido validado", "Validar stock", true);
        Tarea t2 = new Tarea("Generar Factura", "Pedido validado", "Factura emitida", "Crear PDF", false);
        Tarea t3 = new Tarea("Enviar Factura", "Factura emitida", "Factura enviada", "Enviar email al cliente", true);
        op1.agregarTarea(t1);
        op1.agregarTarea(t2);
        op1.agregarTarea(t3);

        Operacion op2 = new Operacion("Logística de Entrega");
        Tarea t4 = new Tarea("Preparar Paquete", "Orden de envío", "Paquete listo", "Empaquetar producto", true);
        op2.agregarTarea(t4);


        operaciones.add(op1);
        operaciones.add(op2);
    }

    public static synchronized SistemaOperaciones getInstancia() {
        if (instancia == null) {
            instancia = new SistemaOperaciones();
        }
        return instancia;
    }

    public void agregarOperacion(Operacion operacion) {
        operaciones.add(operacion);
    }

    public void eliminarOperacion(Operacion operacion) {
        operaciones.remove(operacion);
    }

    public ObservableList<Operacion> getOperaciones() {
        return operaciones;
    }
}