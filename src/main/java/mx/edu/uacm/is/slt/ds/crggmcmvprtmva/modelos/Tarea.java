package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos;

public class Tarea {

    private String nombre;
    private String descripcion;
    private String precondiciones;
    private String postcondiciones;
    private String comportamiento;
    private boolean pausable;
    private Estado estado;
    private Operacion operacionAsignada;


    public Tarea(String nombre, String descripcion, String precondiciones, String postcondiciones, String comportamiento, boolean pausable, Estado estado, Operacion operacionAsignada) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precondiciones = precondiciones;
        this.postcondiciones = postcondiciones;
        this.comportamiento = comportamiento;
        this.pausable = pausable;
        this.estado = Estado.NO_EJECUTADA;
        this.operacionAsignada = operacionAsignada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecondiciones() {
        return precondiciones;
    }

    public void setPrecondiciones(String precondiciones) {
        this.precondiciones = precondiciones;
    }

    public String getPostcondiciones() {
        return postcondiciones;
    }

    public void setPostcondiciones(String postcondiciones) {
        this.postcondiciones = postcondiciones;
    }

    public String getComportamiento() {
        return comportamiento;
    }

    public void setComportamiento(String comportamiento) {
        this.comportamiento = comportamiento;
    }

    public boolean isPausable() {
        return pausable;
    }

    public void setPausable(boolean pausable) {
        this.pausable = pausable;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Operacion getOperacionAsignada() {
        return operacionAsignada;
    }

    public void setOperacionAsignada(Operacion operacionAsignada) {
        this.operacionAsignada = operacionAsignada;
    }
}
