/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * FINAL COPY
 * @author Royce Rabanal
 * GitHub: https://github.com/roycemicah
 * Email: royce.rabanal93@gmail.com
 * Date: August 18th, 2021
 * Purpose: Milestone 3 Assessment: Vending Machine Exercise
 */
public class UserIOConsoleImpl implements UserIO {
    
    final private Scanner sc = new Scanner(System.in);
    
    @Override 
    public void print (String message) {
        System.out.println(message);
    }
    
    @Override
    public String readString (String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }
    
    @Override
    public int readInt (String messagePrompt) {
        
        boolean invalidInput = true;
        int num = 0;
        
        while(invalidInput) {
            try {
                String stringValue = this.readString(messagePrompt);
                num = Integer.parseInt(stringValue);
                invalidInput = false; 
            } catch (NumberFormatException e) {
                this.print("Input error, please try again.");
            }
        }
        return num;
        
    }
    
    @Override
    public int readInt(String messagePrompt, int min, int max) {
        
        int result;
        
        do {
            result = readInt(messagePrompt);
        } while(result < min || result > max);
        
        return result;
        
    }
    
    @Override
    public long readLong(String messagePrompt) {
        
        while(true) {
            try {
                return Long.parseLong(this.readString(messagePrompt));
            } catch (NumberFormatException e) {
                this.print("Input error, please try again.");
            }
        }
        
    }
    
    @Override
    public long readLong(String messagePrompt, long min, long max) {
        
        long result;
        
        do{
            result = readLong(messagePrompt);
        } while (result < min || result > max);
        
        return result;
        
    }
    
    @Override
    public float readFloat(String messagePrompt) {
        
        while(true) {
            try {
                return Float.parseFloat(this.readString(messagePrompt));
            } catch (NumberFormatException e) {
                this.print("Input error, please try again.");
            }
        }
        
    }
    
    @Override
    public float readFloat(String messagePrompt, float min, float max) {
        
        float result;
        
        do {
            result = readFloat(messagePrompt);
        } while (result < min || result > max);
        
        return result;
        
    }
    
    @Override
    public double readDouble(String messagePrompt) {
        
        while(true) {
            try {
                return Double.parseDouble(this.readString(messagePrompt));
            } catch (NumberFormatException e) {
                this.print("Input error, please try again.");
            }
        }
        
    }
    
    @Override
    public double readDouble(String messagePrompt, double min, double max) {
        
        double result;
        
        do {
            result = readDouble(messagePrompt);
        } while (result < min || result > max);
        
        return result;
        
    }

    @Override
    public BigDecimal readBigDecimal(String messagePrompt) {
        
        String inputString = this.readString(messagePrompt);
        
        BigDecimal input = null;
        
        try {
            input = new BigDecimal(inputString);
        } catch (NumberFormatException e) {
            this.print("Input error, please try again!");
        }
        return input;
        
    }
}
