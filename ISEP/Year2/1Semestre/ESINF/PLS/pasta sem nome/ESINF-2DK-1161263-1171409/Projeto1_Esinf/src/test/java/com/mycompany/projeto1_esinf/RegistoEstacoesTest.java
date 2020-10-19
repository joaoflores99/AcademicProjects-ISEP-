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
 * @author jose
 */
public class RegistoEstacoesTest{
    
    public RegistoEstacoesTest() {
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
     * Test of setRegisto method, of class RegistoEstacoes.
     */
    @Test
    public void testSetRegisto() {
        System.out.println("setRegisto");
        DoublyLinkedList<Estacao> listaEstacoes = new DoublyLinkedList<>();
        RegistoEstacoes instance = new RegistoEstacoes();
        instance.setRegisto(listaEstacoes);
        assertEquals(instance.getListaEstacao(), listaEstacoes);
        
    }

    /**
     * Test of getEstacao method, of class RegistoEstacoes.
     */
    @Test
    public void testGetEstacao() {
        System.out.println("getEstacao");    
        int numEstacao = 0;
        Estacao expResult = RegistoEstacoes.getEstacao(numEstacao);
        Estacao result = RegistoEstacoes.getEstacao(numEstacao);
        assertEquals(expResult, result);
    }

    /**
     * Test of getListaEstacao method, of class RegistoEstacoes.
     */
    @Test
    public void testAddEstacao() {
        System.out.println("addEstacao");
        Estacao e = new Estacao(56, "er", "er");
        RegistoEstacoes instance = new RegistoEstacoes();
        boolean expResult = true;
        boolean result = instance.addEstacao(e);
        assertEquals(expResult, result);
    }

    /**
     * Test of procuraEstacao method, of class RegistoEstacoes.
     */
    @Test
    public void testProcuraEstacao() {
        System.out.println("procuraEstacao");
        Estacao e = new Estacao(1, "c", "c");
        DoublyLinkedList<Estacao> lista = new DoublyLinkedList<>();
        lista.addLast(e);
        RegistoEstacoes instance = new RegistoEstacoes(lista);
        Singleton.getInstance().setRe(instance);
        int expResult = 0;
        int result = Singleton.getInstance().getRe().procuraEstacao(e);
        assertEquals(expResult, result);
    }

    /**
     * Test of getListaEstacao method, of class RegistoEstacoes.
     */
    @Test
    public void testGetListaEstacao() {
        System.out.println("getListaEstacao");
        RegistoEstacoes instance = new RegistoEstacoes();
        DoublyLinkedList<Estacao> expResult = new DoublyLinkedList<>();
        DoublyLinkedList<Estacao> result = instance.getListaEstacao();
        assertEquals(expResult, result);
    }
    
}
