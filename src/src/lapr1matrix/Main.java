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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        * Garantir que tem os argumentos válidos
         */
        if (args.length <= 0) {
            //falta argumentos.
            System.out.println("Falta argumentos\nUSAGE: java -jar programa.jar nome_do_ficheiro.txt");
            System.exit(1);
        }
        /*
        * Show menu
        * A trabalhar na class Menu.java
         */
        Testes.runTestes();
        Menu m = new Menu();
        m.loop(args);
    }

}
