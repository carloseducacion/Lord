/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author pcserrano
 */
public class Server {
    private static final int PORT = 9001;
    
    ServerSocket listener;
    Socket mysocket;
    
    static InputStream in;
    static OutputStream out;
    
    String command;
    
    public Server() throws IOException{
        listener=new ServerSocket(PORT);
        command="";
    }
    public void connect() throws IOException{
        while(true){
            
            try{
                UI.print("Su Server está listo para recibir órdenes");
                //Bloquea ejecución hasta que no hay conexión establecida
                mysocket=listener.accept();
                UI.print("Cuanto tiempo mi Lord");
                in=mysocket.getInputStream();
                out=mysocket.getOutputStream();

                do{
                    UI.print("Ordene, mi señor.");
                    String request=readIn();
                    String[] requestDecoded=Communication.getRequest(request);
                    command=requestDecoded[0];
                    Controller.doTask(requestDecoded);

                }while(!command.equals("exit"));
                in.close();
                out.close();
                mysocket.close();
            }catch(Exception e){
                e.printStackTrace();
                in.close();
                out.close();
                mysocket.close();
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
