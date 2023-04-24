/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;

import static com.group_one.expresscart.ExpressCart.InventoryMgr;
import static com.group_one.expresscart.ExpressCart.SceneGenerator;
import java.util.HashMap;
import java.util.Map;
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
 * @author Group 1
 */
public class Revenue extends VBox {

    private final BarChart<String, Float> _bar_chart;
    private final Button _home_btn;
    private final Label profit_label;
    private double profit = 0.0;

    HashMap<String, Integer> ItemQuantityMap;
    HashMap<String, Double> ItemPriceMap;

    /**
     * The Constructor for Layout for the Revenue Page
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     */
    public Revenue(Stage primaryStage) {

        ItemQuantityMap = new HashMap<>();
        ItemPriceMap = new HashMap<>();

        // Populate Profit and Quantity Maps
        for (Item item : InventoryMgr.getPurchasedItemsList()) {
            increment(ItemQuantityMap, item.getItemName());
            double d = item.getItemInvoicePrice() - item.getItemSellPrice();
            ItemPriceMap.putIfAbsent(item.getItemName(), d);
        }

        // Configuring category and NumberAxis   
        CategoryAxis xaxis = new CategoryAxis();
        NumberAxis yaxis = new NumberAxis(0.0, 10, 1.0);
        xaxis.setLabel("Item");
        yaxis.setLabel("Quantity Sold");

        // Configuring BarChart   
        _bar_chart = new BarChart(xaxis, yaxis);
        _bar_chart.setTitle("Revenue from Sales");

        // Configuring Series for XY chart   
        XYChart.Series<String, Float> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> set
                : ItemQuantityMap.entrySet()) {
            String s = set.getKey();
            Double p = set.getValue() * ItemPriceMap.get(s);
            profit += p;
            int q = set.getValue();
            series.getData().add(new XYChart.Data(s, q));
        }

        _home_btn = new Button("Home");
        _home_btn.setOnAction(e -> {
            Scene seller_home_scene = SceneGenerator.getScene(SceneFactory.SceneType.SELLER_HOME);
            assert seller_home_scene != null : "Precondition : Check that value is not null Object";
            primaryStage.setScene(seller_home_scene);
        });

        // Adding series to the barchart   
        _bar_chart.getData().add(series);

        profit_label = new Label();
        // FIX ME: Temp Fix to Profit calcualtion
        profit = Math.abs(profit);
        profit_label.setText("Profits From Sales: $" + String.valueOf(profit));

        this.getChildren().add(_home_btn);
        this.getChildren().add(profit_label);
        this.getChildren().add(_bar_chart);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(15));
    }

    /**
     * Method to add Key to a map if the Key doesn't exist, this method will
     * also increment the count of the Key if it exist.
     *
     * @param <K> The type of object that the key is.
     * @param map The Map Objects to increment the value.
     * @param key The Key to check if it exists within the map. If it exist,
     * increment the count. If it is absent from the map, add the key with initial
     * count of 0.
     */
    public static <K> void increment(Map<K, Integer> map, K key) {
        map.putIfAbsent(key, 0);
        map.put(key, map.get(key) + 1);
    }
}
