package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos;

import java.util.ArrayList;
import java.util.List;

public class Operacion implements IMetodosEstados {

    // Atributos
    private String nombre;
    private List<Tarea> tareas;
    private EnumEstado estado;

    // Constructor
    public Operacion (String nombre){
        this.nombre = nombre;
        tareas = new ArrayList<Tarea>();
        this.estado = EnumEstado.NO_EJECUTADA;
    }

    // Métodos sobreescritos de la interfaz
    @Override
    public void ejecutar() {

    }

    @Override
    public void pausar() {

    }

    @Override
    public void reanudar() {

    }

    @Override
    public void detener() {

    }

    @Override
    public void actualizar(EnumEstado estado) {

    }

    @Override
    public EnumEstado getEstado() {
        return null;
    }

    // Métodos propios de la clase
    public void agregarTarea(Tarea tarea){
        tareas.add(tarea);
    }

    public void eliminarTarea(Tarea tarea){
        tareas.remove(tarea);
    }

    public void modificarTarea(Tarea tarea){

    }

}
