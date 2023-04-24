/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;

import static com.group_one.expresscart.ExpressCart.SceneGenerator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Group 1
 */
public final class Login extends GridPane {

    public final String CUSTOMER_USERNAME = "customer";
    public final String CUSTOMER_PASSWORD = "password";
    public final String SELLER_USERNAME = "seller";
    public final String SELLER_PASSWORD = "password";

    private final Button _sign_in_btn;
    private final RadioButton _customer_account_btn;
    private final RadioButton _seller_account_btn;

    private final ToggleGroup _group;
    private final Text _scene_title;

    private final Label _username_label;
    private final TextField _username_text_field;
    private final Label _password_label;
    private final PasswordField _password_text_field;
    private final HBox hbBtn;

    Alert _alert;

    /**
     * The Constructor for Layout for the Login Page
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     */
    public Login(Stage primaryStage) {

        _customer_account_btn = new RadioButton("Customer Account");
        _customer_account_btn.setSelected(true);
        _seller_account_btn = new RadioButton("Seller Account");
        _seller_account_btn.setSelected(false);

        _group = new ToggleGroup();
        _customer_account_btn.setToggleGroup(_group);
        _seller_account_btn.setToggleGroup(_group);

        _sign_in_btn = new Button("Sign in");

        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

        _scene_title = new Text("User Login");
        _scene_title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        _username_label = new Label("User Name:");
        _username_text_field = new TextField();

        _password_label = new Label("Password:");
        _password_text_field = new PasswordField();

        _alert = new Alert(Alert.AlertType.ERROR);
        _alert.setTitle("Login Form Error!");
        _alert.setHeaderText(null);
        _sign_in_btn.setOnAction(e -> {
            String username = String.valueOf(_username_text_field.getText());
            String password = String.valueOf(_password_text_field.getText());

            if (username.equals("")) {
                _alert.setContentText("Please enter your Username");
                _alert.show();
                return;
            }

            if (password.equals("")) {
                _alert.setContentText("Please enter your Password");
                _alert.show();
                return;
            }

            boolean valid_customer = username.equals(CUSTOMER_USERNAME)
                    && password.equals(CUSTOMER_PASSWORD);

            boolean valid_seller = username.equals(SELLER_USERNAME)
                    && password.equals(SELLER_PASSWORD);

            if (valid_customer && _customer_account_btn.isSelected()) {
                Scene s = SceneGenerator.getScene(SceneFactory.SceneType.CUSTOMER_HOME);
                primaryStage.setScene(s);
            } else if (valid_seller && _seller_account_btn.isSelected()) {
                Scene s = SceneGenerator.getScene(SceneFactory.SceneType.SELLER_HOME);
                primaryStage.setScene(s);
            } else {
                _alert.setContentText("Please enter your correct Username and Password");
                _alert.show();
            }
        });

        hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(_sign_in_btn);

        this.add(_scene_title, 0, 0, 2, 1);
        this.add(_username_label, 0, 1);
        this.add(_username_text_field, 1, 1);
        this.add(_password_label, 0, 2);
        this.add(_password_text_field, 1, 2);
        this.add(hbBtn, 1, 5);
        this.add(_customer_account_btn, 1, 3);
        this.add(_seller_account_btn, 1, 4);
    }
}
