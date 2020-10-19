/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto1_esinf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
public class LerTest {

    public LerTest() {
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
     * Test of ler method, of class Ler.
     */
    @Test
    public void testLerViagems() throws Exception {
        System.out.println("ler");
        String nome = "fx_viagens.txt";
        boolean expResult = true;
        boolean result = Ler.ler(nome);
        assertEquals(expResult, result);
    }

    @Test
    public void testLerEstacao() throws Exception {
        System.out.println("ler");
        String nome = "fx_estacoes.txt";
        boolean expResult = true;
        boolean result = Ler.ler(nome);
        assertEquals(expResult, result);
    }

    /**
     * Test of lerLinha method, of class Ler.
     *
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testLerLinhaEstacao() throws FileNotFoundException {
        System.out.println("lerLinha");
        List<Estacao>le=new ArrayList<>();
        Scanner sc = new Scanner(new File("fx_estacoes.txt"));
        int i = 1;
        boolean expResult = true;
        boolean result = Ler.lerLinha(sc, i,le);
        assertEquals(expResult, result);
    }

    @Test
    public void testLinhaCorreta() throws FileNotFoundException {
        System.out.println("ler linha a linha");
        Scanner sc = new Scanner(new File("testEstacao.txt"));
        List<Estacao>le=new ArrayList<>();
        int i = 1;
        Ler.lerLinha(sc, i,le);
        DoublyLinkedList<Estacao> expected = new DoublyLinkedList<Estacao>();
        Estacao e = new Estacao(1,"Hospital de S.Joao", "C6");
        expected.addLast(e);
        assertEquals(expected.first().toString(), Singleton.getInstance().getRe().getListaEstacao().first().toString());

    }

    @Test
    public void testLerLinhaViagem() throws FileNotFoundException {
        System.out.println("lerLinha");
        List<Estacao>le=new ArrayList<>();
        Scanner sc = new Scanner(new File("testViagens.txt"));;
        int i = 2;
        Ler.lerLinha(sc, i,le);
        List<Viagem> expected=new ArrayList<>();
        Bilhete b=new Bilhete(111222333,"Z2");
        Viagem v=new Viagem(b,1,10);
        expected.add(v);
        assertEquals(expected.get(0).toString(), Singleton.getInstance().getRv().getListaViagens().get(0).toString());
    }

    /**
     * Test of criarEstacao method, of class Ler.
     */
    @Test
    public void testCriarEstacao() {
        System.out.println("criarEstacao");
        String linha = "1,Hospital de S.Joao,C6";
        List<Estacao>le=new ArrayList<>();
        Ler.criarEstacao(linha,le);
        Estacao expected = new Estacao(1, "Hospital de S.Joao", "C6");
        assertEquals(expected.toString(), Singleton.getInstance().getRe().getListaEstacao().first().toString());
    }

    /**
     * Test of criarListaViagens method, of class Ler.
     */
    @Test
    public void testCriarListaViagens() {
        System.out.println("criarListaViagens");
        String linha = "111222333,Z2,1,10";
        Ler.criarListaViagens(linha);
        Bilhete b = new Bilhete(111222333, "Z2");
        Viagem expected = new Viagem(b, 1, 10);
        assertEquals(expected.toString(), Singleton.getInstance().getRv().getListaViagens().get(0).toString());
    }

}
