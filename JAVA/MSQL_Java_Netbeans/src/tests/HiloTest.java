/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

/**
 *
 * @author brybre
 */
public class HiloTest extends Thread {

    private final String aux;

    public HiloTest(String nombre) {
        this.aux = nombre;
    }

    @Override
    public void run() {
        //super.run(); //To change body of generated methods, choose Tools | Templates.
        for (int i = 0; i < 10; i++) {
            System.out.println(aux + "-> " + i);
        }
    }

}
