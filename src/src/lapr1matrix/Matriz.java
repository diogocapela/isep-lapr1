/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr1matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Esta classe é um util de matrizes.
 *
 * @author pushdword
 */
public class Matriz {

    /**
     * @return the md
     */
    public double[][] getMd() {
        return md.clone();
    }

    /**
     * @param md the md to set
     */
    public void setMd(double[][] md) {
        if(md==null) return;
        this.md = md.clone();
    }

    private double[][] md = null;
    //private int[][] m = null;
    private int mi = 0;
    private int mj = 0;
    private String descricao;
    private int dimensao;
    private Transformacoes TM;
    private Filtros f;

    public String getCompressed() {
        return compressed;
    }

    public void setCompressed(String compressed) {
        this.compressed = compressed;
    }
    private String compressed;

    public int getCompressLvl() {
        return compressLvl;
    }

    public void setCompressLvl(int compressLvl) {
        this.compressLvl = compressLvl;
    }

    private int compressLvl;
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    private String path="";

    /**
     * Isto é um construtor. Ele leva uma matrix como argumento.
     *
     * @param matrix
     */
    public Matriz() {
        TM = new Transformacoes();
        f = new Filtros();
    }

    /*
    * Este método verifica se a matriz é quadrada
    * Devolve uma lista de linhas (int) onde não está em conformidade.
     */
    public LinkedList<Integer> isMatrixSquare() {
        if (md == null) {
            return null;
        }
        LinkedList<Integer> notCompliantLines = new LinkedList<>();
        for (int i = 0; md.length > i; i++) {
            if (md[i].length != md.length) {
                notCompliantLines.add(i + 1);
            }
        }
        return notCompliantLines;
    }

    /*
    * Sort function using insertion sort algorithm.
     */
    public static double[] sortVect(double[] vect) {
        double tmp;
        for (int i = 1; i < vect.length; i++) {
            for (int j = i; j > 0; j--) {
                if (vect[j] < vect[j - 1]) {
                    tmp = vect[j];
                    vect[j] = vect[j - 1];
                    vect[j - 1] = tmp;
                }
            }
        }
        return vect;
    }

    /*
    * Compara duas matrizes.
     */
    public static boolean compararMatrizesDouble(double[][] m1, double[][] m2) {
        boolean resultado = true;
        for (int i = 0; i < m1.length; i++) {
            for (int k = 0; k < m1.length; k++) {
                if (m1[i][k] != m2[i][k]) {
                    resultado = false;
                }
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("");
        buffer.append(descricao);
        buffer.append("  ");
        buffer.append(dimensao);
        buffer.append("\n\n");
        for (int i = 0; md.length > i; i++) {
            for (int j = 0; md[i].length > j; j++) {
                buffer.append(md[i][j]);
                if (j + 1 < md[i].length) {
                    buffer.append(",");
                }
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }
    public String toStringCsv() {
        StringBuffer buffer = new StringBuffer("");
        for (int i = 0; md.length > i; i++) {
            for (int j = 0; md[i].length > j; j++) {
                buffer.append(md[i][j]);
                if (j + 1 < md[i].length) {
                    buffer.append(",");
                }
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

    public void printM() {
        System.out.println(descricao + "  " + dimensao);
        for (int i = 0; md.length > i; i++) {
            for (int j = 0; md[i].length > j; j++) {
                System.out.print("" + md[i][j]);
                if (j + 1 < md[i].length) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }

    //print matrix doubles
    public void printMd() {
        System.out.println(descricao + "  " + dimensao);
        for (int i = 0; getMd().length > i; i++) {
            for (int j = 0; getMd()[i].length > j; j++) {
                System.out.print("" + getMd()[i][j]);
                if (j + 1 < getMd()[i].length) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    public boolean rodar90() {
        if (md == null) {
            return false;
        }
        double[][] mcopy;
        mcopy = TM.rodarMatrix(md);
        if (mcopy != null) {
            md = mcopy.clone();
            path+="rodar90";
            return true;
        } else {
            return false;
        }
    }
    public boolean rodar90AntiClockwise() {
        if (md == null) {
            return false;
        }
        double[][] mcopy;
        mcopy = TM.rodarMatrixAntiClockwise(md);
        if (mcopy != null) {
            md = mcopy.clone();
            path+="rodar90";
            return true;
        } else {
            return false;
        }
    }

    public boolean filtroMedia() {
        if (md == null) {
            return false;
        }
        double[][] mcopy;
        mcopy = f.filterMedia(md);
        if (mcopy != null) {
            md = mcopy.clone();
            path+="Media";
            return true;
        } else {
            return false;
        }
    }

    public boolean filtroMediana() {
        if (md == null) {
            return false;
        }
        double[][] mcopy;
        mcopy = f.filterMediana(md);
        if (mcopy != null) {
            md = mcopy.clone();
            path+="Mediana";
            return true;
        } else {
            return false;
        }
    }

    public boolean filtroMinimo() {
        if (md == null) {
            return false;
        }
        double[][] mcopy;
        mcopy = f.filterMinimo(md);
        if (mcopy != null) {
            md = mcopy.clone();
            path+="Minimo";
            return true;
        } else {
            return false;
        }
    }

    public boolean filtroMaximo() {
        if (md == null) {
            return false;
        }
        double[][] mcopy;
        mcopy = f.filterMaximo(md);
        if (mcopy != null) {
            md = mcopy.clone();
            path+="Maximo";
            return true;
        } else {
            return false;
        }
    }
    
    public boolean filtroConv(int t){
                if (md == null || t<0) {
            return false;
        }
        double[][] mcopy;
        mcopy = TM.transformarMatrizDeIntsEmDoubles(f.filterConvulsao(TM.transformarMatrizDeDoublesEmInt(md), t));
        if (mcopy != null) {
            md = mcopy.clone();
            path+="Convolução"+t;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean filtroVar(){
                if (md == null) {
            return false;
        }
        double[][] mcopy;
        mcopy = TM.transformarMatrizDeIntsEmDoubles(f.filterVariancia(TM.transformarMatrizDeDoublesEmInt(md)));
        if (mcopy != null) {
            md = mcopy.clone();
            path+="Variância";
            return true;
        } else {
            return false;
        }
    }

    public boolean bubbleSort() {
        if (md == null) {
            return false;
        }
        double[][] mcopy;
        mcopy = TM.bubbleSortMatrixCol(md);
        if (mcopy != null) {
            md = mcopy.clone();
            path+="Bubblesort";
            return true;
        } else {
            return false;
        }
    }

    public boolean insertionSort() {
        if (md == null) {
            return false;
        }
        double[][] mcopy;
        mcopy = TM.insertionSortMatrixCol(md);
        if (mcopy != null) {
            md = mcopy.clone();
            path+="Insertionsort";
            return true;
        } else {
            return false;
        }
    }

    public boolean selectionSort() {
        if (md == null) {
            return false;
        }
        double[][] mcopy;
        mcopy = TM.selectionSortMatrixCol(md);
        if (mcopy != null) {
            md = mcopy.clone();
            path+="Selectionsort";
            return true;
        } else {
            return false;
        }
    }

    public boolean showStatistics() {
        try {
            /*
        * Soma das linhas:
             */
            System.out.println("Soma das linhas:");
            for (double i : Estatistica.somaLinha(md)) {
                System.out.print(i + " ");
            }
            System.out.println();

            /*
        * Soma das colunas
             */
            System.out.println("Soma das colunas:");
            for (double i : Estatistica.somaColuna(md)) {
                System.out.print(i + " ");
            }
            System.out.println();

            /*
            * Média Colunas
             */
            System.out.println("Média das colunas:");
            for (double i : Estatistica.mediaColunas(md)) {
                System.out.printf("%.2f ", i);
            }
            System.out.println();

            /*
            * Média Linhas
             */
            System.out.println("Média das linhas:");
            for (double i : Estatistica.mediaLinhas(md)) {
                System.out.printf("%.2f ", i);
            }
            System.out.println();
            /*
            * Média total
             */
            System.out.printf("Media total: %.2f\n", Estatistica.mediaTotal(md));

            /*
        * Soma total
             */
            System.out.println("Soma total: " + Estatistica.somaTotal(md));

            /*
            * Eigen value
             */
            System.out.println("Valores próprios: ");
            for (double i : Eigen.getEigenValues(md)) {
                System.out.print(i + " ");
            }
            System.out.println();

            /*
            * Vetor próprio
             */
            System.out.println("Matrix de vetores próprios: ");
            Matriz vProprio = new Matriz();
            vProprio.setDimensao(dimensao);
            vProprio.setDescricao(descricao);
            vProprio.setMd(Eigen.getEigenVectors(md));
            vProprio.printMd();
            
            /*Integração*/
            System.out.println("Integração: ");
            double[] integral = Estatistica.trapezoidalMethod(md);
            for(double d : integral){
                System.out.printf("%.2f ",d);
            }
            System.out.println();

        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public Matriz cloneMatrix(){
        Matriz toClone=null;
        if(md!=null || md!=null){
            toClone = new Matriz();
            toClone.setMd(md);
            toClone.setDescricao(descricao);
            toClone.setDimensao(dimensao);
        }
        return toClone;
    }
    
    public boolean showGnuplot() {
        
        /*Write a temp file to hold a compliance gnuplot matrix*/
        Ficheiros f  = new Ficheiros();
        String pathOfTmpMatrix = f.writeMatrixCsv(this);
        if (pathOfTmpMatrix == null) {
            return false;
        }
        try {
            Runtime rt = Runtime.getRuntime();
            Process prcs = rt.exec("cmd /c start gnuplot -e \"filename='"+pathOfTmpMatrix+"\"' ex1.gp");
            System.out.println("Prima qualquer tecla para continuar...");
            System.in.read();
            f.deleteFile(pathOfTmpMatrix);
            if (prcs.exitValue() != 0) {
                System.out.println("Erro: "+System.getProperty("user.dir"));
                
                return false;
            }
        } catch (IOException iex) {
            System.out.print("Ficheiro não foi encontrado:\n" + iex.getMessage());
            f.deleteFile(pathOfTmpMatrix);
            return false;
        }
        return true;
    }
    
    public void compress(int lvl){
        compressLvl=lvl;
        compressed=(new Compressao().compressMatrix(md, lvl));
    }
    
    public String printCompressed(boolean mute){
        String retStr="";
        if(compressed!=null){
            if(compressed.length()>0){
                retStr+=descricao+"  "+dimensao+"\n\n";
                retStr+=compressed;
                if(mute)
                    return retStr;
                System.out.println(retStr);
                return retStr;
            }
        }
        return null;
    }
}
    
