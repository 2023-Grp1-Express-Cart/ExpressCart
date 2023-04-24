package com.group_one.expresscart;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Group 1
 */
public class InventoryManagerTest {

    public InventoryManagerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }
    
    /**
     * Test of Inventory Manager Constructor, of class InventoryManager.
     */
    @Test
    public void testInventoryManagerContructor() {
        System.out.println("InventoryManager->Contructor");
        InventoryManager InventoryMgr1 = InventoryManager.getInstance();
        InventoryManager InventoryMgr2 = InventoryManager.getInstance();
        InventoryManager InventoryMgr3 = InventoryManager.getInstance();
        assertEquals(InventoryMgr1, InventoryMgr2);
        assertEquals(InventoryMgr1, InventoryMgr3);
    }
    
    /**
     * Test of Inventory Manager Set and Get Methods for the Purchased Items List,
     * of class InventoryManager.
     */
    @Test
    public void testInventoryManagerPurchasedItems() {
        System.out.println("InventoryManager->Purchased Items Test");
        InventoryManager InventoryMgr = InventoryManager.getInstance();
        InventoryMgr.clearPurchasedItemsList();
        ArrayList<Item> items = new ArrayList<>();
        Item item1 = new Item(1, "Apple Watch", 200.00, 350.00);
        Item item2 = new Item(2, "IPhone 14", 900.00, 1200.00);
        items.add(item1);
        items.add(item2);
        InventoryMgr.setPurchasedItemsList(items);
        ArrayList<Item> purchased_items = InventoryMgr.getPurchasedItemsList();
        assertEquals(purchased_items.get(0), item1);
        assertEquals(purchased_items.get(1), item2);
        InventoryMgr.clearPurchasedItemsList();
    }
    
    /**
     * Test of Inventory Manager Set and Get Methods for the Shopping Cart Items
     * Lists of class InventoryManager.
     */
    @Test
    public void testInventoryManagerShoppingCartItems() {
        System.out.println("InventoryManager->Shopping Cart Items Test");
        InventoryManager InventoryMgr = InventoryManager.getInstance();
        InventoryMgr.clearShoppingCart();
        ArrayList<Item> items = new ArrayList<>();
        Item item1 = new Item(1, "Air Pods", 200.00, 350.00);
        Item item2 = new Item(2, "IPhone 13", 900.00, 1200.00);
        items.add(item1);
        items.add(item2);
        InventoryMgr.setShoppingCartItemsList(items);
        ArrayList<Item> shoppingcart_items = InventoryMgr.getShoppingCartItemsList();
        assertEquals(shoppingcart_items.get(0), item1);
        assertEquals(shoppingcart_items.get(1), item2);
        InventoryMgr.clearShoppingCart();
    }

    /**
     * Test of Inventory Manager Set and Get Methods for the Store Cart Items
     * Lists of class InventoryManager.
     */
    @Test
    public void testInventoryManagerStoreItems() {
        System.out.println("InventoryManager->Store Items Test");
        InventoryManager InventoryMgr = InventoryManager.getInstance();
        InventoryMgr.clearStoreItemsList();
        ArrayList<Item> items = new ArrayList<>();
        Item item1 = new Item(1, "Apple TV", 200.00, 350.00);
        Item item2 = new Item(2, "IPad Pro", 900.00, 1200.00);
        items.add(item1);
        items.add(item2);
        InventoryMgr.setStoreItemsList(items);
        ArrayList<Item> store_items = InventoryMgr.getStoreItemsList();
        assertEquals(store_items.get(0), item1);
        assertEquals(store_items.get(1), item2);
        InventoryMgr.clearStoreItemsList();
    }
    
    /**
     * Test of Inventory Manager Set and Get Methods for the Wish List Items
     * Lists of class InventoryManager.
     */
    @Test
    public void testInventoryManagerWishListItems() {
        System.out.println("InventoryManager->Wish List Items Test");
        InventoryManager InventoryMgr = InventoryManager.getInstance();
        InventoryMgr.clearWishListItems();
        ArrayList<Item> items = new ArrayList<>();
        Item item1 = new Item(1, "Apple TV", 200.00, 350.00);
        Item item2 = new Item(2, "IPad Pro", 900.00, 1200.00);
        items.add(item1);
        items.add(item2);
        InventoryMgr.setWishListItemsList(items);
        ArrayList<Item> wish_list_items = InventoryMgr.getWishListItemsList();
        assertEquals(wish_list_items.get(0), item1);
        assertEquals(wish_list_items.get(1), item2);
        InventoryMgr.clearWishListItems();
    }
}
