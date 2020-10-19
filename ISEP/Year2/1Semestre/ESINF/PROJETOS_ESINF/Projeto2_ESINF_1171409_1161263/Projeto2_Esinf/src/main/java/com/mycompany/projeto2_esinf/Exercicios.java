/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto2_esinf;

import Generic.GraphAlgorithms;
import Generic.Graph;
import Generic.Edge;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author joaoflores
 */
public class Exercicios {

 /**
     * checks if the graph es connected
     *
     * @param metro graph of stations 
     * @return null if the grph is connected, otherwise return a list of graphs 
     */
   public static List<Graph<Estacao, String>> a2_Conexo(Metro metro) {
        Graph<Estacao, String> graph = metro.getMetro();
        boolean[] visited = new boolean[graph.numVertices()];

        List<Graph<Estacao, String>> componentes = new ArrayList<>();
        Estacao[] keysEstacao = metro.getMetro().allkeyVerts();
        Estacao e = keysEstacao[0];
        LinkedList<Estacao> bfsresult = GraphAlgorithms.BreadthFirstSearch(graph, e);
        if (bfsresult.size() == graph.numVertices() && componentes.isEmpty()) {
            return null;
        } else {
            Graph<Estacao, String> auxiliarGraph = new Graph(true);
            for (Estacao v : bfsresult) {
                visited[graph.getKey(v)] = bfsresult.contains(v);
                auxiliarGraph.insertVertex(v);
            }
            LinkedList<Estacao> notVisited = a2_notVisited(visited, graph);
            Iterator<Estacao> it = notVisited.iterator();
            while (notVisited.size() > 0) {
                a2_visited(bfsresult, it, auxiliarGraph, visited, graph);
                if (!componentes.contains(auxiliarGraph)) {
                    componentes.add(auxiliarGraph);
                }
               
                notVisited = a2_notVisited(visited, graph);
                if (notVisited.size() > 0) {
                    auxiliarGraph = new Graph(true);
                    Estacao first = notVisited.getFirst();
                    auxiliarGraph.insertVertex(first);
                    visited[graph.getKey(first)] = true;
                    bfsresult = GraphAlgorithms.BreadthFirstSearch(graph, first);
                    for (Estacao v : bfsresult) {
                        visited[graph.getKey(v)] = true;          
                    }
                    it = notVisited.iterator();               
                }
            }          
            if (componentes.size() == 1) {
                return null;
            }
        }
        return componentes;
    }

   
   
   /**
     * check the visited stations 
     *
     * @param   bfs linkedList of stations 
     * @param   it linkedList of stations
     * @param   auxiliarGraph  auxiliar graph to insert stations 
     * @param   visited  array of ll the visited stations 
     * @param   graph graph of stations
     * 
     */
    private static void a2_visited(LinkedList<Estacao> bfs, Iterator<Estacao> it, Graph<Estacao, String> auxiliarGraph, boolean visited[], Graph<Estacao, String> graph) {
        while (it.hasNext()) {
            Estacao estacao = it.next();
            Iterable<Estacao> it1 = graph.adjVertices(estacao);
            for (Estacao adj : it1) {
                if (bfs.contains(adj)) {
                    auxiliarGraph.insertVertex(estacao);                    
                    visited[graph.getKey(estacao)] = true;
                }
            }
        }
    }

    /**
     * check the visited stations 
     *
     * @param   visited  array of ll the visited stations 
     * @param   graph graph of stations
     * @return  Linkedlist with the statiokns that weren't visited 
     * 
     */
    private static LinkedList<Estacao> a2_notVisited(boolean visited[], Graph<Estacao, String> graph) {
        LinkedList<Estacao> notVisited = new LinkedList<>();
        for (Estacao e : graph.vertices()) {
            if (!visited[graph.getKey(e)]) {
                notVisited.add(e);
            }
        }
        return notVisited;
    }
    
    
    /**
     * smallest number of stations usind the sortetPathEstacoes
     *
     * @param metro graph of all stations 
     * @param eOrig  the station that is the starter point 
     * @param eDest  the end 
     * @return  instance of classe percurso 
     */
    public static Percurso a4(Metro metro, Estacao eOrig, Estacao eDest) {
        Estacao vOrig=eOrig;
        double peso = 0;
        LinkedList<Estacao> shortstPath = new LinkedList<>();
        double minimoEstacoes = GraphAlgorithms.shortestPathEstacoes(metro.getMetro(), vOrig, eDest, shortstPath);
        Map<Estacao, String> mapEstacaoLinha = new LinkedHashMap<>();
        Map<Estacao, Double> mapaEstacoesTempoCorrespondente = new LinkedHashMap<>();
        for (Estacao e : shortstPath) {
            if (e != vOrig) {
            Edge<Estacao, String> edge = metro.getMetro().getEdge(vOrig, e);
            if(edge!=null){
            String linha = edge.getElement();
            peso += edge.getWeight();
            mapEstacaoLinha.put(e, linha);
            mapaEstacoesTempoCorrespondente.put(e, peso);
            }
        }
         vOrig=e;
           }
 
        Percurso p = new Percurso(eOrig, eDest, mapEstacaoLinha, mapaEstacoesTempoCorrespondente, minimoEstacoes);
        return p;
    }

    
    
    /**
     *   shortest path in values of time of stations usind the sortetPath
     *
     * @param metro graph of all stations 
     * @param eOrig  the station that is the starter point 
     * @param eDest  the end 
     * @return  instance of classe percurso 
     */
    public static Percurso a5(Metro metro, Estacao eOrig, Estacao eDest) {
        Estacao vOrig=eOrig;
        double instanteInicial=0.0;
        LinkedList<Estacao> shortstPath = new LinkedList<>();
        double minimoTempoEstacoes = GraphAlgorithms.shortestPath(metro.getMetro(), vOrig, eDest, shortstPath);
        double peso = 0;
        Map<Estacao, Double> mapEstacaoPeso = new LinkedHashMap<>();
        Map<Estacao, String> mapEstacaoLinha = new LinkedHashMap<>();
        for (Estacao e : shortstPath) {
            if (e != vOrig) {
                Edge<Estacao, String> edge = metro.getMetro().getEdge(vOrig, e);
                if(edge!=null){
                String linha = edge.getElement();
                peso += edge.getWeight();
                mapEstacaoLinha.put(e, linha);
                mapEstacaoPeso.put(e, peso);
                }
            }
            vOrig = e;
        }
        Percurso p = new Percurso(eOrig, eDest, instanteInicial, mapEstacaoLinha, mapEstacaoPeso, minimoTempoEstacoes);
        return p;
    }

}


