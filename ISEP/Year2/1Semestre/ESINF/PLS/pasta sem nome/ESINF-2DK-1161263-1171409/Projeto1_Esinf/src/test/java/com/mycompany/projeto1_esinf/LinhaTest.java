/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto1_esinf;

import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jose
 */
public class LinhaTest {
    
    public LinhaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getM method, of class Linha.
     */
    @Test
    public void testGetM() {
        System.out.println("getM");
        Linha instance = new Linha();
        Linha a=new Linha();
        Map<String, DoublyLinkedList<Estacao>> expResult =a.getM() ;
        Map<String, DoublyLinkedList<Estacao>> result = instance.getM();
        assertEquals(expResult, result);
    }

    /**
     * Test of setM method, of class Linha.
     */
    @Test
    public void testSetM() {
        System.out.println("setM");
        Map<String, DoublyLinkedList<Estacao>> m = null;
        Linha instance = new Linha();
        Linha test=new Linha(m);
        instance.setM(m);
        assertEquals(test.getM(), instance.getM());
    }

    /**
     * Test of createLinha method, of class Linha.
     */
    @Test
    public void testCreateLinha() {
        System.out.println("createLinha");
        Linha instance = new Linha();
        instance.createLinha();
        
    }
    
}
