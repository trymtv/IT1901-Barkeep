module barkeep {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;

    opens barkeep to javafx.fxml, com.fasterxml.jackson.annotation, com.fasterxml.jackson.core, com.fasterxml.jackson.databind;
    exports barkeep;
}