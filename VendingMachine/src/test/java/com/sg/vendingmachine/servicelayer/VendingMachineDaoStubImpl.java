/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.servicelayer;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dto.VendItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * FINAL COPY
 * @author Royce Rabanal
 * GitHub: https://github.com/roycemicah
 * Email: royce.rabanal93@gmail.com
 * Date: August 18th, 2021
 * Purpose: Milestone 3 Assessment: Vending Machine Exercise
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    public VendItem item;

    public VendingMachineDaoStubImpl() {

        String itemRow = "3";
        String itemName = "Vickie's Applewood Barbecue";
        BigDecimal itemPrice = new BigDecimal(1.50).setScale(2, RoundingMode.HALF_UP);
        int itemQuantity = 6;

        this.item = new VendItem(itemRow, itemName, itemPrice, itemQuantity);

    }
    
    @Override
    public List<VendItem> getAllItems() {
        
        List<VendItem> itemList = new ArrayList<>();
        itemList.add(item);
        return itemList;
        
    }
    
    @Override
    public VendItem getItem(String itemRow) {
        
        if(itemRow.equals(item.getRow())) {
            return item;
        } else {
            return null;
        }
        
    }
    
    @Override
    public void setItemCount(String itemRow, int count) {
        
        this.item.setQuantity(count);
        
    }
    
}
