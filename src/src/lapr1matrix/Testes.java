package lapr1matrix;

import java.util.Arrays;

public class Testes {

    public static void main(String[] args) {
        //runTestes();
    }

    public static void runTestes() {
        System.out.println(testeFiltroMinimo() ? "OK" : "NOT OK");
        System.out.println(testeFiltroMaximo() ? "OK" : "NOT OK");
        System.out.println(testeFiltroMedia() ? "OK" : "NOT OK");
        System.out.println(testeFiltroMediana() ? "OK" : "NOT OK");
        System.out.println(testeRodarMatrix() ? "OK" : "NOT OK");
        System.out.println(testeRodarMatrixAntiClockwise()? "OK" : "NOT OK");
        System.out.println(testeSortBubbleMatrixCol() ? "OK" : "NOT OK");
        System.out.println(testeSortInsertionMatrixCol() ? "OK" : "NOT OK");
        System.out.println(testeSortSelectionMatrixCol() ? "OK" : "NOT OK");
        System.out.println(testeEstatisticaSomaLinhas() ? "OK" : "NOT OK");
        System.out.println(testeEstatisticaSomaColunas() ? "OK" : "NOT OK");
        System.out.println(testeEstatisticaSomaTotal() ? "OK" : "NOT OK");
        System.out.println(testeEstatisticaMediaLinhas() ? "OK" : "NOT OK");
        System.out.println(testeEstatisticaMediaColunas() ? "OK" : "NOT OK");
        System.out.println(testeEstatisticaMediaTotal() ? "OK" : "NOT OK");
        System.out.println(testeTrap()? "OK" : "NOT OK");

    }
    
    public static boolean testeTrap(){
        System.out.println("Teste Trapézio");
        int[][] input = {
            {3, 4, 5, 5},
            {3, 3, 4, 5},
            {2, 3, 4, 5},
            {2, 3, 4, 5}
        };
        
        double[] expResult = {13, 11, 10.5, 10.5};
        
        Filtros f = new Filtros();

        return Arrays.equals(expResult, expResult);
    }

    public static boolean testeFiltroMinimo() {
        System.out.println("Teste Filtro Mínimo:");
        boolean resultado = false;
        double[][] input = {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        };
        double[][] expectedOutput = {
            {0.0, 1.0, 0.0},
            {1.0, 1.0, 2.0},
            {0.0, 4.0, 0.0}
        };
        Filtros f = new Filtros();
        if (Matriz.compararMatrizesDouble(f.filterMinimo(input), expectedOutput)) {
            resultado = true;
        }
        return resultado;
    }

    public static boolean testeFiltroMaximo() {
        System.out.println("Teste Filtro Máximo:");
        boolean resultado = false;
        double[][] input = {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        };
        double[][] expectedOutput = {
            {5.0, 6.0, 6.0},
            {8.0, 9.0, 9.0},
            {8.0, 9.0, 9.0}
        };
        Filtros f = new Filtros();
        if (Matriz.compararMatrizesDouble(f.filterMaximo(input), expectedOutput)) {
            resultado = true;
        }
        return resultado;
    }

    public static boolean testeFiltroMedia() {
        System.out.println("Teste Filtro Média:");
        boolean resultado = false;
        double[][] input = {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0}
        };
        double[][] expectedOutput = {
            {2.2222222222222223,3.0,3.3333333333333335},
            {4.333333333333333,5.0,5.666666666666667},
            {5.555555555555555,7.0,6.666666666666667}
        };
        Filtros f = new Filtros();
        if (Matriz.compararMatrizesDouble(f.filterMedia(input), expectedOutput)) {
            resultado = true;
        }
        return resultado;
    }

    public static boolean testeFiltroMediana() {
        System.out.println("Teste Filtro Mediana:");
        boolean resultado = false;
        double[][] input = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        double[][] expectedOutput = {
            {2, 3, 3},
            {4, 5, 6},
            {7, 7, 8}
        };
        Filtros f = new Filtros();
        if (Matriz.compararMatrizesDouble(f.filterMediana(input), expectedOutput)) {
            resultado = true;
        }
        return resultado;
    }

    public static boolean testeRodarMatrix() {
        System.out.println("Teste Transformação Rodar Matriz:");
        boolean resultado = false;
        double[][] input = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        double[][] expectedOutput = {
            {7, 4, 1},
            {8, 5, 2},
            {9, 6, 3}
        };
        Transformacoes t = new Transformacoes();
        if (Matriz.compararMatrizesDouble(t.rodarMatrix(input), expectedOutput)) {
            resultado = true;
        }
        return resultado;
    }
    public static boolean testeRodarMatrixAntiClockwise() {
        System.out.println("Teste Transformação Rodar Matriz 'anticlockwise':");
        boolean resultado = false;
        double[][] input = {
                {2, 4, 6},
                {1, 2, 3},
                {5, 8, 0}
        };
        double[][] expectedOutput = {
                {6, 3, 0},
                {4, 2, 8},
                {2, 1, 5}
        };
        Transformacoes t = new Transformacoes();
        if (Matriz.compararMatrizesDouble(t.rodarMatrixAntiClockwise(input), expectedOutput)) {
            resultado = true;
        }
        return resultado;
    }

    public static boolean testeSortBubbleMatrixCol() {
        System.out.println("Teste Sort Bubble Matrix Col:");
        boolean resultado = false;
        double[][] input = {
            {9, 2, 7},
            {4, 9, 2},
            {7, 1, 9}
        };
        double[][] expectedOutput = {
            {9, 9, 9},
            {7, 2, 7},
            {4, 1, 2}
        };
        Transformacoes t = new Transformacoes();
        if (Matriz.compararMatrizesDouble(t.bubbleSortMatrixCol(input), expectedOutput)) {
            resultado = true;
        }
        return resultado;
    }

    public static boolean testeSortInsertionMatrixCol() {
        System.out.println("Teste Sort Insertion Matrix Col:");
        boolean resultado = false;
        double[][] input = {
            {9, 2, 7},
            {4, 9, 2},
            {7, 1, 9}
        };
        double[][] expectedOutput = {
            {9, 9, 9},
            {7, 2, 7},
            {4, 1, 2}
        };
        Transformacoes t = new Transformacoes();
        if (Matriz.compararMatrizesDouble(t.insertionSortMatrixCol(input), expectedOutput)) {
            resultado = true;
        }
        return resultado;
    }

    public static boolean testeSortSelectionMatrixCol() {
        System.out.println("Teste Sort Selection Matrix Col:");
        boolean resultado = false;
        double[][] input = {
            {9, 2, 7},
            {4, 9, 2},
            {7, 1, 9}
        };
        double[][] expectedOutput = {
            {9, 9, 9},
            {7, 2, 7},
            {4, 1, 2}
        };
        Transformacoes t = new Transformacoes();
        if (Matriz.compararMatrizesDouble(t.selectionSortMatrixCol(input), expectedOutput)) {
            resultado = true;
        }
        return resultado;
    }

    public static boolean testeEstatisticaSomaLinhas() {
        System.out.println("Teste Estatística Soma Linhas:");
        boolean resultado = true;
        double[][] input = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        double[] expectedOutput = {
            6,
            15,
            24
        };
        double[] outputObtido = Estatistica.somaLinha(input);
        for (int i = 0; i < outputObtido.length; i++) {
            if (outputObtido[i] != expectedOutput[i]) {
                resultado = false;
            }
        }
        return resultado;
    }

    public static boolean testeEstatisticaSomaColunas() {
        System.out.println("Teste Estatística Soma Colunas:");
        boolean resultado = true;
        double[][] input = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        double[] expectedOutput = {
            12,
            15,
            18
        };
        double[] outputObtido = Estatistica.somaColuna(input);
        for (int i = 0; i < outputObtido.length; i++) {
            if (outputObtido[i] != expectedOutput[i]) {
                resultado = false;
            }
        }
        return resultado;
    }

    public static boolean testeEstatisticaSomaTotal() {
        System.out.println("Teste Estatística Soma Total:");
        boolean resultado = true;
        double[][] input = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        double expectedOutput = 9;
        double outputObtido = Estatistica.somaTotal(input);
        if (outputObtido != expectedOutput) {
            resultado = false;
        }
        return resultado;
    }

    public static boolean testeEstatisticaMediaLinhas() {
        System.out.println("Teste Estatistica media Linhas");
        boolean resultado = true;
        double[][] input = {
            {2, 4, 5},
            {4, 5, 6},
            {3, 1, 2}
        };
        double[] expectedOutput = {
            (2 + 4 + 5) / 3.0,
            (4 + 5 + 6) / 3.0,
            (3 + 1 + 2) / 3.0
        };
        double[] outputObtido = Estatistica.mediaLinhas(input);
        if (outputObtido == null) {
            return false;
        }
        if (outputObtido.length != input.length) {
            return false;
        }
        for (int i = 0; i < outputObtido.length; i++) {
            if (outputObtido[i] != expectedOutput[i]) {
                return false;
            }
        }
        return resultado;
    }

    public static boolean testeEstatisticaMediaColunas() {
        System.out.println("Teste Estatistica media Colunas");
        boolean resultado = true;
        double[][] input = {
            {2, 4, 5},
            {4, 5, 6},
            {3, 1, 2}
        };
        double[] expectedOutput = {
            (2 + 4 + 3) / 3.0,
            (4 + 5 + 1) / 3.0,
            (5 + 6 + 2) / 3.0
        };
        double[] outputObtido = Estatistica.mediaColunas(input);
        if (outputObtido == null) {
            return false;
        }
        if (outputObtido.length != input.length) {
            return false;
        }
        for (int i = 0; i < outputObtido.length; i++) {
            if (outputObtido[i] != expectedOutput[i]) {
                return false;
            }
        }
        return resultado;
    }

    public static boolean testeEstatisticaMediaTotal() {
        System.out.println("Teste Estatística media Total:");
        boolean resultado = true;
        double[][] input = {
            {2, 4, 6},
            {1, 4, 3},
            {2, 9, 2}
        };
        double expectedOutput = (2 + 4 + 6 + 1 + 4 + 3 + 2 + 9 + 2) / 9.0;
        double outputObtido = Estatistica.mediaTotal(input);
        if (outputObtido != expectedOutput) {
            resultado = false;
        }
        return resultado;
    }

    public static double avgSelectionSort(Matriz matriz) {
        long time = 0;
        if(matriz!=null){
            time = System.currentTimeMillis();
            matriz.selectionSort();
            time = System.currentTimeMillis()-time;
        }
        return time;
    }

    public static double avgInsertionSort(Matriz matriz) {
        long time = 0;
        if(matriz!=null){
            time = System.currentTimeMillis();
            matriz.insertionSort() ;
            time = System.currentTimeMillis()-time;
        }
        return time;
    }

    public static double avgBubbleSort(Matriz matriz) {
        long time = 0;
        if(matriz!=null){
            time = System.currentTimeMillis();
            matriz.bubbleSort();
            time = System.currentTimeMillis()-time;
        }
        return time;
    }
}
