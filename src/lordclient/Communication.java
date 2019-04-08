/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lordclient;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author carlosserrano
 * REQUEST:
 * {
 * "command": die | writeTXT | sendTXT | writeFile | sendFile | execute
 * "parameters": {
 *   "parameter1" : "...",
 *   "parameter2" : "..."
 *  }
 * }
 * 
 * RESPONSE:
 * {
 *  "response": ok | error | any...
 *  "parameters": {
 *   "parameter1" : "...",
 *   "parameter2" : "..."
 *  }
 * }
 */
public class Communication {
    
    public static String[] getResponse(String request){
        
        JSONParser jsonParser = new JSONParser();
        try{
            JSONObject json = (JSONObject) jsonParser.parse(request);
            
            JSONObject parameters= (JSONObject) json.get("parameters");   
            String[] requestDecoded=new String[parameters.size()+1];
            requestDecoded[0] = (String) json.get("response");
            
            for(int i=1;i<parameters.size()+1;i++){
                requestDecoded[i] = (String) parameters.get("parameter"+i);
            }            
            
            return requestDecoded;
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
        
    }
  public static String setResquest(String[] response){
        JSONObject json = new JSONObject();
        json.put("command", response[0]);
        
        JSONObject parameters = new JSONObject();
        for(int i=1;i<response.length;i++){
            parameters.put("parameter"+(i), response[i]);
        }
        
        json.put("parameters",parameters);
        return json.toString();
    }
    
}
