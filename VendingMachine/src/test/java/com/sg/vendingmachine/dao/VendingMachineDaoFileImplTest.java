/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.VendItem;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * FINAL COPY
 * @author Royce Rabanal
 * GitHub: https://github.com/roycemicah
 * Email: royce.rabanal93@gmail.com
 * Date: August 18th, 2021
 * Purpose: Milestone 3 Assessment: Vending Machine Exercise
 */
public class VendingMachineDaoFileImplTest {

    VendingMachineDaoFileImpl testDao;

    public VendingMachineDaoFileImplTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {

        String testFile = "testdata.txt";
        new FileWriter(testFile);
        testDao = new VendingMachineDaoFileImpl(testFile);

    }

    @Test
    public void testAddGetItem() throws Exception {

        String itemRow = "3";
        String itemName = "Vickie's Applewood Barbecue";
        BigDecimal itemPrice = new BigDecimal("1.50").setScale(2, RoundingMode.HALF_UP);
        int itemCount = 6;

        VendItem testItem = new VendItem(itemRow, itemName, itemPrice, itemCount);

        testDao.addItem(testItem);

        VendItem retrievedItem = testDao.getItem(itemRow);
        // this is the shorter form, equating both objects and comparing their properties
        assertEquals(testItem, retrievedItem);

        /* With the generated hashCode, this is no longer necessary.
        assertEquals(testItem.getRow(), retrievedItem.getRow(), "Checking row for item.");
        assertEquals(testItem.getItem(), retrievedItem.getItem(), "Checking item name.");
        assertEquals(testItem.getPrice(), retrievedItem.getPrice(), "Checking item price.");
        assertEquals(testItem.getQuantity(), retrievedItem.getQuantity(), "Checking quantity.");*/

    }
    
    @Test
    public void testAddGetAllItems() throws Exception {
        
        String itemRow = "3";
        String itemName = "Vickie's Applewood Barbecue";
        BigDecimal itemPrice = new BigDecimal("1.50").setScale(2, RoundingMode.HALF_UP);
        int itemCount = 6;
        
        String secondItemRow = "5";
        String secondItemName = "Matcha KitKat";
        BigDecimal secondItemPrice = new BigDecimal("2.00").setScale(2, RoundingMode.HALF_UP);
        int secondItemCount = 19;
        
        VendItem item = new VendItem(itemRow, itemName, itemPrice, itemCount);
        VendItem secondItem = new VendItem(secondItemRow, secondItemName, secondItemPrice, secondItemCount);

        testDao.addItem(item);
        testDao.addItem(secondItem);
        
        List<VendItem> items = testDao.getAllItems();

        assertNotNull(items, "The list of items must not null");
        assertEquals(2, items.size(), "List of items should have 2 items. ");
        
        VendItem retrievedItem = testDao.getAllItems().stream().filter((p) -> p.getRow().equals(item.getRow())).findAny().get();
        VendItem secondRetrievedItem = testDao.getAllItems().stream().filter((p) -> p.getRow().equals(secondItem.getRow())).findAny().get();
        
        assertEquals(item.getRow(), retrievedItem.getRow(), "Checking row for item.");
        assertEquals(item.getItem(), retrievedItem.getItem(), "Checking item name.");
        assertEquals(item.getPrice(), retrievedItem.getPrice(), "Checking item price.");
        assertEquals(item.getQuantity(), retrievedItem.getQuantity(), "Checking quantity.");
        
        assertEquals(secondItem.getRow(), secondRetrievedItem.getRow(), "Checking row for item.");
        assertEquals(secondItem.getItem(), secondRetrievedItem.getItem(), "Checking item name.");
        assertEquals(secondItem.getPrice(), secondRetrievedItem.getPrice(), "Checking item price.");
        assertEquals(secondItem.getQuantity(), secondRetrievedItem.getQuantity(), "Checking quantity.");

    }
    
    @Test
    public void testUpdateItemQuantity() throws Exception {
        
        String itemRow = "3";
        String itemName = "Vickie's Applewood Barbecue";
        BigDecimal itemPrice = new BigDecimal("1.50").setScale(2, RoundingMode.HALF_UP);
        int itemCount = 6;
        
        VendItem item = new VendItem(itemRow, itemName, itemPrice, itemCount);
        
        testDao.addItem(item);
        testDao.setItemCount(itemRow, itemCount - 1);
        
        VendItem retrievedItem = testDao.getItem(itemRow);
        
        assertEquals(item.getRow(), retrievedItem.getRow(), "Checking row for item.");
        assertEquals(item.getItem(), retrievedItem.getItem(), "Checking item name.");
        assertEquals(item.getPrice(), retrievedItem.getPrice(), "Checking item price.");
        assertEquals(item.getQuantity() - 1, retrievedItem.getQuantity(), "Checking quantity.");

    }

}
