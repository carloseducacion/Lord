/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordserver;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 *
 * @author carlosserrano
 */
public class Utils {
    public static String execute(String command){
        String s;
        String output="";
       try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            do{
                s = br.readLine();
                output=output+"line: " + s+"\n";
            }while(br.ready());
            
            p.waitFor();
            output="\n"+output+"exit: " + p.exitValue();
            p.destroy();
            return output;
       } catch (IOException | InterruptedException e) { return "\n";}
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
    
    
    public static String readTXT(String file){
        String output="";
        try(
             BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {  
            int ch = bufferedInputStream.read();
            while(ch != -1) {
                output = output+(char)ch;
                ch=bufferedInputStream.read();
            }
        } catch (FileNotFoundException e) {
            // exception handling
            return "Archivo no disponible";
        } catch (IOException e) {
            // exception handling
            return "Error al intentar leer el archivo";
        }
        return output;
    }
    
    public static String writeFile(String file, String content){
        byte[] decodedBytes = Base64.getDecoder().decode(content);
        return writeTXT(file, decodedBytes);
    }
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
    
    
}
