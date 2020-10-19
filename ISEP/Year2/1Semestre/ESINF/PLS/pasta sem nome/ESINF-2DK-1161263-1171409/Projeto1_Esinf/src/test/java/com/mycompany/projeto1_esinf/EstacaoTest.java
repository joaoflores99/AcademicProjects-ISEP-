/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto1_esinf;

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
 * @author joaoflores
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
     * Test of getNumEstacao method, of class Estacao.
     */
    @Test
    public void testGetNumEstacao() {
        System.out.println("getNumEstacao");
        Estacao instance = new Estacao(0, "o", "o");
        int expResult = 0;
        int result = instance.getNumEstacao();
        assertEquals(expResult, result);
    }

    /**
     * Test of getZona method, of class Estacao.
     */
    @Test
    public void testGetZona() {
        System.out.println("getZona");
        Estacao instance = new Estacao(1, "o", "C1");
        String expResult = "C1";
        String result = instance.getZona();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescricao method, of class Estacao.
     */
    @Test
    public void testGetDescricao() {
        System.out.println("getDescricao");
        Estacao instance = new Estacao(1, "IPO", "o");
        String expResult = "IPO";
        String result = instance.getDescricao();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValidadoEntrada method, of class Estacao.
     */
    @Test
    public void testGetValidadoEntrada() {
        System.out.println("getValidadoEntrada");
        Estacao instance = new Estacao();
        List<Bilhete> expResult = new ArrayList<>();
        List<Bilhete> result = instance.getValidadoEntrada();
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    /**
     * Test of getValidaoSaida method, of class Estacao.
     */
    @Test
    public void testGetValidaoSaida() {
        System.out.println("getValidaoSaida");
        Estacao instance = new Estacao();
        List<Bilhete> expResult = new  ArrayList<>();
        List<Bilhete> result = instance.getValidaoSaida();
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    /**
     * Test of setDescricao method, of class Estacao.
     */
    @Test
    public void testSetDescricao() {
        System.out.println("setDescricao");
        String descricao = "POLO";
        Estacao instance = new Estacao(1, "IPO", "o");
        instance.setDescricao(descricao);
        assertEquals(instance.getDescricao(), descricao);
    }

    /**
     * Test of setValidadoEntrada method, of class Estacao.
     */
    @Test
    public void testSetValidadoEntrada() {
        System.out.println("setValidadoEntrada");
        ArrayList<Bilhete> validadoEntrada = null;
        Estacao instance = new Estacao();
        instance.setValidadoEntrada(validadoEntrada);
    }

    /**
     * Test of setValidadoSaida method, of class Estacao.
     */
    @Test
    public void testSetValidadoSaida() {
        System.out.println("setValidadoSaida");
        ArrayList<Bilhete> validadoSaida = new ArrayList<>();
        Estacao instance = new Estacao();
        instance.setValidadoSaida(validadoSaida);
    }

    /**
     * Test of setNumEstacao method, of class Estacao.
     */
    @Test
    public void testSetNumEstacao() {
        System.out.println("setNumEstacao");
        int numEstacao = 0;
        Estacao instance = new Estacao(1, "o", "o");
        instance.setNumEstacao(numEstacao);
        assertEquals(instance.getNumEstacao(), numEstacao);
    }

    /**
     * Test of setZona method, of class Estacao.
     */
    @Test
    public void testSetZona() {
        System.out.println("setZona");
        String zona = "C2";
        Estacao instance = new Estacao(1, "IPO", "C1");
        instance.setZona(zona);
        assertEquals(instance.getZona(), zona);
    }

    /**
     * Test of equals method, of class Estacao.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object ob = null;
        Estacao instance = new Estacao();
        boolean expResult = false;
        boolean result = instance.equals(ob);
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsTrue() {
        System.out.println("equals");
        Estacao instance = new Estacao(1, "IPO", "C1");
        Object ob = instance;
        boolean expResult = true;
        boolean result = instance.equals(ob);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Estacao.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Estacao instance = new Estacao(1, "IPO", "C1");
        String descricao = "IPO";
        String zona = "C1";
        int numEstacao = 1;
        String expResult = "numEstacao=" + numEstacao + ", descricao=" + descricao + ", zona=" + zona;
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
