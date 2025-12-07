package domf7m6mg1015;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class DomReadNeptunkod {
    public static void main(String[] args) {
        try {
           
            File xmlFile = new File("f7m6mg_orarend.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            

            document.getDocumentElement().normalize();
            
            System.out.println("DOM fa szerkezete - teljes tartalom:");
            System.out.println("=====================================");
            
            
            displayNodeTree(document.getDocumentElement(), 0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Rekurzív metódus, amely egy DOM csomópontot és annak minden gyermekét megjeleníti
     * fa struktúrában, behúzással jelölve a szinteket.
     * 
     @param node A megjelenítendő csomópont
     @param level A behúzási szint
     */
    private static void displayNodeTree(Node node, int level) {
        
        String indentation = "  ".repeat(level);
        
       
        switch (node.getNodeType()) {
            case Node.DOCUMENT_NODE:
                System.out.println(indentation + "[DOCUMENT]");
                break;
                
            case Node.ELEMENT_NODE:
            
                StringBuilder elementInfo = new StringBuilder(indentation + "<" + node.getNodeName());
                
               
                NamedNodeMap attributes = node.getAttributes();
                if (attributes != null) {
                    for (int i = 0; i < attributes.getLength(); i++) {
                        Node attr = attributes.item(i);
                        elementInfo.append(" ").append(attr.getNodeName()).append("=\"").append(attr.getNodeValue()).append("\"");
                    }
                }
                elementInfo.append(">");
                System.out.println(elementInfo.toString());
                break;
                
            case Node.TEXT_NODE:
                
                String textContent = node.getTextContent().trim();
                if (!textContent.isEmpty()) {
                    System.out.println(indentation + "  " + textContent);
                }
                break;
                
            case Node.COMMENT_NODE:
                System.out.println(indentation + "<!-- " + node.getTextContent() + " -->");
                break;
                
            default:
                System.out.println(indentation + "[UNKNOWN_TYPE: " + node.getNodeType() + "]");
        }
        
        
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            displayNodeTree(children.item(i), level + 1);
        }
        
        
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println(indentation + "</" + node.getNodeName() + ">");
        }
    }
}
