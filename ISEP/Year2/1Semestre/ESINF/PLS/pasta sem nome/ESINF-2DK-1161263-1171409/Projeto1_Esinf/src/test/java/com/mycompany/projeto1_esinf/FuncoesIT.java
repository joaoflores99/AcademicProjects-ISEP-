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
public class FuncoesIT {

    public FuncoesIT() {
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
     * Test of utilizadoresEstacao method, of class Funcoes.
     */
    @Test
    public void testUtilizadoresEstacao() throws FileNotFoundException {
        System.out.println("utilizadoresEstacao");
        int[][] expResult = {{1, 3}, {2, 3}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}, {8, 1}, {9, 1}, {10, 1}, {11, 1}, {12, 1}};
        Scanner sc = new Scanner(new File("fx_estacoes.txt"));
        List<Estacao>list=new ArrayList<>();
        Ler.lerLinha(sc, 1,list);
        int [][]result= Funcoes.utilizadoresEstacao();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of valor method, of class Funcoes.
     */
    @Test
    public void testValor_intArrArr() {
        System.out.println("valor");
        int[][] vec = {{1, 3}, {2, 3}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}, {8, 1}, {9, 1}, {10, 1}, {11, 1}, {12, 1}};
        Funcoes.valor(vec);
    }

    /**
     * Test of numZonas method, of class Funcoes.
     */
     @Test
    public void testNumZonas() throws FileNotFoundException {
        System.out.println("numZonas");
        int estacaoOrigem = 1;
        int estacaoDestino = 6;
        Scanner sc = new Scanner(new File("fx_estacoes.txt"));
        List<Estacao>list=new ArrayList<>();
        Ler.lerLinha(sc, 1,list);
        int expResult = 2;
        int result = Funcoes.numZonas(estacaoOrigem, estacaoDestino);
        assertEquals(expResult, result);
    }

    /**
     * Test of valor method, of class Funcoes.
     */
    @Test
    public void testValor_int_int() throws FileNotFoundException {
        System.out.println("valor");
        int estacaoOrigem = 1;
        int estacaoDestino = 6;
        List<String> expResult = new ArrayList<>();
        List<Estacao>list=new ArrayList<>();
        Scanner sc = new Scanner(new File("fx_estacoes.txt"));
        Ler.lerLinha(sc, 1,list);
        expResult.add("C6");
        expResult.add("C1");
        List<String> result = Funcoes.valor(estacaoOrigem, estacaoDestino);
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    /**
     * Test of transgressao method, of class Funcoes.
     */
    @Test
    public void testTransgressao() throws FileNotFoundException {
        System.out.println("transgressao");
        List<Bilhete>expected=new ArrayList<>();
        Bilhete b =new Bilhete(111222333,"Z1");
        Viagem v=new Viagem(b,1,12);
        expected.add(b);
        List<Estacao>list =new ArrayList<>();
        Scanner sc = new Scanner(new File("fx_estacoes.txt"));
        Ler.lerLinha(sc, 1,list);
        Scanner s = new Scanner(new File("fx_viagens.txt"));
        Ler.lerLinha(s, 2,list);
        
        List<Bilhete>result=Funcoes.transgressao();
        assertEquals(expected.get(0).toString(), result.get(0).toString());
    }

  

    /**
     * Test of preencherInicial method, of class Funcoes.
     */
    @Test
    public void testPreencherInicial() {
        System.out.println("preencherInicial");
        int[][] vec = new int [500][2];
        Funcoes.preencherInicial(vec);
    }

    /**
     * Test of adicionar method, of class Funcoes.
     */
    @Test
    public void testAdicionar() {
        System.out.println("adicionar");
        int[][] vec = {{1, 3}, {3, 2}, {2, 2}};
        Bilhete b = new Bilhete(121212121, "Z2");
        Viagem viagem = new Viagem(b, 1, 3);
        Funcoes.adicionar(vec, viagem);
        int[][] expected = {{1, 4}, {3, 3}, {2, 3}};
        assertArrayEquals(expected, vec);
    }

    /**
     * Test of mostrarUtilizadoresEstacao method, of class Funcoes.
     */
    @Test
    public void testMostrarUtilizadoresEstacao() {
        System.out.println("mostrarUtilizadoresEstacao");
        int[][] u = {{1, 3}, {3, 2}, {2, 2}};
        Funcoes.mostrarUtilizadoresEstacao(u);
    }

    /**
     * Test of ordena method, of class Funcoes.
     */
    @Test
    public void testOrdena() {
        System.out.println("ordena");
        int[][] vec = {{1, 3}, {3, 2}, {2, 2}};
        int[][] expected = {{1, 3}, {2, 2}, {3, 2}};
        boolean result = Funcoes.ordena(vec);
        assertArrayEquals(expected, vec);
    }

    /**
     * Test of swap method, of class Funcoes.
     */
    @Test
    public void testSwap() {
        System.out.println("swap");
        int[][] vec = {{1, 0}, {2, 0}};
        int j = 0;
        int i = 1;
        int[][] expected = {{2, 0}, {1, 0}};
        Funcoes.swap(vec, j, i);
        assertArrayEquals(vec, expected);
    }
 
  
//    @Test
//    public void testConjuntoEstacoesMaximaParaTipoBilhete() {
//        RegistoEstacoes rg = new RegistoEstacoes();
//        String tipo = "Z3";
//        Estacao e1 = new Estacao(1, "HSJ", "C1");
//        Estacao e2 = new Estacao(2, "IPO", "C1");
//        Estacao e3 = new Estacao(3, "TRE", "C2");
//        Estacao e4 = new Estacao(4, "SRNH", "C3");
//        Estacao e5 = new Estacao(5, "PVZ", "C4");
//        rg.addEstacao(e1);
//        rg.addEstacao(e2);
//        rg.addEstacao(e3);
//        rg.addEstacao(e4);
//        rg.addEstacao(e5);
//        Singleton.getInstance().setRe(rg);
//        List<Integer> expected = new ArrayList<>();
//        expected.add(e1.getNumEstacao());
//        expected.add(e2.getNumEstacao());
//        expected.add(e3.getNumEstacao());
//        expected.add(e4.getNumEstacao());
//
//        List<Integer> result = Funcoes.conjuntoEstacoesMaximaParaTipoBilhete(tipo);
//        assertEquals(expected, result);
//    }

}
