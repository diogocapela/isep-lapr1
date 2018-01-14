/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr1matrix;

/**
 *
 * @author pushdword
 */
public class CompararSorts {
    public static double selectionSort(int it,Matriz m){
        double[][] originalMatrix= new double[m.getDimensao()][m.getDimensao()];
        originalMatrix=copyMatrix(m.getMd(), originalMatrix);
        long medtime=0;
        for(int i=0;i<it;i++){
            Matriz mTest = new Matriz();
            mTest.setMd(originalMatrix);
            long time = System.currentTimeMillis();
            mTest.selectionSort();
            medtime+=System.currentTimeMillis()-time;
            mTest=null;
            originalMatrix=copyMatrix(m.getMd(), originalMatrix);
        }
        return medtime/(double)it;
    }
    
    public static double insertionSort(int it,Matriz m){
        double[][] originalMatrix= new double[m.getDimensao()][m.getDimensao()];
        originalMatrix=copyMatrix(m.getMd(), originalMatrix);
        long medtime=0;
        for(int i=0;i<it;i++){
            Matriz mTest = new Matriz();
            mTest.setMd(originalMatrix);
            long time = System.currentTimeMillis();
            mTest.insertionSort();
            medtime+=System.currentTimeMillis()-time;
            mTest=null;
            originalMatrix=copyMatrix(m.getMd(), originalMatrix);
        }
        return medtime/(double)it;
    }
    
    public static double bubbleSort(int it,Matriz m){
        double[][] originalMatrix= new double[m.getDimensao()][m.getDimensao()];
        originalMatrix=copyMatrix(m.getMd(), originalMatrix);
        long medtime=0;
        for(int i=0;i<it;i++){
            Matriz mTest = new Matriz();
            mTest.setMd(originalMatrix);
            long time = System.currentTimeMillis();
            mTest.bubbleSort();
            medtime+=System.currentTimeMillis()-time;
            mTest=null;
            originalMatrix=copyMatrix(m.getMd(), originalMatrix);
        }
        return medtime/(double)it;
        
    }
    public static double[][] copyMatrix(double[][] original,double[][]copy){
        for(int i=0;i<original.length;i++){
            System.arraycopy(original[i], 0, copy[i], 0, original.length);
        }
        return copy;
    }
}
