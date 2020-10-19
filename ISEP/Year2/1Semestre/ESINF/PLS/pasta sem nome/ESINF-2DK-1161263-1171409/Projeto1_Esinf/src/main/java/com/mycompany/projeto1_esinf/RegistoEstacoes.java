/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto1_esinf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author jose
 */
public class RegistoEstacoes {

    private DoublyLinkedList<Estacao> listaEstacoes;

    public RegistoEstacoes() {
        listaEstacoes = new DoublyLinkedList<>();
    }

    public RegistoEstacoes(DoublyLinkedList<Estacao> listaEstacoes) {
        this.listaEstacoes = listaEstacoes;
    }
    
    public void setRegisto( DoublyLinkedList<Estacao> listaEstacoes){
        this.listaEstacoes=listaEstacoes;
    }

    public boolean addEstacao(Estacao e) {
        int i = procuraEstacao(e);
        if (i == -1) {
            listaEstacoes.addLast(e);
            return true;
        }
        return false;
    }

    public int procuraEstacao(Estacao e) {
        int c = 0;
        Iterator<Estacao> it = Singleton.getInstance().getRe().getListaEstacao().iterator();
        while (it.hasNext()) {

            if (e.getNumEstacao() == it.next().getNumEstacao()) {
                return c;
            }
            c++;
        }
        return -1;
    }

    public static Estacao getEstacao(int numEstacao) {
        int c = 0;
        Iterator<Estacao> it = Singleton.getInstance().getRe().getListaEstacao().iterator();
        while (it.hasNext()) {
            Estacao e = it.next();
            if (e.getNumEstacao() == numEstacao) {
                return e;
            }
        }
        return null;
    }

    public DoublyLinkedList<Estacao> getListaEstacao() {
        return listaEstacoes;
    }
}