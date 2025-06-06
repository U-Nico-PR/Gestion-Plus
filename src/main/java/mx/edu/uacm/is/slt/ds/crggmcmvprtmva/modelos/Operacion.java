package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Operacion {
    private final String nombre;
    private final ObservableList<Tarea> tareas;
    private final StringProperty estado;

    public Operacion(String nombre) {
        this.nombre = nombre;
        this.tareas = FXCollections.observableArrayList();
        this.estado = new SimpleStringProperty(EnumEstado.NO_EJECUTADA.toString());
    }

    public void ejecutar() {
        estado.set(EnumEstado.EJECUTANDO.toString());
        for (Tarea tarea : tareas) {
            if (tarea.getEstadoEnum() != EnumEstado.FINALIZADA) {
                tarea.ejecutar();
            }
        }
    }

    public void pausar() {
        if (estado.get().equals(EnumEstado.EJECUTANDO.toString())) {
            estado.set(EnumEstado.PAUSADA.toString());
            for (Tarea tarea : tareas) {
                if (tarea.getEstadoEnum() == EnumEstado.EJECUTANDO && tarea.isPausable()) {
                    tarea.pausar();
                }
            }
        }
    }

    public void reanudar() {
        if (estado.get().equals(EnumEstado.PAUSADA.toString())) {
            estado.set(EnumEstado.EJECUTANDO.toString());
            for (Tarea tarea : tareas) {
                if (tarea.getEstadoEnum() == EnumEstado.PAUSADA) {
                    tarea.reanudar();
                }
            }
        }
    }

    public void detener() {
        estado.set(EnumEstado.DETENIDA.toString());
        for (Tarea tarea : tareas) {
            tarea.detener();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public ObservableList<Tarea> getTareas() {
        return tareas;
    }

    public void agregarTarea(Tarea tarea) {
        this.tareas.add(tarea);
    }

    public void eliminarTarea(Tarea tarea) {
        this.tareas.remove(tarea);
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }
}