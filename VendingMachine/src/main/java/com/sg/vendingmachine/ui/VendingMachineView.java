/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.VendItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

/**
 * FINAL COPY
 * @author Royce Rabanal
 * GitHub: https://github.com/roycemicah
 * Email: royce.rabanal93@gmail.com
 * Date: August 18th, 2021
 * Purpose: Milestone 3 Assessment: Vending Machine Exercise
 */
public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayWelcomeMessage() {
        io.print("*******************************************");
        io.print("***** Welcome to the Vending Machine! *****");
        io.print("*******************************************");
    }

    public void printNewLine() {
        io.print(" ");
    }

    public void displayMenuItems(List<VendItem> menuList) {

        for (VendItem item : menuList) {
            if (item.getQuantity() > 0) {
                System.out.print(item.getRow() + " ");
                System.out.print(item.getItem() + " ");
                System.out.print("$" + item.getPrice() + " ");
                System.out.println("\n-------------------------------------------------------");
            }
        }
        
    }

    public boolean getYesOrNo() {

        Scanner sc = new Scanner(System.in);

        io.print("(Y) Continue (N) Exit");
        String userResponse = sc.nextLine().toLowerCase();

        if (userResponse.equals("y")) {
            return true;
        }

        return false;

    }

    public void displayDepositFundsMessage() {
        io.print("Please deposit funds to proceed: ");
    }

    public BigDecimal getDeposit() {
        Scanner sc = new Scanner(System.in);
        this.displayDepositFundsMessage();
        BigDecimal change = new BigDecimal(sc.nextLine()).setScale(2, RoundingMode.HALF_UP);
        return change;
    }

    public String getMenuChoice(List<VendItem> menuList) {
        Scanner sc = new Scanner(System.in);

        int size = menuList.size();
        int choiceInt = io.readInt("Select item number to vend.", 1, size);

        String choice = Integer.toString(choiceInt);

        return choice;
    }

    public void displayDispenseSuccess(String item) {
        io.print("Vending " + item + "!");
    }

    public void displayChange(int[] changeArray) {
        io.print("Returning Change: ");
        io.print("----------------");
        io.print("Quarters: " + changeArray[0]);
        io.print("Dimes: " + changeArray[1]);
        io.print("Nickels: " + changeArray[2]);
        io.print("Pennies: " + changeArray[3]);
    }

    public void displayErrorMessage(String errorMessage) {
        io.print(errorMessage);
    }

    public void displayExitMessage() {
        io.print("Thank you for your service!");
    }

}
