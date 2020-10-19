/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto2_esinf;

import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author morei
 */
public class PercursoTest {
    
    Estacao instance = new Estacao("1", 40, 40);
    Estacao instance2 = new Estacao("2", 50, 50);
    Estacao instance3 = new Estacao("2", 40, 90);
    
    Tempo tempo1 = new Tempo(1, 1, 0);
    Tempo tempo2 = new Tempo(0, 2, 0);
    Tempo tempo3 = new Tempo(0, 3, 0);
    Map<Estacao, Double> mapa;
    Map<Estacao, String> mapa2;
    
    
    public PercursoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mapa = new HashMap<>();
        
        mapa.put(instance, 12.0);
        mapa.put(instance2, 14.0);
        mapa.put(instance3, 16.0);
        
        mapa2=new HashMap<>();
        
        mapa2.put(instance, "1");
        mapa2.put(instance2, "2");
        mapa2.put(instance3, "3");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTempoInstanteInicial method, of class Percurso.
     */
    @Test
    public void testGetTempoInstanteInicial() {
        System.out.println("getTempoInstanteInicial");
        Tempo tempo = new Tempo(12, 12, 12);
        Percurso instance = new Percurso(this.instance, instance2, 12);
        
        double result = instance.getTempoInstanteInicial();
        assertEquals(12.0, result,0.1);
        // TODO review the generated test code an
    }

    

    /**
     * Test of getEstacaoOrig method, of class Percurso.
     */
    @Test
    public void testGetEstacaoOrig() {
        System.out.println("getEstacaoOrig");
        Percurso instance = new Percurso(this.instance,this.instance2,12.0);
        Estacao result = instance.getEstacaoOrig();
        assertEquals(this.instance, result);
    }

    /**
     * Test of setEstacaoOrig method, of class Percurso.
     */
    @Test
    public void testSetEstacaoOrig() {
        System.out.println("setEstacaoOrig");
        Percurso instance = new Percurso(this.instance, this.instance,12.0);
        instance.setEstacaoOrig(this.instance3);
        assertEquals(instance3, instance.getEstacaoOrig());
    }

    /**
     * Test of getEstacaoDest method, of class Percurso.
     */
    @Test
    public void testGetEstacaoDest() {
        System.out.println("getEstacaoDest");
        Percurso instance = new Percurso(this.instance, this.instance2, 12.0);
        Estacao result = instance.getEstacaoDest();
        assertEquals(instance2, result);
    }

    /**
     * Test of setEstacaoDest method, of class Percurso.
     */
    @Test
    public void testSetEstacaoDest() {
        System.out.println("setEstacaoDest");
        Estacao estacaoDest = null;
        Percurso instance = new Percurso(this.instance, this.instance2, 12.0);
        instance.setEstacaoDest(instance3);
        assertEquals(instance3, instance.getEstacaoDest());
    }

    /**
     * Test of getTempoViagem method, of class Percurso.
     */
    @Test
    public void testGetTempoViagem() {
        System.out.println("getTempoViagem");
        Percurso instance = new Percurso(this.instance,this.instance2);
        instance.setTempoViagem(12);
        double result = instance.getTempoViagem();
        assertEquals(12.0, result,0.1);
    }

    /**
     * Test of setTempoViagem method, of class Percurso.
     */
    @Test
    public void testSetTempoViagem() {
        System.out.println("setTempoViagem");
        Percurso instance = new Percurso(this.instance,instance2);
        instance.setTempoViagem(12);
        assertEquals(12.0, instance.getTempoViagem(),0.1);
    }

    /**
     * Test of getMapaEstacoesLinha method, of class Percurso.
     */
    @Test
    public void testGetMapaEstacoesLinha() {
        System.out.println("getMapaEstacoesLinha");
        Percurso instance = new Percurso(this.instance,this.instance2,12.0,mapa2,mapa);
        Map<Estacao, String> expResult = mapa2;
        Map<Estacao, String> result = instance.getMapaEstacoesLinha();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMapaEstacoesLinha method, of class Percurso.
     */
    @Test
    public void testSetMapaEstacoesLinha() {
        System.out.println("setMapaEstacoesLinha");
        Percurso instance = new Percurso(this.instance,instance2,12.0,null,mapa);
        Map<Estacao, String> expResult = mapa2;
        instance.setMapaEstacoesLinha(mapa2);
        assertEquals(expResult, instance.getMapaEstacoesLinha());
    }

    /**
     * Test of getMapaEstacoesTempoCorrespondente method, of class Percurso.
     */
    @Test
    public void testGetMapaEstacoesTempoCorrespondente() {
        System.out.println("getMapaEstacoesTempoCorrespondente");
        Percurso instance = new Percurso(this.instance,instance2,12.0,mapa2,mapa);
        instance.setMapaEstacoesTempoCorrespondente(mapa);
        Map<Estacao, Double> result = instance.getMapaEstacoesTempoCorrespondente();
        assertEquals(mapa, result);
    }

    /**
     * Test of setMapaEstacoesTempoCorrespondente method, of class Percurso.
     */
    @Test
    public void testSetMapaEstacoesTempoCorrespondente() {
        System.out.println("setMapaEstacoesTempoCorrespondente");
        Map<Estacao, Double> mapaEstacoesTempoCorrespondente = mapa;
        Percurso instance = new Percurso(this.instance,this.instance2,12.0,mapa2,mapa);
        instance.setMapaEstacoesTempoCorrespondente(mapaEstacoesTempoCorrespondente);
        assertEquals(mapa, instance.getMapaEstacoesTempoCorrespondente());
    }
    
}
