/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Inventory Manager Class used to handle all transactions of Items from the 
 * Seller and the Customer Account.
 * @author norvinholness
 */
public final class InventoryManager {
    
    private static InventoryManager _inventory_manager_instance = null;
    public static ArrayList<Item> _purchased_items;
    public static ArrayList<Item> _wishlist_items;
    public static ArrayList<Item> _store_items;
    public static ArrayList<Item> _shopping_cart_items;
    
    final private String PURCHASED_ITEMS     = "PurchasedItems.csv";
    final private String WISHLIST_ITEMS      = "WishListItems.csv";
    final private String STORE_ITEMS         = "StoreItems.csv";
    final private String SHOPPING_CART_ITEMS = "ShoppingCartItems.csv";
    final private Map<String, ArrayList<Item>> ItemMap;
    
    /**
     * TODO
     */
    private InventoryManager() {
        _purchased_items = new ArrayList<>();
        _wishlist_items = new ArrayList<>();
        _store_items = new ArrayList<>();
        _shopping_cart_items = new ArrayList<>();
        
        ItemMap = new HashMap<>();
        ItemMap.put(PURCHASED_ITEMS, _purchased_items);
        ItemMap.put(WISHLIST_ITEMS, _wishlist_items);
        ItemMap.put(STORE_ITEMS, _store_items);
        ItemMap.put(SHOPPING_CART_ITEMS, _shopping_cart_items);
        
        for (Map.Entry<String, ArrayList<Item>> e : ItemMap.entrySet()){
            load(e.getKey());
        }
    }

    /**
     * 
     * @return 
     */
    public static synchronized InventoryManager getInstance() {
        if (_inventory_manager_instance == null) {
            _inventory_manager_instance = new InventoryManager();
        }

        return _inventory_manager_instance;
    }
    
    /**
     * 
     * @param fileName
     */
    private void load(String fileName) {

        try {
            File file = new File(fileName);
            Scanner inFile = new Scanner(file);

            while (inFile.hasNext()) {
                String input = inFile.nextLine();
                String fieldsFromLine[] = input.split(",");

                String item_name = fieldsFromLine[0].trim();
                double item_invoice_price = Double.parseDouble(fieldsFromLine[1].trim());
                double item_sell_price = Double.parseDouble(fieldsFromLine[2].trim());
                

                Item item = new Item(item_name, item_invoice_price,item_sell_price);
                ItemMap.get(fileName).add(item);
            }

            inFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param fileName
     */
    private void save(String fileName) {

        try {
            FileWriter myWriter = new FileWriter(fileName);

            for (Item i : ItemMap.get(fileName)) {
                myWriter.write(Item.toCsvString(i));
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Getter for Purchased Items
     * @return 
     */
    public ArrayList<Item> getPurchasedItemsList() {
        return _purchased_items;
    }
    
    /**
     * 
     * @param items 
     */
    public void setPurchasedItemsList(ArrayList<Item> items) {
        
        for (Item i : items){
            _purchased_items.add(i);
        }
        
        
        save(PURCHASED_ITEMS);
    }
    
    /**
     * Getter for Items in Customer Shopping Cart
     * @return 
     */
    public ArrayList<Item> getShoppingCartItemsList() {
        return _shopping_cart_items;
    }
    
    /**
     * 
     * @param items 
     */
    public void setShoppingCartItemsList(ArrayList<Item> items) {
        _shopping_cart_items = items;
        save(SHOPPING_CART_ITEMS);
    }
    
    public void clearShoppingCart(){
        _shopping_cart_items.clear();
        save(SHOPPING_CART_ITEMS);
    }

    /**
     * Getter for Items the Seller has put in the Store
     * @return 
     */
    public ArrayList<Item> getStoreItemsList() {
        return _store_items;
    }
    
    /**
     * 
     * @param items 
     */
    public void setStoreItemsList (ArrayList<Item> items) {
        _store_items = items;
        save(STORE_ITEMS);
    }
    
    /**
     * Getter for Items currently in Customer Wish List
     * @return 
     */
    public ArrayList<Item> getWishListItemsList() {
        return _wishlist_items;
    }
    
    /**
     * 
     * @param items 
     */
    public void setWishListItemsList(ArrayList<Item> items) {
        _wishlist_items = items;
        save(WISHLIST_ITEMS);
    }
    
    public void removeItemsFromInventory(ArrayList<Item> items){
        // TODO Remove Items from Inventory After they have been purchased
    }
}