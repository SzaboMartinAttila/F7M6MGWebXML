package domf7m6mg1105;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;

public class DomQueryf7m6mg {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("hallgatof7m6mg.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Hallgatók vezetéknevei:");
            NodeList hallgatoNodes = doc.getElementsByTagName("hallgato");

            for (int i = 0; i < hallgatoNodes.getLength(); i++) {
                Node hallgatoNode = hallgatoNodes.item(i);
                if (hallgatoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element hallgatoElement = (Element) hallgatoNode;
                    String vezeteknev = hallgatoElement.getElementsByTagName("vezeteknev").item(0).getTextContent();
                    System.out.println("Vezetéknév: " + vezeteknev);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}