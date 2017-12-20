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
public class prueba {

    public static void main(String[] args) {
//           HiloTest p1 = new HiloTest("p1");
//           p1.start();
//           
//           HiloTest p2 = new HiloTest("p2");
//           p2.start();
//           
//           HiloTest p3 = new HiloTest("p3");
//           p3.start();
        String aaa = String.format("%-10s%-10s", "lala","babala");
        String bbb = String.format("%10s%10s", "laladda","dadad");
        System.out.println(aaa);
        System.out.println(bbb);
    }

    public class Hilo extends Thread {

        String aux;
        
        public Hilo(String nombre) {
            this.aux = nombre;
        }
        
        @Override
        public void run() {
            //super.run(); //To change body of generated methods, choose Tools | Templates.
            for (int i = 0; i < 10; i++) {
                System.out.println(aux+"-> "+i);
            }
        }
        
    }
}
