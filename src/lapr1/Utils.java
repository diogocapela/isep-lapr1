package lapr1;

public class Utils {

    // Ordenar matriz por coluna utilizando o algoritmo bubble sort.
    public static int[][] bubbleSortMatrixCol(int[][] matrix) {
        for (int coluna = 0; matrix[0].length > coluna; coluna++) {
            boolean trocado = true;
            while (trocado) {
                trocado = false;
                for (int i = 1; i < matrix[coluna].length; i++) {
                    int t = 0;
                    if (matrix[i - 1][coluna] < matrix[i][coluna]) {
                        t = matrix[i - 1][coluna];
                        matrix[i - 1][coluna] = matrix[i][coluna];
                        matrix[i][coluna] = t;
                        trocado = true;
                    }
                }
            }
        }
        return matrix;
    }

    // Ordenar matriz por coluna utilizando o algoritmo insertion sort.
    public static int[][] insertionSortMatrixCol(int[][] matrix) {
        for (int coluna = 0; matrix[0].length > coluna; coluna++) {
            int tmp;
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
        return matrix;
    }

    // Ordenar matriz por coluna utilizando o algoritmo selection sort.
    public static int[][] selectionSortMatrixCol(int[][] matrix) {
        for (int coluna = 0; matrix[0].length > coluna; coluna++) {
            for (int i = 0; i < matrix.length - 1; i++) {
                int index = i;
                for (int j = i + 1; j < matrix.length; j++) {
                    if (matrix[j][coluna] > matrix[index][coluna]) {
                        index = j;
                    }
                }
                int minimo = matrix[index][coluna];
                matrix[index][coluna] = matrix[i][coluna];
                matrix[i][coluna] = minimo;
            }
        }
        return matrix;
    }
    
    // Rodar matriz 90º.
    public static int[][] rodarMatrix90(int[][] matrix) {
        int[][] rotateMatrix = new int[matrix.length][matrix.length];
        int visited = matrix.length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                rotateMatrix[j][visited - 1] = matrix[i][j];
            }
            visited--;
        }
        return rotateMatrix;
    }
    
    // Transformar uma matriz de ints numa matriz de doubles.
    public static double[][] transformarMatrizDeIntsEmDoubles(int[][] intMatrix) {
        double[][] doubleMatrix = new double[intMatrix.length][intMatrix.length];
        for (int i = 0; i < intMatrix.length; i++) {
            for (int k = 0; k < intMatrix.length; k++) {
                doubleMatrix[i][k] = (double) intMatrix[i][k];
            }
        }
        return doubleMatrix;
    }

    // Transformar uma matriz de doubles numa matriz de ints.
    public static int[][] transformarMatrizDeDoublesEmInts(double[][] doubleMatrix) {
        int[][] intMatrix = new int[doubleMatrix.length][doubleMatrix.length];
        for (int i = 0; i < doubleMatrix.length; i++) {
            for (int k = 0; k < doubleMatrix.length; k++) {
                intMatrix[i][k] = (int) Math.floor(doubleMatrix[i][k]);
            }
        }
        return intMatrix;
    }

    // Compara duas matrizes de ints.
    public static boolean compararMatrizesInts(int[][] m1, int[][] m2) {
        if(m1.length != m2.length) {
            return false;
        } else if(m1[0].length != m2[0].length) {
            return false;
        }
        for (int i = 0; i < m1.length; i++) {
            for (int k = 0; k < m1.length; k++) {
                if (m1[i][k] != m2[i][k]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Compara duas matrizes de doubles.
    public static boolean compararMatrizesDoubles(double[][] m1, double[][] m2) {
        if(m1.length != m2.length) {
            return false;
        } else if(m1[0].length != m2[0].length) {
            return false;
        }
        for (int i = 0; i < m1.length; i++) {
            for (int k = 0; k < m1.length; k++) {
                if (m1[i][k] != m2[i][k]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // Faz print a um vector de strings.
    public static void printStringVector(String[] vector) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print(i + ": " + vector[i]);
            System.out.println();
        }
    }

    // Faz print a um vector de ints.
    public static void printIntVector(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print(i + ": " + vector[i]);
            System.out.println();
        }
    }

    // Faz print a um vector de doubles.
    public static void printDoubleVector(double[] vector) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print(i + ": " + vector[i]);
            System.out.println();
        }
    }

    // Faz print a uma matriz de strings.
    public static void printStringMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Faz print a uma matriz de ints.
    public static void printIntMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Faz print a uma matriz de doubles.
    public static void printDoubleMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
