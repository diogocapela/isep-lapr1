package lapr1matrix;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.la4j.Matrix;
import org.la4j.matrix.DenseMatrix;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.decomposition.EigenDecompositor;
import org.la4j.decomposition.SingularValueDecompositor;

public class Compressao {

    public String compressMatrix(double[][] rawMatrix, int compressLvl) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.###",otherSymbols);
        // String da imagem comprimida
        String compressedMatrixString = "";
        // Decompor a matriz de entrada (rawMatrix) através do algoritmo SVD em 3 matrizes
        SingularValueDecompositor svd = new SingularValueDecompositor(new Basic2DMatrix(rawMatrix));
        Matrix[] m = svd.decompose();
        // Matriz A (U)
        double[][] matA = m[0].toDenseMatrix().toArray();
        // Matriz B (V)
        double[][] matB = m[2].toDenseMatrix().toArray();
        // Matriz dos valores singulares (VS)
        double[][] matValSingulares = m[1].toDenseMatrix().toArray();
        // Escrever imagem comprimida na string
        for (int i = 0; i < compressLvl; i++) {
            // Se o valor singular for diferente de zero
            if(matValSingulares[i][i] != 0) {
                // Escrever valor singular
                compressedMatrixString = compressedMatrixString + df.format(matValSingulares[i][i]) + "\n";
                // Escrever vector A
                for (int a = 0; a < matA.length; a++) {
                    if (a == matA.length - 1) {
                        compressedMatrixString = compressedMatrixString + df.format(matA[a][i]) + "\n";
                    } else {
                        compressedMatrixString = compressedMatrixString + df.format(matA[a][i]) + ",";
                    }
                }
                // Escrever vector B
                for (int b = 0; b < matB.length; b++) {
                    if (b == matB.length - 1) {
                        compressedMatrixString = compressedMatrixString + df.format(matB[b][i]) + "\n";
                    } else {
                        compressedMatrixString = compressedMatrixString + df.format(matB[b][i]) + ",";
                    }
                }
            }
        }
        Transformacoes t = new Transformacoes();
        int[][] decompressedMatrix = t.transformarMatrizDeDoublesEmInt(decompressMatrix(compressedMatrixString));
        // Calcular erro absoluto médio
        double somatorioErroAbsolutoMedio = 0;
        for(int i = 0; i < rawMatrix.length; i++) {
            for(int k = 0; k < rawMatrix.length; k++) {
                somatorioErroAbsolutoMedio = somatorioErroAbsolutoMedio + Math.abs(rawMatrix[i][k] - decompressedMatrix[i][k]);
            }
        }
        double erroAbsolutoMedio = ((double) 1 / ((double) rawMatrix.length * (double) rawMatrix.length)) * (double) somatorioErroAbsolutoMedio;
        System.out.println("Erro absoluto médio: " + erroAbsolutoMedio);
        // Calcular perda de informação
        double infoInicial = rawMatrix.length * rawMatrix.length;
        double infoComprimida = ((rawMatrix.length * 2) + 1) * compressLvl;
        double perdaInformacao = infoInicial - infoComprimida;
        System.out.println("Informação Inicial: " + infoInicial);
        System.out.println("Informação Após Compressão: " + infoComprimida);
        System.out.println("Ganhos de espaço: " + perdaInformacao);
        return compressedMatrixString;
    }
    
    public double[][] decompressMatrix(String compressedMatrix) {
        int compressLevel = compressedMatrix.split("\n").length / 3;
        int tamanhoImagem = compressedMatrix.split("\n")[1].split(",").length;
        double[][] imagem = new double[tamanhoImagem][tamanhoImagem];
        String[] linhasVectores = compressedMatrix.split("\n");
        for (int c = 0; c < compressLevel; c++) {
            double valorSingular = Double.parseDouble(linhasVectores[0 + 3 * c]);
            String[] vectorA = linhasVectores[1 + 3 * c].split(",");
            String[] vectorB = linhasVectores[2 + 3 * c].split(",");
            for (int i = 0; i < tamanhoImagem; i++) {
                for (int k = 0; k < tamanhoImagem; k++) {
                    imagem[i][k] += valorSingular * Double.parseDouble(vectorA[i]) * Double.parseDouble(vectorB[k]);
                }
            }
        }
        return imagem;
    }
    
    public static String writeCompressedMatrixToFile(String compressedMatrix) {
        String date = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
        String fileName = date + ".compix";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8"); //encoding
            writer.write(compressedMatrix);
            writer.close();
            System.out.println("Escrito.");
        } catch (Exception ex) {
            System.out.println("Erro ao tentar abrir ficheiro\nErr:" + ex.getMessage());
        } 
        return System.getProperty("user.dir")+"\\"+fileName;
    }
    
}
