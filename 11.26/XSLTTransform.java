import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XSLTTransform {

    public static void main(String[] args) {
        // Ensure output directory exists
        new File("output").mkdirs();

        // Define transformations: {xmlFile, xslFile, outputFile}
        String[][] transformations = {
                // Task 1 - Student data
                { "hallgatof7m6mg.xml", "hallgatof7m6mg.xsl", "output/hallgatof7m6mg.html" },
                { "hallgatof7m6mg.xml", "hallgatof7m6mg.xsl", "output/hallgatof7m6mg.out.xml" }, // Also generate
                                                                                                 // .out.xml as
                                                                                                 // requested

                // Task 2 - Timetable
                { "orarendf7m6mg.xml", "orarendf7m6mg.xsl", "output/orarendf7m6mg.html" },
                { "orarendf7m6mg.xml", "orarendf7m6mg.xsl", "output/orarendf7m6mg.out.xml" }, // Also generate .out.xml
                                                                                              // as requested

                // Task 3 - Cars
                { "autokf7m6mg.xml", "autokf7m6mg.xsl", "output/autokf7m6mg.html" },
                { "autokf7m6mg.xml", "autok1f7m6mg.xsl", "output/autok1f7m6mg.html" },
                { "autokf7m6mg.xml", "autok2f7m6mg.xsl", "output/autok2f7m6mg.html" },
                { "autokf7m6mg.xml", "autok3f7m6mg.xsl", "output/autok3f7m6mg.html" },
                { "autokf7m6mg.xml", "autok4f7m6mg.xsl", "output/autok4f7m6mg.html" },
                { "autokf7m6mg.xml", "autok5f7m6mg.xsl", "output/autok5f7m6mg.html" },
                { "autokf7m6mg.xml", "autok6f7m6mg.xsl", "output/autok6f7m6mg.html" },
                { "autokf7m6mg.xml", "autok7f7m6mg.xsl", "output/autok7f7m6mg.xml" }
        };

        System.out.println("Starting XSLT transformations...\n");

        int successCount = 0;
        for (String[] task : transformations) {
            String xmlFile = task[0];
            String xslFile = task[1];
            String outputFile = task[2];

            if (transform(xmlFile, xslFile, outputFile)) {
                successCount++;
            }
        }

        System.out.println(
                "\n" + successCount + "/" + transformations.length + " transformations completed successfully!");
    }

    private static boolean transform(String xmlPath, String xslPath, String outputPath) {
        try {
            File xmlFile = new File(xmlPath);
            File xslFile = new File(xslPath);
            File outputFile = new File(outputPath);

            if (!xmlFile.exists()) {
                System.out.println("X Error: XML file not found: " + xmlPath);
                return false;
            }
            if (!xslFile.exists()) {
                System.out.println("X Error: XSL file not found: " + xslPath);
                return false;
            }

            Source xmlSource = new StreamSource(xmlFile);
            Source xslSource = new StreamSource(xslFile);
            Result result = new StreamResult(outputFile);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(xslSource);

            // Set output properties if needed, though usually defined in XSLT
            // transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(xmlSource, result);

            System.out.println("V Successfully created: " + outputPath);
            return true;

        } catch (TransformerException e) {
            System.out.println("X Error transforming " + xmlPath + " with " + xslPath + ": " + e.getMessage());
            // e.printStackTrace(); // Uncomment for debug
            return false;
        } catch (Exception e) {
            System.out.println("X Unexpected error: " + e.getMessage());
            return false;
        }
    }
}