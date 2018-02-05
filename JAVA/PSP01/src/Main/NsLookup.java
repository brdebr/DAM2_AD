/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Scanner;

/*
Implement an order similar to nslookup of the operating system. To perform the implementation you will create a class called NsLookup. The class will have a main method by which you will receive the arguments of the order directly from the system. Remember that in Java the system arguments arrive in the parameter of the main method in format of character strings array’s. Each position in the array represents an argument of the command.
Specifically, our NsLookup command may receive either an argument or an IP or the name of a host. The command will analyze the parameter arrived from the operating system to detect if it is an IP address or the name of a host. It is necessary to detect any of the two versions of IP addresses.
Remember that the String class has a method called matches that allows you to pass a regular expression as a pattern, which can help you detect the addresses. You do not need to use a too complex expression. It's not about validating the addresses but knowing how to distinguish them from domain names or devices.
Once the type of argument is detected, if it is an IP address, we must find the name of the associated host, producing an output similar to the following:
Answer:
 */
/**
 *
 * @author brybre
 */
public class NsLookup {

    public static void main(String[] args) {
        if (args.length > 0) {
            String argumento = args[0];
            
//        String argumento = "";
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Di algo");
//        argumento = scanner.nextLine();

            if (argumento.trim().matches("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$")) {
                try {
                    InetAddress address = InetAddress.getByName(argumento);
                    System.out.println("Nombre host = " + address.getHostName());
                } catch (Exception e) {
                }
            } else {
                try {
                    InetAddress address = InetAddress.getByName(argumento);
                    System.out.println("Direccion IP = " + address.getHostAddress());
                } catch (Exception e) {
                }
            }
        }
    }

}
