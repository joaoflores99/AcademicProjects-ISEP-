/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto2_esinf;

import Generic.Graph;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author morei
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Metro metro = new Metro();
        LerLinhaEstacoes l = new LerLinhaEstacoes();
        l.ler("coordinates.csv", metro);

        l.ler("lines_and_stations.csv", metro);

        l.ler("connections.csv", metro);
        for (Estacao e : metro.getMetro().allkeyVerts()) {
            System.out.println(e.getNome());
        }
        for (Estacao e : metro.getMetro().allkeyVerts()) {
            for (String s : e.getTodasLinhas()) {
                System.out.println(s);
            }
        }
        System.out.println("Numero" + metro.getMetro().numEdges());
        System.out.println("Linhaaaaaaaaaaaaaaaaaaaaaaa " + metro.getMetro().getEdge(metro.getEstacao("La Defense"), metro.getEstacao("Esplanade de La Defense")).getElement());

        
            //System.out.println(e.getElement() + " " +cont);
            //cont++;
        
        List<Graph<Estacao, String>> aa = Exercicios.a2_Conexo(metro);
        if (aa == null) {
            System.out.println("CONEXO");
        } else {
            System.out.println("nao e conexo");
            System.out.println();
        }
        PercursoComEstacoesIntermedias p = new PercursoComEstacoesIntermedias();
        List<Estacao> listaEstacoesIntermedias = new ArrayList<>();
        listaEstacoesIntermedias.add(metro.getEstacao("Pont de Neuilly"));
        System.out.println("Leew EWYRUJHGFDTJNDVFEGSB " + metro.getMetro().edgeLessWeight(metro.getEstacao("Porte Maillot"), metro.getEstacao("Argentine")));
        Percurso per = p.caminhoMinimoComEstacoesIntermedias(metro.getEstacao("La Defense"), metro.getEstacao("Les Sablons"), 5, listaEstacoesIntermedias, metro);
        System.out.println(per.getEstacaoOrig() + "  " + per.getTempoViagem() + " ");
        Percurso pe = p.caminhoMinimoLinhas(metro.getEstacao("La Defense"), metro.getEstacao("Les Sablons"), 5, metro);
        System.out.println(pe.getEstacaoOrig());
    }

}
