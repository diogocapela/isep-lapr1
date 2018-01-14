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
public class Transformacoes {

    public Transformacoes() {
    }

    //Esta função roda uma matriz.
    public double[][] rodarMatrix(double[][] matrix) {

        double[][] rotateMatrix = new double[matrix.length][matrix.length];
        int visited = matrix.length;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                rotateMatrix[j][visited - 1] = matrix[i][j];
            }
            visited--;
        }

        return rotateMatrix;
    }

    public double[][] rodarMatrixAntiClockwise(double[][] matrix) {

        double[][] rotateMatrix = new double[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            int visited = matrix.length;
            for (int j = 0; j < matrix[i].length; j++) {
                rotateMatrix[visited - 1][i] = matrix[i][j];
                visited--;
            }
        }
        return rotateMatrix;
    }


    // Este método transforma uma matriz de inteiros numa matriz de doubles.
    public double[][] transformarMatrizDeIntsEmDoubles(int[][] intMatrix) {
        double[][] doubleMatrix = new double[intMatrix.length][intMatrix.length];
        for (int i = 0; i < intMatrix.length; i++) {
            for (int k = 0; k < intMatrix.length; k++) {
                doubleMatrix[i][k] = (double) intMatrix[i][k];
            }
        }
        return doubleMatrix;
    }

    public int[][] transformarMatrizDeDoublesEmInt(double[][] doubleMatrix) {
        int[][] intMatrix = new int[doubleMatrix.length][doubleMatrix.length];
        for (int i = 0; i < doubleMatrix.length; i++) {
            for (int k = 0; k < doubleMatrix.length; k++) {
                intMatrix[i][k] = (int) Math.floor(doubleMatrix[i][k]);
            }
        }
        return intMatrix;
    }

    //esta função ordena por coluna utilizando o algoritmo bubble sort.
    public double[][] bubbleSortMatrixCol(double[][] matrix) {
        for (int coluna = 0; matrix[0].length > coluna; coluna++) {
            boolean trocado = true;
            while (trocado) {
                trocado = false;
                for (int i = 1; i < matrix[coluna].length; i++) {
                    double t = 0;
                    if (matrix[i - 1][coluna] < matrix[i][coluna]) {
                        t = matrix[i - 1][coluna];
                        matrix[i - 1][coluna] = matrix[i][coluna];
                        matrix[i][coluna] = t;
                        trocado = true;
                    }
                }
            }
        }
        return matrix;//default
    }

    //esta função ordena por coluna utilizando o algoritmo insertion sort.
    public double[][] insertionSortMatrixCol(double[][] matrix) {
        for (int coluna = 0; matrix[0].length > coluna; coluna++) {
            double tmp;
            for (int i = 1; i < matrix[coluna].length; i++) {
                for (int j = i; j > 0; j--) {
                    if (matrix[j][coluna] > matrix[j - 1][coluna]) {
                        tmp = matrix[j][coluna];
                        matrix[j][coluna] = matrix[j - 1][coluna];
                        matrix[j - 1][coluna] = tmp;
                    }
                }
            }
        }
        return matrix;//default
    }

    //esta função ordena por coluna utilizando o algoritmo selection sort.
    public double[][] selectionSortMatrixCol(double[][] matrix) {
        for (int coluna = 0; matrix[0].length > coluna; coluna++) {
            for (int i = 0; i < matrix.length - 1; i++) {
                int index = i;
                for (int j = i + 1; j < matrix.length; j++) {
                    if (matrix[j][coluna] > matrix[index][coluna]) {
                        index = j;
                    }
                }
                double minimo = matrix[index][coluna];
                matrix[index][coluna] = matrix[i][coluna];
                matrix[i][coluna] = minimo;
            }
        }
        return matrix;//default
    }
}
