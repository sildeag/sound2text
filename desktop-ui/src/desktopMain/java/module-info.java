module org.example.sound2textapp {
    // JavaFX modules
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;
    requires javafx.media;

    // Kotlin standard library
    requires kotlin.stdlib;

    // Third-party libraries (if modular)
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    // Vosk (if modular — see note below)
    requires vosk;

    // iText 9.0.0 needed due to errors in 9.2.0
    // with Kotlin version available to IntelliJ Community
    requires kernel;
    requires layout;

    // Java
    requires java.prefs;
    requires java.desktop;


    // Open for reflection (FXML)
    opens org.example.sound2text to javafx.fxml;

    // Export your main package
    exports org.example.sound2text;
}