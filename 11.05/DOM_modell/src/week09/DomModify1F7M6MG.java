package week09;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DomModify1F7M6MG {
	public static void main(String[] args) {
		try {
			File inputFile = new File("f7m6mg_orarend.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			
			Node orak = doc.getFirstChild();
			
			Node ora = doc.getElementsByTagName("ora").item(0);
			
			NamedNodeMap attr = ora.getAttributes();
			Node nodeAttr = attr.getNamedItem("id");
			nodeAttr.setTextContent("01");
			
			NodeList list = doc.getElementsByTagName("ora");
			
			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node; 
					if("01".equals(eElement.getAttribute("id"))) {
					Element ujOktato = doc.createElement("oktato");
					ujOktato.appendChild(doc.createTextNode("Kiss Gyula"));
					eElement.appendChild(ujOktato);
					}
					String tipus = eElement.getAttribute("tipus");
					 if(tipus.equals("gyakorlat")) {
						 eElement.setAttribute("tipus", "elmelet");
					 }
				}
			}
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			
			
			DOMSource source = new DOMSource(doc);
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
