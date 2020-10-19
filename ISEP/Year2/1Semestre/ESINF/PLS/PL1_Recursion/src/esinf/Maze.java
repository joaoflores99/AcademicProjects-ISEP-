/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf;

/**
 *
 * @author joaoflores
 */
public class Maze {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] actual = {
            {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        
        System.out.println("Original");
        for (int i = 0; i < actual.length; i++) {
            for (int j = 0; j < actual[0].length - 1; j++) {
                   System.out.println(actual[i][j]);
            }

        }
        System.out.println(" ");

        for (int i = 0; i < actual.length; i++) {
            for (int j = 0; j < actual[0].length - 1; j++) {

                Labirinth.check(actual, i, j);
            }
        }
        
        System.out.println("PATH");
        for (int i = 0; i < actual.length; i++) {
            for (int j = 0; j < actual[0].length - 1; j++) {

                System.out.println(actual[i][j]) ;
            }
        }
    }

}
