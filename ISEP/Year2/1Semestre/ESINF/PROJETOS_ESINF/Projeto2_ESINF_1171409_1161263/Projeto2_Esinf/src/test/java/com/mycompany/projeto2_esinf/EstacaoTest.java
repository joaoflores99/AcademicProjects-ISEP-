/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto2_esinf;

import java.util.ArrayList;
import java.util.List;
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
public class EstacaoTest {
    
    public EstacaoTest() {
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
     * Test of getTodasLinhas method, of class Estacao.
     */
    @org.junit.Test
    public void testGetTodasLinhas() {
        System.out.println("getTodasLinhas");
        Estacao instance = new Estacao("1",40,40);
        instance.addLinha("1");
        instance.addLinha("2");
        List<String> expResult = new ArrayList<>();
        expResult.add("1");
        expResult.add("2");
        List<String> result = instance.getTodasLinhas();
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    /**
     * Test of getNome method, of class Estacao.
     */
    @org.junit.Test
    public void testGetNome() {
        System.out.println("getNome");
        Estacao instance = new Estacao("1",40,40);
        String expResult = "1";
        String result = instance.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLatitude method, of class Estacao.
     */
    @org.junit.Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        Estacao instance = new Estacao("1",50,50);
        double expResult = 50.0;
        double result = instance.getLatitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getLongitude method, of class Estacao.
     */
    @org.junit.Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        Estacao instance = new Estacao("1",40,40);
        double expResult = 40.0;
        double result = instance.getLongitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setNome method, of class Estacao.
     */
    @org.junit.Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "1";
        Estacao instance = new Estacao("2",50,50);
        instance.setNome(nome);
        assertEquals(instance.getNome(), nome);
    }

    /**
     * Test of setLatitude method, of class Estacao.
     */
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testSetLatitude() {
        System.out.println("setLatitude");
        double latitude = 700.0;
        Estacao instance = new Estacao();
        instance.setLatitude(latitude);
    }

    /**
     * Test of setLinhas method, of class Estacao.
     */
    @org.junit.Test
    public void testSetLinhas() {
        System.out.println("setLinhas");
        List<String> linhas = new ArrayList<>();
        linhas.add("1");
        Estacao instance = new Estacao("1",40,40);
        instance.setLinhas(linhas);
        assertArrayEquals(linhas.toArray(), instance.getTodasLinhas().toArray());
    }

    /**
     * Test of setLongitude method, of class Estacao.
     */
    @org.junit.Test
    public void testSetLongitudeValido() {
        System.out.println("setLongitude");
        double longitude = 40.0;
        Estacao instance = new Estacao("1",50,50);
        instance.setLongitude(longitude);
        assertEquals(longitude, instance.getLongitude(),0.0);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testSetLongitude() {
        System.out.println("setLongitude");
        double longitude = -700;
        Estacao instance = new Estacao();
        instance.setLongitude(longitude);
    }

    /**
     * Test of addLinha method, of class Estacao.
     */
    @org.junit.Test
    public void testAddLinha() {
        System.out.println("addLinha");
        String linha = "1";
        Estacao instance = new Estacao("estacao",40,40);
        boolean expResult = true;
        boolean result = instance.addLinha(linha);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Estacao.
     */
    @org.junit.Test
    public void testEqualsFalse() {
        System.out.println("equals");
        Object obj = new Estacao();
        Estacao instance = new Estacao("1",40,40);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    /**
     * Test of equals method, of class Estacao.
     */
    @org.junit.Test
    public void testEqualsTrue() {
        System.out.println("equals");
        Object obj = new Estacao("1",40,40);
        Estacao instance = new Estacao("1",40,40);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Estacao.
     */
    @org.junit.Test
    public void testToString() {
        System.out.println("toString");
        String nome="1";
        double latitude =40;
        double longitude =40;
        Estacao instance = new Estacao(nome,latitude,longitude);
        String expResult = "Estacao nome=" + nome + " latitude=" + latitude + " longitude=" + longitude;
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
