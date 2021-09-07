/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.servicelayer;

/**
 * FINAL COPY
 * @author Royce Rabanal
 * GitHub: https://github.com/roycemicah
 * Email: royce.rabanal93@gmail.com
 * Date: August 18th, 2021
 * Purpose: Milestone 3 Assessment: Vending Machine Exercise
 */
public class NoItemInventoryException extends Exception {
    
    public NoItemInventoryException(String message) {
        // calls the base class constructor
        super(message);
    }
    
    public NoItemInventoryException(String message, Throwable cause) {
        super(message);
    }
    
}
