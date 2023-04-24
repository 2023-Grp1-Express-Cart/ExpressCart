/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Group 1
 */
public class ItemTest {

    public ItemTest() {
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
     * Test of equals Method, of class Item.
     */
    @Test
    public void testEquals() {
        System.out.println("item->equals()");
        Item i = new Item(1, "Apple Ipad 3", 100.00, 150.00);
        Item instance = new Item(1, "Apple Ipad 3", 100.00, 150.00);
        boolean expResult = true;
        boolean result = instance.equals(i);
        assertEquals(result, expResult);
    }

    /**
     * Test of toString Method, of class Item.
     */
    @Test
    public void testToString() {
        System.out.println("item->toString()");
        Item i = new Item(1,"Apple Ipad 3", 100.00, 150.00);
        Item instance = new Item(1, "Apple Ipad 3", 100.00, 150.00);
        String expResult = i.toString();
        String result = instance.toString();
        assertEquals(result, expResult);
    }

    /**
     * Test of getItemName Method, of class Item.
     */
    @Test
    public void testGetItemName() {
        System.out.println("item->getItemName()");
        Item i = new Item("Apple Ipad 3", 100.00, 150.00);
        Item instance = new Item(1, "Apple Ipad 3", 100.00, 150.00);
        String expResult = i.getItemName();
        String result = instance.getItemName();
        assertEquals(result, expResult);
    }

    /**
     * Test of getItemName Method, of class Item.
     */
    @Test
    public void testGetItemSellPrice() {
        System.out.println("item->getItemSellPrice()");
        Item i = new Item("Apple Ipad 3", 100.00, 150.00);
        Item instance = new Item(1, "Apple Ipad 3", 100.00, 150.00);
        Double expResult = i.getItemSellPrice();
        Double result = instance.getItemSellPrice();
        assertEquals(result, expResult);
    }

    /**
     * Test of getItemName Method, of class Item.
     */
    @Test
    public void testGetItemID() {
        System.out.println("item->getItemId()");
        Item i = new Item(1, "Apple Ipad 3", 100.00, 150.00);
        Item instance = new Item(1, "Apple Ipad 3", 100.00, 150.00);
        Integer expResult = i.getItemId();
        Integer result = instance.getItemId();
        assertEquals(result, expResult);
    }

    /**
     * Test of getItemInvoicePrice Method, of class Item.
     */
    @Test
    public void testGetItemInvoicePrice() {
        System.out.println("item->getItemInvoicePrice()");
        Item i = new Item("Apple Ipad 3", 100.00, 150.00);
        Item instance = new Item(1, "Apple Ipad 3", 100.00, 150.00);
        Double expResult = i.getItemInvoicePrice();
        Double result = instance.getItemInvoicePrice();
        assertEquals(result, expResult);
    }

    /**
     * Test of setItemInvoicePrice Method, of class Item.
     */
    @Test
    public void testSetItemName() {
        System.out.println("item->setItemName()");
        Item i = new Item("Apple Ipad 3", 100.00, 150.00);
        Item instance = new Item(1, "Apple Ipad 3", 100.00, 150.00);
        i.setItemName("Apple Ipad 4");
        instance.setItemName("Apple Ipad 4");
        String expResult = i.getItemName();
        String result = instance.getItemName();
        assertEquals(result, expResult);
    }

    /**
     * Test of setItemSellPrice Method, of class Item.
     */
    @Test
    public void testSetItemSellPrice() {
        System.out.println("item->setItemSellPrice()");
        Item i = new Item("Apple Ipad 3", 100.00, 150.00);
        Item instance = new Item(1, "Apple Ipad 3", 100.00, 150.00);
        i.setItemSellPrice(300.00);
        instance.setItemSellPrice(300.00);
        Double expResult = i.getItemSellPrice();
        Double result = instance.getItemSellPrice();
        assertEquals(result, expResult);
    }

    /**
     * Test of setItemId Method, of class Item.
     */
    @Test
    public void testSetItemId() {
        System.out.println("item->setItemId()");
        Item i = new Item("Apple Ipad 3", 100.00, 150.00);
        Item instance = new Item(1, "Apple Ipad 3", 100.00, 150.00);
        i.setItemId(2);
        instance.setItemId(2);
        Integer expResult = i.getItemId();
        Integer result = instance.getItemId();
        assertEquals(result, expResult);
    }

    /**
     * Test of getItemInvoicePrice Method, of class Item.
     */
    @Test
    public void testSetItemInvoicePrice() {
        System.out.println("item->setItemInvoicePrice()");
        Item i = new Item("Apple Ipad 3", 100.00, 150.00);
        Item instance = new Item(1, "Apple Ipad 3", 100.00, 150.00);
        i.setItemInvoicePrice(120.00);
        instance.setItemInvoicePrice(120.00);
        Double expResult = i.getItemInvoicePrice();
        Double result = instance.getItemInvoicePrice();
        assertEquals(result, expResult);
    }
}
