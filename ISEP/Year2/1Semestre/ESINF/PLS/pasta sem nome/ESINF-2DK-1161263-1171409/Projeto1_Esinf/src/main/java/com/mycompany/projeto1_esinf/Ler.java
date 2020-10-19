/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto1_esinf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jose
 */
public final class Ler {

    static Scanner sc;
    

    public static boolean ler(String nome) throws FileNotFoundException { //ON
        sc = new Scanner(new File(nome));//O1
        List<Estacao>le=new ArrayList<>();//O1
        if (nome.equals("fx_estacoes.txt")) { //01
            lerLinha(sc, 1,le);//ON
        }
        if (nome.equals("fx_viagens.txt")) {//O1
            lerLinha(sc, 2,le);//ON
        }
        return true;//O1
    }

    public static boolean lerLinha(Scanner sc, int i,List<Estacao>le) {//ON
        if (!sc.hasNext()) { //O1
            return false;//O1
        } else {//O1
            if (i == 1) {//O1
                criarEstacao(sc.nextLine(),le);//ON
                lerLinha(sc, i,le); //ON
            } else { //O1
                criarListaViagens(sc.nextLine());//O1
                lerLinha(sc, i,le); //ON
            }
            return true;//O1
        }
    }

    public static void criarEstacao(String linha,List<Estacao>le) { //ON
        String tmp[] = linha.split(",");//O1
        Estacao es;//O1
        
        try {//O1
            es = new Estacao(Integer.parseInt(tmp[0]), tmp[1], tmp[2]);//O1
            le.add(es);//O1
        } catch (NumberFormatException e) {//O1
            e.getMessage();//O1
        }
        ordenarEstacoes(le); //ON
        
    }
    
    public static void ordenarEstacoes(List<Estacao> list) {    //ON
       Collections.sort(list); //O1
       for (int i = 0; i < list.size() ; i++) {//ON
           Singleton.getInstance().getRe().addEstacao(list.get(i));//O1
       }
    }

    public static void criarListaViagens(String linha) {//O1
        String tmp[] = linha.trim().split(",");//O1
        Viagem v;//O1
        Bilhete bilhete;//O1
        try {//O1
            bilhete = new Bilhete(Integer.parseInt(tmp[0]), tmp[1]);//O1
            v = new Viagem(bilhete, Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3]));//O1
            Estacao a = RegistoEstacoes.getEstacao(Integer.parseInt(tmp[3]));//O1
            getListaValidacoes(a, bilhete, Integer.parseInt(tmp[2]), 0);//O1
            Estacao b = RegistoEstacoes.getEstacao(Integer.parseInt(tmp[3]));//O1
            getListaValidacoes(b, bilhete, Integer.parseInt(tmp[2]), 1);//O1
            Singleton.getInstance().getRv().getListaViagens().add(v);//O1
        } catch (IllegalArgumentException e) {//O1
            e.getMessage();//O1
        }
    }

    public static boolean getListaValidacoes(Estacao e, Bilhete b, int estacao, int i) { //O1
        List<Bilhete> lista; //O1
        if (i == 0) { //01
            lista = e.getValidadoEntrada(); //O1
            lista.add(b); //O1
            e.setValidadoEntrada(lista);//O1
        } else {//O1
            lista = e.getValidaoSaida();//O1
            lista.add(b);//O1
            e.setValidadoSaida(lista);//O1
        }
        return true;//O1
    }

}
