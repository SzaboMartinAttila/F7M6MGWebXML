package f7m6mg.domparse.hu;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * F7M6MG - DOM Parser XML módosítások
 * Ez az osztály különböző módosításokat hajt végre az XML dokumentumon,
 * majd kiírja a konzolra és elmenti a módosított dokumentumot
 */
public class F7M6MGDOMModify {

    public static void main(String[] args) {
        try {
            // DocumentBuilderFactory létrehozása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // XML fájl beolvasása
            File xmlFile = new File("F7M6MG_XML.xml");
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            System.out.println("=== F7M6MG - XML Módosítások ===\n");

            // 1. módosítás: Új pizza hozzáadása
            modify1_AddNewPizza(document);

            // 2. módosítás: Pizza árának módosítása
            modify2_UpdatePizzaPrice(document);

            // 3. módosítás: Új vevő hozzáadása
            modify3_AddNewCustomer(document);

            // 4. módosítás: Rendelés fizetési módjának módosítása
            modify4_UpdatePaymentMethod(document);

            // Módosított dokumentum mentése
            saveModifiedDocument(document, "F7M6MGXML_Modified.xml");

            System.out.println("\n=== Minden módosítás sikeresen végrehajtva! ===");
            System.out.println("Módosított dokumentum elmentve: F7M6MGXML_Modified.xml\n");

        } catch (Exception e) {
            System.err.println("Hiba történt a módosítások során:");
            e.printStackTrace();
        }
    }

    /**
     * 1. MÓDOSÍTÁS: Új pizza hozzáadása a dokumentumhoz
     */
    private static void modify1_AddNewPizza(Document doc) {
        System.out.println("--- 1. MÓDOSÍTÁS: Új pizza hozzáadása ---\n");

        // Gyökér elem lekérése
        Element root = doc.getDocumentElement();

        // Új Pizza elem létrehozása
        Element ujPizza = doc.createElement("Pizza");
        ujPizza.setAttribute("PizzaID", "4");

        // Pizza adatok hozzáadása
        Element nev = doc.createElement("Nev");
        nev.setTextContent("Carbonara");
        ujPizza.appendChild(nev);

        Element ar = doc.createElement("Ar");
        ar.setTextContent("3500");
        ujPizza.appendChild(ar);

        Element meret = doc.createElement("Meret");
        meret.setTextContent("32");
        ujPizza.appendChild(meret);

        // Pizza elem hozzáadása a dokumentumhoz
        root.appendChild(ujPizza);

        System.out.println("Új pizza hozzáadva:");
        System.out.println("  Pizza ID: 4");
        System.out.println("  Név: Carbonara");
        System.out.println("  Ár: 3500 Ft");
        System.out.println("  Méret: 32 cm\n");
    }

    /**
     * 2. MÓDOSÍTÁS: Meglévő pizza árának módosítása
     */
    private static void modify2_UpdatePizzaPrice(Document doc) {
        System.out.println("--- 2. MÓDOSÍTÁS: Pizza árának frissítése ---\n");

        // Margherita pizza keresése (PizzaID="1")
        NodeList pizzaList = doc.getElementsByTagName("Pizza");

        for (int i = 0; i < pizzaList.getLength(); i++) {
            Element pizza = (Element) pizzaList.item(i);
            if (pizza.getAttribute("PizzaID").equals("1")) {
                // Ár elem megkeresése és módosítása
                NodeList arList = pizza.getElementsByTagName("Ar");
                if (arList.getLength() > 0) {
                    Element ar = (Element) arList.item(0);
                    String regiAr = ar.getTextContent();
                    ar.setTextContent("2800");

                    System.out.println("Margherita pizza árának módosítása:");
                    System.out.println("  Régi ár: " + regiAr + " Ft");
                    System.out.println("  Új ár: 2800 Ft\n");
                }
                break;
            }
        }
    }

    /**
     * 3. MÓDOSÍTÁS: Új vevő hozzáadása
     */
    private static void modify3_AddNewCustomer(Document doc) {
        System.out.println("--- 3. MÓDOSÍTÁS: Új vevő hozzáadása ---\n");

        Element root = doc.getDocumentElement();

        // Új Vevő elem létrehozása
        Element ujVevo = doc.createElement("Vevo");
        ujVevo.setAttribute("VevoID", "4");

        // Név
        Element nev = doc.createElement("Nev");
        nev.setTextContent("Szabó Anna");
        ujVevo.appendChild(nev);

        // Cím
        Element cim = doc.createElement("Cim");

        Element iranyitoszam = doc.createElement("Iranyitoszam");
        iranyitoszam.setTextContent("6720");
        cim.appendChild(iranyitoszam);

        Element varos = doc.createElement("Varos");
        varos.setTextContent("Szeged");
        cim.appendChild(varos);

        Element utca = doc.createElement("Utca");
        utca.setTextContent("Dugonics tér 13.");
        cim.appendChild(utca);

        ujVevo.appendChild(cim);

        // Telefonszám
        Element telefon = doc.createElement("Telefonszam");
        telefon.setTextContent("06204445555");
        ujVevo.appendChild(telefon);

        // Email
        Element email = doc.createElement("Email");
        email.setTextContent("szabo.anna@email.hu");
        ujVevo.appendChild(email);

        // Vevő hozzáadása a dokumentumhoz
        root.appendChild(ujVevo);

        System.out.println("Új vevő hozzáadva:");
        System.out.println("  Vevő ID: 4");
        System.out.println("  Név: Szabó Anna");
        System.out.println("  Cím: 6720 Szeged, Dugonics tér 13.");
        System.out.println("  Telefon: 06204445555");
        System.out.println("  Email: szabo.anna@email.hu\n");
    }

    /**
     * 4. MÓDOSÍTÁS: Rendelés fizetési módjának módosítása
     */
    private static void modify4_UpdatePaymentMethod(Document doc) {
        System.out.println("--- 4. MÓDOSÍTÁS: Fizetési mód módosítása ---\n");

        // Rendelés keresése (RendelesID="2")
        NodeList rendelesList = doc.getElementsByTagName("Rendeles");

        for (int i = 0; i < rendelesList.getLength(); i++) {
            Element rendeles = (Element) rendelesList.item(i);
            if (rendeles.getAttribute("RendelesID").equals("2")) {
                // Fizetési mód elem megkeresése és módosítása
                NodeList fizetesModList = rendeles.getElementsByTagName("FizetesMod");
                if (fizetesModList.getLength() > 0) {
                    Element fizetesMod = (Element) fizetesModList.item(0);
                    String regiFizetes = fizetesMod.getTextContent();
                    fizetesMod.setTextContent("Online átutalás");

                    System.out.println("Rendelés ID 2 fizetési módjának módosítása:");
                    System.out.println("  Régi fizetési mód: " + regiFizetes);
                    System.out.println("  Új fizetési mód: Online átutalás\n");
                }
                break;
            }
        }
    }

    /**
     * Módosított XML dokumentum mentése fájlba
     */
    private static void saveModifiedDocument(Document document, String filename) throws TransformerException {
        System.out.println("--- Dokumentum mentése ---\n");

        // Transformer létrehozása
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

        // Konzolra is kiírjuk a módosított dokumentumot
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }
}
