package lapr1;

import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.decomposition.EigenDecompositor;

public class Eigen {

    public static double[] getEigenValues(double[][] matrix) {
        // Criar objeto do tipo Matriz
        Matrix a = new Basic2DMatrix(matrix);
        // Obtem valores e vetores próprios fazendo "Eigen Decomposition"
        EigenDecompositor eigenD = new EigenDecompositor(a);
        Matrix[] mattD = eigenD.decompose();
        double eigenValueMatrix[][] = mattD[1].toDenseMatrix().toArray();
        double[] eigenValues = new double[eigenValueMatrix.length];
        for (int i = 0; i < eigenValues.length; i++) {
            eigenValues[i] = eigenValueMatrix[i][i];
        }
        return eigenValues;
    }

    public static double[][] getEigenVectors(double[][] matrix) {
        // Criar objeto do tipo Matriz
        Matrix a = new Basic2DMatrix(matrix);
        // Obtem valores e vetores próprios fazendo "Eigen Decomposition"
        EigenDecompositor eigenD = new EigenDecompositor(a);
        Matrix[] mattD = eigenD.decompose();
        double eigenVectorMatrix[][] = mattD[0].toDenseMatrix().toArray();
        return eigenVectorMatrix;
    }

}