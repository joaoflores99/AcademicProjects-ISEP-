/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto2_esinf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author morei
 */
public class LerLinhaEstacoes {

    private static Scanner sc;

    /**
     * metodo de entrada no ler por criar o scanner
     *
     * @param nome identifica o ficheiro
     * @param metro cotem o grafo
     * @return
     * @throws FileNotFoundException
     */
    public boolean ler(String nome, Metro metro) throws FileNotFoundException {
        sc = new Scanner(new File(nome));
        if (nome.equals("connections.csv")) {
            lerLinha(sc, 3, metro);
        }
        if (nome.equals("coordinates.csv")) {
            lerLinha(sc, 1, metro);
        }
        if (nome.equals("lines_and_stations.csv")) {
            lerLinha(sc, 2, metro);
        }

        return true;
    }

    /**
     * passar linha a linha conforme a instanciação adequada
     *
     * @param sc scanner acima criado
     * @param i varivel
     * @param metro contem o grafo
     * @return
     */
    public boolean lerLinha(Scanner sc, int i, Metro metro) {
        if (!sc.hasNext()) {
            return false;
        } else {
            if (i == 1) {
                Estacao estacao = criarEstacao(sc.nextLine());
                metro.getMetro().insertVertex(estacao);
                lerLinha(sc, i, metro);
            }
            if (i == 2) {
                lines_and_stations(sc.nextLine(), metro);
                lerLinha(sc, i, metro);
            }
            if (i == 3) {
                ligacoes(sc.nextLine(), metro);
                lerLinha(sc, i, metro);
            }
            return true;
        }
    }

    /**
     * criar a estacao com uma linha separada por ;
     *
     * @param nextLine linha com os parametro da estacao
     * @return
     */
    public static Estacao criarEstacao(String nextLine) {
        String tmp[] = nextLine.split(";");
        Estacao estacao = null;
        try {
            estacao = new Estacao(tmp[0], Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]));
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        return estacao;
    }

    /**
     * cria ligacoes entre estacoes
     *
     * @param nextLine linha que contem os parametros para criar as ligacoes
     * @param metro contem o grafo
     */
    public void ligacoes(String nextLine, Metro metro) {
        String tmp[] = nextLine.split(";");
        metro.getMetro().insertEdge(metro.getEstacao(tmp[1]), metro.getEstacao(tmp[2]), tmp[0], Double.parseDouble(tmp[3]));
    }

    /**
     * colocar as linhas que cada estacao tem
     *
     * @param nextLine contem a estacao e linhas
     * @param metro
     */
    public static void lines_and_stations(String nextLine, Metro metro) {
        String tmp[] = nextLine.split(";");
        Estacao estacao = metro.getEstacao(tmp[1]);
        if (estacao != null) {
            estacao.addLinha(tmp[0]);
        }

    }
}
