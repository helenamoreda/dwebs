package ip;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TestIP_1 {

    public static void main(String args[]) {
        InetAddress direccion;
        String nOrdenador;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Nombre del ordenador");
            nOrdenador = sc.nextLine();
            direccion = InetAddress.getByName(nOrdenador);
            System.out.print("Dirección IP: ");
            System.out.println(direccion.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("Ordenador desconocido");
        } catch (Exception e) {
            System.out.println("Error en la lectura");
        }

        sc.close();
    }
}
