/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto1_esinf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author jose
 */
public class Funcoes {

    public static int [][] utilizadoresEstacao() {
        int[][] vec = obterUtilizadoresEstacao();
        mostrarUtilizadoresEstacao(vec);
        return vec;
    }

    public static int[][] obterUtilizadoresEstacao() {
        int vec[][] = new int[Singleton.getInstance().getRe().getListaEstacao().size()][2];
        preencherInicial(vec);
        ordena(vec);
        valor(vec);
        return vec;
    }

    public static void preencherInicial(int vec[][]) {
        Iterator<Estacao> it = Singleton.getInstance().getRe().getListaEstacao().iterator();
        int c = 0;
        while (it.hasNext()) {
            vec[c][0] = it.next().getNumEstacao();
            vec[c][1] = 0;
            c++;
        }
    }

    public static void valor(int vec[][]) {
        for (Viagem viagem : Singleton.getInstance().getRv().getListaViagens()) {
            adicionar(vec, viagem);
        }
    }

    public static void adicionar(int vec[][], Viagem viagem) {

        if (viagem.getEstacao_origem() < viagem.getEstacao_destino()) {
            for (int k = viagem.getEstacao_origem(); k <= viagem.getEstacao_destino(); k++) {
                vec[k - 1][1]++;
            }
        } else {
            for (int k = viagem.getEstacao_destino(); k <= viagem.getEstacao_origem(); k++) {
                vec[k - 1][1]++;
            }
        }
    }

    public static void mostrarUtilizadoresEstacao(int[][] u) {
        for (int i = 0; i < u.length; i++) {
            System.out.println("A estacão" + u[i][0] + " tem " + u[i][1] + " utilizadores");
        }
    }

    public static boolean ordena(int[][] vec) {
        for (int i = 0; i < vec.length - 1; i++) {
            for (int j = 0; j < vec.length - i - 1; j++) {
                if (vec[j][0] > vec[j + 1][0]) {
                    swap(vec, j, j + 1);
                }
            }
        }
        return true;
    }

    public static void swap(int[][] vec, int j, int i) {
        int temp = vec[i][0];
        vec[i][0] = vec[j][0];
        vec[j][0] = temp;
    }

    public static int numZonas(int estacaoOrigem, int estacaoDestino) {
        if (estacaoOrigem > estacaoDestino) {
            int tem;
            tem = estacaoDestino;
            estacaoDestino = estacaoOrigem;
            estacaoOrigem = tem;
        }
        List<String> auxTemp = valor(estacaoOrigem, estacaoDestino);
        return auxTemp.size();
    }

    public static List<String> valor(int estacaoOrigem, int estacaoDestino) {
        List<String> auxTemp = new ArrayList<>();
        for (int i = estacaoOrigem; i < estacaoDestino; i++) {
            if (!auxTemp.contains(RegistoEstacoes.getEstacao(i).getZona())) {
                auxTemp.add(RegistoEstacoes.getEstacao(i).getZona());
            }
        }
        return auxTemp;
    }

    public static List<Bilhete> transgressao() {
        List<Bilhete> lista = new ArrayList<>();
        for (Viagem v : Singleton.getInstance().getRv().getListaViagens()) {
            int temp = Integer.parseInt(v.getBilhete().getTipoBilhete().substring(1));
            if (numZonas(v.getEstacao_origem(), v.getEstacao_destino()) > temp) {
                System.out.println(v.getBilhete().getNumeroBilhete());
                lista.add(v.getBilhete());
            }
        }
        return lista;
    }

//    private static int getDiferencaZonas(String zonaInicio, String zonaFim) {
//        return Math.abs(Integer.parseInt(zonaFim.substring(1)) - Integer.parseInt(zonaInicio.substring(1)));
//    }
//
//    private static String getZonaEstacao(int numEstacao) {
//        Estacao e = RegistoEstacoes.getEstacao(numEstacao);
//        return e.getZona();
//    }
    
    public static int getNumZonaBilhete(String zona) {//O1
        return Integer.parseInt(zona.trim().substring(1));//O1
    }

    public static int getNumZonaEstacao(String zona) {//O1
        return Integer.parseInt(zona.trim().substring(1));//O1
    }

    public static List<Integer> conjuntoEstacoesMaximaParaTipoBilhete(String zona) {//ON
        Set<ArrayList<Estacao>> sequencias = new HashSet<>();//O1
        int numeroDasZonasPossiveisDoBilhete = getNumZonaBilhete(zona);//O1
        sequenciasEstacoes(numeroDasZonasPossiveisDoBilhete, sequencias);//ON
        List<Estacao> lista = getMaiorListaZonaPossiveisComTipoBilhete(sequencias);//ON
        List<Integer> listaComEstacoesPossiveisComBilhete = new ArrayList<>();//O1
        for (Estacao e : lista) {//ON
            listaComEstacoesPossiveisComBilhete.add(e.getNumEstacao());//O1
        }
        return listaComEstacoesPossiveisComBilhete;//O1

    }

    public static void sequenciasEstacoes(int numeroDasZonasPossiveisDoBilhete, Set<ArrayList<Estacao>> sequencias) {//ON
        List<Integer> zonas = new ArrayList<>();

        for (Estacao e : Singleton.getInstance().getRe().getListaEstacao()) {//ON
            Iterator<Estacao> it = Singleton.getInstance().getRe().getListaEstacao().iterator();//O1
            posicaoEstacao(it, e);//O1
            ArrayList<Estacao> s = new ArrayList<>();//O1
            zonas.add(getNumZonaEstacao(e.getZona()));//O1
            s.add(e);//O1
            adicionaEstacaoSequencia(it, zonas, s, numeroDasZonasPossiveisDoBilhete);//O1
            zonas = new ArrayList<>();//O1
            sequencias.add(s);//O1
        }

    }

    public static void adicionaEstacaoSequencia(Iterator<Estacao> it, List<Integer> zonas, List<Estacao> s, int numeroDasZonasPossiveisDoBilhete) {//ON
        if (it.hasNext()) {//O1
            while ((it.hasNext()) && zonas.size() != numeroDasZonasPossiveisDoBilhete || (it.hasNext()) && zonas.size() < numeroDasZonasPossiveisDoBilhete) {//ON
                Estacao e1 = it.next();//O1
                if (zonas.contains(getNumZonaEstacao(e1.getZona()))) {//O1
                    s.add(e1);//O1
                } else {//O1
                    if (zonas.size() != numeroDasZonasPossiveisDoBilhete) {//O1
                        s.add(e1);//O1
                        zonas.add(getNumZonaEstacao(e1.getZona()));//O1
                    }
                }
            }
        }
    }

    public static Iterator<Estacao> posicaoEstacao(Iterator<Estacao> it, Estacao e) {//ON
        Estacao estacao = it.next();//O1
        while (estacao != e) {//ON
            estacao = it.next();//O1
        }
        return it;//O1
    }

    public static List<Estacao> getMaiorListaZonaPossiveisComTipoBilhete(Set<ArrayList<Estacao>> sequencias) {//ON
        List<Estacao> maior = new ArrayList<>();//O1
        for (List<Estacao> listaEstacoes : sequencias) {//ON
            if (listaEstacoes.size() > maior.size()) {//O1
                maior = listaEstacoes;//O1
            }
        }
        return maior;//O1
    }

}
