/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericsortingarrays;
 
import java.util.Arrays;
 
/**
 *
 * @author DEI-ISEP
 */
public class GenericSortingArrays {
 
    /**
     * Swaps two vector positions O(1)
     */
    public static <E> void swap(E[] v, int i, int j) {
 
        E temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }
 
    //  printArray                                
    public static <E> void printArray(E[] v) {
        for (E element : v) {
            System.out.println(", " + element);
        }
    }
 
    /**
     * Selection Sort Algorithm
     */
    public static <E extends Comparable<E>> void selectionSort(E[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < v.length; j++) {
                if (v[j].compareTo(v[min_idx]) < 0) {
                    min_idx = j;
                }
            }
            swap(v,i,min_idx);
        }
    }
 
    /**
     * Bubble Sort Algorithm
     *
     * @param v
     */
    public static <E extends Comparable<E>> void bubbleSort(E[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            for (int j = 0; j < v.length - i - 1; j++) {
                if (v[j].compareTo(v[j + 1]) > 0) {
                    swap(v, j, j + 1);
                }
            }
        }
    }
 
    /**
     * insertionSort Algorithm
     */
    public static <E extends Comparable<E>> void insertionSort(E[] v) {
        for (int i = 1; i < v.length; ++i) {
            int j = i;
            while (j > 0 && v[j].compareTo(v[j - 1]) < 0) {
                swap(v, j, j - 1);
                j--;
            }
        }
    }
 
    
    
    /**
     * Mergesort Algorithm
     */
    private static <E extends Comparable<E>> void merge(E[] S1, E[] S2, E[] S) {
       
    }
 
    public static <E extends Comparable<E>> void mergeSort(E[] S) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
 
    /**
     * Quicksort Algorithm
     */
    public static <E extends Comparable<E>> void quickSort(E v[]) {
 
        throw new UnsupportedOperationException("Not supported yet.");
    }
 
    private static <E extends Comparable<E>> void quickSort(E v[], int left, int right) {
 
        throw new UnsupportedOperationException("Not supported yet.");
    }
}