/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.group_one.expresscart;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author Group 1
 */
public final class ExpressCart extends Application {

    public static InventoryManager InventoryMgr;
    public static SceneFactory SceneGenerator;

    /**
     * @param primaryStage - the primary stage for this application, onto which
     * the application scene can be set.
     * @throws Exception - Throws an Exception if something goes wrong.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        InventoryMgr = InventoryManager.getInstance();
        SceneGenerator = new SceneFactory(primaryStage);
        Scene AppHomeScene = new Scene(new Home(primaryStage), 600, 750);
        primaryStage.setTitle("Welcome to ExpressCart");
        primaryStage.setScene(AppHomeScene);
        primaryStage.show();
    }

    /**
     * Main method to Launch the application
     * @param args - the command line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}