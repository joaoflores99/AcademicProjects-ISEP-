/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto2_esinf;

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
public class TempoTest {
    
    Tempo t1 = new Tempo(5,30,12);
    Tempo t2 = new Tempo(18,5,20);
    
    public TempoTest() {
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
     * Test of getHoras method, of class Tempo.
     */
    @Test
    public void testGetHoras() {
        System.out.println("getHoras");
        
        int expResult = 5;
        int result = t1.getHoras();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getMinutos method, of class Tempo.
     */
    @Test
    public void testGetMinutos() {
        System.out.println("getMinutos");
        
        int expResult = 30;
        int result = t1.getMinutos();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getSegundos method, of class Tempo.
     */
    @Test
    public void testGetSegundos() {
        System.out.println("getSegundos");
       
        int expResult = 12;
        int result = t1.getSegundos();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setTempo method, of class Tempo.
     */
    @Test
    public void testSetTempo() {
        System.out.println("setTempo");
        int horas = 18;
        int minutos =5;
        int segundos = 20;
        Tempo instance = new Tempo();
        instance.setTempo(horas, minutos, segundos);
       
    }

    /**
     * Test of toString method, of class Tempo.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "5:30:12";
        String result = t1.toString();
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of converterTempo method, of class Tempo.
     */
    @Test
    public void testConverterTempo() {
        System.out.println("converterTempo");
        String expResult = "5:30:12 AM";
        String result = t1.converterTempo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of converterTempo method, of class Tempo.
     */
    @Test
    public void testConverterTempoT2() {
        System.out.println("converterTempo");
        String expResult = "6:5:20 PM";
        String result = t2.converterTempo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of adicionarSegundo method, of class Tempo.
     */
    @Test
    public void testAdicionarSegundo() {
        System.out.println("adicionarSegundo");
        String expResult = "Adicionado 1 segundo (5:30:13)";
        String result = t1.adicionarSegundo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of isMaiorTempo method, of class Tempo.
     */
    @Test
    public void testIsMaiorTempo() {
        System.out.println("isMaiorTempo");
        String expResult = "O maior tempo é: 18:5:20";
        String result = t1.isMaiorTempo(t2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }


    /**
     * Test of isMaiorParametros method, of class Tempo.
     */
    @Test
    public void testIsMaiorParametros() {
        System.out.println("isMaiorParametros");
        int hora = 23;
        int minuto = 7;
        int segundo = 4;
        String expResult = "O maior tempo é: 23:7:4";
        String result = t1.isMaiorParametros(hora, minuto, segundo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of diferencaTempos method, of class Tempo.
     */
    @Test
    public void testDiferencaTempos() {
        System.out.println("diferencaTempos");
        String expResult = "A diferenca entre os tempos é: 12:35:8 (45308 segundos)";
        String result = t1.diferencaTempos(t2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}

