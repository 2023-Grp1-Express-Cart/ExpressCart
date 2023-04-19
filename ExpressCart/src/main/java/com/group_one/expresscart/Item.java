/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;

/**
 *
 * @author Group 1
 */
public final class Item {

    private int _id;
    private String _item_name;
    private double _sell_price;
    private double _invoice_price;

    /**
     * Constructor for a New Item
     * @param name The desired name of the Item
     * @param invoice_price The invoice price for the item
     * @param sell_price The sell price for the item
     */
    public Item(String name, double invoice_price, double sell_price) {
        this._id = InventoryManager.GenerateItemId();
        this._item_name = name;
        this._invoice_price = invoice_price;
        this._sell_price = sell_price;
    }

    /**
     * Getter for Item Name.
     * @return String value of Item name.
     */
    public String getItemName() {
        return _item_name;
    }

    /**
     * Getter for Item Sell Price.
     * @return Item Sell Price.
     */
    public double getItemSellPrice() {
        return _sell_price;
    }

    /**
     * Getter for Item ID.
     * @return Item Id.
     */
    public int getItemId() {
        return _id;
    }

    /**
     * Getter for Item Invoice Price.
     * @return Item Invoice Price.
     */
    public double getItemInvoicePrice() {
        return _invoice_price;
    }

    /**
     * Setter for Item Name.
     * @param s The desired name of the item.
     */
    public void setItemName(String s) {
        assert (s.length() > 0): "Precondition : Item Name length > 0";
        _item_name = s;
    }

    /**
     * Setter for Item Sell Price.
     * @param d The desired value for item sell price.
     */
    public void setItemSellPrice(double d) {
        assert (d > 0): "Precondition : Item Sell Price > 0";
        _sell_price = d;
    }

    /**
     * Setter for Item ID
     * @param i The desired value for Item ID.
     */
    public void setItemId(int i) {
        assert (i > 0): "Precondition : Item ID > 0";
        _id = i;
    }

    /**
     * Setter for Item Invoice Price.
     * @param d The desired value of Item invoice price.
     */
    public void setItemInvoicePrice(double d) {
        assert (d > 0): "Precondition : Item Invoice Price > 0";
        _invoice_price = d;
    }

    /**
     * Method to convert Item data to String representation.
     * @return String representing Item information.
     */
    @Override
    public String toString() {
        return String.valueOf(this._id) + "\t" + this._item_name + "\t ($" + this._sell_price + ")";
    }

    /**
     * Method to convert Item data to a CSV string.
     * @param item The item Object whose data will be converted to CSV String.
     * @return String in CSV format.
     */
    public static String toCsvString(Item item) {
        assert (item != null): "Precondition : Item not a null object";
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
     * Method to check the validity of the fields of a New Item.
     * @param name The name of the New item.
     * @param invoice_price The invoice price of the New item.
     * @param sell_price The sell price of the New item.
     * @return true if all conditions are satisfied, false otherwise.
     */
    public static boolean isValidItemDetail(String name, double invoice_price, double sell_price) {
        boolean invalid_name = name.equals("");
        boolean invalid_sell_price = sell_price < 0.0;
        boolean invalid_invoice_price = invoice_price < 0.0;
        boolean invalid_pricing = invoice_price > sell_price;

        return !(invalid_name || invalid_sell_price || invalid_invoice_price
                || invalid_pricing);
    }
}
