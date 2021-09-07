/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.servicelayer;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoPersistenceException;
import com.sg.vendingmachine.dto.VendItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * FINAL COPY
 * @author Royce Rabanal
 * GitHub: https://github.com/roycemicah
 * Email: royce.rabanal93@gmail.com
 * Date: September 7th, 2021
 * Purpose: Milestone 4 Assessment: Vending Machine with Spring DI Exercise
 */
public class VendingMachineServiceLayerFileImpl implements VendingMachineServiceLayer {
    
    private VendingMachineDao dao;
    private VendingMachineAuditDao audit;
    
    public VendingMachineServiceLayerFileImpl(VendingMachineDao dao, 
            VendingMachineAuditDao audit) {
        this.dao = dao;
        this.audit = audit;
    }
    
    @Override
    public List<VendItem> getAllItems() throws VendingMachineDaoPersistenceException {
        return dao.getAllItems();
    }
    
    @Override
    public VendItem getItem(String itemRow) throws VendingMachineDaoPersistenceException {
        return dao.getItem(itemRow);
    }
    
    // the most important business logic...
    @Override
    public BigDecimal vendItem(BigDecimal deposit, String row) throws VendingMachineDaoPersistenceException, 
            NoItemInventoryException, 
            InsufficientFundsException {

        VendItem item = getItem(row);
        
        if(item.getQuantity() <= 0) {
            throw new NoItemInventoryException("Item sold out!");
        }
        
        if(item.getPrice().compareTo(deposit) > 0) {
            throw new InsufficientFundsException("Insufficient funds!");
        }
        
        audit.writeAuditEntry("User purchased " + item.getItem() + " @ $" + item.getPrice() + " from row " 
                + row);
        
        // decrement item from vending machine once vended
        updateItemQuantity(item.getRow(), item.getQuantity() - 1);
        
        BigDecimal change = deposit.subtract(item.getPrice());
        
        return change;
               
    }
    
    @Override
    public void updateItemQuantity(String itemRow, int quantity) throws VendingMachineDaoPersistenceException {       
        dao.setItemCount(itemRow, quantity);
    }
    
    // we're calling this from the controller
    
    @Override
    public int[] returnCoins(BigDecimal change) {
    
        int[] coins = new int[4];

        BigDecimal[] coinList;
        
        int numQuarters;
        int numDimes;
        int numNickels;
        int numPennies;

        coinList = change.divideAndRemainder(new BigDecimal("0.25").setScale(2, RoundingMode.HALF_UP));
        numQuarters = coinList[0].intValue();
        change = coinList[1];
        
        coinList = change.divideAndRemainder(new BigDecimal("0.10").setScale(2, RoundingMode.HALF_UP));
        numDimes = coinList[0].intValue();
        change = coinList[1];
        
        coinList = change.divideAndRemainder(new BigDecimal("0.05").setScale(2, RoundingMode.HALF_UP));
        numNickels = coinList[0].intValue();
        change = coinList[1];
        
        coinList = change.divideAndRemainder(new BigDecimal("0.01").setScale(2, RoundingMode.HALF_UP));
        numPennies = coinList[0].intValue();
        
        coins[0] = numQuarters;
        coins[1] = numDimes;
        coins[2] = numNickels;
        coins[3] = numPennies;
        
        return coins;

    }
    
}
