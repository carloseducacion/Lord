/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordserver;

import java.io.IOException;

/**
 *
 * @author pcserrano
 */
public class LordServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            Server myserver=new Server();
            myserver.connect();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
