/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto2_esinf;

import Generic.Graph;
import Generic.Edge;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
 * @author joaoflores
 */
public class ExerciciosTest {

    private Metro metro;
    Graph<Estacao, String> completeMap = new Graph<>(true);
    Graph<Estacao, String> incompleteMap = new Graph<>(true);

    public ExerciciosTest() {
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

        Estacao porto = new Estacao("Porto", 56, 77);
        Estacao braga = new Estacao("Braga", 34, 55);
        Estacao vilaReal = new Estacao("Vila Real", 12, 10);
        Estacao aveiro = new Estacao("Aveiro", 5, 2);
        Estacao coimbra = new Estacao("Coimbra", 1, 6);
        Estacao leiria = new Estacao("Leiria", 34, 14);
        Estacao viseu = new Estacao("Viseu", 33, 22);
        Estacao guarda = new Estacao("Guarda", 33, 3);
        Estacao casteloBranco = new Estacao("Castelo Branco", -58, 11);
        Estacao lisboa = new Estacao("Lisboa", -4, -3);
        Estacao faro = new Estacao("Faro", 33, -24);

        completeMap.insertVertex(porto);
        completeMap.insertVertex(braga);
        completeMap.insertVertex(vilaReal);
        completeMap.insertVertex(aveiro);
        completeMap.insertVertex(coimbra);
        completeMap.insertVertex(leiria);

        completeMap.insertVertex(viseu);
        completeMap.insertVertex(guarda);
        completeMap.insertVertex(casteloBranco);
        completeMap.insertVertex(lisboa);
        completeMap.insertVertex(faro);

        completeMap.insertEdge(porto, aveiro, "A1", 75.0);
        completeMap.insertEdge(aveiro, porto, "A1", 75.0);
        completeMap.insertEdge(braga, porto, "A3", 60.0);
        completeMap.insertEdge(porto, vilaReal, "A4", 100.0);
        completeMap.insertEdge(vilaReal, porto, "A4", 100.0);
        completeMap.insertEdge(viseu, guarda, "A25", 75.0);
        completeMap.insertEdge(guarda, viseu, "A25", 75.0);
        completeMap.insertEdge(guarda, casteloBranco, "A23", 100.0);
        completeMap.insertEdge(casteloBranco, guarda, "A23", 100.0);
        completeMap.insertEdge(aveiro, coimbra, "A1", 60.0);
        completeMap.insertEdge(coimbra, aveiro, "A1", 60.0);
        completeMap.insertEdge(coimbra, lisboa, "A1", 200.0);
        completeMap.insertEdge(lisboa, coimbra, "A1", 200.0);
        completeMap.insertEdge(coimbra, leiria, "A34", 80.0);
        completeMap.insertEdge(leiria, coimbra, "A34", 80.0);
        completeMap.insertEdge(aveiro, leiria, "A17", 120.0);
        completeMap.insertEdge(leiria, aveiro, "A17", 120.0);
        completeMap.insertEdge(leiria, lisboa, "A8", 150.0);
        completeMap.insertEdge(lisboa, leiria, "A8", 150.0);

        completeMap.insertEdge(aveiro, viseu, "A25", 85.0);
        completeMap.insertEdge(leiria, casteloBranco, "A23", 170.0);
        completeMap.insertEdge(lisboa, faro, "A2", 280.0);

        incompleteMap = completeMap.clone();

        incompleteMap.removeEdge(aveiro, viseu);
        incompleteMap.removeEdge(leiria, casteloBranco);
        incompleteMap.removeEdge(lisboa, faro);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of a2_Conexo method, of class Exercicios.
     */
    @Test
    public void testA2_Not_Conexo() {
        System.out.println("a2_Conexo");
        metro.setMetro(incompleteMap);
        List<Graph<Estacao, String>> expResult = new ArrayList<>();
        Graph<Estacao, String> aux = new Graph(true);

        aux.insertVertex(new Estacao("Porto", 56, 77));
        aux.insertVertex(new Estacao("Aveiro", 5, 2));
        aux.insertVertex(new Estacao("Vila Real", 12, 10));
        aux.insertVertex(new Estacao("Coimbra", 1, 6));
        aux.insertVertex(new Estacao("Leiria", 34, 14));
        aux.insertVertex(new Estacao("Lisboa", -4, -3));
        aux.insertVertex(new Estacao("Braga", 34, 55));
        expResult.add(aux);

        Graph<Estacao, String> aux2 = new Graph(true);
        aux2.insertVertex(new Estacao("Viseu", 33, 22));
        aux2.insertVertex(new Estacao("Guarda", 33, 3));
        aux2.insertVertex(new Estacao("Castelo Branco", -58, 11));
        expResult.add(aux2);

        Graph<Estacao, String> aux3 = new Graph(true);
        aux3.insertVertex(new Estacao("Faro", 33, -24));
        expResult.add(aux3);

        List<Graph<Estacao, String>> result = Exercicios.a2_Conexo(metro);

        assertEquals(expResult, result);
    }

    @Test
    public void testA2_Conexo() {
        System.out.println("a2_Conexo");
        metro.setMetro(completeMap);
        List<Graph<Estacao, String>> expResult = null;
        List<Graph<Estacao, String>> result = Exercicios.a2_Conexo(metro);
        assertEquals(expResult, result);
    }

    @Test
    public void testA4() {
        System.out.println("a4_ShortestPath_Numero_Estacoes");

        double peso=0.0;
 metro.setMetro(completeMap);
        Map<Estacao, String> mapEstacaoLinha = new LinkedHashMap<>();
        Map<Estacao, Double> mapaEstacoesTempoCorrespondente = new LinkedHashMap<>();

        List<Estacao> list = new ArrayList<>();
        
        Estacao porto = new Estacao("Porto", 56, 77);
        Estacao braga = new Estacao("Braga", 34, 55);
        Estacao aveiro = new Estacao("Aveiro", 5, 2);
        Estacao coimbra = new Estacao("Coimbra", 1, 6);
        list.add(braga);
        list.add(porto);
        list.add(aveiro);
        list.add(coimbra);
        Estacao orig = braga;
        Estacao dest = coimbra;
        
        for(Estacao e: list){
            if (e != orig) {
                Edge<Estacao, String> edge = metro.getMetro().getEdge(orig, e);
                String linha = edge.getElement();
                peso+=edge.getWeight();
                    mapEstacaoLinha.put(e, linha);   
                    mapaEstacoesTempoCorrespondente.put(e,peso);
                    
                }
                 orig=e;
            }
        double minExp = 4.0;
        orig = braga;
        Percurso exp = new Percurso(orig, dest, mapEstacaoLinha,
                mapaEstacoesTempoCorrespondente, minExp); 
        Percurso result = Exercicios.a4(metro, orig, dest);
        assertEquals(exp.getEstacaoOrig(), result.getEstacaoOrig());
        assertEquals(exp.getMapaEstacoesLinha(), result.getMapaEstacoesLinha());
        assertEquals(exp.getMapaEstacoesTempoCorrespondente(), result.getMapaEstacoesTempoCorrespondente());
        assertEquals(exp.getNumeroEstacoesMinimo(), result.getNumeroEstacoesMinimo(),0.1);
        
    }
       
    
    
    @Test
    public void testA5() {
        System.out.println("a5_ShortestPath_Tempo_Estacoes");

        double tempo=0.0;
        metro.setMetro(completeMap);
        Map<Estacao, String> mapEstacaoLinha = new LinkedHashMap<>();
        Map<Estacao, Double> mapaEstacoesTempoCorrespondente = new LinkedHashMap<>();

        List<Estacao> list = new ArrayList<>();
        
        Estacao porto = new Estacao("Porto", 56, 77);
        Estacao braga = new Estacao("Braga", 34, 55);
        Estacao aveiro = new Estacao("Aveiro", 5, 2);
        Estacao coimbra = new Estacao("Coimbra", 1, 6);
        list.add(braga);
        list.add(porto);
        list.add(aveiro);
        list.add(coimbra);
        Estacao orig = braga;
        Estacao dest = coimbra;
        
        for(Estacao e: list){
            if (e != orig) {
                Edge<Estacao, String> edge = metro.getMetro().getEdge(orig, e);
                String linha = edge.getElement();
                tempo+=edge.getWeight();
                    mapEstacaoLinha.put(e, linha);   
                    mapaEstacoesTempoCorrespondente.put(e,tempo);
                    
                }
                 orig=e;
            }
        
        double minExp = 195.0;
        orig = braga;
        Percurso exp = new Percurso(orig, dest,0, mapEstacaoLinha,
                mapaEstacoesTempoCorrespondente,minExp);      
        Percurso result = Exercicios.a5(metro, orig, dest);
        assertEquals(exp.getEstacaoOrig(), result.getEstacaoOrig());
        assertEquals(exp.getMapaEstacoesLinha(), result.getMapaEstacoesLinha());
        assertEquals(exp.getMapaEstacoesTempoCorrespondente(), result.getMapaEstacoesTempoCorrespondente());
        assertEquals(exp.getTempoViagem(), result.getTempoViagem(),0.01);
        
    }
       
        

    }
