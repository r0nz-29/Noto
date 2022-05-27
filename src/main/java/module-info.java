module com.raunits.noto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.raunits.noto to javafx.fxml;
    exports com.raunits.noto;
}