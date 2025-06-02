package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos;

import java.util.ArrayList;
import java.util.List;

public class Operacion {
    private String identificador;
    private String nombre;
    private Estado estado;
    private List<Tarea> tareas;

    public Operacion(String identificador, String nombre, Estado estado, List<Tarea> tareas) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.estado = Estado.NO_EJECUTADA;
        this.tareas = new ArrayList<>();
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }


    public void pausar() {
        if (this.estado == Estado.EJECUTANDO) {
            boolean todasPausables = true;
            for (Tarea t : tareas) {
                if (t.getEstado() == Estado.EJECUTANDO && !t.isPausable()) {
                    todasPausables = false;
                    break;
                }
            }

            if (todasPausables) {
                this.estado = Estado.PAUSADA;
                for (Tarea t : tareas) {
                    if (t.getEstado() == Estado.EJECUTANDO) {
                        t.setEstado(Estado.PAUSADA);
                    }
                }
            }
        }
    }

    public void reanudar() {
        if (this.estado == Estado.PAUSADA) {
            this.estado = Estado.EJECUTANDO;
            for (Tarea t : tareas) {
                if (t.getEstado() == Estado.PAUSADA) {
                    t.setEstado(Estado.EJECUTANDO);
                }
            }
        }
    }

    public void detener() {
        if (this.estado == Estado.EJECUTANDO || this.estado == Estado.PAUSADA) {
            this.estado = Estado.DETENIDA;
            for (Tarea t : tareas) {
                if (t.getEstado() == Estado.EJECUTANDO || t.getEstado() == Estado.PAUSADA) {
                    t.setEstado(Estado.DETENIDA);
                }
            }
        }
    }

    public void ejecutar() {
        if (this.estado == Estado.NO_EJECUTADA || this.estado == Estado.DETENIDA) {
            this.estado = Estado.EJECUTANDO;
            for (Tarea t : tareas) {
                if (t.getEstado() == Estado.NO_EJECUTADA || t.getEstado() == Estado.DETENIDA) {
                    t.setEstado(Estado.EJECUTANDO);
                }
            }
        }
    }
}
