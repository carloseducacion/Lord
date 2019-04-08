/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordclient;

import java.util.Scanner;

/**
 *
 * @author pcserrano
 */
public class UI {
    public static void print(String msg){
        System.out.println(msg);
    }
    
    public static String getIP(){
        String result;
        System.out.println("Por favor, mi Lord, introduzca la IP del Server:");
        Scanner s=new Scanner(System.in);
        result=s.nextLine();
        return result;
    }
    public static String[] readyForNewRequest(){
        Scanner teclado=new Scanner(System.in);
        String[] request=null;
        UI.print("Mi Lord, qué desea: (pulse h para ayuda o exit para salir)");
        String command=teclado.nextLine();
        switch(command){
            case "h":
                UI.ayuda(); 
                request=new String[1];
                request[0]="h";
                break;
            case "exit":
            case "die":
                request=new String[1];
                request[0]=command;
                break;
            case "execute":
                request=new String[2];
                request[0]=command;
                UI.print("Mi Lord, qué comando debe ejecutar Server");
                request[1]=UI.getCommand();
                break;
            case "writeTXT":
                request=new String[2];
                request[0]=command;
                UI.print("Mi Lord, qué mensaje desea almacenar en el archivo LordToldme.txt?");
                request[1]=UI.getLordToldmeMessage();
                break; 
            case "readTXT":
                request=new String[1];
                request[0]=command;
                break;
            case "writeFile":
                request=new String[2];
                request[0]=command;
                UI.print("Mi Lord, qué archivo desea enviar a Server?");
                request[1]=UI.getFiletoSend();
                break;
            case "readFile":
                request=new String[2];
                request[0]=command;
                UI.print("Mi Lord, qué archivo desea que Server le envíe?");
                request[1]=UI.getFiletoReceive();
                break;
            default:
                UI.print("Mi Lord, perdone, pero no reconozco ese comando");
        }
        return request;       
    }
    public static void ayuda(){
        UI.print("Comandos disponible, mi Lord");
        UI.print("> die: mata al Server y sale del programa Lord");
        UI.print("> execute: ejecuta un comando en el Server y muestra la salida en Lord");
        UI.print("> writeTXT: escribe un mensaje en el archivo LordToldme.txt en el Server");
        UI.print("> sendTXT: recibe el contenido del archivo LordToldme.txt en el Server");
        UI.print("> writeFile: envía el archivo indicado al Server");
        UI.print("> sendFile: recibe el archivo indicado desde el Server");
    }
    public static String getCommand(){
        return (new Scanner(System.in)).nextLine();
    }
    public static String getLordToldmeMessage(){
        return (new Scanner(System.in)).nextLine();
    }  
    public static String getFiletoSend(){
        return (new Scanner(System.in)).nextLine();
    }
    public static String getFiletoReceive(){
        return (new Scanner(System.in)).nextLine();
    }
    public static void printResponse(String status){
        UI.print("Server responsde: "+status);
    }
    public static void setFileCopied(String msg){
        UI.print(msg);
    }
    
    
    
}