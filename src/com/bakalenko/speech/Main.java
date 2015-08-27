/**
 * Valery Bakalenko
 *
 * Copyright (c) 2015-2016 DONNTU, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of DONNTU ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with DONNTU.
 *
 * DONNTU MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package com.bakalenko.speech;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Main class that starts the JavaFX application
 * @version 25.08.2015
 * @author Wish
 */
public class Main extends Application {
    /** Variable with default locale for the application */
    private Locale currentLocale = Locale.forLanguageTag("ru");

    @Override
    public void start(Stage primaryStage) throws Exception{
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