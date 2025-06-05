package mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos;

import java.util.ArrayList;
import java.util.List;

public class SistemaOperaciones {
    private static SistemaOperaciones instancia;
    private List<String> operaciones;

    private SistemaOperaciones() {
        this.operaciones = new ArrayList<>();
    }

    public static synchronized SistemaOperaciones getInstancia() {
        if (instancia == null) {
            instancia = new SistemaOperaciones();
        }
        return instancia;
    }

    public void crearOperacion(String nombre) {
        operaciones.add(nombre);
    }

    public List<String> getOperaciones() {
        return operaciones;
    }
}

