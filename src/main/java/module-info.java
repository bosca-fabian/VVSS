module pizzashop {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;

    opens pizzashop.model to javafx.base;
    exports pizzashop.model;
    opens pizzashop to javafx.fxml;
    exports pizzashop;
    opens pizzashop.controller to javafx.fxml;
    exports pizzashop.controller;

    opens pizzashop.repository to javafx.base;
    exports pizzashop.repository;

    opens pizzashop.service;
    exports pizzashop.service; // Export the pizzashop.service package

}
