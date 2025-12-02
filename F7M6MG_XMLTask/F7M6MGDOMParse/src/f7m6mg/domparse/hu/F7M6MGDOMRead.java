package f7m6mg.domparse.hu;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * F7M6MG - DOM Parser XML olvasás
 * Ez az osztály beolvassa az XML dokumentumot, kiírja a konzolra formázottan,
 * és elmenti egy új fájlba.
 */
public class F7M6MGDOMRead {

    public static void main(String[] args) {
        try {
            // DocumentBuilderFactory létrehozása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // XML fájl beolvasása
            File xmlFile = new File("F7M6MG_XML.xml");
            Document document = builder.parse(xmlFile);

            // Dokumentum normalizálása
            document.getDocumentElement().normalize();

            System.out.println("=== F7M6MG - XML Dokumentum Beolvasása ===\n");
            System.out.println("Gyökér elem: " + document.getDocumentElement().getNodeName());
            System.out.println("\n--- Teljes dokumentum tartalma ---\n");

            // Teljes dokumentum kiírása a konzolra
            printDocument(document.getDocumentElement(), "");

            // Dokumentum mentése új fájlba
            saveDocument(document, "F7M6MGXML_Saved.xml");

            System.out.println("\n=== Sikeres művelet! ===");
            System.out.println("Az XML dokumentum elmentve: F7M6MGXML_Saved.xml");

        } catch (Exception e) {
            System.err.println("Hiba történt az XML feldolgozása során:");
            e.printStackTrace();
        }
    }

    /**
     * Rekurzív függvény az XML fa bejárására és konzolra írására
     * 
     * @param node   Az aktuális csomópont
     * @param indent Behúzás a formázott megjelenítéshez
     */
    private static void printDocument(Node node, String indent) {
        // Csomópont típusának ellenőrzése
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            // Elem neve és attribútumai
            System.out.print(indent + "<" + element.getNodeName());

            // Attribútumok kiírása, ha vannak
            NamedNodeMap attributes = element.getAttributes();
            if (attributes.getLength() > 0) {
                for (int i = 0; i < attributes.getLength(); i++) {
                    Node attr = attributes.item(i);
                    System.out.print(" " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
                }
            }
            System.out.print(">");

            // Gyerek csomópontok feldolgozása
            NodeList children = element.getChildNodes();
            boolean hasElementChildren = false;

            // Ellenőrizzük, hogy vannak-e elem gyerekek
            for (int i = 0; i < children.getLength(); i++) {
                if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    hasElementChildren = true;
                    break;
                }
            }

            if (hasElementChildren) {
                System.out.println(); // Új sor, ha vannak gyerek elemek
                for (int i = 0; i < children.getLength(); i++) {
                    printDocument(children.item(i), indent + "  ");
                }
                System.out.println(indent + "</" + element.getNodeName() + ">");
            } else {
                // Szöveges tartalom kiírása
                String textContent = element.getTextContent().trim();
                if (!textContent.isEmpty()) {
                    System.out.print(textContent);
                }
                System.out.println("</" + element.getNodeName() + ">");
            }
        }
    }

    /**
     * XML dokumentum mentése fájlba
     * 
     * @param document A mentendő dokumentum
     * @param filename A célfájl neve
     */
    private static void saveDocument(Document document, String filename) throws TransformerException {
        // Transformer létrehozása a mentéshez
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Formázási beállítások
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        // DOM forrás és fájl cél
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filename));

        // Mentés végrehajtása
        transformer.transform(source, result);
    }
}
