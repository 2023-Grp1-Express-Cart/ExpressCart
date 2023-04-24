/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group_one.expresscart;

import java.util.Comparator;

/**
 *
 * @author Group 1
 */
public class ItemSortHelper {

    /**
     * Helper class implementing Comparator interface for Item ID
     */
    public class SortbyItemID implements Comparator<Item> {

        /**
         * Method to Sorting in ascending order of ID
         *
         * @param a Item to be compared by ID.
         * @param b Other Item to be compared by ID.
         * @return a negative integer, zero, or a positive integer as the first
         * argument is less than, equal to, or greater than the second.
         */
        @Override
        public int compare(Item a, Item b) {
            return a.getItemId() - b.getItemId();
        }
    }

    /**
     * Helper class implementing Comparator interface for Item Name
     */
    class SortbyName implements Comparator<Item> {

        /**
         * Method to Sorting in ascending order of name
         *
         * @param a Item to be compared by name.
         * @param b Other Item to be compared by name.
         * @return a negative integer, zero, or a positive integer as the first
         * argument is less than, equal to, or greater than the second.
         */
        @Override
        public int compare(Item a, Item b) {
            return a.getItemName().compareTo(b.getItemName());
        }
    }
}
