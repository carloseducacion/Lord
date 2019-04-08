/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordclient;

/**
 *
 * @author carlosserrano
 */
public class Controller {
    public static void doTask(String[] commands){
        
        String[] requestArray;
        String request;
        String responseString;
        String[] response;
        
        switch(commands[0]){
            case "exit": UI.print("Debo marchar, pero volveré"); System.exit(0); break;
            case "die":
                requestArray=new String[1];
                requestArray[0]="die";
                request=Communication.setResquest(commands);
                Client.writeOut(request.getBytes());
                UI.print("El cuervo acaba de salir, Server debe morir");
                System.exit(0);
                break;
            case "execute":
                request=Communication.setResquest(commands);
                Client.writeOut(request.getBytes());
                UI.print("El cuervo acaba de salir, Server debe ejecutar");
                responseString=Client.readIn();
                response=Communication.getResponse(responseString);
                UI.printResponse(response[0]);
                UI.print(response[1]);              
                break;
            case "writeTXT":
                request=Communication.setResquest(commands);
                Client.writeOut(request.getBytes());
                UI.print("El cuervo acaba de salir, Server debe modificar el archivo LordToldme.txt");
                responseString=Client.readIn();
                response=Communication.getResponse(responseString);
                UI.printResponse(response[0]);
                UI.print(response[1]); 
                break;
            case "readTXT":
                request=Communication.setResquest(commands);
                Client.writeOut(request.getBytes());
                UI.print("El cuervo acaba de salir, Server debe enviarnos el contenido del archivo LordToldme.txt");
                responseString=Client.readIn();
                response=Communication.getResponse(responseString);
                UI.printResponse(response[0]);
                UI.print(response[1]); 
                break;
            case "writeFile":
                requestArray=new String[3];
                requestArray[0]=commands[0];
                String[] result=Utils.readFile(commands[1]);  
                requestArray[1]=result[0]; requestArray[2]=result[1];  
                request=Communication.setResquest(requestArray);
                Client.writeOut(request.getBytes());
                UI.print("El cuervo acaba de salir, Server debe recibir el archivo "+requestArray[1]);
                responseString=Client.readIn();
                response=Communication.getResponse(responseString);
                UI.printResponse(response[0]);
                UI.print(response[1]); 
                break;
            case "readFile":
                request=Communication.setResquest(commands);
                Client.writeOut(request.getBytes());
                UI.print("El cuervo acaba de salir, Server debe enviarnos el archivo "+commands[1]);
                responseString=Client.readIn();
                response=Communication.getResponse(responseString);
                UI.printResponse(response[0]);
                String resultWritten=Utils.writeFile(response[1], response[2]);
                UI.setFileCopied(resultWritten);
        }
    }
    
}
