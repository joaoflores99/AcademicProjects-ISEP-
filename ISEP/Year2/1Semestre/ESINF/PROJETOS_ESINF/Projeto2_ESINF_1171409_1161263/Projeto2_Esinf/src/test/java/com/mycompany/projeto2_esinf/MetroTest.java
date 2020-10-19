/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto2_esinf;

import Generic.Graph;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author morei
 */
public class MetroTest {
    
    public MetroTest() {
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
     * Test of getMetro method, of class Metro.
     */
    @Test
    public void testGetMetro() throws FileNotFoundException {
        System.out.println("getMetro");
        Metro m=new Metro();
        Graph<Estacao, String> expResult = new Graph<>(true);
        Graph<Estacao, String> result = m.getMetro();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMetro method, of class Metro.
     */
    @Test
    public void testSetMetro() throws FileNotFoundException {
        System.out.println("setMetro");
        Metro m=new Metro();
        Graph<Estacao, String> metro = new Graph <>(true);
        m.setMetro(metro);
    }

    /**
     * Test of getEstacao method, of class Metro.
     */
    @Test
    public void testGetEstacao() throws FileNotFoundException {
        System.out.println("getEstacao");
        Metro m=new Metro();
        LerLinhaEstacoes l=new LerLinhaEstacoes();
        l.ler("coordinates.csv",m);
        l.ler("lines_and_stations.csv",m);
        l.ler("connections.csv",m);
        String estacao = "Abbesses";
        Estacao expResult = new Estacao("Abbesses",2.33855,48.8843);
        Estacao result = m.getEstacao(estacao);
        assertEquals(expResult, result);
    }
    
}
