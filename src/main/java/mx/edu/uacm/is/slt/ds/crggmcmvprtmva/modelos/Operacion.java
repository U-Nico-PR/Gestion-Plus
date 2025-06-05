package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos;

import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.observador.Observable;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.observador.Observador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Operacion implements IMetodosEstados, Observable {
    private String nombre;
    private List<Tarea> tareas;
    private EnumEstado estado;
    private List<Observador> observadores = new ArrayList<>();

    public Operacion(String nombre) {
        this.nombre = nombre;
        this.tareas = new ArrayList<>();
        this.estado = EnumEstado.NO_EJECUTADA;
    }

    @Override
    public void ejecutar() {
        estado = EnumEstado.EJECUTANDO;
        notificarObservadores("La operación " + nombre + " ha comenzado a ejecutarse");
        for (Tarea tarea : tareas) {
            tarea.ejecutar();
        }
        verificarEstado();
    }

    @Override
    public void pausar() {
        if (estado == EnumEstado.EJECUTANDO) {
            for (Tarea tarea : tareas) {
                if (tarea.getEstado() == EnumEstado.EJECUTANDO && tarea.isPausable()) {
                    tarea.pausar();
                }
            }
            estado = EnumEstado.PAUSADA;
            notificarObservadores("La operación " + nombre + " ha sido pausada");
        }
    }

    @Override
    public void reanudar() {
        if (estado == EnumEstado.PAUSADA) {
            for (Tarea tarea : tareas) {
                if (tarea.getEstado() == EnumEstado.PAUSADA) {
                    tarea.reanudar();
                }
            }
            estado = EnumEstado.EJECUTANDO;
            notificarObservadores("La operación " + nombre + " ha sido reanudada");
        }
    }

    @Override
    public void detener() {
        estado = EnumEstado.DETENIDA;
        notificarObservadores("La operación " + nombre + " ha sido detenida");
        for (Tarea tarea : tareas) {
            tarea.detener();
        }
    }

    @Override
    public void actualizar(EnumEstado nuevoEstado) {
        this.estado = nuevoEstado;
        notificarObservadores("El estado de la operación " + nombre + " ha cambiado a " + nuevoEstado);
        verificarEstado();
    }

    @Override
    public EnumEstado getEstado() {
        return estado;
    }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
        notificarObservadores("Nueva tarea agregada a la operación " + nombre);
    }

    public void eliminarTarea(Tarea tarea) {
        tareas.remove(tarea);
        notificarObservadores("Tarea eliminada de la operación " + nombre);
    }

    public void modificarOrden(int index, boolean moverArriba) {
        if (moverArriba && index > 0) {
            Collections.swap(tareas, index, index - 1);
            notificarObservadores("Se modificó el orden de tareas en la operación " + nombre);
        } else if (!moverArriba && index < tareas.size() - 1) {
            Collections.swap(tareas, index, index + 1);
            notificarObservadores("Se modificó el orden de tareas en la operación " + nombre);
        }
    }

    private void verificarEstado() {
        if (tareas.stream().allMatch(t -> t.getEstado() == EnumEstado.FINALIZADA)) {
            estado = EnumEstado.FINALIZADA;
            notificarObservadores("La operación " + nombre + " ha finalizado todas sus tareas");
        }
    }

    // Implementación de los métodos de Observable
    @Override
    public void registrarObservador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void eliminarObservador(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores(String mensaje) {
        for (Observador observador : observadores) {
            observador.actualizar(this, mensaje);
        }
    }

    // Getters para los atributos
    public String getNombre() {
        return nombre;
    }

    public List<Tarea> getTareas() {
        return new ArrayList<>(tareas); // Devolvemos una copia para proteger el encapsulamiento
    }
}