/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.VendItem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * FINAL COPY
 * @author Royce Rabanal
 * GitHub: https://github.com/roycemicah
 * Email: royce.rabanal93@gmail.com
 * Date: August 18th, 2021
 * Purpose: Milestone 3 Assessment: Vending Machine Exercise
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    // a member/field to declare for the main method
    public final String DATA_FILE;
    public final String DELIMITER = "::";

    // keys and properties. This is the DAO that can't be accessed
    private final Map<String, VendItem> items = new HashMap<>();

    public VendingMachineDaoFileImpl() {

        DATA_FILE = "DATA_FILE.txt";

    }

    public VendingMachineDaoFileImpl(String fileName) {

        this.DATA_FILE = fileName;

    }

    public void loadData() throws VendingMachineDaoPersistenceException {

        Scanner sc;

        try {
            sc = new Scanner(new BufferedReader(new FileReader(DATA_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineDaoPersistenceException("Could not persist inventory data!");
        }

        String currentLine;
        VendItem currentItem;

        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentItem = unmarshallItem(currentLine);

            items.put(currentItem.getRow(), currentItem);
        }
        sc.close();

    }

    private VendItem unmarshallItem(String line) {

        VendItem newItem;

        String[] propertyList = line.split(DELIMITER);

        String row = propertyList[0];
        String itemName = propertyList[1];
        BigDecimal price = new BigDecimal(propertyList[2]).setScale(2, RoundingMode.HALF_UP);
        int itemCount = Integer.parseInt(propertyList[3]);

        // from DTO
        newItem = new VendItem(row, itemName, price, itemCount);

        return newItem;
    }

    public void writeData() throws VendingMachineDaoPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DATA_FILE));
        } catch (IOException e) {
            throw new VendingMachineDaoPersistenceException("Could not write data!");
        }

        String itemAsText;
        List<VendItem> itemList = this.getAllItems();

        for (VendItem item : itemList) {
            itemAsText = marshallItem(item);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }
    
    @Override
    public List<VendItem> getAllItems() throws VendingMachineDaoPersistenceException {
        loadData();
        return new ArrayList(items.values());
    }

    public String marshallItem(VendItem item) {

        String itemAsText = item.getRow() + DELIMITER;
        itemAsText += item.getItem() + DELIMITER;
        itemAsText += item.getPrice() + DELIMITER;
        itemAsText += item.getQuantity();

        return itemAsText;

    }

    @Override
    public VendItem getItem(String row) throws VendingMachineDaoPersistenceException {
        loadData();
        return this.getAllItems().stream().filter((item) -> item.getRow().equals(row)).findAny().get();
    }

    // manipulates the quantity of VendItem, for a specific product
    @Override
    public void setItemCount(String itemRow, int count) throws VendingMachineDaoPersistenceException {

        //new DTO
        VendItem itemToUpdate = this.getItem(itemRow);
        itemToUpdate.setQuantity(count);

        writeData();

    }

    public void addItem(VendItem item) throws VendingMachineDaoPersistenceException {

        this.items.put(item.getRow(), item);
        writeData();

    }

}
