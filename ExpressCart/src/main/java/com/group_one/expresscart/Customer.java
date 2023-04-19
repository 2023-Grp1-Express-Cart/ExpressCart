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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Group 1
 */
public final class Customer extends VBox {

    private ArrayList<Item> _shopping_cart_list;
    private ArrayList<Item> _wish_list;
    private ArrayList<Item> _store_item_list;

    private ListView<Item> _item_view;
    private ListView<Item> _shopping_cart_view;
    private ListView<Item> _wish_list_view;

    private final Label labelForSubTotal;
    private final Label labelForTax;
    private final Label labelForTotal;

    private double subTotal = 0.0;
    private double tax = 0.0;
    private double total = 0.0;
    private final double TAX_RATE = 0.07;

    private final Button removeFromCartBtn;
    private final Button clearCartBtn;
    private final Button addToCartBtn;
    private final Button checkoutBtn;
    private final Button addToWishListBtn;
    private final Button removeFromWishListBtn;
    private final Button clearWishListBtn;
    private final Button moveToCartButton;
    private final Button customerlogoutBtn;

    private final ObservableList<Item> observableList;
    private final ObservableList<Item> WishListobservableList;
    private final ObservableList<Item> ShoppingCartobservableList;

    /**
     * TODO
     * @param primaryStage 
     */
    public Customer(Stage primaryStage) {
        _store_item_list = InventoryMgr.getStoreItemsList();
        _shopping_cart_list = InventoryMgr.getShoppingCartItemsList();
        _wish_list = InventoryMgr.getWishListItemsList();

        observableList = FXCollections.observableArrayList(_store_item_list);
        _item_view = new ListView<>(observableList);

        ShoppingCartobservableList = FXCollections.observableArrayList(_shopping_cart_list);
        _shopping_cart_view = new ListView<>(ShoppingCartobservableList);

        WishListobservableList = FXCollections.observableArrayList(_wish_list);
        _wish_list_view = new ListView<>(WishListobservableList);

        labelForSubTotal = new Label("Sub Total: ");
        labelForTax = new Label("Tax: ");
        labelForTotal = new Label("Grand Total: ");

        addToCartBtn = new Button("Add To Cart");
        addToCartBtn.setOnAction(e -> {
            _item_view.refresh();
            int index = _item_view.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                Item i = _store_item_list.get(index);
                if (!_shopping_cart_list.contains(i) && !_wish_list.contains(i)) {
                    _shopping_cart_list.add(_store_item_list.get(index));
                    _shopping_cart_view.setItems(FXCollections.observableArrayList(_shopping_cart_list));
                    subTotal += i.getItemSellPrice();
                    updateLabels();
                    InventoryMgr.setShoppingCartItemsList(_shopping_cart_list);
                }
            }
        });

        removeFromCartBtn = new Button("Remove From Cart");
        removeFromCartBtn.setOnAction(e -> {
            _shopping_cart_view.refresh();
            int index = _shopping_cart_view.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                subTotal -= _shopping_cart_list.get(index).getItemSellPrice();
                _shopping_cart_list.remove(index);
                _shopping_cart_view.setItems(FXCollections.observableArrayList(_shopping_cart_list));
                updateLabels();
                InventoryMgr.setShoppingCartItemsList(_shopping_cart_list);
            }
        });

        addToWishListBtn = new Button("Add To Wish List"); // Remove from Cart then add to WishList
        addToWishListBtn.setOnAction(e -> {
            int index = _item_view.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                Item i = _store_item_list.get(index);
                if (!_wish_list.contains(i) && !_shopping_cart_list.contains(i)) {
                    _wish_list.add(_store_item_list.get(index));
                    _wish_list_view.setItems(FXCollections.observableArrayList(_wish_list));
                    InventoryMgr.setWishListItemsList(_wish_list);
                }
            }
        });

        removeFromWishListBtn = new Button("Remove From Wish List");
        removeFromWishListBtn.setOnAction(e -> {
            int index = _wish_list_view.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                _wish_list.remove(index);
                _wish_list_view.setItems(FXCollections.observableArrayList(_wish_list));
                InventoryMgr.setWishListItemsList(_wish_list);
            }
        });

        moveToCartButton = new Button("Move to Cart");
        moveToCartButton.setOnAction(e -> {
            int index = _wish_list_view.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                Item i = _wish_list.get(index);
                _shopping_cart_list.add(i);
                _wish_list.remove(i);
                subTotal += i.getItemSellPrice();
                updateLabels();
                _shopping_cart_view.setItems(FXCollections.observableArrayList(_shopping_cart_list));
                _wish_list_view.setItems(FXCollections.observableArrayList(_wish_list));
                InventoryMgr.setWishListItemsList(_wish_list);
                InventoryMgr.setShoppingCartItemsList(_shopping_cart_list);
            }
        });

        clearWishListBtn = new Button("Clear Wishlist");
        clearWishListBtn.setOnAction(e -> {
            _wish_list.clear();
            _wish_list_view.setItems(FXCollections.observableArrayList(_wish_list));
            InventoryMgr.setWishListItemsList(_wish_list);

        });

        clearCartBtn = new Button("Clear Cart");
        clearCartBtn.setOnAction(e -> {
            subTotal = 0;
            tax = 0;
            total = 0;
            updateLabels();
            _shopping_cart_list.clear();
            _shopping_cart_view.setItems(FXCollections.observableArrayList(_shopping_cart_list));
            InventoryMgr.setShoppingCartItemsList(_shopping_cart_list);
        });

        checkoutBtn = new Button("Checkout");
        checkoutBtn.setOnAction(e -> {
            updateLabels();
            InventoryMgr.setShoppingCartItemsList(_shopping_cart_list);
            Scene s = SceneGenerator.GetScene(SceneFactory.SceneType.CHECKOUT);
            primaryStage.setScene(s);
        });

        customerlogoutBtn = new Button("Log Out");
        customerlogoutBtn.setOnAction(e -> {
            Scene s = SceneGenerator.GetScene(SceneFactory.SceneType.APP_HOME);
            primaryStage.setScene(s);

        });

        HBox hb1 = new HBox(addToCartBtn, addToWishListBtn);
        hb1.setSpacing(10);
        hb1.setAlignment(Pos.CENTER);

        HBox hb2 = new HBox(removeFromCartBtn, clearCartBtn, checkoutBtn, customerlogoutBtn);
        hb2.setSpacing(10);
        hb2.setAlignment(Pos.CENTER);

        HBox hb3 = new HBox(moveToCartButton, removeFromWishListBtn, clearWishListBtn);
        hb3.setSpacing(10);
        hb3.setAlignment(Pos.CENTER);

        Label available_items_label = new Label("Available Items");
        Label shopping_cart_label = new Label("Cart");
        Label wish_list_label = new Label("Wish List");
        
        this.getChildren().add(available_items_label);
        this.getChildren().add(_item_view);
        this.getChildren().add(hb1);
        this.getChildren().add(wish_list_label);
        this.getChildren().add(_wish_list_view);
        this.getChildren().add(hb3);
        this.getChildren().add(shopping_cart_label);
        this.getChildren().add(_shopping_cart_view);
        this.getChildren().add(hb2);
        this.getChildren().add(labelForSubTotal);
        this.getChildren().add(labelForTax);
        this.getChildren().add(labelForTotal);
    }

    /**
     * TODO
     */
    private void updateLabels() {
        tax = subTotal * TAX_RATE;
        total = subTotal + tax;
        labelForSubTotal.setText(String.format("Sub Total: $%.2f", subTotal));
        labelForTax.setText(String.format("Tax: $%.2f", tax));
        labelForTotal.setText(String.format("Grand Total: $%.2f", total));
    }
}
