/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto1_esinf;

/**
 *
 * @author jose
 */
public class Singleton {

    private static Metro instance = null;

    private Singleton() {
    }

    public static Metro getInstance() {
        if (instance == null) {
            instance = new Metro();
        }
        return instance;
    }
}
