/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author pcserrano
 */
public class Client {
   private static final int PORT = 9001;
    Socket mysocket;
    String ip;
    
    static InputStream in;
    static OutputStream out;
    
    String command;
    String parameter;
    String parameterextra;
    
    public Client() throws IOException{
        ip=UI.getIP();
    }
    public void connect() throws IOException{
            UI.print("Soy el Lord");
            //Bloquea ejecución hasta que no hay conexión establecida
            mysocket=new Socket(ip,PORT);
            UI.print("Tu Lord ha llegado, sírveme bien Server");
            in=mysocket.getInputStream();
            out=mysocket.getOutputStream();
            
            boolean exit=false;
            
            while(!exit){
                String[] request=UI.readyForNewRequest();
                if(request!=null){
                    Controller.doTask(request);
                }
            }
    } 
    public static String readIn(){
        StringBuilder input= new StringBuilder();
        byte[] contents = new byte[1024];
        int bytesRead = 0;
        boolean end=false;
        while(!end) { 
            try{
                bytesRead = in.read(contents);
                input.append(new String(contents,0,bytesRead)); 
                if (bytesRead < 1024 ){
                    end = true;
                }
            }catch(IOException e){
                e.printStackTrace();
                end=true;
            }
        }
        return input.toString();
    }
    public static void writeOut(byte[] msg){
       try{ 
        out.write(msg);
        out.flush();
       }catch(IOException e){
           e.printStackTrace();
       }
    }
}
