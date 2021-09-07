/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.servicelayer;

import com.sg.vendingmachine.dao.VendingMachineDaoPersistenceException;
import com.sg.vendingmachine.dto.VendItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * FINAL COPY
 * @author Royce Rabanal
 * GitHub: https://github.com/roycemicah
 * Email: royce.rabanal93@gmail.com
 * Date: August 18th, 2021
 * Purpose: Milestone 3 Assessment: Vending Machine Exercise
 */
public class VendingMachineServiceLayerFileImplTest {
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerFileImplTest() {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextTest.xml");
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
        
        /*
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao audit = new VendingMachineAuditDaoStubImpl();
        service = new VendingMachineServiceLayerFileImpl(dao, audit);
        */
    }

    @Test
    public void testGetAllItems() throws Exception {

        VendItem testClone = new VendItem("3", "Vickie's Applewood Barbecue", new BigDecimal("1.50").setScale(2, RoundingMode.HALF_UP), 6);
        assertEquals(1, service.getAllItems().size(), "Should only have one item.");
        VendItem shouldBeVickies = service.getAllItems().get(0);
        assertEquals(testClone.getRow(), shouldBeVickies.getRow(), "Item stored under row 3 should be Vickie's Applewood Barbecue.");
        assertEquals(testClone.getItem(), shouldBeVickies.getItem(), "Item stored under row 3 should be Vickie's Applewood Barbecue.");
        assertEquals(testClone.getPrice(), shouldBeVickies.getPrice(), "Item stored under row 3 should be Vickie's Applewood Barbecue.");
        assertEquals(testClone.getQuantity(), shouldBeVickies.getQuantity(), "Item stored under row 3 should be Vickie's Applewood Barbecue.");

    }

    @Test
    public void testGetItem() throws Exception {

        VendItem testClone = new VendItem("3", "Vickie's Applewood Barbecue", new BigDecimal("1.50").setScale(2, RoundingMode.HALF_UP), 6);

        VendItem shouldBeVickies = service.getItem("3");
        assertNotNull(shouldBeVickies, "Getting 3 should be not null.");
        assertEquals(testClone.getRow(), shouldBeVickies.getRow(), "Item stored under row 3 should be Vickie's Applewood Barbecue.");
        assertEquals(testClone.getItem(), shouldBeVickies.getItem(), "Item stored under row 3 should be Vickie's Applewood Barbecue.");
        assertEquals(testClone.getPrice(), shouldBeVickies.getPrice(), "Item stored under row 3 should be Vickie's Applewood Barbecue.");
        assertEquals(testClone.getQuantity(), shouldBeVickies.getQuantity(), "Item stored under row 3 should be Vickie's Applewood Barbecue.");

        VendItem shouldBeNull = service.getItem("5");
        assertNull(shouldBeNull, "Getting 5 should be null.");

    }

    @Test
    public void testEditItem() throws Exception {

        VendItem testClone = new VendItem("3", "Vickie's Applewood Barbecue", new BigDecimal("1.50").setScale(2, RoundingMode.HALF_UP), 6);

        service.updateItemQuantity("3", 5);

        VendItem item = service.getItem(testClone.getRow());

        assertEquals(item.getQuantity(), testClone.getQuantity() - 1);

    }

    @Test
    public void testPurchaseSoldOut() {

        VendItem testClone = new VendItem("3", "Vickie's Applewood Barbecue", new BigDecimal("1.50").setScale(2, RoundingMode.HALF_UP), 6);

        BigDecimal shortAmount = new BigDecimal("1.49").setScale(2, RoundingMode.HALF_UP);

        try {
            service.vendItem(shortAmount, testClone.getRow());
            fail("InsufficientFundsException was not thrown!");
        } catch (NoItemInventoryException
                | VendingMachineDaoPersistenceException e) {
            fail("Incorrect exception was thrown.");
        } catch (InsufficientFundsException e) {
            return;
        }

    }

    @Test
    public void testOutOfStock() throws Exception {

        VendItem testClone = new VendItem("3", "Vickie's Applewood Barbecue", new BigDecimal("1.50").setScale(2, RoundingMode.HALF_UP), 0);

        service.updateItemQuantity(testClone.getRow(), 0);

        try {
            service.vendItem(testClone.getPrice(), testClone.getRow());
            fail("NoItemInventoryException was not thrown!");

        } catch (InsufficientFundsException
                | VendingMachineDaoPersistenceException e) {
            fail("Incorrect exception was thrown.");
        } catch (NoItemInventoryException e) {
            return;
        }

    }

}
