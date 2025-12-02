import f7m6mg.domparse.hu.*;
import java.util.Scanner;

/**
 * F7M6MG - XML DOM Parser Menürendszer
 * Központi konzolos menü az összes DOM műveletek kezeléséhez
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            clearScreen();
            displayMenu();

            int choice = getChoice();

            switch (choice) {
                case 1:
                    runDOMRead();
                    break;
                case 2:
                    runDOMQuery();
                    break;
                case 3:
                    runDOMModify();
                    break;
                case 4:
                    runAllOperations();
                    break;
                case 0:
                    running = false;
                    System.out.println("\nViszlat! Program bezarva.");
                    break;
                default:
                    System.out.println("\nErvenytelen valasztas! Probalja ujra.");
                    pause();
            }
        }

        scanner.close();
    }

    /**
     * Főmenü megjelenítése
     */
    private static void displayMenu() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         F7M6MG - XML DOM PARSER MENURENDSZER              ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║                                                            ║");
        System.out.println("║  [1] XML Beolvasas es mentes (DOMRead)                    ║");
        System.out.println("║      - XML fajl betoltese es formazott mentes              ║");
        System.out.println("║                                                            ║");
        System.out.println("║  [2] XML Lekerdezesek (DOMQuery)                          ║");
        System.out.println("║      - Vevok, pizzak, futarok es rendelesek lekerdezese    ║");
        System.out.println("║                                                            ║");
        System.out.println("║  [3] XML Modositasok (DOMModify)                          ║");
        System.out.println("║      - Uj elemek hozzaadasa es meglevok modositasa         ║");
        System.out.println("║                                                            ║");
        System.out.println("║  [4] Osszes muvelet futtatasa                             ║");
        System.out.println("║      - Read -> Query -> Modify sorrendben                  ║");
        System.out.println("║                                                            ║");
        System.out.println("║  [0] Kilepes                                              ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.print("\n>> Valassz egy opciot (0-4): ");
    }

    /**
     * Felhasználói választás beolvasása
     */
    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * 1. DOMRead futtatása
     */
    private static void runDOMRead() {
        clearScreen();
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║               XML BEOLVASAS ES MENTES                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        try {
            F7M6MGDOMRead.main(new String[] {});
        } catch (Exception e) {
            System.err.println("HIBA: Hiba tortent: " + e.getMessage());
        }

        pause();
    }

    /**
     * 2. DOMQuery futtatása
     */
    private static void runDOMQuery() {
        clearScreen();
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  XML LEKERDEZESEK                         ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        try {
            F7M6MGDOMQuery.main(new String[] {});
        } catch (Exception e) {
            System.err.println("HIBA: Hiba tortent: " + e.getMessage());
        }

        pause();
    }

    /**
     * 3. DOMModify futtatása
     */
    private static void runDOMModify() {
        clearScreen();
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  XML MODOSITASOK                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        try {
            F7M6MGDOMModify.main(new String[] {});
        } catch (Exception e) {
            System.err.println("HIBA: Hiba tortent: " + e.getMessage());
        }

        pause();
    }

    /**
     * 4. Összes művelet futtatása sorrendben
     */
    private static void runAllOperations() {
        clearScreen();
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            OSSZES MUVELET FUTTATASA                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // 1. DOMRead
        System.out.println("[1/3] DOMRead futtatasa...\n");
        System.out.println("------------------------------------------------------------");
        try {
            F7M6MGDOMRead.main(new String[] {});
        } catch (Exception e) {
            System.err.println("HIBA: Hiba a DOMRead soran: " + e.getMessage());
        }

        System.out.println("\n------------------------------------------------------------\n");
        System.out.print("Nyomj ENTER-t a folytatashoz...");
        scanner.nextLine();

        // 2. DOMQuery
        System.out.println("\n[2/3] DOMQuery futtatasa...\n");
        System.out.println("------------------------------------------------------------");
        try {
            F7M6MGDOMQuery.main(new String[] {});
        } catch (Exception e) {
            System.err.println("HIBA: Hiba a DOMQuery soran: " + e.getMessage());
        }

        System.out.println("\n------------------------------------------------------------\n");
        System.out.print("Nyomj ENTER-t a folytatashoz...");
        scanner.nextLine();

        // 3. DOMModify
        System.out.println("\n[3/3] DOMModify futtatasa...\n");
        System.out.println("------------------------------------------------------------");
        try {
            F7M6MGDOMModify.main(new String[] {});
        } catch (Exception e) {
            System.err.println("HIBA: Hiba a DOMModify soran: " + e.getMessage());
        }

        System.out.println("\n------------------------------------------------------------");
        System.out.println("\nMinden muvelet sikeresen befejezve!\n");

        pause();
    }

    /**
     * Képernyő "törlése" (több üres sor)
     */
    private static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * Várakozás ENTER billentyűre
     */
    private static void pause() {
        System.out.print("\nNyomj ENTER-t a folytatashoz...");
        scanner.nextLine();
    }
}