/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr1matrix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Formatter;
import javafx.scene.shape.Path;

/**
 *
 * @author pushdword
 *
 * Esta class é destinada a fazer operações aos ficheiros. assim como ler,
 * escrever, e outras funcionalidades. Além de mais, é destinada a certificar
 * que o programa faz as validações a certos parametros.
 */
public class Ficheiros {

    private int MAX_BUFFER_LEN = 255;
    private String descricao;
    private int dimensao = -1;

    /*
    * Este método, protected, apenas é acessivel dentro de Ficheiros.
    * Este valida se o ficheiro existe ou se não temos permissões.
     */
    protected int isFileExists(String path) {

        try {
            FileReader fr = new FileReader(path);//test
        } //ficheiro não encontrado
        catch (FileNotFoundException ex) {
            return -1;
        } //outro erro: Permissões no disco?
        catch (Exception ex) {
            return -2;
        }
        return 0;//file exists.
    }

    /*
    * Este método verifica que o ficheiro está vazio.
    *
     */
    protected boolean hasContents(FileReader f) {
        try {
            if (f.ready()) {
                char[] buffer = new char[MAX_BUFFER_LEN];
                f.read(buffer, 0 /* we don't want offset*/, MAX_BUFFER_LEN);
                if (buffer.length > 0) {
                    //yes. it has contents
                    return true;
                } else {
                    //no content. please, fill it.
                    return false;
                }
            } else {
                //something's strange it's java. get used to it.
                return false;
            }
        } catch (Exception ex) {
            //dummy catch.
        }
        return false;//it shouldn't be here.
    }

    public int[][] getMatrix(String path) {

        int[][] matrix = null;
        int dimensaoit = 0; //iteração da dimensao nos ciclos
        switch (isFileExists(path)) {
            case -1:
                System.out.println("O ficheiro " + path + " não foi encontrado\n" + System.getProperty("user.dir"));
                return null;
            case -2:
                System.out.println("Existiu um outro erro. Verifique se o programa tem permissões para ler.");
                return null;
            default:
                break; //
        }
        try {
            FileReader fr = new FileReader(path);//temos a certeza que não crasha -> são por race condution.
            if (!hasContents(fr)) {
                System.out.println("O ficheiro está vazio...");
                System.exit(-1);
            } else {
                //Obter a primeira linha. descrição[espaço][espaço]número
                InputStream fis = new FileInputStream(new File(path));
                BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
                int linha = -1;
                String line;
                while ((line = bf.readLine()) != null) {
                    linha++;
                    //Isto corre a cada linha.
                    switch (linha) {
                        case 0: {
                            //tem que ter a descrição[espaço][espaço]número
                            if (line.length() < 4) {
                                /*
                                * A matriz não está bem escrita.
                                 */
                                System.out.println("Primeira linha do ficheiro está errado.\nO ficheiro deve conter uma descrição separada por 2 espaços e o número da ordem");
                                return null;
                            } else {
                                //* obter descrição
                                // find double space
                                String[] splitedFirstline = line.split("  ");
                                if (splitedFirstline.length == 2) {
                                    //ok, verificar se o ultimo não é um int.
                                    if (splitedFirstline[1].length() >= 1 && Integer.parseInt(splitedFirstline[1]) > 0) {
                                        //se o número existe, e se é realmente um número.
                                        dimensao = Integer.parseInt(splitedFirstline[1]);
                                        descricao = splitedFirstline[0];
                                        matrix = new int[dimensao][dimensao];//matriz quadrada
                                    } else {
                                        //erro, validar se contém um número válido positivo.
                                        System.out.println("Erro no ficheiro\nVerifique se contém descrição[espaço][espaço]número");
                                        return null;
                                    }
                                } else {
                                    //erro de sintax.
                                    System.out.println("Erro no ficheiro\nVerifique se contém descrição[espaço][espaço]número");
                                    return null;
                                }
                            }
                            break;
                        }
                        case 1: {
                            //tem que ser um break line
                            if (line.length() != 0) {
                                //erro, tem que ser um break line
                                System.out.println("Erro no ficheiro\nVerifique se na segunda linha tem um break line");
                                return null;
                            }
                            break;
                        }
                        default: {
                            //resto das linhas
                            //verificar se o número de linha é realmente
                            if (stringToVect(line) != null && matrix != null) {
                                //então foi possivel obter o vetor da matrix.
                                matrix[dimensaoit++] = stringToVect(line);
                            } else {
                                System.out.println("Erro no ficheiro\nVerifique se na matriz, na linha " + (dimensaoit + 1) + ", tem conteúdos\nDimensão:" + dimensao);
                                return null;
                            }

                            break;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            //ups
        }
        return matrix;
    }

    public double[][] getMatrixDouble(String path) {

        double[][] matrix = null;
        int dimensaoit = 0; //iteração da dimensao nos ciclos
        switch (isFileExists(path)) {
            case -1:
                System.out.println("O ficheiro " + path + " não foi encontrado\n" + System.getProperty("user.dir"));
                return null;
            case -2:
                System.out.println("Existiu um outro erro. Verifique se o programa tem permissões para ler.");
                return null;
            default:
                break; //
        }
        try {
            FileReader fr = new FileReader(path);//temos a certeza que não crasha -> são por race condution.
            if (!hasContents(fr)) {
                System.out.println("O ficheiro está vazio...");
                System.exit(-1);
            } else {
                //Obter a primeira linha. descrição[espaço][espaço]número
                InputStream fis = new FileInputStream(new File(path));
                BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
                int linha = -1;
                String line;
                while ((line = bf.readLine()) != null) {
                    linha++;
                    //Isto corre a cada linha.
                    switch (linha) {
                        case 0: {
                            //tem que ter a descrição[espaço][espaço]número
                            if (line.length() < 4) {
                                /*
                                * A matriz não está bem escrita.
                                 */
                                System.out.println("Primeira linha do ficheiro está errado.\nO ficheiro deve conter uma descrição separada por 2 espaços e o número da ordem");
                                return null;
                            } else {
                                //* obter descrição
                                // find double space
                                String[] splitedFirstline = line.split("  ");
                                if (splitedFirstline.length == 2) {
                                    //ok, verificar se o ultimo não é um int.
                                    if (splitedFirstline[1].length() >= 1 && Integer.parseInt(splitedFirstline[1]) > 0) {
                                        //se o número existe, e se é realmente um número.
                                        dimensao = Integer.parseInt(splitedFirstline[1]);
                                        descricao = splitedFirstline[0];
                                        matrix = new double[dimensao][dimensao];//matriz quadrada
                                    } else {
                                        //erro, validar se contém um número válido positivo.
                                        System.out.println("Erro no ficheiro\nVerifique se contém descrição[espaço][espaço]número");
                                        return null;
                                    }
                                } else {
                                    //erro de sintax.
                                    System.out.println("Erro no ficheiro\nVerifique se contém descrição[espaço][espaço]número");
                                    return null;
                                }
                            }
                            break;
                        }
                        case 1: {
                            //tem que ser um break line
                            if (line.length() != 0) {
                                //erro, tem que ser um break line
                                System.out.println("Erro no ficheiro\nVerifique se na segunda linha tem um break line");
                                return null;
                            }
                            break;
                        }
                        default: {
                            //resto das linhas
                            //verificar se o número de linha é realmente
                            if (stringToVect(line) != null && matrix != null) {
                                //então foi possivel obter o vetor da matrix.
                                matrix[dimensaoit++] = stringToVectDouble(line);
                            } else {
                                System.out.println("Erro no ficheiro\nVerifique se na matriz, na linha " + (dimensaoit + 1) + ", tem conteúdos\nDimensão:" + dimensao);
                                return null;
                            }

                            break;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            //ups
        }
        return matrix;
    }
    
    public String getCompressedMatrix(String path){
        String matrix = null;
        switch (isFileExists(path)) {
            case -1:
                System.out.println("O ficheiro " + path + " não foi encontrado\n" + System.getProperty("user.dir"));
                return null;
            case -2:
                System.out.println("Existiu um outro erro. Verifique se o programa tem permissões para ler.");
                return null;
            default:
                break; //
        }
        try {
            FileReader fr = new FileReader(path);//temos a certeza que não crasha -> são por race condution.
            if (!hasContents(fr)) {
                System.out.println("O ficheiro está vazio...");
                System.exit(-1);
            } else {
                InputStream fis = new FileInputStream(new File(path));
                BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
                int linha = -1;
                String line;
                while ((line = bf.readLine()) != null) {
                    linha++;
                    //Isto corre a cada linha.
                    switch (linha) {
                        case 0: {
                            if (line.length() < 4) {
                                /*
                                * A matriz não está bem escrita.
                                 */
                                System.out.println("Primeira linha do ficheiro está errado.\nO ficheiro deve conter uma descrição separada por 2 espaços e o número da ordem");
                                return null;
                            } else {
                                //* obter descrição
                                // find double space
                                String[] splitedFirstline = line.split("  ");
                                if (splitedFirstline.length == 2) {
                                    //ok, verificar se o ultimo não é um int.
                                    if (splitedFirstline[1].length() >= 1 && Integer.parseInt(splitedFirstline[1]) > 0) {
                                        //se o número existe, e se é realmente um número.
                                        dimensao = Integer.parseInt(splitedFirstline[1]);
                                        descricao = splitedFirstline[0];
                                        matrix = "";
                                    } else {
                                        //erro, validar se contém um número válido positivo.
                                        System.out.println("Erro no ficheiro\nVerifique se contém descrição[espaço][espaço]número");
                                        return null;
                                    }
                                } else {
                                    //erro de sintax.
                                    System.out.println("Erro no ficheiro\nVerifique se contém descrição[espaço][espaço]número");
                                    return null;
                                }
                            }
                            break;
                        }
                        case 1: {
                            //tem que ser um break line
                            if (line.length() != 0) {
                                //erro, tem que ser um break line
                                System.out.println("Erro no ficheiro\nVerifique se na segunda linha tem um break line");
                                return null;
                            }
                            break;
                        }
                        default: {
                            matrix+=line+"\n";
                            break;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.exit(1);
        }
        return matrix;
    }

    private int[] stringToVect(String s) {
        try {
            if (s.length() == 0) {
                return null;
            }
            String[] vetor = s.split(",");
            if (vetor.length <= 0) {
                //err
                return null;
            }
            int[] vetorRetorno = new int[vetor.length];
            for (int pos = 0; vetor.length > pos; pos++) {
                if (Integer.parseInt(vetor[pos]) >= 0 && Integer.parseInt(vetor[pos]) <= 255) //validar se é um valor entre 0 e 255
                {
                    vetorRetorno[pos] = Integer.parseInt(vetor[pos]);
                } else {
                    return null; //se não for, erro.
                }
            }
            return vetorRetorno;
        } catch (Exception ex) {
            return null;
        }
    }

    private double[] stringToVectDouble(String s) {
        try {
            if (s.length() == 0) {
                return null;
            }
            String[] vetor = s.split(",");
            if (vetor.length <= 0) {
                //err
                return null;
            }
            double[] vetorRetorno = new double[vetor.length];
            for (int pos = 0; vetor.length > pos; pos++) {
                if (Integer.parseInt(vetor[pos]) >= 0 && Integer.parseInt(vetor[pos]) <= 255) //validar se é um valor entre 0 e 255
                {
                    vetorRetorno[pos] = Integer.parseInt(vetor[pos]);
                } else {
                    return null; //se não for, erro.
                }
            }
            return vetorRetorno;
        } catch (Exception ex) {
            return null;
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDimensao() {
        return dimensao;
    }

    //Write matrix to an exinting file
    public boolean writeMatrixEx(Matriz m) {
        if (m == null) {
            return false;
        }
        if (m.getDescricao() == null || m.getDimensao() == 0) {
            return false;
        }
        try {
            PrintWriter writer = new PrintWriter(m.getPath() + ".txt", "UTF-8"); //encoding
            writer.write(m.toString());
            writer.close();
        } catch (Exception ex) {
            System.out.println("Erro ao tentar abrir ficheiro\nErr:" + ex.getMessage());
        }
        return false;
    }

    public boolean deleteFile(String filePath) {
        try {
            File f = new File(filePath);
            if (!f.delete()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            return false;//err, maybe permissions?
        }
    }

    public String writeMatrixCsv(Matriz m) {
        String returnpath = null;
        if (m == null) {
            return null;
        }
        if (m.getDescricao() == null || m.getDimensao() == 0) {
            return null;
        }
        try {
            returnpath = m.getPath() + "." + System.currentTimeMillis() + ".tmp";
            PrintWriter writer = new PrintWriter(returnpath, "UTF-8"); //encoding
            writer.write(m.toStringCsv());
            writer.close();
        } catch (Exception ex) {
            System.out.println("Erro ao tentar abrir ficheiro\nErr:" + ex.getMessage());
        }
        return returnpath;
    }

    public static void EscreverCompressao(Matriz M) {
        try {
            Formatter fOutput = new Formatter(new File("ResultadosCompressao.txt"));
            String fileOutputStr;
            fileOutputStr = M.printCompressed(true);
            fOutput.format(fileOutputStr);
            fOutput.close();
        } catch (Exception ex) {
            //err
        }
    }
}
