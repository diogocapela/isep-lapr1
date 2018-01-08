package lapr1;

public class Estatisticas {
    
    public static int[] somaLinhas(int[][] matrix) {
        int[] somaLinhas = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int soma = 0;
            for (int k = 0; k < matrix.length; k++) {
                soma += matrix[i][k];
            }
            somaLinhas[i] = soma;
        }
        return somaLinhas;
    }

    public static int[] somaColunas(int[][] matrix) {
        int[] somaColunas = new int[matrix.length];
        int currentCol = 0;
        for (int i = 0; i < matrix.length; i++) {
            int soma = 0;
            for (int k = 0; k < matrix.length; k++) {
                soma += matrix[k][currentCol];
            }
            currentCol++;
            somaColunas[i] = soma;
        }
        return somaColunas;
    }

    public static int somaTotal(int[][] matrix) {
        int soma = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                soma += matrix[i][j];
            }
        }
        return soma;
    }

    public static double[] mediaColunas(int[][] matrix) {
        double[] mediaColunas = new double[matrix.length];
        int[] totalColunas = somaColunas(matrix);
        for (int i = 0; i < matrix.length; i++) {
            mediaColunas[i] = totalColunas[i] / (double) matrix[i].length;
        }
        return mediaColunas;
    }

    public static double[] mediaLinhas(int[][] matrix) {
        double[] mediaLinhas = new double[matrix.length];
        int[] totalLinhas = somaLinhas(matrix);
        for (int i = 0; i < matrix.length; i++) {
            mediaLinhas[i] = totalLinhas[i] / (double) matrix[i].length;
        }
        return mediaLinhas;
    }

    public static double mediaTotal(int[][] matrix) {
        double mediaTotal = 0.0;
        int totalMatrix = somaTotal(matrix);
        mediaTotal = totalMatrix / (double) (matrix.length * matrix.length);
        return mediaTotal;
    }
    
}
