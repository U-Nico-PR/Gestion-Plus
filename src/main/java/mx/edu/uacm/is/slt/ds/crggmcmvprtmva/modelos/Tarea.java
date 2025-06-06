package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos;

public class Tarea extends Thread implements IMetodosEstados {
    private String nombre;
    private String precondiciones;
    private String postcondiciones;
    private String instrucciones;
    private boolean pausable;
    private boolean tareaInicial;
    private EnumEstado estado;
    private boolean running = true;

    public Tarea(String nombre, String precondiciones, String postcondiciones, String instrucciones, boolean pausable, boolean tareaInicial) {
        this.nombre = nombre;
        this.precondiciones = precondiciones;
        this.postcondiciones = postcondiciones;
        this.instrucciones = instrucciones;
        this.pausable = pausable;
        this.tareaInicial = tareaInicial;
        this.estado = EnumEstado.NO_EJECUTADA;
    }

    //Segundo Constructor para Crear Tarea
    public Tarea(String nombre, String precondiciones, String postcondiciones, String instrucciones, boolean pausable) {
        this.nombre = nombre;
        this.precondiciones = precondiciones;
        this.postcondiciones = postcondiciones;
        this.instrucciones = instrucciones;
        this.pausable = pausable;
        this.estado = EnumEstado.NO_EJECUTADA;
    }


    @Override
    public void run() {
        ejecutar();
        while (running) {
            try {
                System.out.println("Ejecutando tarea: " + nombre);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Tarea interrumpida: " + nombre);
                Thread.currentThread().interrupt();
                running = false;
            }
        }
    }

    @Override
    public void ejecutar() {
        actualizar(EnumEstado.EJECUTANDO);
        new Thread(this).start(); // Inicia un nuevo hilo
    }

    @Override
    public void pausar() {
        if (pausable) {
            actualizar(EnumEstado.PAUSADA);
            running = false;
        }
    }

    @Override
    public void reanudar() {
        if (pausable && estado == EnumEstado.PAUSADA) {
            running = true;
            actualizar(EnumEstado.EJECUTANDO);
            new Thread(this).start();
        }
    }

    @Override
    public void detener() {
        actualizar(EnumEstado.DETENIDA);
        running = false;
        this.interrupt();
    }

    @Override
    public void actualizar(EnumEstado estado) {
        this.estado = estado;
        System.out.println("Estado actualizado a: " + estado);
    }

    @Override
    public EnumEstado getEstado() {
        return estado;
    }

    public String getPostcondiciones() {
        return postcondiciones;
    }
    public String getInstrucciones() {
        return instrucciones;
    }
    public String getPrecondiciones() {
        return precondiciones;
    }
    public String getNombre() {
        return nombre;
    }
    

    public boolean isPausable() {
        return pausable;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecondiciones(String precondiciones) {
        this.precondiciones = precondiciones;
    }

    public void setPostcondiciones(String postcondiciones) {
        this.postcondiciones = postcondiciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public void setPausable(boolean pausable) {
        this.pausable = pausable;
    }

    public boolean isTareaInicial() {
        return tareaInicial;
    }

    public void setTareaInicial(boolean tareaInicial) {
        this.tareaInicial = tareaInicial;
    }

    public void setEstado(EnumEstado estado) {
        this.estado = estado;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}