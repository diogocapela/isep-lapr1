package lapr1matrix;

/**
 * Esta classe serve para efetuar filtros das matrizes.
 */
public class Filtros {

    /**
     * Aplicar filtro da convulsão a uma matriz (para edge detection).
     */
    public int[][] filterConvulsao(int[][] matrix, int threshold) {
        int[][] novaMatrix = new int[matrix.length][matrix.length];
        int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix.length; k++) {
                int soma = 0;
                for (int d = 0; d < directions.length; d++) {
                    int positionX = i + directions[d][0];
                    int positionY = k + directions[d][1];
                    if (positionX == -1 && positionY == -1 || positionX == matrix.length && positionY == -1 || positionX == -1 && positionY == matrix.length || positionX == matrix.length && positionY == matrix.length) {
                        soma = soma + 0;
                    } else if (positionX == -1) {
                        soma = soma + matrix[positionX + 1][positionY] * -1;
                    } else if (positionX == matrix.length) {
                        soma = soma + matrix[positionX - 1][positionY] * -1;
                    } else if (positionY == -1) {
                        soma = soma + matrix[positionX][positionY + 1] * -1;
                    } else if (positionY == matrix.length) {
                        soma = soma + matrix[positionX][positionY - 1] * -1;
                    } else if (positionX >= 0 && positionX < matrix.length && positionY >= 0 && positionY < matrix.length) {
                        soma = soma + matrix[positionX][positionY] * -1;
                    }
                }
                int valorConvulsao = (matrix[i][k] * 8) + soma;
                if (valorConvulsao > threshold) {
                    novaMatrix[i][k] = 255;
                } else {
                    novaMatrix[i][k] = 0;
                }
            }
        }
        return novaMatrix;
    }
    
    /**
     * Aplicar filtro da variância a uma matriz.
     */
    public int[][] filterVariancia(int[][] matrix) {
        int[][] novaMatrix = new int[matrix.length][matrix.length];
        int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix.length; k++) {
                int soma = 0;
                int contador = 0;
                for (int d = 0; d < directions.length; d++) {
                    int positionX = i + directions[d][0];
                    int positionY = k + directions[d][1];
                    if (positionX == -1 && positionY == -1 || positionX == matrix.length && positionY == -1 || positionX == -1 && positionY == matrix.length || positionX == matrix.length && positionY == matrix.length) {
                        contador++;
                    } else if (positionX == -1) {
                        soma = soma + matrix[positionX + 1][positionY];
                        contador++;
                    } else if (positionX == matrix.length) {
                        soma = soma + matrix[positionX - 1][positionY];
                        contador++;
                    } else if (positionY == -1) {
                        soma = soma + matrix[positionX][positionY + 1];
                        contador++;
                    } else if (positionY == matrix.length) {
                        soma = soma + matrix[positionX][positionY - 1];
                        contador++;
                    } else if (positionX >= 0 && positionX < matrix.length && positionY >= 0 && positionY < matrix.length) {
                        soma = soma + matrix[positionX][positionY];
                        contador++;
                    }
                }
                novaMatrix[i][k] = ((soma / contador) / contador);
            }
        }
        return novaMatrix;
    }

    /**
     * Aplicar filtro da média a uma matriz.
     */
    public double[][] filterMedia(double[][] matrix) {
        double[][] novaMatrix = new double[matrix.length][matrix.length];
        int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix.length; k++) {
                double soma = 0;
                int contador = 0;
                for (int d = 0; d < directions.length; d++) {
                    int positionX = i + directions[d][0];
                    int positionY = k + directions[d][1];
                    if (positionX == -1 && positionY == -1 || positionX == matrix.length && positionY == -1 || positionX == -1 && positionY == matrix.length || positionX == matrix.length && positionY == matrix.length) {
                        contador++;
                    } else if (positionX == -1) {
                        soma = soma + matrix[positionX + 1][positionY];
                        contador++;
                    } else if (positionX == matrix.length) {
                        soma = soma + matrix[positionX - 1][positionY];
                        contador++;
                    } else if (positionY == -1) {
                        soma = soma + matrix[positionX][positionY + 1];
                        contador++;
                    } else if (positionY == matrix.length) {
                        soma = soma + matrix[positionX][positionY - 1];
                        contador++;
                    } else if (positionX >= 0 && positionX < matrix.length && positionY >= 0 && positionY < matrix.length) {
                        soma = soma + matrix[positionX][positionY];
                        contador++;
                    }
                }
                novaMatrix[i][k] = soma / (double)contador;
            }
        }
        return novaMatrix;
    }

    /**
     * Aplicar filtro do máximo a uma matriz.
     */
    public double[][] filterMaximo(double[][] matrix) {
        double[][] novaMatrix = new double[matrix.length][matrix.length];
        int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix.length; k++) {
                double maximo = 0;
                for (int d = 0; d < directions.length; d++) {
                    int positionX = i + directions[d][0];
                    int positionY = k + directions[d][1];
                    if (positionX == -1 && positionY == -1 || positionX == matrix.length && positionY == -1 || positionX == -1 && positionY == matrix.length || positionX == matrix.length && positionY == matrix.length) {
                        int valor = 0;
                        if (valor > maximo) {
                            maximo = valor;
                        }
                    } else if (positionX == -1) {
                        double valor = matrix[positionX + 1][positionY];
                        if (valor > maximo) {
                            maximo = valor;
                        }
                    } else if (positionX == matrix.length) {
                        double valor = matrix[positionX - 1][positionY];
                        if (valor > maximo) {
                            maximo = valor;
                        }
                    } else if (positionY == -1) {
                        double valor = matrix[positionX][positionY + 1];
                        if (valor > maximo) {
                            maximo = valor;
                        }
                    } else if (positionY == matrix.length) {
                        double valor = matrix[positionX][positionY - 1];
                        if (valor > maximo) {
                            maximo = valor;
                        }
                    } else if (positionX >= 0 && positionX < matrix.length && positionY >= 0 && positionY < matrix.length) {
                        double valor = matrix[positionX][positionY];
                        if (valor > maximo) {
                            maximo = valor;
                        }
                    }
                }
                novaMatrix[i][k] = maximo;
            }
        }
        return novaMatrix;
    }

    /**
     * Aplicar filtro do mínimo a uma matriz.
     */
    public double[][] filterMinimo(double[][] matrix) {
        double[][] novaMatrix = new double[matrix.length][matrix.length];
        int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix.length; k++) {
                double minimo = 255;
                for (int d = 0; d < directions.length; d++) {
                    int positionX = i + directions[d][0];
                    int positionY = k + directions[d][1];
                    if (positionX == -1 && positionY == -1 || positionX == matrix.length && positionY == -1 || positionX == -1 && positionY == matrix.length || positionX == matrix.length && positionY == matrix.length) {
                        int valor = 0;
                        if (valor < minimo) {
                            minimo = valor;
                        }
                    } else if (positionX == -1) {
                        double valor = matrix[positionX + 1][positionY];
                        if (valor < minimo) {
                            minimo = valor;
                        }
                    } else if (positionX == matrix.length) {
                        double valor = matrix[positionX - 1][positionY];
                        if (valor < minimo) {
                            minimo = valor;
                        }
                    } else if (positionY == -1) {
                        double valor = matrix[positionX][positionY + 1];
                        if (valor < minimo) {
                            minimo = valor;
                        }
                    } else if (positionY == matrix.length) {
                        double valor = matrix[positionX][positionY - 1];
                        if (valor < minimo) {
                            minimo = valor;
                        }
                    } else if (positionX >= 0 && positionX < matrix.length && positionY >= 0 && positionY < matrix.length) {
                        double valor = matrix[positionX][positionY];
                        if (valor < minimo) {
                            minimo = valor;
                        }
                    }
                }
                novaMatrix[i][k] = minimo;
            }
        }
        return novaMatrix;
    }

    /**
     * Aplicar filtro da mediana a uma matriz.
     */
    public double[][] filterMediana(double[][] matrix) {
        double[][] novaMatrix = new double[matrix.length][matrix.length];
        int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        double[] valores = new double[9];
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix.length; k++) {
                int contador = 0;
                for (int d = 0; d < directions.length; d++) {
                    int positionX = i + directions[d][0];
                    int positionY = k + directions[d][1];
                    if (positionX == -1 && positionY == -1 || positionX == matrix.length && positionY == -1 || positionX == -1 && positionY == matrix.length || positionX == matrix.length && positionY == matrix.length) {
                        valores[contador] = 0;
                        contador++;
                    } else if (positionX == -1) {
                        valores[contador] = matrix[positionX + 1][positionY];
                        contador++;
                    } else if (positionX == matrix.length) {
                        valores[contador] = matrix[positionX - 1][positionY];
                        contador++;
                    } else if (positionY == -1) {
                        valores[contador] = matrix[positionX][positionY + 1];
                        contador++;
                    } else if (positionY == matrix.length) {
                        valores[contador] = matrix[positionX][positionY - 1];
                        contador++;
                    } else if (positionX >= 0 && positionX < matrix.length && positionY >= 0 && positionY < matrix.length) {
                        valores[contador] = matrix[positionX][positionY];
                        contador++;
                    }
                }
                double[] sortedValores = Matriz.sortVect(valores);
                novaMatrix[i][k] = sortedValores[4];
            }
        }
        return novaMatrix;
    }
    

}
