package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos;

public class Operacion {
    private String identificador;
    private String nombere;
    private Estado estado;
    private Tarea tareas;

    public Operacion(String nombre) {
        estado = Estado.NO_EJECUTADA;
    }
}
