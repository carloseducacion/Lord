/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordclient;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 *
 * @author carlosserrano
 */
public class Utils {
    public static String[] readFile(String file){
        String[] result=new String[2];
        result[0]=(new File(file)).getName();
        result[1]="";
        try{   
            result[1]=Base64.getEncoder().encodeToString(Files.readAllBytes( Paths.get(file) ));
        }catch (IOException e){
            e.printStackTrace();
            result[1]="";
        }
        return result;
    }
    public static String writeTXT(String file, byte[] content){
        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {  
            bufferedOutputStream.write(content);
        } catch (IOException e) {
            // exception handling
            return "Error modificando el archivo "+file;
        }
        return "El archivo "+file+" ha sido modificado";
    }
    
    
    public static String writeFile(String file, String content){
        byte[] decodedBytes = Base64.getDecoder().decode(content);
        return writeTXT(file, decodedBytes);
    }
    
}
