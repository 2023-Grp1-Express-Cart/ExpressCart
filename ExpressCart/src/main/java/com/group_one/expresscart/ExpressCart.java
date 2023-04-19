/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.group_one.expresscart;

import java.io.IOException;
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
     * TODO
     * @param primaryStage
     * @throws IOException 
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        InventoryMgr = InventoryManager.getInstance();
        SceneGenerator = new SceneFactory(primaryStage);
        Scene AppHomeScene = new Scene(new Home(primaryStage), 600, 800);
        primaryStage.setTitle("Welcome to ExpressCart");
        primaryStage.setScene(AppHomeScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}