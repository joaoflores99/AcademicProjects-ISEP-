/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto2_esinf;

import Generic.Graph;
import java.io.FileNotFoundException;

/**
 *
 * @author morei
 */
public class Metro {

    private Graph<Estacao, String> metro;

    public Metro() throws FileNotFoundException {
        metro = new Graph<>(true);
    }

    public Graph<Estacao, String> getMetro() {
        return metro;
    }

    public void setMetro(Graph<Estacao, String> metro) {
        this.metro = metro;
    }

    public Estacao getEstacao(String estacao) {
        for (Estacao e : this.metro.vertices()) {
            if (e.getNome().equals(estacao)) {
                return e;
            }
        }
        return null;
    }

    
}
