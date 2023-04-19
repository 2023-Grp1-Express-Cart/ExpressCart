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
 * Singleton class that handles the transaction of items
 * @author Group 1
 */
public final class InventoryManager {
    
    private static InventoryManager _inventory_manager_instance = null;
    public static int Item_ID = 0;
    public static ArrayList<Item> _purchased_items;
    public static ArrayList<Item> _wishlist_items;
    public static ArrayList<Item> _store_items;
    public static ArrayList<Item> _shopping_cart_items;
    
    final private String PURCHASED_ITEMS     = "Items/PurchasedItems.csv";
    final private String WISHLIST_ITEMS      = "Items/WishListItems.csv";
    final private String STORE_ITEMS         = "Items/StoreItems.csv";
    final private String SHOPPING_CART_ITEMS = "Items/ShoppingCartItems.csv";
    final private Map<String, ArrayList<Item>> ItemMap;
    
    /**
     * The Constructor for a New Inventory Manager Object
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
     * Method to Initialize the Inventory Manager Object if it is Null.
     * @return The Singleton instance of the Inventory Manager Object.
     * @postcondition Inventory Manager object is not Null
     */
    public static synchronized InventoryManager getInstance() {
        if (_inventory_manager_instance == null) {
            _inventory_manager_instance = new InventoryManager();
        }
        
        assert (_inventory_manager_instance != null) : "Postcondition for Valid return of New Object";
        return _inventory_manager_instance;
    }
    
    /**
     * Method to load CSV file
     * @param fileName The name of the file
     * @throws FileNotFoundException, if filename cannot be found.
     * @precondition fileName length must be greater than 0.
     */
    private void load(String fileName) {
        
        assert (fileName.length() > 0): "Precondition : filename length > 0";

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
     * Method to save data to a CSV file.
     * @param fileName The name of the file.
     * @throws IOException, if the named file exists but is a directory rather
     *         than a regular file, does not exist but cannot be
     *         created, or cannot be opened for any other reason
     * @precondition fileName length must be greater than 0.
     */
    private void save(String fileName) {

        assert (fileName.length() > 0): "Precondition : filename length > 0";
        
        try {
            FileWriter myWriter = new FileWriter(fileName);
            for (Item i : ItemMap.get(fileName)) {
                myWriter.write(Item.toCsvString(i));
            }
            myWriter.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Getter for Purchased Items List.
     * @return List with purchased Items.
     */
    public ArrayList<Item> getPurchasedItemsList() {
        return _purchased_items;
    }
    
    /**
     * Setter for Purchased Items List. Also updates CSV file.
     * @param items List of Items to add append to Purchased Items List.
     */
    public void setPurchasedItemsList(ArrayList<Item> items) {
        for (Item i : items){
            _purchased_items.add(i);
        }
        save(PURCHASED_ITEMS);
    }
    
    /**
     * Getter for Items in Customer Shopping Cart
     * @return List of Items in Customer Shopping Cart List.
     */
    public ArrayList<Item> getShoppingCartItemsList() {
        return _shopping_cart_items;
    }
    
    /**
     * Setter for Shopping Items List. Also updates the CSV file.
     * @param items Items to add to Customer Shopping List.
     */
    public void setShoppingCartItemsList(ArrayList<Item> items) {
        _shopping_cart_items = items;
        save(SHOPPING_CART_ITEMS);
    }
    
    /**
     * Method to clear Customer Shopping Cart Items. Also updates the CSV file.
     */
    public void clearShoppingCart(){
        _shopping_cart_items.clear();
        save(SHOPPING_CART_ITEMS);
    }

    /**
     * Getter for Items the Seller has put in the Store.
     * @return Items listed in Store.
     */
    public ArrayList<Item> getStoreItemsList() {
        return _store_items;
    }
    
    /**
     * Setter for New Store Items. Also updates the CSV file.
     * @param items Item List containing items for the Store.
     */
    public void setStoreItemsList (ArrayList<Item> items) {
        _store_items = items;
        save(STORE_ITEMS);
    }
    
    /**
     * Getter for Items currently in Customer Wish List
     * @return WishList Items.
     */
    public ArrayList<Item> getWishListItemsList() {
        return _wishlist_items;
    }
    
    /**
     * 
     * @param items 
     */
    public void setWishListItemsList(ArrayList<Item> items) {
        assert (items != null ): "Precondition : Item Object not null";
        _wishlist_items = items;
        save(WISHLIST_ITEMS);
    }
    
    /**
     * Method to remove purchased items from seller inventory
     */
    public void removeItemsFromInventory(){
        for (Item item : _purchased_items){
            if (_store_items.contains(item)){
                _store_items.remove(item);
            }
        }
    }
    
    /**
     * 
     * @return 
     */
    public static int GenerateItemId(){
        InventoryManager.Item_ID++;
        return InventoryManager.Item_ID;
    }
}