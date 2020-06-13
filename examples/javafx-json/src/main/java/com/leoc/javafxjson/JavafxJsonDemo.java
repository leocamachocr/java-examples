package com.leoc.javafxjson;

import com.leoc.javafxjson.domain.Catalog;
import com.leoc.javafxjson.persistence.CatalogPersistence;
import com.leoc.javafxjson.persistence.CatalogPersistenceFile;
import com.leoc.javafxjson.persistence.InventoryPersistence;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Based on https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
 * Also this can help https://self-learning-java-tutorial.blogspot.com/2018/06/javafx-tableview-adding-new-rows-to.html
 */
public class JavafxJsonDemo extends Application {

    private static CatalogPersistence catalogPersistence = new CatalogPersistenceFile();
    private static InventoryPersistence inventoryPersistence = new InventoryPersistence();

    private final TableView<Map<String, String>> table = new TableView<>();



    public static void main(String[] args) {
        createCatalogSchema();
        createCatalogInventory();

        launch(args);
    }

    private static void createCatalogInventory() {

        List<Map<String, String>> inventory = new ArrayList<>();
        Map<String, String> first = new HashMap<>();
        first.put("Nombre", "Al");
        first.put("Tipo", "foo");
        first.put("Valor", "123");
        Map<String, String> second = new HashMap<>();
        second.put("Nombre", "Bal");
        second.put("Tipo", "bar");
        second.put("Valor", "456");
        second.put("Id", "xxxxx");
        inventory.add(first);
        inventory.add(second);
        inventoryPersistence.save(inventory);

    }

    private static Catalog createCatalogSchema() {
        Catalog catalog = new Catalog();
        catalog.addName("Nombre");
        catalog.addName("Tipo");
        catalog.addName("Valor");
        catalog.addName("Id");
        catalogPersistence.save(catalog);
        return catalog;
    }

    @Override
    public void start(Stage stage) {
        //1. Manejo de los JSON(Files)
        //2. Procesamiento de datos
        //2. Manejo de UI
        //4. Inicialización de la Aplicación
        Scene scene = new Scene(new Group());//Define la ventana de la aplicación
        stage.setTitle("Table View Sample");
        stage.setWidth(300);
        stage.setHeight(500);
        Label title = new Label("Element Book");//Título de la funcionalidad
        title.setFont(new Font("Arial", 20));

        table.setEditable(true);
        Catalog catalog = catalogPersistence.getCatalog();//??file? formato? no se y no nos interesa
        List<Map<String, String>> inventory = inventoryPersistence.getInventory();

        for (String column : catalog.getNames()) {
            TableColumn<Map<String, String>, String> tableColumn = new TableColumn<>(column);
            tableColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().get(column)));
            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            tableColumn.setOnEditCommit(data -> {
                Map<String, String> row = data.getRowValue();
                row.put(column, data.getNewValue());
                if (catalog.getNames().stream().anyMatch(it -> row.get(it) != null)) {
                    inventoryPersistence.save(inventory);
                }

            });

            table.getColumns().add(tableColumn);
        }
        inventory.forEach(it -> table.getItems().add(it));

        Button addRowButton = new Button("Add Row");
        addRowButton.setOnAction(event -> {
            Map<String, String> newRow = new HashMap<>();
            table.getItems().add(newRow);
            inventory.add(newRow);
            TableColumn<Map<String, String>, ?> col1 = table.getColumns().get(0);
            TableView.TableViewSelectionModel<Map<String, String>> selectionModel = table.getSelectionModel();
            selectionModel.setCellSelectionEnabled(true);
            table.requestFocus();
            table.getSelectionModel().select(inventory.size() - 1, col1);
        });
        VBox vbox = new VBox();//pinta los componentes
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(title, addRowButton, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}