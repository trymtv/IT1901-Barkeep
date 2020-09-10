module com.github.TrymTv {
    requires javafx.controls;
    requires javafx.fxml;

    opens barkeep to javafx.fxml;
    exports barkeep;
}