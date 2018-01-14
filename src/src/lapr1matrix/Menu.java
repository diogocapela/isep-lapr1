/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr1matrix;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author pushdword
 */
public class Menu {

    private void write(Matriz mutil, Ficheiros file) {
        print("Tem a certeza que pretende escrever em:" + mutil.getPath() + ".txt" + "\n" + "Se o ficheiro destino existir irá ser substituido.\nMatriz:\n");
        mutil.printM();
        print("Sim ou não? ('s'/'n'):");
        Scanner sc = new Scanner(System.in);
        char input;
        do {
            try {
                input = sc.next().charAt(0);
                if (input != 's' && input != 'n') {
                    print("\nInsira apenas: 's' para sim ou 'n' para não:");
                }
            } catch (Exception ex) {
                print("\nInsira apenas: 's' para sim ou 'n' para não:");
                input = ' ';
            }
        } while (input != 's' && input != 'n');
        switch (input) {
            case 's':
                file.writeMatrixEx(mutil);
                break;
            case 'n':
                break;
        }
    }

    //vars
    enum cmds {
        showMatrix, rotate90, filtroMedia, filtroMax, filtroMin, filtroMediana, sair, write, sort, showStatistics, rotate90Anticlock,
        integrarTrapezios, filtroConv, filtroVariancia, showGnuplot, showLosses, showErrAbsMedio, writeCompressed
    }
    String text
            = "\n======================================================================\n"
            + "(0) Sair\t\t(1) Guardar\t\t(2) Guardar comprimida\n"
            + "(3) Mostrar matriz\t(4) Abrir no GnuPlot\t\n"
            + "(5) Mostrar estatísticas(6) Rodar 90º direita\n"
            + "(7) Rodar 90º esquerda\t(8) Filtro média\t(9) Filtro máximo\n"
            + "(10) Filtro mínimo\t(11) Filtro mediana\t(12) Filtro convolução\n"
            + "(13) Filtro variância\t(14) Ordenar\t\t\n"
            + "=======================================================================\nComando> ";

    //functions
    private cmds getCmd(int cmd) {
        switch (cmd) {
            case 0:
                return cmds.sair;
            case 1:
                return cmds.write;
            case 2:
                return cmds.writeCompressed;
            case 3:
                return cmds.showMatrix;
            case 4:
                return cmds.showGnuplot;
            case 5:
                return cmds.showStatistics;
            case 6:
                return cmds.rotate90;
            case 7:
                return cmds.rotate90Anticlock;
            case 8:
                return cmds.filtroMedia;
            case 9:
                return cmds.filtroMax;
            case 10:
                return cmds.filtroMin;
            case 11:
                return cmds.filtroMediana;
            case 12:
                return cmds.filtroConv;
            case 13:
                return cmds.filtroVariancia;
            case 14:
                return cmds.sort;

            default:
                return null;
        }
    }

    public void loop(String[] args) {
        cmds comando;
        Ficheiros file = new Ficheiros();
        Matriz mutil = new Matriz();
        /**/
        File f = new File(args[0]);

        if(f.isDirectory()) {
            /*
            * Decompress all compressed files
             */
            int i=0;
            File[] lf = f.listFiles();
            Matriz[] listaDeMatrizes = new Matriz[lf.length];
            for (File sf : lf){
                if(sf.getName().endsWith(".compix")){                    
                    /*Decompress*/
                    listaDeMatrizes[i] = new Matriz();
                    Compressao c = new Compressao();
                    listaDeMatrizes[i].setPath(sf.getAbsolutePath());
                    listaDeMatrizes[i].setMd(c.decompressMatrix(file.getCompressedMatrix(sf.getAbsolutePath())));
                    listaDeMatrizes[i].setDescricao(file.getDescricao());
                    listaDeMatrizes[i].setDimensao(file.getDimensao());
                    file.writeMatrixEx(listaDeMatrizes[i]);
                }
                else
                {
                    listaDeMatrizes[i] = new Matriz();
                    listaDeMatrizes[i].setPath(sf.getAbsolutePath());
                    listaDeMatrizes[i].setDescricao(file.getDescricao());
                    listaDeMatrizes[i].setDimensao(file.getDimensao());
                    listaDeMatrizes[i].setMd(file.getMatrixDouble(sf.getAbsolutePath()));
                }
                i++;
            }
            /*Save everything into a subfolder*/
            String tmpFolder = "temp"+System.currentTimeMillis()+"";
            //create folder
            if((new File(tmpFolder)).mkdirs()){
                for(Matriz m : listaDeMatrizes){
                    m.setPath(tmpFolder+"\\"+m.getDescricao());
                    file.writeMatrixCsv(m);
                }
                try{
                Runtime rt = Runtime.getRuntime();
                Process prcs = rt.exec("scriptGnuplot.bat "+tmpFolder);
                }
                catch(Exception ex){
                    print("Não foi possível abrir o script bat\n");
                }
            }
            else{
                print("Não foi possível criar o diretório "+tmpFolder);
                System.exit(1);
            }
            System.exit(0);
        }
        if(f.getAbsolutePath().endsWith(".compix")){
            Compressao c = new Compressao();
            mutil.setMd(c.decompressMatrix(file.getCompressedMatrix(f.getAbsolutePath())));
            file.writeMatrixEx(mutil);
        }
        else
            mutil.setMd(file.getMatrixDouble(f.getAbsolutePath()));
        /*
        * Validar se a matrix é ou não quadrada e indicar onde é que está mal
         */
        LinkedList<Integer> linhasErradas = mutil.isMatrixSquare();
        if (linhasErradas == null) {
            System.out.println("Tem um erro no ficheiro.\nVerifique se está bem escrito.");
            return;
        }
        if (linhasErradas.size() > 0) {
            System.out.print("Existe linha(s) com dimensão errada(s).\nLinha(s): ");
            for (Integer i : linhasErradas) {
                System.out.print(i + "ª ");
            }
            System.out.println();
            return;
        }
        if (mutil.getMd() == null) {
            print("Erro ao carregar a matriz.\nVerifique se está bem escrito\n");
            return;
        } else {
            mutil.setDescricao(file.getDescricao());
            mutil.setDimensao(file.getDimensao());
            mutil.setPath(f.getAbsolutePath().replaceAll(".txt", ""));
        }
        print("Matrix carregada:\n");
        mutil.printM();
        while (true) {
            print(text);//show menu.
            try {
                Scanner sc = new Scanner(System.in);
                comando = getCmd(sc.nextInt());
                if (comando == null) {
                    throw new Exception();
                }
            } catch (Exception ex) {
                print("Digite um numero valido\n");
                continue;
            }
            switch (comando) {
                case showMatrix: {
                    mutil.printM();
                    break;
                }
                case filtroMax: {
                    if (!mutil.filtroMaximo()) {
                        print("Aconteceu algum erro ao aplicar o filtro máximo\n");
                    }
                    break;
                }
                case filtroMedia: {
                    if (!mutil.filtroMedia()) {
                        print("Aconteceu algum erro ao aplicar o filtro media\n");
                    }
                    break;
                }
                case filtroMediana: {
                    if (!mutil.filtroMediana()) {
                        print("Aconteceu algum erro ao aplicar o filtro mediana\n");
                    }
                    break;
                }
                case filtroMin: {
                    if (!mutil.filtroMinimo()) {
                        print("Aconteceu algum erro ao aplicar o filtro mínimo\n");
                    }
                    break;
                }
                case rotate90: {
                    if (!mutil.rodar90()) {
                        print("Aconteceu algum erro ao rodar\n");
                    }
                    break;
                }
                case sort: {
                    if (!mutil.insertionSort()) {
                        print("Aconteceu algum erro ao ordenar\n");
                    }
                    break;
                }
                case sair: {
                    System.exit(0);
                    break;
                }
                case write: {
                    write(mutil, file);
                    break;
                }
                case showStatistics: {
                    if (!mutil.showStatistics()) {
                        print("Aconteceu algum erro ao mostrar a estatistica\n");
                    }
                    break;
                }
                case rotate90Anticlock: {
                    if(!mutil.rodar90AntiClockwise()){
                        print("Aconteceu algum erro ao rodar\n");
                    }
                    break;
                }

                case filtroConv: {
                    print("Peso do filtro:");
                    Scanner sc = new Scanner(System.in);
                    int peso = sc.nextInt();
                    if(!mutil.filtroConv(peso)){
                        print("Não é possível aplicar o filtro.\n");
                    }
                    break;
                }
                case filtroVariancia: {
                    if(!mutil.filtroVar()){
                        print("Aconteceu alguma coisa ao aplicar o filtro de variância.\n");
                    }
                    break;
                }
                case showGnuplot: {
                    mutil.showGnuplot();
                    break;
                }
                case showLosses: {
                    print("Ainda não está disponível\n");
                    break;
                }
                case showErrAbsMedio: {
                    print("Ainda não está disponível\n");
                    break;
                }
                case writeCompressed: {
                    /*
                    * Guardar comprimida
                     */
                    print("Nível de compressão\n(0) Sair\nCompressão:");
                    int compresslvl=-1;
                    do {
                        try {
                            Scanner sc = new Scanner(System.in);
                            compresslvl = sc.nextInt();
                            if (compresslvl < 0) {
                                throw new Exception();
                            }
                        } catch (Exception ex) {
                            print("Digite um numero valido\n");
                        }
                    } while (compresslvl < 0);
                    /*
                    * Compress
                    */
                    Compressao c = new Compressao();
                    mutil.compress(compresslvl);
                    String matrizToWrite=mutil.printCompressed(false);
                    String compressedMatrixPath = Compressao.writeCompressedMatrixToFile(matrizToWrite);
                    print("Escrito em:"+compressedMatrixPath+"\n");
                    Matriz mShow = new Matriz();
                    mShow.setMd(c.decompressMatrix(file.getCompressedMatrix(compressedMatrixPath)));
                    mShow.setDescricao(file.getDescricao());
                    mShow.setDimensao(file.getDimensao());
                    mShow.setPath(file.writeMatrixCsv(mShow));
                    mShow.showGnuplot();
                    print("Pretende guardar (S/N)?:");
                    Scanner sc;
                    char opt;
                    do {
                        sc = new Scanner(System.in);
                        opt = sc.next().charAt(0);
                        switch (opt) {
                            case 'S': {
                                mutil.setMd(mShow.getMd());
                                break;
                            }
                            case 'N': {
                                break;
                            }
                        }
                    } while (opt != 'S' && opt != 'N');
                    break;
                }
                default: {
                    print("Não existe esse comando\n");
                    break;
                }
            }
        }
    }

    private void print(String ptr) {
        System.out.print(ptr);
    }
}
