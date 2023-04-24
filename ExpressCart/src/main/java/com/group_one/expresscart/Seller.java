/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;

import static com.group_one.expresscart.ExpressCart.SceneGenerator;
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
     * The Constructor for Layout for the Seller Page
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     */
    public Seller(Stage primaryStage) {
        
        _seller_logout_btn = new Button("Log Out");
        _inventory_btn = new Button("Inventory");
        _revenue_btn = new Button("Revenue");
        
        _seller_logout_btn.setOnAction(e -> {
            Scene login_scene = SceneGenerator.GetScene(SceneFactory.SceneType.LOGIN);
            assert login_scene != null : "Precondition : Check that value is not null Object";
            primaryStage.setScene(login_scene);
        });

        _inventory_btn.setOnAction(e -> {
            Scene inventory_scene = SceneGenerator.GetScene(SceneFactory.SceneType.INVENTORY);
            assert inventory_scene != null : "Precondition : Check that value is not null Object";
            primaryStage.setScene(inventory_scene);
        });

        _revenue_btn.setOnAction(e -> {
            Scene revenue_scene = SceneGenerator.GetScene(SceneFactory.SceneType.REVENUE);
            assert revenue_scene != null : "Precondition : Check that value is not null Object";
            primaryStage.setScene(revenue_scene);
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
