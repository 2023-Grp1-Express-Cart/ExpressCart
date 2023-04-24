/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;

import static com.group_one.expresscart.ExpressCart.InventoryMgr;
import static com.group_one.expresscart.ExpressCart.SceneGenerator;
import static com.group_one.expresscart.Revenue.increment;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Group 1
 */
public final class Checkout extends VBox {

    private final ArrayList<Item> _checkout_item_list;
    private final ListView<Item> _checkout_item_list_view;
    private final ObservableList<Item> _checkout_items_observable_list;

    private final Label _first_name_label;
    private final TextField _first_name_text_field;
    private final Label _last_name_label;
    private final TextField _last_name_text_field;
    private final Label _street_address_label;
    private final TextField _street_address_text_field;
    private final Label _city_label;
    private final TextField _city_text_field;
    private final Label _state_label;
    private final TextField _state_text_field;
    private final Label _zip_code_label;
    private final TextField _zip_code_text_field;

    private final Label _credit_card_info_label;
    private final TextField _credit_card_text_field;

    private final Label Total;
    private final Label Tax;
    private final Label SubTotal;

    private final double TAX_RATE = 0.07;
    private double _subTotal = 0.0;
    private double _tax = 0.0;
    private double _total = 0.0;

    private final Button _complete_order_btn;
    private final Button _return_to_cart;
    private final GridPane _grid_layout;

    private Alert _alert;

    /**
     * The Constructor for Layout for the Checkout Page
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     */
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
            _alert = new Alert(AlertType.CONFIRMATION);
            _alert.setTitle("Confirm Transaction");
            _alert.setHeaderText(null);
            String receipt = generateReceipt();
            _alert.setContentText(receipt);
            Optional<ButtonType> result = _alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                InventoryMgr.setPurchasedItemsList(_checkout_item_list);
                InventoryMgr.clearShoppingCart();
                InventoryMgr.removeItemsFromInventory();
                updateLabels();

                Scene customer_home_scene = SceneGenerator.getScene(SceneFactory.SceneType.CUSTOMER_HOME);
                assert customer_home_scene != null : "Precondition : Check that value is not null Object";
                primaryStage.setScene(customer_home_scene);
            }
        });

        _return_to_cart = new Button("Return To Cart");
        _return_to_cart.setOnAction(e -> {
            Scene customer_home_scene = SceneGenerator.getScene(SceneFactory.SceneType.CUSTOMER_HOME);
            primaryStage.setScene(customer_home_scene);
        });

        HBox horizontal_buttons = new HBox(10);
        horizontal_buttons.setAlignment(Pos.CENTER);
        horizontal_buttons.getChildren().add(_return_to_cart);
        horizontal_buttons.getChildren().add(_complete_order_btn);
        _grid_layout.add(horizontal_buttons, 1, 7, 2, 1);

        this.getChildren().add(new Label("Review Order"));
        this.getChildren().add(_checkout_item_list_view);
        this.getChildren().add(SubTotal);
        this.getChildren().add(Tax);
        this.getChildren().add(Total);
        this.getChildren().add(_grid_layout);
    }

    /**
     * Method to update the subtotal based on items in checkout list.
     */
    private void updateLabels() {

        for (Item i : _checkout_item_list) {
            _subTotal += i.getItemSellPrice();
        }

        _tax = _subTotal * TAX_RATE;
        _total = _subTotal + _tax;
        SubTotal.setText(String.format("Sub Total: $%.2f", _subTotal));
        Tax.setText(String.format("Tax: $%.2f", _tax));
        Total.setText(String.format("Grand Total: $%.2f", _total));
    }

    /**
     * Method to generate receipt based on items in the customer checkout list.
     *
     * @return String representing itemized receipt.
     */
    private String generateReceipt() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        String receipt = "";

        receipt += "Time: " + strDate;
        receipt += "\n";
        receipt += "==============\n";

        HashMap<String, Integer> ItemCountMap;
        HashMap<String, Double> ItemPriceMap;
        ItemCountMap = new HashMap<>();
        ItemPriceMap = new HashMap<>();

        // Populate Profit and Quantity Maps
        for (Item item : _checkout_item_list) {
            increment(ItemCountMap, item.getItemName());
            ItemPriceMap.putIfAbsent(item.getItemName(), item.getItemSellPrice());
        }

        for (Map.Entry<String, Integer> set
                : ItemCountMap.entrySet()) {
            String name = set.getKey();
            Double price = set.getValue() * ItemPriceMap.get(name);
            int count = set.getValue();
            String s = "(" + String.valueOf(count) + ") " + name + " - ($" + String.valueOf(price) + ")\n";
            receipt += s;
        }

        String tax = String.format("%.0f", _tax);
        String subTotal = String.format("%.0f", _subTotal);
        String Total = String.format("%.0f", _total);

        receipt += "==============\n";
        receipt += "Sub Total = $(";
        receipt += subTotal;
        receipt += ")\n";
        receipt += "Tax = $(";
        receipt += tax;
        receipt += ")\n";
        receipt += "Grand Total = $(";
        receipt += Total;
        receipt += ")\n";

        return receipt;
    }
}
