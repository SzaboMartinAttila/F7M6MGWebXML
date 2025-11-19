package f7m6mgJSON;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONReadf7m6mg1 {

	public static void main(String[] args) {
		try {
            
            JSONParser parser = new JSONParser();

            
            Object obj = parser.parse(new FileReader("orarendf7m6mg.json"));

            
            JSONObject root = (JSONObject) obj;

            
            JSONObject orarendObj = (JSONObject) root.get("f7m6mg_orarend");

            
            JSONArray orak = (JSONArray) orarendObj.get("ora");

            
            PrintWriter writer = new PrintWriter(new FileWriter("orarendf7m6mg.txt"));
            
            for (Object o : orak) {
                JSONObject ora = (JSONObject) o;
                
                StringBuilder block = new StringBuilder();

                block.append("======================================").append("\n");
                block.append("Óra ID:" + ora.get("id")).append("\n");
                block.append("Típus: "+ ora.get("tipus")).append("\n");
                block.append("Tárgy: " + ora.get("targy")).append("\n");

                
                JSONObject idopont = (JSONObject) ora.get("idopont");

                block.append("Időpont:").append("\n");
                block.append("   Nap: " + idopont.get("nap")).append("\n");
                block.append("   Kezdés: " + idopont.get("kezdes")).append("\n");
                block.append("   Végzés: " + idopont.get("vegzes")).append("\n");

                block.append("Helyszín: " + ora.get("helyszin")).append("\n");
                block.append("Oktató: " + ora.get("oktato")).append("\n");
                block.append("Szak: " + ora.get("szak")).append("\n");
                block.append("======================================\n");
            
            System.out.print(block.toString());
            writer.print(block.toString());
                
            }
            writer.close();
            	

        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}
