package f7m6mg.domparse.hu;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * F7M6MG - DOM Parser XML olvasás (Blokkosított/Strukturált kimenet)
 * Ez az osztály beolvassa az XML dokumentumot, kiírja a konzolra OLVASHATÓ formában,
 * és elmenti egy új fájlba.
 */
public class F7M6MGDOMRead {

    public static void main(String[] args) {
        try {
            // 1. XML fájl betöltése
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File xmlFile = new File("F7M6MG_XML.xml"); // A bemeneti fájl neve
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            System.out.println("=== F7M6MG - XML Tartalom (Strukturált Nézet) ===\n");

          
            printDocument(document.getDocumentElement(), "");

            
            saveDocument(document, "F7M6MGXML_Saved.xml");

            System.out.println("\n=== Sikeres művelet! ===");
            System.out.println("Az XML dokumentum elmentve: F7M6MGXML_Saved.xml");

        } catch (Exception e) {
            System.err.println("Hiba történt: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Rekurzív metódus a fa bejárására és blokkosított kiírására
     */
    private static void printDocument(Node node, String indent) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            String nodeName = element.getNodeName();

            
            boolean hasChildElements = hasChildElements(element);

            if (hasChildElements) {
      
                System.out.println(indent + "-------------------------");
                
                // Név kiírása nagybetűvel
                System.out.print(indent + nodeName.toUpperCase());

                // Attribútumok kiírása mellé (ha vannak)
                if (element.hasAttributes()) {
                    NamedNodeMap attrs = element.getAttributes();
                    for (int i = 0; i < attrs.getLength(); i++) {
                        Node attr = attrs.item(i);
                        System.out.print(" | " + attr.getNodeName() + ": " + attr.getNodeValue());
                    }
                }
                System.out.println(); // Sorzárás

                // Rekurzív hívás a gyerekekre
                NodeList children = element.getChildNodes();
                for (int i = 0; i < children.getLength(); i++) {
                    printDocument(children.item(i), indent + "  ");
                }

            } else {
                // --- ADAT ELEM (pl. Nev, Iranyitoszam) ---
                String textContent = element.getTextContent().trim();
                
                if (!textContent.isEmpty()) {
                    System.out.print(indent + nodeName + ": " + textContent);
                    
                    // Ritka eset: ha egy adatmezőnek attribútuma is van
                    if (element.hasAttributes()) {
                        NamedNodeMap attrs = element.getAttributes();
                        System.out.print(" (");
                        for (int i = 0; i < attrs.getLength(); i++) {
                            Node attr = attrs.item(i);
                            System.out.print(attr.getNodeName() + "=" + attr.getNodeValue() + " ");
                        }
                        System.out.print(")");
                    }
                    System.out.println();
                }
            }
        }
    }

    /**
     * Segédfüggvény: Megnézi, hogy van-e az elemnek további gyerek TAG-je.
     */
    private static boolean hasChildElements(Element el) {
        NodeList children = el.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Mentés fájlba (Ez változatlan, XML formátumban ment)
     */
    private static void saveDocument(Document document, String filename) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filename));
        transformer.transform(source, result);
    }
}