/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto2_esinf;

import Generic.GraphAlgorithms;
import Generic.Graph;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
public class PercursoComEstacoesIntermediasTest {

    Metro metro;
    LerLinhaEstacoes ler;
    Graph<Estacao, String> graph;

    Estacao estacaoOrig = new Estacao("Estacao1", 10, 10);
    Estacao estacaoDest = new Estacao("Estacao6", 25, 20);

    Estacao estacao2 = new Estacao("Estacao2", 10, 15);
    Estacao estacao3 = new Estacao("Estacao3", 15, 10);
    Estacao estacao4 = new Estacao("Estacao4", 15, 15);
    Estacao estacao5 = new Estacao("Estacao5", 20, 20);
    Estacao estacao7 = new Estacao("Estacao7", 30, 20);
    Estacao estacao8 = new Estacao("Estacao8", 35, 20);

    public PercursoComEstacoesIntermediasTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FileNotFoundException {
        metro = new Metro();
        graph = metro.getMetro();

        graph.insertVertex(estacaoOrig);
        graph.insertVertex(estacao2);
        graph.insertVertex(estacao3);
        graph.insertVertex(estacao4);
        graph.insertVertex(estacao5);
        graph.insertVertex(estacaoDest);
        graph.insertVertex(estacao7);
        graph.insertVertex(estacao8);

        graph.insertEdge(estacaoOrig, estacao2, "2", 1.0);
        graph.insertEdge(estacaoOrig, estacao3, "1", 3.0);
        graph.insertEdge(estacaoOrig, estacao4, "3", 2.0);
        graph.insertEdge(estacao3, estacaoDest, "1", 6.0);
        graph.insertEdge(estacao2, estacao5, "2", 4.0);
        graph.insertEdge(estacao5, estacaoDest, "2", 3.0);
        graph.insertEdge(estacao4, estacao5, "3", 5.0);
        graph.insertEdge(estacao3, estacao4, "5", 2.0);
        graph.insertEdge(estacao2, estacao4, "4", 2.0);

        graph.insertEdge(estacao2, estacaoOrig, "2", 1.0);
        graph.insertEdge(estacao3, estacaoOrig, "1", 3.0);
        graph.insertEdge(estacao4, estacaoOrig, "3", 2.0);
        graph.insertEdge(estacaoDest, estacao3, "1", 6.0);
        graph.insertEdge(estacao5, estacao2, "2", 4.0);
        graph.insertEdge(estacaoDest, estacao5, "2", 3.0);
        graph.insertEdge(estacao5, estacao4, "3", 5.0);
        graph.insertEdge(estacao4, estacao3, "5", 2.0);
        graph.insertEdge(estacao4, estacao2, "4", 2.0);
        graph.insertEdge(estacaoDest, estacao7, "2", 6.0);
        graph.insertEdge(estacao7, estacaoDest, "2", 6.0);
        graph.insertEdge(estacao7, estacao8, "2", 7.0);
        graph.insertEdge(estacao8, estacao7, "2", 7.0);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of caminhoMinimoComEstacoesIntermedias method, of class
     * PercursoComEstacoesIntermedias.
     */
    @Test
    public void testCaminhoMinimoComEstacoesIntermedias() throws FileNotFoundException {
        System.out.println("caminhoMinimoComEstacoesIntermedias");
        double tempoInstanteInicial = 0.0;
        List<Estacao> listaEstacoesIntermedias = new ArrayList<>();
        listaEstacoesIntermedias.add(metro.getEstacao("Estacao5"));
        listaEstacoesIntermedias.add(metro.getEstacao("Estacao2"));
        PercursoComEstacoesIntermedias instance = new PercursoComEstacoesIntermedias();
        Percurso expResult = new Percurso(estacaoOrig, estacaoDest);
        Percurso result = instance.caminhoMinimoComEstacoesIntermedias(estacaoOrig, estacaoDest, tempoInstanteInicial, listaEstacoesIntermedias, metro);
        Map<Estacao, String> map1 = new LinkedHashMap<>();
        map1.put(estacaoOrig, "2");
        map1.put(estacao2, "2");
        map1.put(estacao5, "2");
        map1.put(estacaoDest, "2");
        Map<Estacao, Double> map2 = new LinkedHashMap<>();
        map2.put(estacaoOrig, 0.0);
        map2.put(estacao2, 1.0);
        map2.put(estacao5, 5.0);
        map2.put(estacaoDest, 8.0);
        assertEquals(expResult.getEstacaoDest(), result.getEstacaoDest());
        assertEquals(expResult.getEstacaoOrig(), result.getEstacaoOrig());
        assertEquals(expResult.getTempoInstanteInicial(), result.getTempoInstanteInicial(),0.00);
        assertEquals(8.0, result.getTempoViagem(),0.01);
        assertEquals(map1, result.getMapaEstacoesLinha());
        assertEquals(map2, result.getMapaEstacoesTempoCorrespondente());

    }

    /**
     * Test of colocarValoresMapTempo method, of class
     * PercursoComEstacoesIntermedias.
     */
    @Test
    public void testColocarValoresMapTempo() {
        System.out.println("colocarValoresMapTempo");
        LinkedList<Estacao> listaTemporario = new LinkedList<>();
        listaTemporario.add(estacaoDest);
        listaTemporario.add(estacao3);
        listaTemporario.add(estacaoOrig);
        double tempo = 0.0;
        Percurso percurso = new Percurso(estacaoOrig, estacaoDest, tempo);
        PercursoComEstacoesIntermedias instance = new PercursoComEstacoesIntermedias();
        Map<Estacao, Double> expResult = new LinkedHashMap<>();
        expResult.put(estacaoOrig, tempo);
        expResult.put(estacao3, 3.0);
        expResult.put(estacaoDest, 9.0);
        Map<Estacao, Double> result = instance.colocarValoresMapTempo(metro, listaTemporario, tempo, estacaoDest, percurso);
        assertEquals(expResult, result);
    }

    /**
     * Test of colocarValoresMapLinhas method, of class
     * PercursoComEstacoesIntermedias.
     */
    @Test
    public void testColocarValoresMapLinhas() {
        System.out.println("colocarValoresMapLinhas");
        LinkedList<Estacao> listaTemporario = new LinkedList<>();
        listaTemporario.add(estacaoDest);
        listaTemporario.add(estacao3);
        listaTemporario.add(estacaoOrig);
        PercursoComEstacoesIntermedias instance = new PercursoComEstacoesIntermedias();
        Map<Estacao, String> expResult = new LinkedHashMap<>();
        expResult.put(estacaoOrig, "1");
        expResult.put(estacao3, "1");
        expResult.put(estacaoDest, "1");
        Map<Estacao, String> result = instance.colocarValoresMapLinhas(metro, listaTemporario, estacaoDest);
        assertEquals(expResult, result);
    }

    /**
     * Test of getListTemporaria method, of class
     * PercursoComEstacoesIntermedias.
     */
    @Test
    public void testGetListTemporaria() {
        System.out.println("getListTemporaria");
        List<LinkedList<Estacao>> allPath = GraphAlgorithms.allPaths(graph, estacaoOrig, estacaoDest);
        List<Estacao> listaEstacoesIntermedias = new ArrayList<>();
        listaEstacoesIntermedias.add(estacao3);
        listaEstacoesIntermedias.add(estacao4);
        PercursoComEstacoesIntermedias instance = new PercursoComEstacoesIntermedias();
        LinkedList<Estacao> expResult = new LinkedList<>();
        expResult.add(estacaoDest);
        expResult.add(estacao3);
        expResult.add(estacao4);
        expResult.add(estacaoOrig);
        LinkedList<Estacao> result = instance.getListTemporaria(allPath, metro, listaEstacoesIntermedias);
        assertEquals(expResult.get(0), result.get(0));
        assertEquals(expResult.get(1), result.get(1));
        assertEquals(expResult.get(2), result.get(2));
        assertEquals(expResult.get(3), result.get(3));

    }

    /**
     * Test of tempoMenor method, of class PercursoComEstacoesIntermedias.
     */
    @Test
    public void testTempoMenor() {
        System.out.println("tempoMenor");
        LinkedList<Estacao> lista = new LinkedList<>();
        lista.add(estacao8);
        lista.add(estacao7);
        lista.add(estacaoDest);
        PercursoComEstacoesIntermedias instance = new PercursoComEstacoesIntermedias();
        double expResult = 13.0;
        double result = instance.tempoMenor(lista, metro);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of caminhoMinimoLinhas method, of class
     * PercursoComEstacoesIntermedias.
     */
    @Test
    public void testCaminhoMinimoLinhas() {
        System.out.println("caminhoMinimoLinhas");
        double tempoInstanteInicial = 0.0;
        PercursoComEstacoesIntermedias instance = new PercursoComEstacoesIntermedias();
        Percurso expResult = new Percurso(estacaoOrig, estacaoDest, tempoInstanteInicial);
        Map<Estacao, String> map1 = new LinkedHashMap<>();
        map1.put(estacaoOrig, "2");
        map1.put(estacao2, "2");
        map1.put(estacao5, "2");
        map1.put(estacaoDest, "2");
        Map<Estacao, Double> map2 = new LinkedHashMap<>();
        map2.put(estacaoOrig, 0.0);
        map2.put(estacao2, 1.0);
        map2.put(estacao5, 5.0);
        map2.put(estacaoDest, 8.0);
        Percurso result = instance.caminhoMinimoLinhas(estacaoOrig, estacaoDest, tempoInstanteInicial, metro);
        assertEquals(expResult.getEstacaoDest(), result.getEstacaoDest());
        assertEquals(expResult.getEstacaoOrig(), result.getEstacaoOrig());
        assertEquals(expResult.getTempoInstanteInicial(), result.getTempoInstanteInicial(),0.1);
        assertEquals(map1, result.getMapaEstacoesLinha());
        assertEquals(map2, result.getMapaEstacoesTempoCorrespondente());
    }

    /**
     * Test of getListTemporariaLinhas method, of class
     * PercursoComEstacoesIntermedias.
     */
    @Test
    public void testGetListTemporariaLinhas() {
        System.out.println("getListTemporariaLinhas");
        List<LinkedList<Estacao>> allPath = GraphAlgorithms.allPaths(graph, estacaoOrig, estacaoDest);
        PercursoComEstacoesIntermedias instance = new PercursoComEstacoesIntermedias();
        LinkedList<Estacao> expResult = new LinkedList<>();
        expResult.add(estacaoDest);
        expResult.add(estacao5);
        expResult.add(estacao2);
        expResult.add(estacaoOrig);
        LinkedList<Estacao> result = instance.getListTemporariaLinhas(allPath, metro);
        System.out.println(result.size());
        assertEquals(expResult, result);
    }

}
