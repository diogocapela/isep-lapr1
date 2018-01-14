/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr1matrix;

/**
 * Esta classe é responsável para efetuar operações básicas em matrizes.
 *
 * @author pushdword
 */
public class Estatistica {

    public static double[] somaLinha(double[][] matrix) {
        double[] vetor_retorno = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            double soma = 0;
            for (int k = 0; k < matrix.length; k++) {
                soma += matrix[i][k];
            }
            vetor_retorno[i] = soma;
        }
        return vetor_retorno;
    }

    public static double[] somaColuna(double[][] matrix) {
        double[] vetor_retorno = new double[matrix.length];
        int currentCol = 0;
        for (int i = 0; i < matrix.length; i++) {
            double soma = 0;
            for (int k = 0; k < matrix.length; k++) {
                soma += matrix[k][currentCol];
            }
            currentCol++;
            vetor_retorno[i] = soma;
        }
        return vetor_retorno;
    }

    public static double somaTotal(double[][] matrix) {
        double soma = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                soma += matrix[i][j];
            }
        }
        return soma;
    }

    public static double[] mediaColunas(double[][] matrix) {
        double[] mediaColunas = new double[matrix.length];
        double[] totalColunas = somaColuna(matrix);

        for (int i = 0; i < matrix.length; i++) {
            mediaColunas[i] = totalColunas[i] / (double) matrix[i].length;
        }

        return mediaColunas;
    }

    public static double[] mediaLinhas(double[][] matrix) {
        double[] mediaLinhas = new double[matrix.length];
        double[] totalLinhas = somaLinha(matrix);

        for (int i = 0; i < matrix.length; i++) {
            mediaLinhas[i] = totalLinhas[i] / (double) matrix[i].length;
        }

        return mediaLinhas;
    }

    public static double mediaTotal(double[][] matrix) {
        double mediaTotal = 0.0;
        double totalMatrix = somaTotal(matrix);

        mediaTotal = totalMatrix / (double) (matrix.length * matrix.length);

        return mediaTotal;
    }
    
    /*
    * Regra dos trapézios.
    */
    public static double[] trapezoidalMethod(double[][] matrix) {
        double[] out = new double[matrix.length];
        

        for (int i = 0; i < matrix.length; i++) {
            int count = 1;
            double soma = 0;
            for (int j = 0; j < matrix.length -1; j++) {
                double first = (double)((count + 1) - (count)) / 2;
                double second = matrix[i][j] + matrix[i][j+1];
                soma = soma + (first * second);
                out[i] = soma;
                count ++;
            }
        }
        return out;
    }
}
