/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;

import static com.group_one.expresscart.ExpressCart.SceneGenerator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class that Renders the Layout for User Home Page.
 * 
 * @author Group 1
 */
public final class Home extends VBox {

    private final Label _welcome_label;
    private final Button _app_login_btn;

    /**
     * The Constructor for Layout for the Home Page
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     */
    public Home(Stage primaryStage) {

        _welcome_label = new Label("Welcome to ExpressCart");
        _app_login_btn = new Button("Login");
        _app_login_btn.setOnAction(e -> {
            Scene login_scene = SceneGenerator.getScene(SceneFactory.SceneType.LOGIN);
            assert login_scene != null : "Precondition : Check that value is not null Object";
            primaryStage.setScene(login_scene);
        });

        this.getChildren().add(_welcome_label);
        this.getChildren().add(_app_login_btn);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(15));
    }
}
