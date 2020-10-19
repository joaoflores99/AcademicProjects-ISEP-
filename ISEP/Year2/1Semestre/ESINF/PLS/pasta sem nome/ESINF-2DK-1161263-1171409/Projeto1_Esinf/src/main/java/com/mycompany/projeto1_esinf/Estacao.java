/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto1_esinf;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jose
 */
public class Estacao implements Comparable<Estacao>{

    private int numEstacao;
    private String descricao;
    private String zona;
    private List<Bilhete> validadoEntrada;
    private List<Bilhete> validadoSaida;

    private final int SEM_NUM_ESTACAO = -1;
    private final String SEM_DESCRICAO = "Sem descricao";
    private final String SEM_ZONA = "Sem zona";

    public Estacao() {
        numEstacao = SEM_NUM_ESTACAO;
        descricao = SEM_DESCRICAO;
        zona = SEM_ZONA;
        validadoEntrada = new ArrayList<>();
        validadoSaida = new ArrayList<>();
    }

    public Estacao(int numEstacao, String descricao, String zona, List<Bilhete> validadoEntrada, List<Bilhete> validadoSaida) {
        setDescricao(descricao);
        setNumEstacao(numEstacao);
        setZona(zona);
        setValidadoEntrada(validadoEntrada);
        setValidadoSaida(validadoSaida);
    }

    public Estacao(int numEstacao, String descricao, String zona) {
        setDescricao(descricao);
        setNumEstacao(numEstacao);
        setZona(zona);
        validadoEntrada = new ArrayList<>();
        validadoSaida = new ArrayList<>();
    }

    public Estacao(Estacao ob) {
        numEstacao = ob.getNumEstacao();
        descricao = ob.getDescricao();
        zona = ob.getZona();
        validadoEntrada = ob.getValidadoEntrada();
        validadoSaida = ob.getValidaoSaida();
    }

    public int getNumEstacao() {
        return numEstacao;
    }

    public String getZona() {
        return zona;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Bilhete> getValidadoEntrada() {
        return validadoEntrada;
    }

    public List<Bilhete> getValidaoSaida() {
        return validadoSaida;
    }

    public void setDescricao(String descricao) {
        if(descricao.trim().isEmpty()){
            throw new IllegalArgumentException("Descricao invalida");
        }
        this.descricao = descricao;
    }

    public void setValidadoEntrada(List<Bilhete> validadoEntrada) {
        this.validadoEntrada = validadoEntrada;
    }

    public void setValidadoSaida(List<Bilhete> validadoSaida) {
        this.validadoSaida = validadoSaida;
    }

    public void setNumEstacao(int numEstacao) {
        if(numEstacao<0){
            throw new IllegalArgumentException("Num estacao nao pode ser negativo");
        }
        this.numEstacao = numEstacao;
    }

    public void setZona(String zona) {
        if(zona.trim().isEmpty()){
            throw new IllegalArgumentException("Zona invalida");
        }
        this.zona = zona;
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == this) {
            return true;
        }
        if (ob == null) {
            return false;
        }
        if (ob.getClass() != (this.getClass())) {
            return false;
        }
        Estacao e = (Estacao) ob;
        return (e.getDescricao().equals(this.getDescricao()) && e.getNumEstacao() == this.getNumEstacao() && e.getZona().equals(this.getNumEstacao()));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.numEstacao;
        hash = 47 * hash + Objects.hashCode(this.descricao);
        hash = 47 * hash + Objects.hashCode(this.zona);
        hash = 47 * hash + Objects.hashCode(this.validadoEntrada);
        hash = 47 * hash + Objects.hashCode(this.validadoSaida);
        return hash;
    }

    @Override
    public String toString() {
        return "numEstacao=" + numEstacao + ", descricao=" + descricao + ", zona=" + zona;
    }

    public boolean lerEstacao() throws FileNotFoundException{
        Ler.ler("fx_estacoes.txt");
        return true;
    }
    
    @Override
    public int compareTo(Estacao o) {
        return (this.getNumEstacao()- o.getNumEstacao());
    }
}
