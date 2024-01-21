package org.example.gui_toylanguageinterpretor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("PrgStatesSelection.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage1 = new Stage();
        stage1.setTitle("PrgState ListView");
        stage1.setScene(scene1);
        stage1.show();

        PrgStatesSelectionController selectionController = fxmlLoader.getController();

        FXMLLoader fxmlLoader1 = new FXMLLoader(Application.class.getResource("PrgStatesExecution.fxml"));
        Scene scene2 = new Scene(fxmlLoader1.load(), 600, 800);
        selectionController.setExecutionController(fxmlLoader1.getController());
        Stage stage2 = new Stage();
        stage2.setTitle("Main Window");
        stage2.setScene(scene2);
        stage2.show();
    }

    public static void main(String[] args) {
        launch();
    }
}