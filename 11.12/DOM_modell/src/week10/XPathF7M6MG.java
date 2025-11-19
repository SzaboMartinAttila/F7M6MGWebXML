package week10;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class XPathF7M6MG {

	public static void main(String[] args) {
		try {
			File xmlFile= new File("studentF7M6MG.xml");
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder(); 
			Document document = db.parse("studentF7M6MG.xml");

			document.getDocumentElement().normalize();
			
			XPath xp = XPathFactory.newInstance().newXPath();
			
			
			
			
		}catch(Exception e) {
			
		}

	}

}
