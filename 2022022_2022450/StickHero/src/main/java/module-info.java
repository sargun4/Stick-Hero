module com.application.stickhero {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.application.stickhero to javafx.fxml;
    exports com.application.stickhero;
}