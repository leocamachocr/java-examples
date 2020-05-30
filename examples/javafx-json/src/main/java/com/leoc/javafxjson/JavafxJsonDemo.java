package com.leoc.javafxjson;

import com.leoc.javafxjson.domain.Catalog;
import com.leoc.javafxjson.json.JsonUtil;
import javafx.application.Application;
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

/**
 * Based on https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
 */
public class JavafxJsonDemo extends Application {
    private static JsonUtil jsonUtil = new JsonUtil();
    private static File catalogFile;

    private TableView table = new TableView();

    public static void main(String[] args) {
        createFile();

        launch(args);
    }

    private static Catalog createFile() {
        Catalog catalog = new Catalog();
        catalog.addName("Nombre");
        catalog.addName("Tipo");
        catalog.addName("Valor");
        try {
            catalogFile = File.createTempFile("catalog", ".json");//usar ruta fija del archivo, no temporal
            jsonUtil.toFile(catalogFile, catalog);
            System.out.println(catalogFile.getAbsolutePath());
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

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);
        Catalog catalog = jsonUtil.asObject(catalogFile.toURI().toURL(), Catalog.class);

        //a partir de aqui
        for (String column : catalog.getNames()) {
            TableColumn header = new TableColumn(column);
            table.getColumns().addAll(header);
        }


        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}