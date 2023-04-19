/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;


import static com.group_one.expresscart.ExpressCart.SceneGenerator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Group 1
 */
public final class Seller extends VBox {

    private final Button _seller_logout_btn;
    private final Button _inventory_btn;
    private final Button _revenue_btn;

    /**
     * Constructor for seller scene
     * @param primaryStage The Stage needed to 
     */
    public Seller(Stage primaryStage) {
        
        _seller_logout_btn = new Button("Log Out");
        _inventory_btn = new Button("Inventory");
        _revenue_btn = new Button("Revenue");
        
        _seller_logout_btn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * TODO
             * @param e 
             */
            @Override
            public void handle(ActionEvent e) {
                Scene login_scene = SceneGenerator.GetScene(SceneFactory.SceneType.LOGIN);
                primaryStage.setScene(login_scene);
            }
        });

        _inventory_btn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * TODO
             * @param e 
             */
            @Override
            public void handle(ActionEvent e) {
                Scene inventory_scene = SceneGenerator.GetScene(SceneFactory.SceneType.INVENTORY);
                primaryStage.setScene(inventory_scene);
            }
        });

        _revenue_btn.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * TODO
             * @param e 
             */
            @Override
            public void handle(ActionEvent e) {
                Scene reveneu_scene = SceneGenerator.GetScene(SceneFactory.SceneType.REVENUE);
                primaryStage.setScene(reveneu_scene);
            }
        });
        
        HBox welcome_banner = new HBox();
        welcome_banner.getChildren().add(new Label("Welcome Seller"));
        welcome_banner.setAlignment(Pos.CENTER);

        HBox home_buttons = new HBox();
        home_buttons.getChildren().add(_inventory_btn);
        home_buttons.getChildren().add(_revenue_btn);
        home_buttons.getChildren().add(_seller_logout_btn);
        home_buttons.setSpacing(10);
        home_buttons.setAlignment(Pos.CENTER);

        this.getChildren().add(welcome_banner);
        this.getChildren().add(home_buttons);
    }
}
