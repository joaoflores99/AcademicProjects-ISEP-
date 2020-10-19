/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto1_esinf;

import java.util.Objects;

/**
 *
 * @author jose
 */
public class Bilhete {
    private int numero_bilhete;
    private String tipo_bilhete;
    
    public Bilhete(int num_bilhete,String tipo_bilhete){
        this.numero_bilhete=num_bilhete;
        this.tipo_bilhete= tipo_bilhete;
    }
    public Bilhete(Bilhete bilhete){
        this.numero_bilhete= bilhete.numero_bilhete;
        this.tipo_bilhete= bilhete.tipo_bilhete;
    }
    public void setNumeroBilhete(int numero_bilhete){
        this.numero_bilhete=numero_bilhete;
    }
    public void setTipo_Bilhete(String tipo_bilhete){
        this.tipo_bilhete=tipo_bilhete;
    }
    public int getNumeroBilhete(){
       return this.numero_bilhete;
    }
    public String getTipoBilhete(){
        return this.tipo_bilhete;
    }
    @Override
    public String toString(){
        return String.format("Detalhes do Bilhete:\n"
                + "Número do bilhete: "
                + "Tipo de Biilhete:",
                getNumeroBilhete()+getTipoBilhete());
    }
    @Override
    public boolean equals(Object o){
        if(o==this){
            return true;
        }
        if(this.getClass() != o.getClass()|| o== null){
            return false;
        }
        Bilhete p= (Bilhete) o;
        return (this.numero_bilhete>(p.numero_bilhete)) && this.tipo_bilhete.equals(p.tipo_bilhete);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.numero_bilhete;
        hash = 29 * hash + Objects.hashCode(this.tipo_bilhete);
        return hash;
    }
    
    
}
