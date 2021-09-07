/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.servicelayer;

import com.sg.vendingmachine.dao.VendingMachineDaoPersistenceException;
import com.sg.vendingmachine.dto.VendItem;
import java.math.BigDecimal;
import java.util.List;

/**
 * FINAL COPY
 * @author Royce Rabanal
 * GitHub: https://github.com/roycemicah
 * Email: royce.rabanal93@gmail.com
 * Date: September 7th, 2021
 * Purpose: Milestone 4 Assessment: Vending Machine with Spring DI Exercise
 */
public interface VendingMachineServiceLayer {
    
    List<VendItem> getAllItems() throws VendingMachineDaoPersistenceException;
    
    BigDecimal vendItem(BigDecimal deposit, String row) throws VendingMachineDaoPersistenceException, 
            NoItemInventoryException, 
            InsufficientFundsException;
    
    int[] returnCoins(BigDecimal change);
    
    VendItem getItem(String itemRow) throws VendingMachineDaoPersistenceException;
    
    void updateItemQuantity(String itemRow, int quantity) throws VendingMachineDaoPersistenceException;

}
