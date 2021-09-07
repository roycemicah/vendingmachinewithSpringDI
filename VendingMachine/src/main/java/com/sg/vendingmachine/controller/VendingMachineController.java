/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachineDaoPersistenceException;
import com.sg.vendingmachine.dto.VendItem;
import com.sg.vendingmachine.servicelayer.InsufficientFundsException;
import com.sg.vendingmachine.servicelayer.NoItemInventoryException;
import com.sg.vendingmachine.servicelayer.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * FINAL COPY
 * @author Royce Rabanal
 * GitHub: https://github.com/roycemicah
 * Email: royce.rabanal93@gmail.com
 * Date: September 7th, 2021
 * Purpose: Milestone 4 Assessment: Vending Machine with Spring DI Exercise
 */
public class VendingMachineController {
    
    private VendingMachineServiceLayer service;
    private VendingMachineView view;

    public VendingMachineController(VendingMachineServiceLayer service,
            VendingMachineView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() throws VendingMachineDaoPersistenceException {
        
        Scanner sc = new Scanner(System.in);
        
        String choice;
        BigDecimal deposit;
        
        view.displayWelcomeMessage();
        view.printNewLine();
        
        view.displayMenuItems(this.service.getAllItems());
        
        if (view.getYesOrNo() == false) {
            return;
        }
        
        deposit = view.getDeposit();
        choice = view.getMenuChoice(this.service.getAllItems());
        
        BigDecimal returnChange = deposit;
        
        try {
            returnChange = this.service.vendItem(deposit, choice);
            VendItem selectedItem = this.service.getItem(choice);
            view.displayDispenseSuccess(selectedItem.getItem());
        } catch (InsufficientFundsException | NoItemInventoryException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
        int[] changeArray = this.service.returnCoins(returnChange);
        view.displayChange(changeArray);
        
        view.displayExitMessage();
        
    }
    
}
