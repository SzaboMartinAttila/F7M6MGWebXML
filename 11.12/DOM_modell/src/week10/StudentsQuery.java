package week10;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * StudentsQuery - small CLI program to run XPath queries against studentF7M6MG.xml
 * Usage:
 *   java StudentsQuery <xmlPath> <command> [arg]
 * Commands:
 *   listAll                  - print all students (id, full name, age)
 *   getById <id>             - print student with given id (e.g. 01)
 *   olderThan <age>          - list students older than given age
 *   count                   - print number of students
 *   nicknames               - list all nicknames
 *   avgAge                  - print average age
 *
 * This is intentionally simple and uses standard JAXP + XPath (Java 8+).
 */
public class StudentsQuery {

    public static void main(String[] args) throws Exception {
        // default XML file in current folder if not provided
        String xmlPath = args.length >= 1 ? args[0] : "studentF7M6MG.xml";

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlPath);

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();

        System.out.println("=== Query: listAll ===");
        listAll(xpath, doc);
        System.out.println();

        System.out.println("=== Query: count ===");
        count(xpath, doc);
        System.out.println();

        System.out.println("=== Query: nicknames ===");
        nicknames(xpath, doc);
        System.out.println();

        System.out.println("=== Query: avgAge ===");
        avgAge(xpath, doc);
        System.out.println();

        // run an example olderThan query (21) — adjust if you want a different threshold
        System.out.println("=== Query: olderThan 21 ===");
        olderThan(xpath, doc, 21);
        System.out.println();

        // run getById for every student found
        System.out.println("=== Query: getById <each student id> ===");
        XPathExpression listExpr = xpath.compile("/students/student/@id");
        NodeList ids = (NodeList) listExpr.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < ids.getLength(); i++) {
            String id = ids.item(i).getTextContent();
            System.out.println("-- getById " + id + " --");
            getById(xpath, doc, id);
        }
    }

    static void listAll(XPath xpath, Document doc) throws Exception {
        XPathExpression expr = xpath.compile("/students/student");
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            String id = xpath.evaluate("@id", nodes.item(i));
            String kereszt = xpath.evaluate("keresztnev", nodes.item(i));
            String vez = xpath.evaluate("vezeteknev", nodes.item(i));
            String bec = xpath.evaluate("becenev", nodes.item(i));
            String kor = xpath.evaluate("kor", nodes.item(i));
            System.out.printf("id=%s, name=%s %s, nick=%s, age=%s\n", id, kereszt, vez, bec, kor);
        }
    }

    static void getById(XPath xpath, Document doc, String id) throws Exception {
        XPathExpression expr = xpath.compile("/students/student[@id='" + id + "']");
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        if (nodes.getLength() == 0) {
            System.out.println("No student with id=" + id);
            return;
        }
        String kereszt = xpath.evaluate("keresztnev", nodes.item(0));
        String vez = xpath.evaluate("vezeteknev", nodes.item(0));
        String bec = xpath.evaluate("becenev", nodes.item(0));
        String kor = xpath.evaluate("kor", nodes.item(0));
        System.out.printf("id=%s, name=%s %s, nick=%s, age=%s\n", id, kereszt, vez, bec, kor);
    }

    static void olderThan(XPath xpath, Document doc, int age) throws Exception {
        XPathExpression expr = xpath.compile("/students/student[kor > " + age + "]");
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        if (nodes.getLength() == 0) {
            System.out.println("No students older than " + age);
            return;
        }
        for (int i = 0; i < nodes.getLength(); i++) {
            String id = xpath.evaluate("@id", nodes.item(i));
            String kereszt = xpath.evaluate("keresztnev", nodes.item(i));
            String vez = xpath.evaluate("vezeteknev", nodes.item(i));
            String kor = xpath.evaluate("kor", nodes.item(i));
            System.out.printf("id=%s, name=%s %s, age=%s\n", id, kereszt, vez, kor);
        }
    }

    static void count(XPath xpath, Document doc) throws Exception {
        XPathExpression expr = xpath.compile("count(/students/student)");
        Double c = (Double) expr.evaluate(doc, XPathConstants.NUMBER);
        System.out.println("Count = " + c.intValue());
    }

    static void nicknames(XPath xpath, Document doc) throws Exception {
        XPathExpression expr = xpath.compile("/students/student/becenev");
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getTextContent());
        }
    }

    static void avgAge(XPath xpath, Document doc) throws Exception {
        XPathExpression expr = xpath.compile("avg(/students/student/kor)");
        Double avg = (Double) expr.evaluate(doc, XPathConstants.NUMBER);
        if (avg==0) System.out.println("No ages found");
        else System.out.println("Average age = " + String.format("%.2f", avg));
    }
}
