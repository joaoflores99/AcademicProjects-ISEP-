/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto2_esinf;

import Generic.GraphAlgorithms;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author morei
 */
public class PercursoComEstacoesIntermedias {

    /**
     * metodod para returnar um percurso que contenha determinadas estacoes
     *
     * @param estacaoOrig estacao origem do percurso
     * @param estacaoDest estacao destino do percurso
     * @param tempoInstanteInicial do percurso
     * @param listaEstacoesIntermedias que contem todas as estacoes que o utente
     * necessitade de passar
     * @param metro contem o grafo
     * @return o percurso
     */
    public Percurso caminhoMinimoComEstacoesIntermedias(Estacao estacaoOrig, Estacao estacaoDest, double tempoInstanteInicial, List<Estacao> listaEstacoesIntermedias, Metro metro) {
        Percurso percurso = new Percurso(tempoInstanteInicial, estacaoOrig, estacaoDest);
        List<LinkedList<Estacao>> allPath = GraphAlgorithms.allPaths(metro.getMetro(), estacaoOrig, estacaoDest);
        if (allPath == null || allPath.isEmpty()) {
            return null;
        }
        LinkedList<Estacao> listaTemporario = getListTemporaria(allPath, metro, listaEstacoesIntermedias);

        Map<Estacao, String> mapaEstacaoLinha = colocarValoresMapLinhas(metro, listaTemporario, estacaoDest);
        percurso.setMapaEstacoesLinha(mapaEstacaoLinha);

        Map<Estacao, Double> mapaEstacoesTempo = colocarValoresMapTempo(metro, listaTemporario, tempoInstanteInicial, estacaoDest, percurso);
        percurso.setMapaEstacoesTempoCorrespondente(mapaEstacoesTempo);

        return percurso;
    }

    /**
     * colocar num map as estacoes e os valores de tempo, em relacao ao instante
     * inicial, que em eles se encontravam
     *
     * @param metro contem o grafo
     * @param listaTemporario com estacoes onde ele necessita de passar
     * @param tempo inicial
     * @param estacaoDest
     * @param percurso
     * @return map com as estacoes e tempos
     */
    public Map<Estacao, Double> colocarValoresMapTempo(Metro metro, LinkedList<Estacao> listaTemporario, double tempo, Estacao estacaoDest, Percurso percurso) {
        Map<Estacao, Double> mapaEstacoesTempo = new LinkedHashMap<>();
        double tempoInicial = tempo;
        mapaEstacoesTempo.put(listaTemporario.get((listaTemporario.size() - 1)), tempo);
        for (int i = listaTemporario.size() - 1; i > 0; i--) {
            tempo = tempo + (metro.getMetro().getEdge(listaTemporario.get(i - 1), listaTemporario.get(i)).getWeight());
            mapaEstacoesTempo.put(listaTemporario.get(i - 1), tempo);
        }
        percurso.setTempoViagem(tempo - tempoInicial);
        return mapaEstacoesTempo;
    }

    /**
     * colocar num map as estacoes e as linhas em que eles se encontravam
     *
     * @param metro contem o grafo
     * @param listaTemporario lista com as estacoes
     * @param estacaoDest estacao onde termina a viagem
     * @return
     */
    public Map<Estacao, String> colocarValoresMapLinhas(Metro metro, LinkedList<Estacao> listaTemporario, Estacao estacaoDest) {
        Map<Estacao, String> mapaEstacaoLinha = new LinkedHashMap<>();
        Estacao es = null;
        for (int i = listaTemporario.size() - 1; i > 0; i--) {
            mapaEstacaoLinha.put(listaTemporario.get(i), metro.getMetro().getEdge(listaTemporario.get(i - 1), listaTemporario.get(i)).getElement());
            es = listaTemporario.get(i);
        }
        mapaEstacaoLinha.put(estacaoDest, metro.getMetro().getEdge(es, estacaoDest).getElement());
        return mapaEstacaoLinha;
    }

    /**
     * metodo para obter uma lista de estacoes que contenha as estacoes
     * obrigatorias
     *
     * @param allPath todos os caminhos de uma estaca a outra
     * @param metro contem o grafo
     * @param listaEstacoesIntermedias estacoes obrigatorias
     * @return a lista pretendida
     */
    public LinkedList<Estacao> getListTemporaria(List<LinkedList<Estacao>> allPath, Metro metro, List<Estacao> listaEstacoesIntermedias) {
        double peso = Double.MAX_VALUE;
        LinkedList<Estacao> listaTemporario = new LinkedList<>();
        for (LinkedList<Estacao> lista : allPath) {
            double tempoMetodo = tempoMenor(lista, metro);
            System.out.println("Tempo" + tempoMetodo);
            if (lista.containsAll(listaEstacoesIntermedias) && tempoMetodo < peso && tempoMetodo != 0.0) {
                peso = tempoMetodo;
                listaTemporario = lista;
            }
        }
        return listaTemporario;
    }

    /**
     * metodo que returna o tempo de percurer um caminho
     *
     * @param lista representa o caminho
     * @param metro contem o grafo
     * @return
     */
    public double tempoMenor(LinkedList<Estacao> lista, Metro metro) {
        double peso = 0.0;
        int tamanho = lista.size();
        for (int i = 0; i <= tamanho - 2; i++) {
            peso = peso + metro.getMetro().getEdge(lista.get(i + 1), lista.get(i)).getWeight();
        }
        return peso;
    }

    /**
     * metodo para obter o percurso com menor mudança de linhas
     *
     * @param estacaoOrig estacoa de origem no percurso
     * @param estacaoDest
     * @param tempoInstanteInicial
     * @param metro
     * @return
     */
    public Percurso caminhoMinimoLinhas(Estacao estacaoOrig, Estacao estacaoDest, double tempoInstanteInicial, Metro metro) {
        Percurso percurso = new Percurso(tempoInstanteInicial, estacaoOrig, estacaoDest);
        List<LinkedList<Estacao>> allPath = GraphAlgorithms.allPaths(metro.getMetro(), estacaoOrig, estacaoDest);
        if (allPath == null || allPath.isEmpty()) {
            return null;
        }
        LinkedList<Estacao> listaTemporario = getListTemporariaLinhas(allPath, metro);
        Map<Estacao, String> mapaEstacaoLinha = colocarValoresMapLinhas(metro, listaTemporario, estacaoDest);
        percurso.setMapaEstacoesLinha(mapaEstacaoLinha);

        Map<Estacao, Double> mapaEstacoesTempo = colocarValoresMapTempo(metro, listaTemporario, tempoInstanteInicial, estacaoDest, percurso);
        percurso.setMapaEstacoesTempoCorrespondente(mapaEstacoesTempo);
        return percurso;

    }

    /**
     * metodo que retorna a lista que passa por menos estacoes
     *
     * @param allPath todos os caminhos
     * @param metro contem o grafo
     * @return
     */
    public LinkedList<Estacao> getListTemporariaLinhas(List<LinkedList<Estacao>> allPath, Metro metro) {
        LinkedList<Estacao> temporaria = new LinkedList<>();
        LinkedList<String> tem = new LinkedList<>();
        int tamanhoTemporaria = Integer.MAX_VALUE;
        for (LinkedList<Estacao> lista : allPath) {
            for (int i = 0; i < lista.size() - 1; i++) {
                String linha = metro.getMetro().getEdge(lista.get(i), lista.get(i + 1)).getElement();
                if (!tem.contains(linha)) {
                    tem.add(linha);
                }
            }
            if (tem.size() < tamanhoTemporaria && !tem.isEmpty()) {
                temporaria = lista;
            }
            tamanhoTemporaria = tem.size();
        }
        return temporaria;
    }

}
