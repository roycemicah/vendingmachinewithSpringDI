/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.enums;

/**
 * FINAL COPY
 * @author Royce Rabanal
 * GitHub: https://github.com/roycemicah
 * Email: royce.rabanal93@gmail.com
 * Date: August 18th, 2021
 * Purpose: Milestone 3 Assessment: Vending Machine Exercise
 */
public enum Coin {
    
    PENNY(1), NICKEL(5), DIME(10), QUARTER(25);
    
    private int value;
    
    private Coin(int value) {
        this.value = value;
    }
    
    public int getCoinValue() {
        return value;
    }
    
}
