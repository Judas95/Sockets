/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketsvideojuego;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zasca
 */
public class JuegoCliente {

    
    final static int PORT = 40080;
    final static String HOST = "localhost";
    
    public static void main(String[] args) {
        try {
            Socket sk = new Socket(HOST, PORT);
            
            enviarMensajesAlServidor(sk);
        } catch (IOException ex) {
            Logger.getLogger(JuegoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void enviarMensajesAlServidor(Socket sk) {
        OutputStream os = null;
        InputStream is = null;
        try {
            os = sk.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            is = sk.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
           Scanner sc = new Scanner(System.in);
            String linea;

            while (true) {
                System.out.println("Select a tv show:"
                        + "\n 1. Pocoyo"
                        + "\n 2. Razer "
                        + "\n 3. Victor ");
                        

                linea = sc.nextLine();
                bw.write(linea);
                bw.newLine();
                bw.flush();
                linea = br.readLine();
                
                System.out.println(linea);
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(JuegoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(os != null) os.close();
            } catch (IOException ex) {
                Logger.getLogger(JuegoCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
