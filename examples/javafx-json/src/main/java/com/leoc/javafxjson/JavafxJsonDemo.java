package com.leoc.javafxjson;

import com.leoc.javafxjson.domain.Catalog;
import com.leoc.javafxjson.json.JsonUtil;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Based on https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
 */
public class JavafxJsonDemo extends Application {
    private static JsonUtil jsonUtil = new JsonUtil();
    private static File catalogSchemaFile;
    private static File inventorySchemaFile;

    private TableView<Map<String, String>> table = new TableView();

    public static void main(String[] args) {
        Integer value = 11;
        value.equals(11);
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
        inventory.add(first);
        inventory.add(second);
        try {
            inventorySchemaFile = File.createTempFile("inventory", ".json");//usar ruta fija del archivo, no temporal
            jsonUtil.toFile(inventorySchemaFile, inventory);
            System.out.println(catalogSchemaFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Catalog createCatalogSchema() {
        Catalog catalog = new Catalog();
        catalog.addName("Nombre");
        catalog.addName("Tipo");
        catalog.addName("Valor");
        try {
            catalogSchemaFile = File.createTempFile("catalog", ".json");//usar ruta fija del archivo, no temporal
            jsonUtil.toFile(catalogSchemaFile, catalog);
            System.out.println(catalogSchemaFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return catalog;
    }

    @Override
    public void start(Stage stage) throws MalformedURLException {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(300);
        stage.setHeight(500);

        final Label label = new Label("Element Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);
        Catalog catalog = jsonUtil.asObject(catalogSchemaFile.toURI().toURL(), Catalog.class);
        List<Map<String, String>> inventory = jsonUtil.asObject(inventorySchemaFile.toURI().toURL(), List.class);

        for (String column : catalog.getNames()) {
            TableColumn<Map<String, String>, String> header = new TableColumn<>(column);
            header.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().get(column)));
            table.getColumns().add(header);
        }
        inventory.forEach(it -> table.getItems().add(it));

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}