/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto1_esinf;

import java.io.FileNotFoundException;

/**
 *
 * @author jose
 */
public class Main1 {

    public static void main(String[] args) throws FileNotFoundException {
        Ler.ler("fx_estacoes.txt");
        Ler.ler("fx_viagens.txt");
        Funcoes.utilizadoresEstacao();
        Funcoes.transgressao();
    } 
    
}
