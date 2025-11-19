package f7m6mgJSON;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JSONReadf7m6mg {
	

	    public static void main(String[] args) {

	        try {
	            
	            JSONParser parser = new JSONParser();

	            
	            Object obj = parser.parse(new FileReader("orarendf7m6mg.json"));

	            
	            JSONObject root = (JSONObject) obj;

	            
	            JSONObject orarendObj = (JSONObject) root.get("f7m6mg_orarend");

	            
	            JSONArray orak = (JSONArray) orarendObj.get("ora");

	            
	            for (Object o : orak) {
	                JSONObject ora = (JSONObject) o;

	                System.out.println("======================================");
	                System.out.println("Óra ID: " + ora.get("id"));
	                System.out.println("Típus: " + ora.get("tipus"));
	                System.out.println("Tárgy: " + ora.get("targy"));

	                
	                JSONObject idopont = (JSONObject) ora.get("idopont");

	                System.out.println("Időpont:");
	                System.out.println("   Nap: " + idopont.get("nap"));
	                System.out.println("   Kezdés: " + idopont.get("kezdes"));
	                System.out.println("   Végzés: " + idopont.get("vegzes"));

	                System.out.println("Helyszín: " + ora.get("helyszin"));
	                System.out.println("Oktató: " + ora.get("oktato"));
	                System.out.println("Szak: " + ora.get("szak"));
	                System.out.println("======================================\n");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
