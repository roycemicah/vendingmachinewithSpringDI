/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

/**
 * FINAL COPY
 * @author Royce Rabanal
 * GitHub: https://github.com/roycemicah
 * Email: royce.rabanal93@gmail.com
 * Date: September 7th, 2021
 * Purpose: Milestone 4 Assessment: Vending Machine with Spring DI Exercise
 */
public class VendingMachineDaoPersistenceException extends Exception {
    
    public VendingMachineDaoPersistenceException(String message) {
        super(message);
    }
    
    public VendingMachineDaoPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
