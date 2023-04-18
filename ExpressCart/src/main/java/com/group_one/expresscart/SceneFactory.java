/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author norvinholness
 */
public class SceneFactory {
    
    /**
     * 
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
    public Stage stage;
    
    public SceneFactory(Stage primaryStage){
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
                Revenue r = new Revenue(stage);
                return new Scene(r.getLayout(), WIDTH, HEIGHT);
            case LOGIN:
                Login login = new Login(stage);
                return new Scene(login.getLayout(), WIDTH, HEIGHT);
            case INVENTORY:
                Inventory i = new Inventory(stage);
                return new Scene(i.getLayout(), WIDTH, HEIGHT);
            case CHECKOUT:
                Checkout c = new Checkout(stage);
                return new Scene(c.getLayout(), WIDTH, HEIGHT);
            case CUSTOMER_HOME:
                Customer customer = new Customer(stage);
                return new Scene(customer.getLayout(), WIDTH, HEIGHT);
            case SELLER_HOME:
                Seller s = new Seller(stage);
                return new Scene(s.getLayout(), WIDTH, HEIGHT);
            case APP_HOME:
                Home h = new Home(stage);
                return new Scene(h.getLayout(), WIDTH, HEIGHT);
            default:
                return null;
        }
    }
}
