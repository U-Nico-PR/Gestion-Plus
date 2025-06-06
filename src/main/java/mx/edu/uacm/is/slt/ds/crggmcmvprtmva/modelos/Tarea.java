package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Tarea {
    private String nombre;
    private String precondiciones;
    private String postcondiciones;
    private String instrucciones;
    private boolean pausable;
    private final ObjectProperty<EnumEstado> estado;

    public Tarea(String nombre, String precondiciones, String postcondiciones, String instrucciones, boolean pausable) {
        this.nombre = nombre;
        this.precondiciones = precondiciones;
        this.postcondiciones = postcondiciones;
        this.instrucciones = instrucciones;
        this.pausable = pausable;
        this.estado = new SimpleObjectProperty<>(EnumEstado.NO_EJECUTADA);
    }

    public void ejecutar() {
        if (this.getEstadoEnum() == EnumEstado.NO_EJECUTADA || this.getEstadoEnum() == EnumEstado.DETENIDA) {
            this.estado.set(EnumEstado.EJECUTANDO);
        }
    }

    public void pausar() {
        if (this.isPausable() && this.getEstadoEnum() == EnumEstado.EJECUTANDO) {
            this.estado.set(EnumEstado.PAUSADA);
        }
    }

    public void reanudar() {
        if (this.isPausable() && this.getEstadoEnum() == EnumEstado.PAUSADA) {
            this.estado.set(EnumEstado.EJECUTANDO);
        }
    }

    public void detener() {
        this.estado.set(EnumEstado.DETENIDA);
    }

    // --- GETTERS ---
    public String getNombre() { return nombre; }
    public EnumEstado getEstadoEnum() { return estado.get(); }
    public ObjectProperty<EnumEstado> estadoProperty() { return estado; }
    public boolean isPausable() { return pausable; }
    public String getPrecondiciones() { return precondiciones; }
    public String getPostcondiciones() { return postcondiciones; }
    public String getInstrucciones() { return instrucciones; }

    // --- SETTERS ---
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecondiciones(String precondiciones) { this.precondiciones = precondiciones; }
    public void setPostcondiciones(String postcondiciones) { this.postcondiciones = postcondiciones; }
    public void setInstrucciones(String instrucciones) { this.instrucciones = instrucciones; }
    public void setPausable(boolean pausable) { this.pausable = pausable; }
}