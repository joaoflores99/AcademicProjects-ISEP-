/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto2_esinf;

/**
 *
 * @author morei
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jose
 */
public class Estacao {

    private String nome;
    private double latitude;
    private double longitude;
    private List<String> linhas;

    /**
     * construtor vazio
     */
    public Estacao() {
    }

    /**
     * construtor da estacao
     *
     * @param nome nome da estacao
     * @param latitude latitude da estacao
     * @param longitude longitude da estacao
     */
    public Estacao(String nome, double latitude, double longitude) {
        setNome(nome);
        setLatitude(latitude);
        setLongitude(longitude);
        linhas = new ArrayList<>();
    }

    /**
     * obter todas as linhas numa certa estacao
     *
     * @return
     */
    public List<String> getTodasLinhas() {
        return linhas;
    }

    /**
     * obter o nome
     *
     * @return
     */

    public String getNome() {
        return nome;
    }

    /**
     * obter a latitude
     *
     * @return
     */

    public double getLatitude() {
        return latitude;
    }

    /**
     * obter a longitude
     *
     * @return
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * alterar o nome
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLatitude(double latitude) {
        if (latitude > 90 || latitude < -90) {
            throw new IllegalArgumentException("Valor de latidude inesperado");
        }
        this.latitude = latitude;
    }

    public void setLinhas(List<String> linhas) {
        this.linhas = linhas;
    }

    public void setLongitude(double longitude) {
        if (longitude > 180 || longitude < -180) {
            throw new IllegalArgumentException("Valor de longitude inesperado");
        }
        this.longitude = longitude;
    }

    public boolean addLinha(String linha) {
        if (!linhas.contains(linha)) {
            linhas.add(linha);
            return true;
        }
        return false;
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
        final Estacao other = (Estacao) obj;
        return this.nome.equals(other.nome);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return "Estacao nome=" + nome + " latitude=" + latitude + " longitude=" + longitude;
    }

}
