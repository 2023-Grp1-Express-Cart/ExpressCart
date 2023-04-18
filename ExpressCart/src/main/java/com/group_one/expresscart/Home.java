/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;

import static com.group_one.expresscart.ExpressCart.SceneGenerator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author norvinholness
 */
public class Home {
    
    private final VBox _layout;
    private final Label _welcome_label;
    private final Button _app_login_btn;
    
    public Home(Stage primaryStage){
        
        _welcome_label = new Label("Welcome to ExpressCart");
        _layout = new VBox();
        
        _app_login_btn = new Button("Login");
        _app_login_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Scene s = SceneGenerator.GetScene(SceneFactory.SceneType.LOGIN);
                primaryStage.setScene(s);
            }
        });
        
        _layout.getChildren().add(_welcome_label);
        _layout.getChildren().add(_app_login_btn);
        _layout.setAlignment(Pos.CENTER);
        _layout.setPadding(new Insets(15));
    }
    
    public VBox getLayout(){
        return _layout;
    }
}
