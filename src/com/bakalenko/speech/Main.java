/**
 * Created by Wish on 25.08.2015.
 */
package com.bakalenko.speech;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    private Locale currentLocale = Locale.forLanguageTag("ru");
    static public Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        ResourceBundle textResources = ResourceBundle.getBundle("com.bakalenko.resources.TextBundle", currentLocale);
        BorderPane root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"), textResources);

        Scene mainScene = new Scene(root);
        primaryStage.setTitle(textResources.getString("appName"));
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);

        primaryStage.show();
    }

    // Launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}