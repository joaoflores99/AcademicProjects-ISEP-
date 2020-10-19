/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto2_esinf;

/**
 *
 * @author joaoflores
 */
public class Tempo {

    private int horas;
    private int minutos;
    private int segundos;

    private static final int HORAS_POR_OMISSAO = 0;
    private static final int MINUTOS_POR_OMISSAO = 0;
    private static final int SEGUNDOS_POR_OMISSAO = 0;

    public Tempo(int horas, int minutos, int segundos) {
        if (segundos > 59) {
            int vezes = segundos / 60;
            this.minutos = minutos + vezes;
            this.segundos = segundos - 60 * vezes;
        } else {
            this.segundos = segundos;
            this.minutos = minutos;
        }
        if (minutos > 59) {
            int vezes = this.minutos / 60;
            this.horas = horas + vezes;
            this.minutos = this.minutos - 60 * vezes;
        } else {
            this.horas = horas;
        }
    }

    public Tempo(Tempo tempo){
        this.horas=tempo.getHoras();
        this.minutos=tempo.getMinutos();
        this.segundos=tempo.getSegundos();
    }
    public Tempo() {
        this.horas = HORAS_POR_OMISSAO;
        this.minutos = MINUTOS_POR_OMISSAO;
        this.segundos = SEGUNDOS_POR_OMISSAO;
    }

    public int getHoras() {
        return horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setTempo(int horas, int minutos, int segundos) {
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    public void setTempo(int horas, int minutos, int segundos, String periodo) {
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    @Override
    public String toString() {
        return String.format("%d:%d:%d", horas, minutos, segundos);
    }

    public String converterTempo() {
        String periodo;
        int horas = this.horas;
        if (this.horas > 12) {
            horas = this.horas - 12;
            periodo = "PM";
        } else {
            periodo = "AM";
        }
        return String.format("%d:%d:%d %s", horas, minutos, segundos, periodo);
    }

    public String adicionarSegundo() {
        segundos++;
        if (segundos > 59) {
            segundos = 0;
            minutos++;
            if (minutos > 59) {
                minutos = 0;
                horas++;
            }
        }
        return String.format("Adicionado 1 segundo (%d:%d:%d)", horas, minutos, segundos);
    }

    public String isMaiorTempo(Tempo t2) {
        int totalSegundosT1 = converterParaSegundos();
        int totalSegundosT2 = t2.converterParaSegundos();

        String resultado;
        if (totalSegundosT1 > totalSegundosT2) {
            resultado = String.format("O maior tempo é: %d:%d:%d", horas, minutos, segundos);
        } else if (totalSegundosT1 < totalSegundosT2) {
            resultado = String.format("O maior tempo é: %d:%d:%d", t2.horas, t2.minutos, t2.segundos);
        } else {
            resultado = String.format("Ambos os tempos são iguais a: %d:%d:%d", horas, minutos, segundos);
        }

        return resultado;
    }

    public String isMaiorParametros(int hora, int minuto, int segundo) {
        int totalSegundosT1 = converterParaSegundos();
        int totalSegundosT2 = converterParaSegundosParametros(hora, minuto, segundo);

        String resultado;
        if (totalSegundosT1 > totalSegundosT2) {
            resultado = String.format("O maior tempo é: %d:%d:%d", horas, minutos, segundos);
        } else if (totalSegundosT1 < totalSegundosT2) {
            resultado = String.format("O maior tempo é: %d:%d:%d", hora, minuto, segundo);
        } else {
            resultado = String.format("Ambos os tempos são iguais a: %d:%d:%d", horas, minutos, segundos);
        }

        return resultado;
    }

    public String diferencaTempos(Tempo t2) {
        int totalSegundosT1 = converterParaSegundos();
        int totalSegundosT2 = t2.converterParaSegundos();

        String resultado;
        int diferenca;
        if (totalSegundosT1 > totalSegundosT2) {
            diferenca = totalSegundosT1 - totalSegundosT2;
            String tempo = converterParaTempo(diferenca);
            resultado = String.format("A diferenca entre os tempos é: %s (%d segundos)", tempo, diferenca);
        } else if (totalSegundosT1 < totalSegundosT2) {
            diferenca = totalSegundosT2 - totalSegundosT1;
            String tempo = converterParaTempo(diferenca);
            resultado = String.format("A diferenca entre os tempos é: %s (%d segundos)", tempo, diferenca);
        } else {
            resultado = "Ambos os tempos são iguais.";
        }
        return resultado;
    }

    public String converterParaTempo(int sec) {
        int horas = sec / 3600;
        int minutos = (sec % 3600) / 60;
        int segundos = sec % 60;

        String tempo = String.format("%d:%d:%d", horas, minutos, segundos);

        return tempo;
    }

    public int converterParaSegundos() {
        int totalSegundos = horas * 3600 + minutos * 60 + segundos;
        return totalSegundos;
    }

    public static Tempo convert(String tempoString) {
        String tmp[] = tempoString.split(":");
        Tempo tempo = new Tempo(Integer.parseInt(tmp[2]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[0]));
        return tempo;
    }

    public int converterParaSegundosParametros(int horas, int minutos, int segundos) {
        int totalSegundos = horas * 3600 + minutos * 60 + segundos;
        return totalSegundos;
    }

    public static Tempo somaTempos(Tempo tempo1, Tempo tempo2) {
        int segundos = tempo1.getSegundos() + tempo2.getSegundos();
        int minutos = tempo1.getMinutos() + tempo2.getMinutos();
        int horas = tempo1.getHoras() + tempo2.getHoras();
        if (segundos > 59) {
            int vezes = segundos / 60;
            minutos = minutos + vezes;
            segundos = segundos - 60 * vezes;
        } else {
            segundos = segundos;
            minutos = minutos;
        }
        if (minutos > 59) {
            int vezes = minutos / 60;
            horas = horas + vezes;
            minutos = minutos - 60 * vezes;
        } else {
            horas = horas;
        }
        Tempo t=new Tempo(horas,minutos,segundos);
        return t;
    }
    
    public Tempo addicionarMinutos(int minutos){
        this.minutos=this.minutos+minutos;
        if(this.minutos>59){
            this.horas+=this.minutos/60;
            this.minutos+=this.minutos%60;
        }
        return this;
    }
    
}
