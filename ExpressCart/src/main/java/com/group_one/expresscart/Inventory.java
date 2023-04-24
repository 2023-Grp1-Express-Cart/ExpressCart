/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;


import static com.group_one.expresscart.ExpressCart.InventoryMgr;
import static com.group_one.expresscart.ExpressCart.SceneGenerator;
import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Group 1
 */
public final class Inventory extends VBox {

    private final Button _add_new_item_btn;
    private final Button _remove_Item_btn;
    private final Button _edit_Item_btn;
    private final Button _seller_home_btn;

    private final ArrayList<Item> _store_items;
    private final ObservableList<Item> _store_items_observable_list;
    private final ListView<Item> _store_items_list_view;

    /**
     * The Constructor for Layout for the Inventory Page
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     */
    public Inventory(Stage primaryStage) {

        _store_items = InventoryMgr.getStoreItemsList();
        _store_items_observable_list = FXCollections.observableArrayList(_store_items);
        _store_items_list_view = new ListView<>(_store_items_observable_list);

        _remove_Item_btn = new Button("Remove");
        _remove_Item_btn.setOnAction(e -> {
            int index = _store_items_list_view.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                _store_items.remove(index);
                _store_items_list_view.setItems(FXCollections.observableArrayList(_store_items));
            }

            InventoryMgr.setStoreItemsList(_store_items);
        });

        _edit_Item_btn = new Button("Edit");
        _edit_Item_btn.setOnAction(e -> {
            int index = _store_items_list_view.getSelectionModel().getSelectedIndex();
            Item i = _store_items.get(index);

            Label item_id_label = new Label("Id");
            Label item_name_label = new Label("Name");
            Label item_invoice_price_label = new Label("Invoice Price");
            Label item_unit_price_label = new Label("Sell Price");
            Label item_quantity_label = new Label("Quantity");

            TextField item_id_text_field = new TextField(String.valueOf(i.getItemId()));
            TextField item_name_text_field = new TextField(i.getItemName());
            TextField item_invoice_price_text_field = new TextField(String.valueOf(i.getItemInvoicePrice()));
            TextField item_unit_price_text_field = new TextField(String.valueOf(i.getItemSellPrice()));
            TextField item_quantity_text_field = new TextField("1");

            GridPane grid = new GridPane();
            grid.add(item_id_label, 1, 1);
            grid.add(item_id_text_field, 2, 1);
            grid.add(item_name_label, 1, 2);
            grid.add(item_name_text_field, 2, 2);
            grid.add(item_invoice_price_label, 1, 3);
            grid.add(item_invoice_price_text_field, 2, 3);
            grid.add(item_unit_price_label, 1, 4);
            grid.add(item_unit_price_text_field, 2, 4);
            grid.add(item_quantity_label, 1, 5);
            grid.add(item_quantity_text_field, 2, 5);

            TextInputDialog editItemDialog = new TextInputDialog("Edit Item");
            editItemDialog.setResizable(true);
            editItemDialog.setTitle("Edit Item");
            editItemDialog.getDialogPane().setContent(grid);
            Optional<String> result = editItemDialog.showAndWait();
            if (result.isPresent()) {

                String name = item_name_text_field.getText();
                double invoice_price = Double.parseDouble(item_invoice_price_text_field.getText());
                double unit_price = Double.parseDouble(item_unit_price_text_field.getText());
                int quantity = Integer.parseInt(item_quantity_text_field.getText());
                if (quantity - 1 > 1) {
                    int local = 0;
                    while (local < (quantity - 1)) {
                        _store_items.add(new Item(name, invoice_price, unit_price));
                        local++;
                    }
                } else {

                    _store_items.get(index).setItemId(Integer.parseInt(item_id_text_field.getText()));
                    _store_items.get(index).setItemName(item_name_text_field.getText());
                    _store_items.get(index).setItemSellPrice(Double.parseDouble(item_unit_price_text_field.getText()));
                    _store_items.get(index).setItemInvoicePrice(Double.parseDouble(item_invoice_price_text_field.getText()));
                }

                _store_items_list_view.setItems(FXCollections.observableArrayList(_store_items));
            }

            InventoryMgr.setStoreItemsList(_store_items);
        });

        _add_new_item_btn = new Button("New");
        _add_new_item_btn.setOnAction(e -> {

            Label item_name_label = new Label("Name");
            Label item_invoice_price_label = new Label("Invoice Price");
            Label item_unit_price_label = new Label("Sell Price");
            Label item_quantity_label = new Label("Quantity");

            TextField item_name_text_field = new TextField();
            TextField item_invoice_price_text_field = new TextField();
            TextField item_unit_price_text_field = new TextField();
            TextField item_quantity_text_field = new TextField();

            GridPane grid = new GridPane();
            grid.add(item_name_label, 1, 1);
            grid.add(item_name_text_field, 2, 1);
            grid.add(item_invoice_price_label, 1, 2);
            grid.add(item_invoice_price_text_field, 2, 2);
            grid.add(item_unit_price_label, 1, 3);
            grid.add(item_unit_price_text_field, 2, 3);
            grid.add(item_quantity_label, 1, 4);
            grid.add(item_quantity_text_field, 2, 4);

            TextInputDialog newItemDialog = new TextInputDialog("Add New Item");
            newItemDialog.setResizable(true);
            newItemDialog.setTitle("New Item");
            newItemDialog.getDialogPane().setContent(grid);
            Optional<String> result = newItemDialog.showAndWait();
            if (result.isPresent()) {
                String name = item_name_text_field.getText();
                double invoice_price = Double.parseDouble(item_invoice_price_text_field.getText());
                double unit_price = Double.parseDouble(item_unit_price_text_field.getText());
                int quantity = Integer.parseInt(item_quantity_text_field.getText());

                if (Item.isValidItemDetail(name, invoice_price, unit_price)) {

                    if (quantity > 0) {
                        int local = 0;
                        while (local < quantity) {
                            _store_items.add(new Item(name, invoice_price, unit_price));
                            local++;
                        }
                    } else {
                        _store_items.add(new Item(name, invoice_price, unit_price));
                    }

                    _store_items_list_view.setItems(FXCollections.observableArrayList(_store_items));
                }
            }

            InventoryMgr.setStoreItemsList(_store_items);
        });

        _seller_home_btn = new Button("Home");
        _seller_home_btn.setOnAction(e -> {
            Scene seller_home_scene = SceneGenerator.GetScene(SceneFactory.SceneType.SELLER_HOME);
            assert seller_home_scene != null : "Precondition : Check that value is not null Object";
            primaryStage.setScene(seller_home_scene);
        });

        HBox _store_action_buttons = new HBox(_seller_home_btn, _edit_Item_btn,
                _add_new_item_btn, _remove_Item_btn);
        _store_action_buttons.setSpacing(10);
        _store_action_buttons.setAlignment(Pos.CENTER);
        this.getChildren().add(_store_action_buttons);
        this.getChildren().add(_store_items_list_view);
    }
}
