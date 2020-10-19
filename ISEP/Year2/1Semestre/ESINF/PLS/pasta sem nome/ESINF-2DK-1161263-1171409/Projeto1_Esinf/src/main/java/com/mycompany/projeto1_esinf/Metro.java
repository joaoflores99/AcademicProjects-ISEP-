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
public class Metro {

    private RegistoEstacoes re;
    private RegistoViagens rv;

    public Metro() {
        re = new RegistoEstacoes();
        rv = new RegistoViagens();
    }

    public Metro(RegistoEstacoes re, RegistoViagens rv) {
        this.re = re;
        this.rv = rv;
    }

    public RegistoEstacoes getRe() {
        return re;
    }

    public RegistoViagens getRv() {
        return rv;
    }

    public void setRe(RegistoEstacoes re) {
        this.re = re;
    }

    public void setRv(RegistoViagens rv) {
        this.rv = rv;
    }

}
