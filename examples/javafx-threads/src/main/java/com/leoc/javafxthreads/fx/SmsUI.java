package com.leoc.javafxthreads.fx;

import com.leoc.javafxthreads.service.RechargeService;
import com.leoc.javafxthreads.thread.ThreadPool;
import com.leoc.javafxthreads.provider.SMSProviderAPIImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import static com.leoc.javafxthreads.fx.UIConstants.*;

public class SmsUI extends Application {

    private TextField amountTextField;
    private TextField serviceTextField;
    private Button rechargeButton;
    private ProgressBar rechargeProgressBar;

    private RechargeService rechargeService = new RechargeService(new SMSProviderAPIImpl());


    @Override
    public void start(Stage stage) {
        title(stage);
        GridPane pane = buildPane();
        setupControls(pane);
        stage.setScene(createScene(pane));
        handlers();
        stage.show();

    }

    private void handlers() {
        rechargeButton.setOnAction(actionEvent -> recharge());
    }

    private void recharge() {
        rechargeProgressBar.setProgress(-1);
        serviceTextField.setEditable(false);
        amountTextField.setEditable(false);
        rechargeButton.setDisable(true);
        ThreadPool.getPool().submit(() -> {
            rechargeService.recharge(serviceTextField.getText(), Integer.parseInt(amountTextField.getText()));
            RechargeService.Status status;
            do {
                status = rechargeService.status(serviceTextField.getText());
                ThreadPool.pause();
                System.out.println(status);
            } while (status == RechargeService.Status.PROGRESS || status == null);
            RechargeService.Status finalStatus = status;

            Platform.runLater(() -> {
                        rechargeProgressBar
                                .setProgress(finalStatus == RechargeService.Status.COMPLETED ? 1 : 0);
                        serviceTextField.setEditable(true);
                        amountTextField.setEditable(true);
                        rechargeButton.setDisable(false);
                    }
            );

        });

    }

    private GridPane buildPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(LABEL_WITH, LABEL_WITH, LABEL_WITH_MAX);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(INPUT_WITH, INPUT_WITH, INPUT_WITH_MAX);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void setupControls(GridPane pane) {
        amountTextField = buildTextInput("Monto: ", pane, 0);
        serviceTextField = buildTextInput("Servicio: ", pane, 1);
        rechargeButton = buildGenerateButton("Recargar", pane, 2);
        rechargeProgressBar = new ProgressBar(0);
        pane.add(rechargeProgressBar, 0, 3, 3, 1);

    }

    private Button buildGenerateButton(String label, GridPane pane, int row) {
        Button button = new Button(label);
        pane.add(button, 0, row, 2, 1);
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setMargin(button, new Insets(20, 0, 20, 0));
        return button;
    }

    private TextField buildTextInput(String text, GridPane pane, int row) {
        Label minNumberLabel = new Label(text);
        pane.add(minNumberLabel, 0, row);
        TextField textField = new TextField();
        pane.add(textField, 1, row);
        return textField;
    }

    private void title(Stage stage) {
        stage.setTitle("Bienvenido al generador de passwords");
    }

    private Scene createScene(Pane pane) {
        return new Scene(pane, 800, 500);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
