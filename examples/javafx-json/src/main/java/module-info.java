module javafxJson {
    requires javafx.controls;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    exports com.leoc.javafxjson to javafx.graphics;
    exports  com.leoc.javafxjson.domain to com.fasterxml.jackson.databind;
}