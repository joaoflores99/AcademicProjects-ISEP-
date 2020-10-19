/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto1_esinf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jose
 */
public class Linha {

    private Map<String, DoublyLinkedList<Estacao>> m;

    public Linha() {
        m = new HashMap<>();
    }

    public Linha(Map<String, DoublyLinkedList<Estacao>> m) {
        this.m = m;
    }

    public Map<String, DoublyLinkedList<Estacao>> getM() {
        return m;
    }

    public void setM(Map<String, DoublyLinkedList<Estacao>> m) {
        this.m = m;
    }

    public void createLinha() {
        DoublyLinkedList<Estacao> le = Singleton.getInstance().getRe().getListaEstacao();
        m.put("Linha A", le);
    }
}
