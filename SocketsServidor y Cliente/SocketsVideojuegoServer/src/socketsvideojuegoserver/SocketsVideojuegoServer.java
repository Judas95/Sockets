/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketsvideojuegoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zasca
 */
public class SocketsVideojuegoServer {

    public static void main(String[] args) {
        final int PORT = 40080;
    
        try {
            ServerSocket sk = new ServerSocket(PORT);
            while(true){
                Socket socket = sk.accept();
                System.out.println("Alguien se conectó");
                HiloConCliente hilo = new HiloConCliente(socket);
                hilo.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketsVideojuegoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
