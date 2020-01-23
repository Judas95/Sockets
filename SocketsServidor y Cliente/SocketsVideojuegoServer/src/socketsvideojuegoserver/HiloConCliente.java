/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketsvideojuegoserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zasca
 */
public class HiloConCliente extends Thread{
    Socket sk;
    public HiloConCliente(Socket sk){
        this.sk = sk;
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = sk.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            os = sk.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            while (true) {

                if (br.ready()) {
                    String option = br.readLine();
                    String resultado = Conexion.getInfo(option);
                    if (!resultado.equals("vacio")) {
                        bw.write(resultado);
                        bw.newLine();
                        bw.flush();
                    } else {
                        bw.write("sin resultado");
                        bw.newLine();
                        bw.flush();
                    }
                }

            }
            
        } catch (IOException ex) {
            Logger.getLogger(HiloConCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(is != null) is.close();
            } catch (IOException ex) {
                Logger.getLogger(HiloConCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    
}
