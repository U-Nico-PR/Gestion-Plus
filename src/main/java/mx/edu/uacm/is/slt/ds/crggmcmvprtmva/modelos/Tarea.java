package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos;

public class Tarea implements IMetodosEstados{

    // Atributos de la clase
    private String nombre;
    private String precondiciones;
    private String postcondiciones;
    private String instrucciones;
    private boolean pausable;
    private boolean tareaInicial;
    private EnumEstado estado;

    // Constructor de la clase
    public Tarea(String nombre,
                 String precondiciones,
                 String postcondiciones,
                 String instrucciones,
                 boolean pausable,
                 boolean tareaInicial)
    {
        this.nombre = nombre;
        this.precondiciones = precondiciones;
        this.postcondiciones = postcondiciones;
        this.instrucciones = instrucciones;
        this.pausable = pausable;
        this.tareaInicial = tareaInicial;
        this.estado = EnumEstado.NO_EJECUTADA;
    }


    // comentario
    // Métodos de la interface que se definen
    @Override
    public void ejecutar() {
        actualizar(EnumEstado.EJECUTANDO);
    }

    @Override
    public void pausar() {
        actualizar(EnumEstado.PAUSADA);
    }

    @Override
    public void reanudar() {
        actualizar(EnumEstado.EJECUTANDO);
    }

    @Override
    public void detener() {
        actualizar(EnumEstado.DETENIDA);
    }

    @Override
    public void actualizar(EnumEstado estado) {
        this.estado = estado;
    }

    @Override
    public EnumEstado getEstado() {
        return estado;
    }
}
