package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.observador;

// Interface para los objetos observables
public interface Observable {
    void registrarObservador(Observador observador);
    void eliminarObservador(Observador observador);
    void notificarObservadores(String mensaje);
}
