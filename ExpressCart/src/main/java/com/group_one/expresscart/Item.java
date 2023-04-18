/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;

/**
 *
 * @author norvinholness
 */
public class Item {

    private int _id;
    private String _item_name;
    private double _sell_price;
    private double _invoice_price;

    public Item(String name, double invoice_price, double sell_price) {
        this._id = GetRandomId();
        this._item_name = name;
        this._invoice_price = invoice_price;
        this._sell_price = sell_price;
    }

    public String getItemName() {
        return _item_name;
    }

    public double getItemSellPrice() {
        return _sell_price;
    }

    public int getItemId() {
        return _id;
    }

    public double getItemInvoicePrice() {
        return _invoice_price;
    }

    public void setItemName(String s) {
        _item_name = s;
    }

    public void setItemUnitPrice(double d) {
        _sell_price = d;
    }

    public void setItemId(int i) {
        _id = i;
    }

    public void setItemInvoicePrice(double d) {
        _invoice_price = d;
    }

    @Override
    public String toString() {
        return String.valueOf(this._id) + "\t" + this._item_name + "\t ($" + this._sell_price + ")";
    }

    /**
     * 
     * @param item
     * @return 
     */
    public static String toCsvString(Item item) {
        String s = "";
        s += item.getItemName();
        s += ",";
        s += String.valueOf(item.getItemInvoicePrice());
        s += ",";
        s += String.valueOf(item.getItemSellPrice());
        s += "\n";
        return s;
    }
    
    /**
     * 
     * @param name
     * @param invoice_price
     * @param sell_price
     * @return 
     */
    public static boolean isValidItemDetail(String name, double invoice_price, double sell_price) {
        boolean invalid_name = name.equals("");
        boolean invalid_sell_price = sell_price < 0.0;
        boolean invalid_invoice_price = invoice_price < 0.0;
        boolean invalid_pricing = invoice_price > sell_price;

        return !(invalid_name || invalid_sell_price || invalid_invoice_price
                || invalid_pricing);

    }
    
    // TODO: May need to Sort Items by ID,
    // then check if Item ID exist before generating ID for Element.
    // Maybe Sort based on last item in (Sorted) Array List.
    private static int id = 0; 
    /**
     * 
     * @return 
     */
    public static int GetRandomId() {
        id++;
        return id;
    }
}
