/*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto2_esinf;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author joaoflores
 */
public class Percurso {

    private double tempoInstanteInicial;
    private double tempoViagem; //AQUI VAI SER ACOMULADO O TEMPO DA VIAGEM DESDE O MOMNETO DA PARTIDA ATE AO MOMENTO DE CHEGADA
    private Estacao estacaoOrig;
    private Estacao estacaoDest;

    
    // usamos LinkedHashMap uma vez que assim podemos manter a ordem de insersão.
    private Map<Estacao, String> mapaEstacoesLinha = new LinkedHashMap<>();
    private Map<Estacao, Double> mapaEstacoesTempoCorrespondente = new LinkedHashMap<>();

    private static Map<Estacao, String> MAP_ES_OMISSAO = new LinkedHashMap<>();
    private static Map<Estacao, Double> MAP_ET_OMISSAO = new LinkedHashMap<>();
    
    
    double numeroEstacoesMinimo;
    

    private static final double TEMPO_OMISSAO=0;

    public Percurso() {
    }
    
    public Percurso(Estacao estacaoOrig, Estacao estacaoDest, 
            Map<Estacao, String> mapaEstacoesLinha,Map<Estacao,Double> mapaEstacoesTempoCorrespondente,
            double  numeroEstacoesMinimo){
        
        this.estacaoOrig = estacaoOrig;
        this.estacaoDest = estacaoDest;
        this.numeroEstacoesMinimo=numeroEstacoesMinimo;
        setMapaEstacoesLinha(mapaEstacoesLinha);
        setMapaEstacoesTempoCorrespondente(mapaEstacoesTempoCorrespondente);
    }
    
    
    
    public Percurso(Estacao estacaoOrig, Estacao estacaoDest, double tempoInstanteInicial, 
            Map<Estacao, String> mapaEstacoesLinha, 
            Map<Estacao, Double> mapaEstacoesTempoCorrespondente,double tempoViagem) {
        this.estacaoOrig = estacaoOrig;
        this.estacaoDest = estacaoDest;
        this.tempoInstanteInicial=tempoInstanteInicial;
        this.tempoViagem=tempoViagem;
        setMapaEstacoesLinha(mapaEstacoesLinha);
        setMapaEstacoesTempoCorrespondente(mapaEstacoesTempoCorrespondente);

    }
    
    
    

    public Percurso(Estacao estacaoOrig, Estacao estacaoDest) {
        this.estacaoOrig = estacaoOrig;
        this.estacaoDest = estacaoDest;
        mapaEstacoesTempoCorrespondente = MAP_ET_OMISSAO;
        mapaEstacoesLinha = MAP_ES_OMISSAO;
        tempoInstanteInicial = TEMPO_OMISSAO;
    }

    public Percurso(Estacao estacaoOrig, Estacao estacaoDest, double tempoInstanteInicial) {
        this.estacaoOrig = estacaoOrig;
        this.estacaoDest = estacaoDest;
        this.tempoInstanteInicial=tempoInstanteInicial;
        mapaEstacoesTempoCorrespondente = MAP_ET_OMISSAO;
        mapaEstacoesLinha = MAP_ES_OMISSAO;

    }

    public Percurso(double tempoInstanteInicial, Estacao estacaoOrig, Estacao estacaoDest) {
        this.tempoInstanteInicial = tempoInstanteInicial;
        this.estacaoOrig = estacaoOrig;
        this.estacaoDest = estacaoDest;
    }
       

    public Percurso(Estacao estacaoOrig, Estacao estacaoDest, double tempoInstanteInicial, Map<Estacao, String> mapaEstacoesLinha, Map<Estacao, Double> mapaEstacoesTempoCorrespondente) {
        this.estacaoOrig = estacaoOrig;
        this.estacaoDest = estacaoDest;
        this.tempoInstanteInicial=tempoInstanteInicial;
        setMapaEstacoesLinha(mapaEstacoesLinha);
        setMapaEstacoesTempoCorrespondente(mapaEstacoesTempoCorrespondente);

    }

//    public void setTempoInstanteInicial(String tempoInstanteInicial) {
//        String tmp[] = tempoInstanteInicial.trim().split(":");
//        Tempo tempoInstanteInicialAUX = new Tempo(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[1]));
//        this.tempoInstanteInicial = tempoInstanteInicialAUX;
//
//    }
    
    public static Tempo getTempoString(String tempo) {
        String tmp[] = tempo.trim().split(":");
        Tempo tempoInstante = new Tempo(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]));
        return tempoInstante;

    }

    public double getTempoInstanteInicial() {
        return tempoInstanteInicial;
    }

    public double getTempoViagem() {
        return tempoViagem;
    }

    public void setTempoViagem(double tempoViagem) {
        this.tempoViagem = tempoViagem;
    }
    
    public static Tempo getAcumucaoTempo(Tempo tempo,String tempoString){
        Tempo tempo2=getTempoString(tempoString);
        Tempo soma=Tempo.somaTempos(tempo, tempo2);
        return soma;
    }

    public Estacao getEstacaoOrig() {
        return estacaoOrig;
    }

    public void setEstacaoOrig(Estacao estacaoOrig) {
        this.estacaoOrig = estacaoOrig;
    }

    public Estacao getEstacaoDest() {
        return estacaoDest;
    }

    public void setEstacaoDest(Estacao estacaoDest) {
        this.estacaoDest = estacaoDest;
    }

    public Map<Estacao, String> getMapaEstacoesLinha() {
        return mapaEstacoesLinha;
    }

    public void setMapaEstacoesLinha(Map<Estacao, String> mapaEstacoesLinha) {
        this.mapaEstacoesLinha = mapaEstacoesLinha;
    }

    public Map<Estacao, Double> getMapaEstacoesTempoCorrespondente() {
        return mapaEstacoesTempoCorrespondente;
    }

    public void setMapaEstacoesTempoCorrespondente(Map<Estacao, Double> mapaEstacoesTempoCorrespondente) {
        this.mapaEstacoesTempoCorrespondente = mapaEstacoesTempoCorrespondente;
    }
    public double getNumeroEstacoesMinimo() {
        return numeroEstacoesMinimo;
    }

    public void setNumeroEstacoesMinimo(int numeroEstacoesMinimo) {
        this.numeroEstacoesMinimo = numeroEstacoesMinimo;
    }
    
    

    @Override
    public String toString() {
        return "TempoInstanteInicial=" + tempoInstanteInicial + ", tempoViagem=" + tempoViagem + ", estacaoOrig=" + estacaoOrig + ", estacaoDest=" + estacaoDest + ", mapaEstacoesLinha=" + mapaEstacoesLinha + ", mapaEstacoesTempoCorrespondente=" + mapaEstacoesTempoCorrespondente;
    }

}
