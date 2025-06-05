module mx.edu.uacm.is.slt.ds.crggmcmvprtmva.principal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens mx.edu.uacm.is.slt.ds.crggmcmvprtmva.principal to javafx.fxml;
    exports mx.edu.uacm.is.slt.ds.crggmcmvprtmva.principal;

    exports mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores;
    opens mx.edu.uacm.is.slt.ds.crggmcmvprtmva.controladores to javafx.fxml;

    opens mx.edu.uacm.is.slt.ds.crggmcmvprtmva.modelos to javafx.base;
}
