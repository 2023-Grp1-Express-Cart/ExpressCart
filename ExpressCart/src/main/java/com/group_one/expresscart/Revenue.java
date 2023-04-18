/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;

import static com.group_one.expresscart.ExpressCart.InventoryMgr;
import static com.group_one.expresscart.ExpressCart.SceneGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author norvinholness
 */
public class Revenue {

    // Profit = Revenues - Costs,
    // Revenues = Sum of sell price for all sold items
    // Costs = Sum of invoice price for all items brought in the inventory (bought)
    // profit = revenues - costs;
    Label profit_label;
    double profit = 0.0;
    ArrayList<Item> _purchased_items;
    BarChart<String, Float> bar;
    VBox _layout;
    Button _home_btn;

    HashMap<String, Integer> ItemQuantityMap;
    HashMap<String, Double> ItemPriceMap;


    public Revenue(Stage primaryStage) {
        _purchased_items = InventoryMgr.getPurchasedItemsList();
        
        ItemQuantityMap = new HashMap<>();
        ItemPriceMap = new HashMap<>();
        
        for (Item i : _purchased_items) {
            increment(ItemQuantityMap, i.getItemName());
            double d = i.getItemInvoicePrice() - i.getItemSellPrice();
            ItemPriceMap.putIfAbsent(i.getItemName(), d); 
        }

        //Configuring category and NumberAxis   
        CategoryAxis xaxis = new CategoryAxis();
        NumberAxis yaxis = new NumberAxis(0.0, 30, 5.0);
        xaxis.setLabel("Item");
        yaxis.setLabel("Quantity Sold");

        //Configuring BarChart   
        bar = new BarChart(xaxis, yaxis);
        bar.setTitle("Revenue from Sales");

        //Configuring Series for XY chart   
        XYChart.Series<String, Float> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> set
                : ItemQuantityMap.entrySet()) {
            String s = set.getKey();
            Double p = set.getValue() * ItemPriceMap.get(s);
            profit+= p;
            int q =  set.getValue();
            series.getData().add(new XYChart.Data(s, q));
        }

        _home_btn = new Button("Home");
        _home_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Scene s = SceneGenerator.GetScene(SceneFactory.SceneType.SELLER_HOME);
                primaryStage.setScene(s);
            }
        });

        // TODO Legend
        _layout = new VBox();

        //Adding series to the barchart   
        bar.getData().add(series);
        
        profit_label = new Label();
        profit_label.setText("Profits From Sales: $" + String.valueOf(profit));

        _layout.getChildren().add(_home_btn);
        _layout.getChildren().add(profit_label);
        _layout.getChildren().add(bar);
        _layout.setAlignment(Pos.CENTER);
        _layout.setPadding(new Insets(15));
    }

    public VBox getLayout() {
        return this._layout;
    }

    public static <K> void increment(Map<K, Integer> map, K key) {
        map.putIfAbsent(key, 0);
        map.put(key, map.get(key) + 1);
    }
}
