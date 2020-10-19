/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto1_esinf;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joaoflores
 */
public class BilheteTest {

    public BilheteTest() {
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
     * Test of setNumeroBilhete method, of class Bilhete.
     */
    @Test
    public void testSetNumeroBilhete() {
        System.out.println("setNumeroBilhete");
        int numero_bilhete = 3;
        Bilhete instance = new Bilhete(12, "c4");
        instance.setNumeroBilhete(numero_bilhete);
        assertEquals(numero_bilhete, instance.getNumeroBilhete());
    }

    /**
     * Test of setTipo_Bilhete method, of class Bilhete.
     */
    @Test
    public void testSetTipo_Bilhete() {
        System.out.println("setTipo_Bilhete");
        String tipo_bilhete = "c5";
        Bilhete instance = new Bilhete(12, "c4");
        instance.setTipo_Bilhete(tipo_bilhete);
        assertEquals(tipo_bilhete, instance.getTipoBilhete());
    }

    /**
     * Test of toString method, of class Bilhete.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Bilhete instance = new Bilhete(12, "c4");
        String expResult = String.format("Detalhes do Bilhete:\n"
                + "Número do bilhete: "
                + "Tipo de Biilhete:",
                instance.getNumeroBilhete() + instance.getTipoBilhete());;
        String result = instance.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Bilhete.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = new Bilhete(12,"c4");
         Bilhete instance = new Bilhete(12,"c4");
        boolean expResult = true;
        boolean result = instance.equals(o);
        
    }

    /**
     * Test of hashCode method, of class Bilhete.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
         Bilhete instance = new Bilhete(12,"c4");
        int expResult = instance.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
       
    }

}
