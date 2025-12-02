package f7m6mg.domparse.hu;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

/**
 * F7M6MG - DOM Parser XML lekérdezések
 * Ez az osztály különböző lekérdezéseket hajt végre az XML dokumentumon
 * XPath használata NÉLKÜL, csak DOM API-val
 */
public class F7M6MGDOMQuery {

    public static void main(String[] args) {
        try {
            // DocumentBuilderFactory létrehozása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // XML fájl beolvasása
            File xmlFile = new File("F7M6MG_XML.xml");
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            System.out.println("=== F7M6MG - XML Lekérdezések ===\n");

            // 1. lekérdezés: Összes vevő neve és címe
            query1_ListAllCustomers(document);

            // 2. lekérdezés: Pizzák neve és ára
            query2_ListPizzasWithPrices(document);

            // 3. lekérdezés: Futárok és járműveik
            query3_DeliveryPersonsWithVehicles(document);

            // 4. lekérdezés: Rendelések összesítése
            query4_OrderSummary(document);

        } catch (Exception e) {
            System.err.println("Hiba történt a lekérdezések során:");
            e.printStackTrace();
        }
    }

    /**
     * 1. LEKÉRDEZÉS: Összes vevő nevének és címének listázása
     */
    private static void query1_ListAllCustomers(Document doc) {
        System.out.println("--- 1. LEKÉRDEZÉS: Vevők listája címekkel ---\n");

        // Vevő elemek lekérése
        NodeList vevoList = doc.getElementsByTagName("Vevo");

        for (int i = 0; i < vevoList.getLength(); i++) {
            Element vevo = (Element) vevoList.item(i);
            String vevoID = vevo.getAttribute("VevoID");

            // Név elem tartalmának kinyerése
            String nev = getElementTextContent(vevo, "Nev");

            // Cím adatok kinyerése
            NodeList cimList = vevo.getElementsByTagName("Cim");
            if (cimList.getLength() > 0) {
                Element cim = (Element) cimList.item(0);
                String iranyitoszam = getElementTextContent(cim, "Iranyitoszam");
                String varos = getElementTextContent(cim, "Varos");
                String utca = getElementTextContent(cim, "Utca");

                System.out.println("Vevő ID: " + vevoID);
                System.out.println("  Név: " + nev);
                System.out.println("  Cím: " + iranyitoszam + " " + varos + ", " + utca);
                System.out.println();
            }
        }
    }

    /**
     * 2. LEKÉRDEZÉS: Pizzák nevének és árának listázása
     */
    private static void query2_ListPizzasWithPrices(Document doc) {
        System.out.println("--- 2. LEKÉRDEZÉS: Pizzák árakkal ---\n");

        // Pizza elemek lekérése
        NodeList pizzaList = doc.getElementsByTagName("Pizza");

        for (int i = 0; i < pizzaList.getLength(); i++) {
            Element pizza = (Element) pizzaList.item(i);
            String pizzaID = pizza.getAttribute("PizzaID");

            String nev = getElementTextContent(pizza, "Nev");
            String ar = getElementTextContent(pizza, "Ar");
            String meret = getElementTextContent(pizza, "Meret");

            System.out.println("Pizza ID: " + pizzaID);
            System.out.println("  Név: " + nev);
            System.out.println("  Ár: " + ar + " Ft");
            System.out.println("  Méret: " + meret + " cm");
            System.out.println();
        }
    }

    /**
     * 3. LEKÉRDEZÉS: Futárok és hozzájuk tartozó járművek
     */
    private static void query3_DeliveryPersonsWithVehicles(Document doc) {
        System.out.println("--- 3. LEKÉRDEZÉS: Futárok és járműveik ---\n");

        // Futár elemek lekérése
        NodeList futarList = doc.getElementsByTagName("Futar");
        NodeList jarmuList = doc.getElementsByTagName("Jarmu");

        for (int i = 0; i < futarList.getLength(); i++) {
            Element futar = (Element) futarList.item(i);
            String futarID = futar.getAttribute("FutarID");
            String nev = getElementTextContent(futar, "Nev");
            String telefon = getElementTextContent(futar, "Telefonszam");

            System.out.println("Futár ID: " + futarID);
            System.out.println("  Név: " + nev);
            System.out.println("  Telefon: " + telefon);

            // Járművek keresése, amelyek ehhez a futárhoz tartoznak
            for (int j = 0; j < jarmuList.getLength(); j++) {
                Element jarmu = (Element) jarmuList.item(j);
                String tulajdonos = getElementTextContent(jarmu, "TulajdonosFutar");

                if (tulajdonos.equals(futarID)) {
                    String rendszam = jarmu.getAttribute("Rendszam");
                    String tipus = getElementTextContent(jarmu, "Tipus");
                    String uzemanyag = getElementTextContent(jarmu, "Uzemanyag");

                    System.out.println("  Jármű: " + tipus + " (" + rendszam + ")");
                    System.out.println("  Üzemanyag: " + uzemanyag);
                }
            }
            System.out.println();
        }
    }

    /**
     * 4. LEKÉRDEZÉS: Rendelések részletes összesítése
     */
    private static void query4_OrderSummary(Document doc) {
        System.out.println("--- 4. LEKÉRDEZÉS: Rendelések összesítése ---\n");

        NodeList rendelesList = doc.getElementsByTagName("Rendeles");

        int osszesRendeles = rendelesList.getLength();
        int osszVegosszeg = 0;

        System.out.println("Összes rendelés száma: " + osszesRendeles + "\n");

        for (int i = 0; i < rendelesList.getLength(); i++) {
            Element rendeles = (Element) rendelesList.item(i);
            String rendelesID = rendeles.getAttribute("RendelesID");
            String datum = getElementTextContent(rendeles, "Datum");
            String vegosszeg = getElementTextContent(rendeles, "Vegosszeg");
            String fizetesMod = getElementTextContent(rendeles, "FizetesMod");

            osszVegosszeg += Integer.parseInt(vegosszeg);

            System.out.println("Rendelés ID: " + rendelesID);
            System.out.println("  Dátum: " + datum);
            System.out.println("  Végösszeg: " + vegosszeg + " Ft");
            System.out.println("  Fizetési mód: " + fizetesMod);

            // Rendelés tételek számának meghatározása
            NodeList tetelList = rendeles.getElementsByTagName("RendelesTetel");
            System.out.println("  Tételek száma: " + tetelList.getLength());
            System.out.println();
        }

        System.out.println("Összesített végösszeg: " + osszVegosszeg + " Ft\n");
    }

    /**
     * Segédfüggvény egy elem szöveges tartalmának kinyeréséhez
     * 
     * @param parent  Szülő elem
     * @param tagName Keresett elem neve
     * @return Az elem szöveges tartalma
     */
    private static String getElementTextContent(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        return "";
    }
}
