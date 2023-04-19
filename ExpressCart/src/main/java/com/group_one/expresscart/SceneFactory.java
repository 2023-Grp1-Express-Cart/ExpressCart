/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Factory Method to create appropriate Scene object.
 *
 * @author Group 1
 */
public class SceneFactory {

    /**
     * Enumerated type representing the available Scenes of the application
     */
    public enum SceneType {
        REVENUE,
        LOGIN,
        INVENTORY,
        CHECKOUT,
        CUSTOMER_HOME,
        SELLER_HOME,
        APP_HOME
    }

    private final int WIDTH = 600;
    private final int HEIGHT = 800;
    private final Stage stage;

    private Scene revenue_scene = null;
    private Scene login_scene = null;
    private Scene inventory_scene = null;
    private Scene checkout_scene = null;
    private Scene customer_home_scene = null;
    private Scene seller_home_scene = null;
    private Scene app_home_scene = null;

    /**
     *
     * @param primaryStage
     */
    public SceneFactory(Stage primaryStage) {
        stage = primaryStage;
    }

    /**
     *
     * @param scenetype
     * @return
     */
    public Scene GetScene(SceneType scenetype) {

        switch (scenetype) {
            case REVENUE:
                revenue_scene = new Scene(new Revenue(stage), WIDTH, HEIGHT);
                return revenue_scene;
            case LOGIN:
                login_scene = new Scene(new Login(stage), WIDTH, HEIGHT);
                return login_scene;
            case INVENTORY:
                inventory_scene = new Scene(new Inventory(stage), WIDTH, HEIGHT);
                return inventory_scene;
            case CHECKOUT:
                checkout_scene = new Scene(new Checkout(stage), WIDTH, HEIGHT);
                return checkout_scene;
            case CUSTOMER_HOME:
                customer_home_scene = new Scene(new Customer(stage), WIDTH, HEIGHT);
                return customer_home_scene;
            case SELLER_HOME:
                seller_home_scene = new Scene(new Seller(stage), WIDTH, HEIGHT);
                return seller_home_scene;
            case APP_HOME:
                app_home_scene = new Scene(new Home(stage), WIDTH, HEIGHT);
                return app_home_scene;
            default:
                return null;
        }
    }
}
