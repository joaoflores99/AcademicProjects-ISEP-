/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto1_esinf;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaoflores
 */
public class RegistoViagens {

    private List<Viagem> listaViagens;

    public RegistoViagens() {
        listaViagens = new ArrayList<>();
    }

    public RegistoViagens(List<Viagem> listaEstacoes) {
        this.listaViagens = listaEstacoes;
    }

    public boolean addViagem(Viagem v) {
        int i = procuraViagem(v);
        if (i == -1) {
            listaViagens.add(v);
            return true;
        }
        return false;
    }

    public int procuraViagem(Viagem v) {
        for (int i = 0; i < listaViagens.size(); i++) {
            if (listaViagens.get(i).equals(v)) {
                return i;
            }
        }
        return -1;
    }

    public boolean remove(Viagem v) {
        listaViagens.remove(v);
        return true;
    }

    public List<Viagem> getListaViagens() {
        return listaViagens;
    }

//public Viagem getViagem(int numBilhete) {
//        int num = 0;
//        Iterator<Viagem> it = Singleton.getInstance().getRv().getListaViagens().iterator();
//        while (it.hasNext()) {
//            Viagem v = it.next();
//            if (v.getNumeroBilhete() == numBilhete) {
//                return v;
//            }
//            num++;
//        }
//        return null;
//    }
//
//    public String getZona(int numBilhete) {
//        Viagem v = Singleton.getInstance().getRv().getViagem(numBilhete);
//        return v.getTipoBilhete();
//    }

//    public int getNumZonas(int estacaoOrigem, int estacaoDestino) {
//        String zona = "";
//        if (qualMaior(estacaoOrigem, estacaoDestino)) {
//            for (int i = estacaoOrigem; i < estacaoDestino; i++) {
//                  
//                        }
//        }
//    }
//
//    public boolean qualMaior(int estacaoOrigem, int estacaoDestino) {
//        return estacaoOrigem < (estacaoDestino);
//    }
    
}
