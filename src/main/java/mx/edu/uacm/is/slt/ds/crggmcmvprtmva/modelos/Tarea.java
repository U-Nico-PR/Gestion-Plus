package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos;

import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.observador.Observable;
import mx.edu.uacm.is.slt.ds.crggmcmvprtmva.observador.Observador;

import java.util.ArrayList;
import java.util.List;

public class Tarea extends Thread implements IMetodosEstados, Observable {
    private String nombre;
    private String precondiciones;
    private String postcondiciones;
    private String instrucciones;
    private boolean pausable;
    private boolean tareaInicial;
    private EnumEstado estado;
    private boolean running = true;
    private List<Observador> observadores = new ArrayList<>();

    public Tarea(String nombre, String precondiciones, String postcondiciones,
                 String instrucciones, boolean pausable, boolean tareaInicial) {
        this.nombre = nombre;
        this.precondiciones = precondiciones;
        this.postcondiciones = postcondiciones;
        this.instrucciones = instrucciones;
        this.pausable = pausable;
        this.tareaInicial = tareaInicial;
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
        new Thread(this).start();
        notificarObservadores("La tarea " + nombre + " ha comenzado a ejecutarse");
    }

    @Override
    public void pausar() {
        if (pausable) {
            actualizar(EnumEstado.PAUSADA);
            running = false;
            notificarObservadores("La tarea " + nombre + " ha sido pausada");
        }
    }

    @Override
    public void reanudar() {
        if (pausable && estado == EnumEstado.PAUSADA) {
            running = true;
            actualizar(EnumEstado.EJECUTANDO);
            new Thread(this).start();
            notificarObservadores("La tarea " + nombre + " ha sido reanudada");
        }
    }

    @Override
    public void detener() {
        actualizar(EnumEstado.DETENIDA);
        running = false;
        this.interrupt();
        notificarObservadores("La tarea " + nombre + " ha sido detenida");
    }

    @Override
    public void actualizar(EnumEstado estado) {
        this.estado = estado;
        System.out.println("Estado actualizado a: " + estado);
        notificarObservadores("La tarea " + nombre + " cambió su estado a " + estado);
    }

    @Override
    public EnumEstado getEstado() {
        return estado;
    }

    public boolean isPausable() {
        return pausable;
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

    public String getPrecondiciones() {
        return precondiciones;
    }

    public String getPostcondiciones() {
        return postcondiciones;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public boolean isTareaInicial() {
        return tareaInicial;
    }
}