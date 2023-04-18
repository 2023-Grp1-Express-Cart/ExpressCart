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
 * @author norvinholness
 */

public class Seller {

    private final Button _seller_logout_btn;
    private final Button _inventory_btn;
    private final Button _revenue_btn;
    private final VBox _home_page_layout;

    public Seller(Stage primaryStage) {

        _home_page_layout = new VBox();
        _seller_logout_btn = new Button("Log Out");
        _inventory_btn = new Button("Inventory");
        _revenue_btn = new Button("Revenue");
        
        _seller_logout_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Scene s = SceneGenerator.GetScene(SceneFactory.SceneType.LOGIN);
                primaryStage.setScene(s);
            }
        });

        _inventory_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Scene s = SceneGenerator.GetScene(SceneFactory.SceneType.INVENTORY);
                primaryStage.setScene(s);
            }
        });

        _revenue_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Scene s = SceneGenerator.GetScene(SceneFactory.SceneType.REVENUE);
                primaryStage.setScene(s);
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

        _home_page_layout.getChildren().add(welcome_banner);
        _home_page_layout.getChildren().add(home_buttons);
        
    }

    public VBox getLayout() {
        return this._home_page_layout;
    }

    public Button getLogoutButton() {
        return _seller_logout_btn;
    }

    public Button getInventoryButton() {
        return _inventory_btn;
    }

    public Button getRevenueButton() {
        return _revenue_btn;
    }
}
