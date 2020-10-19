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
public class ViagemTest {

    public ViagemTest() {
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
     * Test of setEstacaoOrigem method, of class Viagem.
     */
    @Test
    public void testSetEstacaoOrigem() {
        System.out.println("setEstacaoOrigem");
        int estacao_origem = 2;Bilhete b=new Bilhete(111222333, "Z2");
        Viagem instance = new Viagem(b, 1, 10);
        instance.setEstacao_origem(estacao_origem);
        assertEquals(estacao_origem, instance.getEstacao_origem());
    }

    /**
     * Test of setEstacaoDestino method, of class Viagem.
     */
    @Test
    public void testSetEstacaoDestino() {
        System.out.println("setEstacaoDestino");
        int estacao_destino = 2;
        Bilhete b=new Bilhete(111222333, "Z2");
        Viagem instance = new Viagem(b, 1, 10);
        instance.setEstacao_destino(estacao_destino);
        assertEquals(estacao_destino, instance.getEstacao_destino());
    }

    /**
     * Test of toString method, of class Viagem.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Bilhete b=new Bilhete(111222333, "Z2");
        Viagem instance = new Viagem(b, 1, 10);
        String expResult = String.format("Detalhes do Bilhete:\n"
                + "Número do Bilhete: "
                + "Tipo de Biilhete:"
                + "Estação de Origem:"
                + "Estação de Destino:",
                instance.getBilhete().getNumeroBilhete() + instance.getBilhete().getTipoBilhete() + instance.getEstacao_origem() + instance.getEstacao_destino());;
        String result = instance.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Viagem.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        Bilhete b=new Bilhete(111222333, "Z2");
        Viagem instance = new Viagem(b, 1, 10);
        Object o = new Viagem(b, 1, 10);
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of hashCode method, of class Viagem.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Bilhete b=new Bilhete(111222333, "Z2");
        Viagem instance = new Viagem(b, 1, 10);
        int expResult = instance.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
        }

    }

