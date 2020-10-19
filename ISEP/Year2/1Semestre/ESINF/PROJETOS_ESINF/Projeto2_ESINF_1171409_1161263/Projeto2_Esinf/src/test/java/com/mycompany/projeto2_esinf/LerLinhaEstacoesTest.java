/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto2_esinf;

import Generic.Graph;
import java.io.FileNotFoundException;
import java.util.Scanner;
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
public class LerLinhaEstacoesTest {

    static Graph<Estacao, String> graph;
    private Metro m;
    private LerLinhaEstacoes l;

    public LerLinhaEstacoesTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        graph = new Graph<>(true);

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FileNotFoundException {
        m = new Metro();
        l = new LerLinhaEstacoes();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of ler method, of class LerLinhaEstacoes.
     */
    @Test
    public void testLer() throws Exception {
        System.out.println("ler");
        String nome = "coordinates.csv";
        boolean expResult = true;
        boolean result = l.ler(nome, m);
        assertEquals(expResult, result);
    }

    @Test(expected = FileNotFoundException.class)
    public void testLerNotFound() throws Exception {
        System.out.println("ler");
        String nome = "";
        boolean expResult = false;
        boolean result = l.ler(nome, m);
        assertEquals(expResult, result);
    }

    /**
     * Test of lerLinha method, of class LerLinhaEstacoes.
     */
    //@Test
    public void testLerLinha() {
        System.out.println("lerLinha");
        Scanner sc = new Scanner("lines_and_stations.csv");

        int i = 2;
        boolean expResult = true;
        boolean result = l.lerLinha(sc, i, m);
        assertEquals(expResult, result);
    }

    /**
     * Test of criarEstacao method, of class LerLinhaEstacoes.
     */
    @Test
    public void testCriarEstacao() {
        System.out.println("criarEstacao");
        String nextLine = "Abbesses;2.33855;48.8843";

        Estacao result = LerLinhaEstacoes.criarEstacao(nextLine);
        Estacao es = new Estacao("Abbesses", 2.33855, 48.8843);
        assertEquals(es, result);
    }

    /**
     * Test of ligacoes method, of class LerLinhaEstacoes.
     */
    @Test
    public void testLigacoes() throws FileNotFoundException {
        System.out.println("ligacoes");
        String nextLine = "1;La Defense;Esplanade de La Defense;1";

        Estacao instance2 = new Estacao("La Defense",2.23684,48.89252);
        Estacao instance3 = new Estacao("Esplanade de La Defense",2.24995,48.88837);
        graph.insertEdge(instance2, instance3, "1", 1);
        l.ler("coordinates.csv", m);
        l.ler("lines_and_stations.csv", m);
        l.ler("connections.csv", m);
        assertEquals(graph.getEdge(instance2, instance3).toString(), m.getMetro().getEdge(instance2, instance3).toString());
    }

    /**
     * Test of lines_and_stations method, of class LerLinhaEstacoes.
     */
    @Test
    public void testLines_and_stations() throws FileNotFoundException {
        Metro m = new Metro();
        String nextLine = "1;La Defense";

        l.ler("coordinates.csv", m);
        l.ler("lines_and_stations.csv", m);
        l.ler("connections.csv", m);
        String expected = "1";

        assertEquals(expected, m.getMetro().getEdge(m.getEstacao("La Defense"), m.getEstacao("Esplanade de La Defense")).getElement());
    }

    @Test
    public void testLines_and_stationsPeso() throws FileNotFoundException {
        Metro m = new Metro();
        String nextLine = "1;La Defense";

        l.ler("coordinates.csv", m);
        l.ler("lines_and_stations.csv", m);
        l.ler("connections.csv", m);
        double expected = 1;

        assertEquals(expected, m.getMetro().getEdge(m.getEstacao("La Defense"), m.getEstacao("Esplanade de La Defense")).getWeight(), 0.0);
    }

    /**
     * Test of getEstacao method, of class LerLinhaEstacoes.
     */
    @Test
    public void testGetEstacao() throws FileNotFoundException {
        System.out.println("getEstacao");
        String estacao = "Abbesses";

        Estacao expResult = new Estacao("Abbesses", 2.33855, 48.8843);
        l.ler("coordinates.csv", m);
        l.ler("lines_and_stations.csv", m);
        l.ler("connections.csv", m);
        Estacao result = m.getEstacao(estacao);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetEdgeWithLineNumEdges() throws FileNotFoundException {
        System.out.println("getEdgeWithLine");
        Metro m = new Metro();
        l.ler("coordinates.csv", m);
        l.ler("lines_and_stations.csv", m);
        l.ler("connections.csv", m);

        assertEquals(712, m.getMetro().numEdges());
    }

}
