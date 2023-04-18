/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;

import static com.group_one.expresscart.ExpressCart.InventoryMgr;
import static com.group_one.expresscart.ExpressCart.SceneGenerator;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author norvinholness
 */
public final class Checkout {

    ArrayList<Item> _checkout_item_list;
    ListView<Item> _checkout_item_list_view;
    ObservableList<Item> _checkout_items_observable_list;

    final private Label _first_name_label;
    final private TextField _first_name_text_field;
    final private Label _last_name_label;
    final private TextField _last_name_text_field;
    final private Label _street_address_label;
    final private TextField _street_address_text_field;
    final private Label _city_label;
    final private TextField _city_text_field;
    final private Label _state_label;
    final private TextField _state_text_field;
    final private Label _zip_code_label;
    final private TextField _zip_code_text_field;

    final private Label _credit_card_info_label;
    final private TextField _credit_card_text_field;

    final private Label Total;
    final private Label Tax;
    final private Label SubTotal;
    private double subTotal = 0.0;
    private double tax = 0.0;
    private double total = 0.0;
    final double TAX_RATE = 0.07;

    final private Button _complete_order_btn;
    final private Button _return_to_cart;

    final private GridPane _grid_layout;
    final private VBox _vertical_box_layout;

    Checkout(Stage primaryStage) {

        _checkout_item_list = InventoryMgr.getShoppingCartItemsList();
        _checkout_items_observable_list = FXCollections.observableArrayList(_checkout_item_list);
        _checkout_item_list_view = new ListView<>(_checkout_items_observable_list);

        Total = new Label("Total");
        Tax = new Label("Tax");
        SubTotal = new Label("SubTotal");

        updateLabels();

        _grid_layout = new GridPane();
        _grid_layout.setAlignment(Pos.CENTER);
        _grid_layout.setHgap(10);
        _grid_layout.setVgap(10);

        _first_name_label = new Label("First Name");
        _last_name_label = new Label("Last Name");
        _street_address_label = new Label("Street Address");
        _city_label = new Label("City");
        _state_label = new Label("State");
        _zip_code_label = new Label("Zip");
        _credit_card_info_label = new Label("Credit Card #");

        _first_name_text_field = new TextField("John");
        _last_name_text_field = new TextField("Doe");
        _street_address_text_field = new TextField("777 Glades Road");
        _city_text_field = new TextField("Boca Raton");
        _state_text_field = new TextField("FL");
        _zip_code_text_field = new TextField("33431");
        _credit_card_text_field = new TextField("0123-4567-8910-1112");

        _grid_layout.add(_first_name_label, 1, 0);
        _grid_layout.add(_first_name_text_field, 2, 0);

        _grid_layout.add(_last_name_label, 1, 1);
        _grid_layout.add(_last_name_text_field, 2, 1);

        _grid_layout.add(_credit_card_info_label, 1, 2);
        _grid_layout.add(_credit_card_text_field, 2, 2);

        _grid_layout.add(_street_address_label, 1, 3);
        _grid_layout.add(_street_address_text_field, 2, 3);

        _grid_layout.add(_city_label, 1, 4);
        _grid_layout.add(_city_text_field, 2, 4);

        _grid_layout.add(_zip_code_label, 1, 5);
        _grid_layout.add(_zip_code_text_field, 2, 5);

        _grid_layout.add(_state_label, 1, 6);
        _grid_layout.add(_state_text_field, 2, 6);

        _complete_order_btn = new Button("Complete Order");
        _complete_order_btn.setOnAction(e -> {
            InventoryMgr.setPurchasedItemsList(_checkout_item_list);
            InventoryMgr.clearShoppingCart();
            // TODO: Remove purchsed Items from Inventory.
            // clear the list and update the view
            Scene s = SceneGenerator.GetScene(SceneFactory.SceneType.CUSTOMER_HOME);
            primaryStage.setScene(s);
        });

        _return_to_cart = new Button("Return To Cart");
        _return_to_cart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Scene s = SceneGenerator.GetScene(SceneFactory.SceneType.CUSTOMER_HOME);
                primaryStage.setScene(s);
            }
        });

        HBox horizontal_buttons = new HBox(10);
        horizontal_buttons.setAlignment(Pos.CENTER);
        horizontal_buttons.getChildren().add(_return_to_cart);
        horizontal_buttons.getChildren().add(_complete_order_btn);
        _grid_layout.add(horizontal_buttons, 1, 7, 2, 1);
        _vertical_box_layout = new VBox();
        _vertical_box_layout.getChildren().add(new Label("Review Order"));
        _vertical_box_layout.getChildren().add(_checkout_item_list_view);
        _vertical_box_layout.getChildren().add(SubTotal);
        _vertical_box_layout.getChildren().add(Tax);
        _vertical_box_layout.getChildren().add(Total);
        _vertical_box_layout.getChildren().add(_grid_layout);
    }

    public VBox getLayout() {
        return this._vertical_box_layout;
    }

    private void updateLabels() {

        for (Item i : _checkout_item_list) {
            subTotal += i.getItemSellPrice();
        }

        tax = subTotal * TAX_RATE;
        total = subTotal + tax;
        SubTotal.setText(String.format("Sub Total: $%.2f", subTotal));
        Tax.setText(String.format("Tax: $%.2f", tax));
        Total.setText(String.format("Grand Total: $%.2f", total));
    }
}
