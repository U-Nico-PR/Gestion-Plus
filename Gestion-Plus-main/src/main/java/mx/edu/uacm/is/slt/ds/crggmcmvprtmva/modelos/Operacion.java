package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos;

import java.util.ArrayList;
import java.util.List;

public class Operacion implements IMetodosEstados {

    private String nombre;
    private List<Tarea> tareas;
    private EnumEstado estado;

    public Operacion(String nombre) {
        this.nombre = nombre;
        this.tareas = new ArrayList<>();
        this.estado = EnumEstado.NO_EJECUTADA;
    }

    @Override
    public void ejecutar() {
        estado = EnumEstado.EJECUTANDO;
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
        }
    }

    @Override
    public void detener() {
        estado = EnumEstado.DETENIDA;
        for (Tarea tarea : tareas) {
            tarea.detener();
        }
    }

    @Override
    public void actualizar(EnumEstado nuevoEstado) {
        this.estado = nuevoEstado;
        verificarEstado();
    }

    @Override
    public EnumEstado getEstado() {
        return estado;
    }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    public void eliminarTarea(Tarea tarea) {
        tareas.remove(tarea);
    }

    private void verificarEstado() {
        if (tareas.stream().allMatch(t -> t.getEstado() == EnumEstado.FINALIZADA)) {
            estado = EnumEstado.FINALIZADA;
        }
    }
}

