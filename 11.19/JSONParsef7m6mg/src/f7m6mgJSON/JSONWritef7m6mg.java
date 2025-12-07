package neptunf7m6mgJSON;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONWritef7m6mg {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(new FileReader("../orarendf7m6mg.json"));
            
            JSONObject jsonObject = (JSONObject) obj;
            
            JSONObject orarendObj = (JSONObject) jsonObject.get("f7m6mg_orarend");
            
            JSONArray orak = (JSONArray) orarendObj.get("ora");
            
            System.out.println("f7m6mg Órarend 2025 - Feldolgozás és Kiírás");
            System.out.println("=============================================");
            
            JSONObject newJsonObject = new JSONObject();
            JSONObject newOrarendObj = new JSONObject();
            JSONArray newOrak = new JSONArray();
            
            for (int i = 0; i < orak.size(); i++) {
                JSONObject ora = (JSONObject) orak.get(i);
                
                System.out.println("\nÓra #" + (i + 1));
                System.out.println("Tárgy: " + ora.get("targy"));
                
                JSONObject idopont = (JSONObject) ora.get("idopont");
                System.out.println("Időpont: " + idopont.get("nap") + " " + 
                                 idopont.get("tol") + "-" + idopont.get("ig"));
                
                System.out.println("Helyszín: " + ora.get("helyszin"));
                System.out.println("Oktató: " + ora.get("oktato"));
                System.out.println("Szak: " + ora.get("szak"));
                System.out.println("----------------------");
                
                JSONObject newOra = new JSONObject();
                newOra.put("targy", ora.get("targy"));
                newOra.put("idopont", idopont);
                newOra.put("helyszin", ora.get("helyszin"));
                newOra.put("oktato", ora.get("oktato"));
                newOra.put("szak", ora.get("szak"));
                
                newOrak.add(newOra);
            }
            
            newOrarendObj.put("ora", newOrak);
            newJsonObject.put("f7m6mg_orarend_masolat", newOrarendObj);
            
            try (FileWriter file = new FileWriter("../orarendf7m6mg_1.json")) {
                file.write(newJsonObject.toJSONString());
                file.flush();
                System.out.println("\nJSON fájl sikeresen kiírva: orarendf7m6mg_1.json");
            }
            
            System.out.println("\nGenerált JSON tartalom:");
            System.out.println(newJsonObject.toJSONString());
            
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}