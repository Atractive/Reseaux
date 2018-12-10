package clair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientTCP {

    public static void main(String[] args) {

        Socket client = null;

        BufferedReader in;
        PrintWriter out;
        String msg;

        final Scanner sc = new Scanner(System.in);

        try {

            System.out.println("Entrez l'adresse IP du serveur");
            client = new Socket(sc.next(), 4444);
            
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);

            System.out.println("Connexion établie... Vous pouvez entrer votre message");
            while (true) {
                
                out.println(sc.next());
                out.flush();

                msg = in.readLine();
                System.out.println("Serveur : " + msg);
              
            }

        } catch (UnknownHostException e) {
            System.err.println("Destination Unknown");
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("Now to Investigate this IO issue");
            System.exit(-1);
        }
    }
}
