
import java.util.Scanner;

public class Main {

    static int[][] board = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    public static void main(String[] args) {
        System.out.println("Solving...");
        solve();
        System.out.println("No more solutions!");
    }

    static boolean possible(int y, int x, int n) {
        /*
         * Metoden har 3 parametere.
         * x og y (rad og kolonne) -> er posisjonen i brettet , og tallet-n som forsøkes i
         * utvalgt posisjon.
         */

        /*
         * Her sjekker vi over raden for å se etter n i samme rad.
         * Vi returner false, hvis vi møter på n i samme rad. Da tallet-n ikke vil være
         * gyldig i ruten-xy.
         */
        for (int i = 0; i < 9; i++) {
            if (board[y][i] == n) {
                return false;
            }
        }
        // Her sjekker vi kolonnen
        for (int i = 0; i < 9; i++) {
            if (board[i][x] == n) {
                return false;
            }
        }
        /*
         * Her sjekker vi 3*3 boksen.
         * Vi har en nested for loop som sjekker 3*3 ruter.
         * xBox og yBox brukes for å skyve loopen 3 rader og kolonner om gangen
         * for å sjekke boksen til posisjon-xy.
         */
        int xBox = (x / 3) * 3;
        int yBox = (y / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[yBox + i][xBox + j] == n) {
                    return false;
                }
            }
        }
        /*
         * Kommer vi til bunnen av metoden så returnerer vi true, fordi det betyr at vi
         * ikke har funnet n i rad, kolonne eller boks. 
         * n er da et gyldig tall i ruten-xy.
         */
        return true;
    }

    static void solve() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) { // Brute-force: Når vi skal løse brettet, går vi fra topp til bunn.
                if (board[y][x] == 0) { // Vi finner en tom rute.
                    for (int n = 1; n < 10; n++) { // Vi sjekker tall fra 1-9 for å ha i ruten.
                        if (possible(y, x, n)) { // Sjekker om n er gyldig i ruten-xy.
                            board[y][x] = n; // Tallet-n er gyldig, og vi setter inn tallet i rute-xy.
                            solve(); /*
                                      * Vi gjør en recursion, i call stacken
                                      * har vi allerde fylt inn tall fra forrige kall på metoden.
                                      */
                            board[y][x] = 0; /*
                                              * Siste metodekall i call stacken kommer hit når metoden har nådd
                                              * return/blindvei.
                                              * Altså når en rute har ingen gyldig tall,
                                              * så gjør vi en backtrack ved å sette ruten tilbake til 0.
                                              */
                        }
                    }
                    return; /*
                             * Recursion break -> Her møter vi på en blindvei, en rute har ingen gyldige
                             * tall-n. Når n == 9 og n ikke er gyldig i ruten så settes ruten til 0.
                             * Vi hopper til forrige rute i call stacken og prøver neste tall av n.
                             */
                }
                // Alle ruter i brettet har et gyldig tall mellom 1-9 og vi treffer ikke return.
            }
        }
        displayBoard(); /*
                         * Her er brettet løst og vi må vise brettet her, fordi vi fortsatt har en call
                         * stack med backtracking. Etter at vi har printet løsningen så sjekkes
                         * det etter neste løsning, fordi vi fortsatt har en call stack. Siste rute i
                         * call stacken fortsetter med neste tall av n og vi fortsetter med neste
                         * løsning. Når vi fullfører call stacken (etter siste mulige løsning) så går
                         * brettet til originalt oppsett, fordi alle rutene i call stacken gjør en
                         * backtrack før slutten av metoden.
                         */
    }

    static void displayBoard() {
        // Looper over brettet for å printe ut hver rute.
        for (int col = 0; col < 9; col++) { // kolonne
            for (int row = 0; row < 9; row++) { // rad
                System.out.printf("%2d", board[col][row]); // print rute fra rad og kolonne
            }
            System.out.println(); // Ny rad
        }
        System.out.println();
        System.out.println("Press enter for more: ");
        String nextLine = new Scanner(System.in).nextLine(); // Venter på en input når vi har printet hele brettet
    }
}
