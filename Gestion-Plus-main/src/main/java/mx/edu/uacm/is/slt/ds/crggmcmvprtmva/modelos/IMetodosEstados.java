package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos;

public interface IMetodosEstados {

    // Firma de los Métodos para la interfaz
    void ejecutar();

    void pausar();

    void reanudar();

    void detener();

    void actualizar(EnumEstado estado);

    EnumEstado getEstado();

}
