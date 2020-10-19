/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto1_esinf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jose
 */
public class Viagem {

    private Bilhete bilhete;
    private int estacao_origem;
    private int estacao_destino;

    public Viagem(Bilhete b, int estacao_origem, int estacao_destino) {
        this.bilhete = b;
        this.estacao_origem = estacao_origem;
        this.estacao_destino = estacao_destino;
    }

    public Viagem(Viagem v) {

        this.estacao_origem = v.estacao_origem;
        this.estacao_destino = v.estacao_destino;
    }
// VER MELHOR .... O MELHOR PARA ESTES DOIS SETS ERA ALTERAR NO BILHETE DIRETAMENTE LOGO  O SET AQUI PODERÁ SER DESNECESSÁRIO

    public Bilhete getBilhete() {
        return bilhete;
    }

    public int getEstacao_origem() {
        return estacao_origem;
    }

    public int getEstacao_destino() {
        return estacao_destino;
    }

    public void setBilhete(Bilhete bilhete) {
        this.bilhete = bilhete;
    }

    public void setEstacao_origem(int estacao_origem) {
        this.estacao_origem = estacao_origem;
    }

    public void setEstacao_destino(int estacao_destino) {
        this.estacao_destino = estacao_destino;
    }

    @Override
    public String toString() {
        return String.format("Detalhes do Bilhete:\n"
                + "Número do Bilhete: "
                + "Tipo de Biilhete:"
                + "Estação de Origem:"
                + "Estação de Destino:",
                bilhete.getNumeroBilhete() + bilhete.getTipoBilhete() + getEstacao_origem() + getEstacao_destino());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Viagem other = (Viagem) obj;
        if (this.estacao_origem != other.estacao_origem) {
            return false;
        }
        if (this.estacao_destino != other.estacao_destino) {
            return false;
        }
        if (!Objects.equals(this.bilhete, other.bilhete)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.bilhete);
        hash = 13 * hash + this.estacao_origem;
        hash = 13 * hash + this.estacao_destino;
        return hash;
    }

}
