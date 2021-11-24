package tb.soft;

import java.util.*;

/**
 * Program: Aplikacja działająca w oknie konsoli, która umożliwia testowanie
 *          działania wybranych kolekcji.
 *    Plik: PersonConsoleApp.java
 *
 *   Autor: Marcin Gnap
 *    Data: 23.11.2021 r.
 */
public class PersonConsoleApp {

    private static final String GREETING_MESSAGE =
            "Program Kolekcje - wersja konsolowa\n" +
                    "Autor: Marcin Gnap\n" +
                    "Data:  23.11.2021 r.\n";

    private static final String MAIN_MENU =
            "    M E N U   G Ł Ó W N E  \n" +
                    "1 - Set            \n" +
                    "2 - List           \n" +
                    "3 - Map            \n" +
                    "0 - Zakończ program\n";

    private static final String MIDDLE_MENU =
            "    M E N U   K O L E K C J I  \n" +
                    "1 - Dodanie elementu                           \n" +
                    "2 - Usunięcie elementu                         \n" +
                    "3 - Wypisanie elementów                        \n" +
                    "4 - Różnice w metodach equals() i hashCod()    \n" +
                    "5 - Comparable vs. Comparator                  \n" +
                    "0 - Powrót                                     \n";

    private static final String ADD_MENU =
            "    M E N U   D O D A W A N I A  \n" +
                    "1 - Dodanie nowego elementu            \n" +
                    "2 - Dodanie tego samego elementu       \n" +
                    "3 - Dodanie takiego samego elementu    \n" +
                    "0 - Powrót                             \n";


    /**
     * ConsoleUserDialog to pomocnicza klasa zawierająca zestaw
     * prostych metod do realizacji dialogu z użytkownikiem
     * w oknie konsoli tekstowej.
     */
    private static final ConsoleUserDialog UI = new ConsoleUserDialog();


    public static void main(String[] args) throws PersonException {
        // Utworzenie obiektu aplikacji konsolowej
        // oraz uruchomienie głównej pętli aplikacji.
        PersonConsoleApp application = new PersonConsoleApp();
        application.runMainLoop();
    }


    /*
     *  Referencja do obiektu, który zawiera dane aktualnej osoby.
     */
    private NewPerson currentPerson = null;
    private BetterPerson currentBPerson = null;
    private NewPerson additionalPerson = null;
    private BetterPerson additionalBPerson = null;


    /*
     *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
     *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
     *         w której program się zatrzymuje aż do zakończenia
     *         działania za pomocą metody System.exit(0);
     */
    public void runMainLoop() throws PersonException {
        UI.printMessage(GREETING_MESSAGE);

        while (true) {
            UI.clearConsole();
                switch (UI.enterInt(MAIN_MENU + "==>> ")) {
                    case 1:
                        SetCollection();
                        break;
                    case 2:
                        ListCollection();
                        break;
                    case 3:
                        MapCollection();
                        break;
                    case 0:
                        // zakończenie działania programu
                        UI.printInfoMessage("\nProgram zakończył działanie!");
                        System.exit(0);
                } // koniec instrukcji switch
            }// koniec pętli while
    }

    /*
     *  Metoda wyświetla w oknie konsoli dane aktualnej osoby
     *  pamiętanej w zmiennej currentPerson.
     */
    void showCurrentPerson() {
        showPerson(currentPerson);
        showBPerson(currentBPerson);
    }


    /*
     * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej
     * przez obiekt klasy NewPerson i BetterPerson
     */
    static void showPerson(tb.soft.NewPerson person) {
        StringBuilder sb = new StringBuilder();

        if (person != null) {
            sb.append("Aktualna osoba: \n")
                    .append("      Imię: ").append(person.getFirstName()).append("\n")
                    .append("  Nazwisko: ").append(person.getLastName()).append("\n")
                    .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
                    .append("Stanowisko: ").append(person.getJob()).append("\n");
        } else
            sb.append( "Brak danych osoby\n" );
        UI.printMessage( sb.toString() );
    }

    static void showBPerson(tb.soft.BetterPerson person) {
        StringBuilder sb = new StringBuilder();

        if (person != null) {
            sb.append("Aktualna osoba: \n")
                    .append("      Imię: ").append(person.getFirstName()).append("\n")
                    .append("  Nazwisko: ").append(person.getLastName()).append("\n")
                    .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
                    .append("Stanowisko: ").append(person.getJob()).append("\n");
        } else
            sb.append( "Brak danych osoby\n" );
        UI.printMessage( sb.toString() );
    }


    /*
     * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt
     * klasy Person i wypełnia atrybuty wczytanymi danymi.
     * Walidacja poprawności danych odbywa się w konstruktorze i setterach
     * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
     * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
     */

    private void SetCollection() throws PersonException {
        while (true) {
            UI.clearConsole();
            HashSet<NewPerson> hSNewPerson = new HashSet<>();
            TreeSet<NewPerson> tSNewPerson = new TreeSet<>();
            HashSet<BetterPerson> hSBetterPerson = new HashSet<>();
            TreeSet<BetterPerson> tSBetterPerson = new TreeSet<>();
            showCurrentPerson();
                switch (UI.enterInt(MIDDLE_MENU + "==>> ")) {
                    case 1:
                        // Dodatkowe menu służące do dodawania elementów.
                        AddSetPosition(hSNewPerson, tSNewPerson, hSBetterPerson, tSBetterPerson);
                        break;
                    case 2:
                        // Usunięcie elementu.
                        System.out.println("Brak metody.");
                        break;
                    case 3:
                        // Wypisanie elementów.
                        System.out.println("HashSet dla obiektów NewPerson:");
                        for(NewPerson element: hSNewPerson) {
                            System.out.print(element.getFirstName() + " ");
                            System.out.print(element.getLastName() + " ");
                            System.out.print(element.getBirthYear() + " ");
                            System.out.println(element.getJob() + " ");
                        }
                        System.out.println("\n" + "TreeSet dla obiektów NewPerson:");
                        for(NewPerson element: tSNewPerson) {
                            System.out.print(element.getFirstName() + " ");
                            System.out.print(element.getLastName() + " ");
                            System.out.print(element.getBirthYear() + " ");
                            System.out.println(element.getJob() + " ");
                        }
                        System.out.println("\n" + "HasheSet dla obiektów BetterPerson:");
                        for(BetterPerson element: hSBetterPerson) {
                            System.out.print(element.getFirstName() + " ");
                            System.out.print(element.getLastName() + " ");
                            System.out.print(element.getBirthYear() + " ");
                            System.out.println(element.getJob() + " ");
                        }
                        System.out.println("\n" + "TreeSet dla obiektów BetterPerson:");
                        for(BetterPerson element: tSBetterPerson) {
                            System.out.print(element.getFirstName() + " ");
                            System.out.print(element.getLastName() + " ");
                            System.out.print(element.getBirthYear() + " ");
                            System.out.println(element.getJob() + " ");
                        }
                        break;
                    case 4:
                        // Różnice w metodach equals() i hashCod().
                        System.out.println("Equals dla HashSet bez zdefiniowanej metody: " + hSNewPerson.equals(tSBetterPerson));
                        System.out.println("Equals dla TreeSet ze zdefiniowana metoda: " + tSBetterPerson.equals(hSNewPerson));
                        System.out.println("Equals dla HashSet bez zdefiniowanej metody: " + hSNewPerson.equals(tSBetterPerson));
                        System.out.println("Equals dla TreeSet ze zdefiniowana metoda: " + tSBetterPerson.equals(hSNewPerson) + "\n");
                        System.out.println("HashCode dla HashSet bez zdefiniowanej metody: " + hSNewPerson.hashCode());
                        System.out.println("HashCode dla TreeSet ze zdefiniowana metoda: " + tSBetterPerson.hashCode());
                        System.out.println("HashCode dla HashSet bez zdefiniowanej metody: " + hSNewPerson.hashCode());
                        System.out.println("HashCode dla TreeSet ze zdefiniowana metoda: " + tSBetterPerson.hashCode() + "\n");
                        break;
                    case 5:
                        System.out.println("Brak metody");
                        break;
                    case 0: return;
                }  // koniec instrukcji switch
        }
    }

    private void AddSetPosition(HashSet hSNewPerson, TreeSet tSNewPerson, HashSet hSBetterPerson, TreeSet tSBetterPerson) throws PersonException {
        while (true) {
            UI.clearConsole();
            switch (UI.enterInt(ADD_MENU + "==>> ")) {
                case 1:
                    // Dodanie nowego elementu.
                    currentPerson = NewPerson.createNewPerson();
                    currentBPerson = (BetterPerson) BetterPerson.createNewPerson();
                    hSNewPerson.add(currentPerson);
                    tSNewPerson.add(currentPerson);
                    hSBetterPerson.add(currentBPerson);
                    tSBetterPerson.add(currentBPerson);

                    break;
                case 2:
                    // Dodanie tego samego elementu.
                    hSNewPerson.add(currentPerson);
                    tSNewPerson.add(currentPerson);
                    hSBetterPerson.add(currentBPerson);
                    tSBetterPerson.add(currentBPerson);
                    break;
                case 3:
                    // Dodanie takiego samego elementu.
                    additionalPerson = currentPerson;
                    additionalBPerson = currentBPerson;
                    hSNewPerson.add(additionalPerson);
                    tSNewPerson.add(additionalPerson);
                    hSBetterPerson.add(additionalBPerson);
                    tSBetterPerson.add(additionalBPerson);
                    break;
                case 0: return;
            }  // koniec instrukcji switch
        }
    }

    private void ListCollection() throws PersonException {
        while (true) {
            UI.clearConsole();
            ArrayList<NewPerson> aLNewPerson = new ArrayList<>();
            LinkedList<NewPerson> lLNewPerson = new LinkedList<>();
            ArrayList<BetterPerson> aLBetterPerson = new ArrayList<>();
            LinkedList<BetterPerson> lLBetterPerson = new LinkedList<>();
            showCurrentPerson();
                switch (UI.enterInt(MIDDLE_MENU + "==>> ")) {
                    case 1:
                        // Dodatkowe menu służące do dodawania elementów.
                        AddListPosition(aLNewPerson, lLNewPerson, aLBetterPerson, lLBetterPerson);
                        break;
                    case 2:
                        // Usunięcie elementu.
                        int iNr;
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Podaj pozycje, z ktorej ma zostac usuniety obiekt: ");
                        iNr = scanner.nextInt();
                        aLNewPerson.remove(iNr);
                        lLNewPerson.remove(iNr);
                        aLBetterPerson.remove(iNr);
                        aLBetterPerson.remove(iNr);
                        break;
                    case 3:
                        // Wypisanie elementów.
                        System.out.println("ArrayList dla obiektów NewPerson:");
                        for(NewPerson element: aLNewPerson) {
                            System.out.print(element.getFirstName() + " ");
                            System.out.print(element.getLastName() + " ");
                            System.out.print(element.getBirthYear() + " ");
                            System.out.println(element.getJob() + " ");
                        }
                        System.out.println("\n" + "LinkedList dla obiektów NewPerson:");
                        for(NewPerson element: lLNewPerson) {
                            System.out.print(element.getFirstName() + " ");
                            System.out.print(element.getLastName() + " ");
                            System.out.print(element.getBirthYear() + " ");
                            System.out.println(element.getJob() + " ");
                        }
                        System.out.println("\n" + "ArrayList dla obiektów BetterPerson:");
                        for(BetterPerson element: aLBetterPerson) {
                            System.out.print(element.getFirstName() + " ");
                            System.out.print(element.getLastName() + " ");
                            System.out.print(element.getBirthYear() + " ");
                            System.out.println(element.getJob() + " ");
                        }
                        System.out.println("\n" + "LinkedList dla obiektów BetterPerson:");
                        for(BetterPerson element: lLBetterPerson) {
                            System.out.print(element.getFirstName() + " ");
                            System.out.print(element.getLastName() + " ");
                            System.out.print(element.getBirthYear() + " ");
                            System.out.println(element.getJob() + " ");
                        }
                        break;
                    case 4:
                        // Różnice w metodach equals() i hashCod().
                        System.out.println("Equals dla ArrayList bez zdefiniowanej metody: " + aLNewPerson.equals(aLBetterPerson));
                        System.out.println("Equals dla ArrayList ze zdefiniowana metoda: " + aLBetterPerson.equals(aLNewPerson));
                        System.out.println("Equals dla LinkedList bez zdefiniowanej metody: " + lLNewPerson.equals(lLBetterPerson));
                        System.out.println("Equals dla LinkedList ze zdefiniowana metoda: " + lLBetterPerson.equals(lLNewPerson) + "\n");
                        System.out.println("HashCode dla ArrayList bez zdefiniowanej metody: " + aLNewPerson.hashCode());
                        System.out.println("HashCode dla ArrayList ze zdefiniowana metoda: " + aLBetterPerson.hashCode());
                        System.out.println("HashCode dla LinkedList bez zdefiniowanej metody: " + lLNewPerson.hashCode());
                        System.out.println("HashCode dla LinkedList ze zdefiniowana metoda: " + lLBetterPerson.hashCode() + "\n");
                        break;
                    case 5:
                        Collections.sort(aLBetterPerson);
                        for(BetterPerson person : aLBetterPerson) {
                            System.out.println(BetterPerson.getLastName() + ", ");
                        }
                        Collections.sort(aLBetterPerson, new FNComparator());
                        for(BetterPerson person : aLBetterPerson){
                            System.out.println(BetterPerson.getFirstName() + ", ");
                        }
                        Collections.sort(lLBetterPerson);
                        for(BetterPerson person : lLBetterPerson) {
                            System.out.println(BetterPerson.getLastName() + ", ");
                        }
                        Collections.sort(lLBetterPerson, new FNComparator());
                        for(BetterPerson person : lLBetterPerson){
                            System.out.println(BetterPerson.getFirstName() + ", ");
                        }

                        break;
                    case 0: return;
                }  // koniec instrukcji switch
        }
    }
    private void AddListPosition(ArrayList aLNewPerson, LinkedList lLNewPerson, ArrayList aLBetterPerson, LinkedList lLBetterPerson) throws PersonException {
        while (true) {
            UI.clearConsole();
            switch (UI.enterInt(ADD_MENU + "==>> ")) {
                case 1:
                    // Dodanie nowego elementu.
                    currentPerson = NewPerson.createNewPerson();
                    currentBPerson = (BetterPerson) BetterPerson.createNewPerson();
                    aLNewPerson.add(currentPerson);
                    lLNewPerson.add(currentPerson);
                    aLBetterPerson.add(currentBPerson);
                    lLBetterPerson.add(currentBPerson);
                    break;
                case 2:
                    // Dodanie tego samego elementu.
                    aLNewPerson.add(currentPerson);
                    lLNewPerson.add(currentPerson);
                    aLBetterPerson.add(currentBPerson);
                    lLBetterPerson.add(currentBPerson);
                    break;
                case 3:
                    // Dodanie takiego samego elementu.
                    additionalPerson = currentPerson;
                    additionalBPerson = currentBPerson;
                    aLNewPerson.add(additionalPerson);
                    lLNewPerson.add(additionalPerson);
                    aLBetterPerson.add(additionalBPerson);
                    lLBetterPerson.add(additionalBPerson);

                    break;
                case 0: return;
            }  // koniec instrukcji switch
        }
    }

    private void MapCollection() throws PersonException {
        while (true) {
            UI.clearConsole();
            HashMap<Integer, NewPerson> hMNewPerson = new HashMap<>();
            TreeMap<Integer, NewPerson> tMNewPerson = new TreeMap<>();
            HashMap<Integer, BetterPerson> hMBetterPerson = new HashMap<>();
            TreeMap<Integer, BetterPerson> tMBetterPerson = new TreeMap<>();
            showCurrentPerson();
                switch (UI.enterInt(MIDDLE_MENU + "==>> ")) {
                    case 1:
                        // Dodatkowe menu służące do dodawania elementów.
                        AddMapPosition(hMNewPerson, tMNewPerson, hMBetterPerson, tMBetterPerson);
                        break;
                    case 2:
                        // Usunięcie elementu.
                        int key = UI.enterInt("Podaj pozycje, z ktorej ma zostac usuniety element: ");
                        hMNewPerson.remove(key);
                        break;
                    case 3:
                        // Wypisanie elementów.
                        System.out.println("HashMap dla obiektów NewPerson:");
                        for(Map.Entry pairEntry: hMNewPerson.entrySet()) {
                            System.out.print(pairEntry.getValue() + " ");
                        }
                        System.out.println("\n" + "TreeMap dla obiektów NewPerson:");
                        for(Map.Entry pairEntry: tMNewPerson.entrySet()) {
                        System.out.print(pairEntry.getValue() + " ");
                        }
                        System.out.println("\n" + "HashMap dla obiektów BetterPerson:");
                        for(Map.Entry pairEntry: tMBetterPerson.entrySet()) {
                            System.out.print(pairEntry.getValue() + " ");
                        }
                        System.out.println("\n" + "TreeMap dla obiektów BetterPerson:");
                        for(Map.Entry pairEntry: tMBetterPerson.entrySet()) {
                            System.out.print(pairEntry.getValue() + " ");
                        }
                        break;
                    case 4:
                        // Różnice w metodach equals() i hashCod().
                        System.out.println("Equals dla HashSet bez zdefiniowanej metody: " + hMNewPerson.equals(tMBetterPerson));
                        System.out.println("Equals dla TreeSet ze zdefiniowana metoda: " + tMBetterPerson.equals(hMNewPerson));
                        System.out.println("Equals dla HashSet bez zdefiniowanej metody: " + hMNewPerson.equals(tMBetterPerson));
                        System.out.println("Equals dla TreeSet ze zdefiniowana metoda: " + tMBetterPerson.equals(hMNewPerson) + "\n");
                        System.out.println("HashCode dla HashSet bez zdefiniowanej metody: " + hMNewPerson.hashCode());
                        System.out.println("HashCode dla TreeSet ze zdefiniowana metoda: " + tMBetterPerson.hashCode());
                        System.out.println("HashCode dla HashSet bez zdefiniowanej metody: " + hMNewPerson.hashCode());
                        System.out.println("HashCode dla TreeSet ze zdefiniowana metoda: " + tMBetterPerson.hashCode() + "\n");
                        break;
                    case 5:
                        System.out.println("Brak metody.");
                        break;
                    case 0: return;
                }  // koniec instrukcji switch
        }
    }

    private void AddMapPosition(HashMap hMNewPerson, TreeMap tMNewPerson, HashMap hMBetterPerson, TreeMap tMBetterPerson) throws PersonException {
        while (true) {
            UI.clearConsole();
                switch (UI.enterInt(ADD_MENU + "==>> ")) {
                    case 1:
                        // Dodanie nowego elementu.
                        currentPerson = NewPerson.createNewPerson();
                        currentBPerson = (BetterPerson) BetterPerson.createNewPerson();
                        hMNewPerson.put(hMNewPerson.size()+1, currentPerson);
                        tMNewPerson.put(hMNewPerson.size()+1, currentPerson);
                        hMBetterPerson.put(hMBetterPerson.size()+1, currentBPerson);
                        tMBetterPerson.put(hMBetterPerson.size()+1, currentBPerson);
                        break;
                    case 2:
                        // Dodanie tego samego elementu.
                        hMNewPerson.put(hMNewPerson.size()+1, currentPerson);
                        tMNewPerson.put(hMNewPerson.size()+1, currentPerson);
                        hMBetterPerson.put(hMBetterPerson.size()+1, currentBPerson);
                        tMBetterPerson.put(hMBetterPerson.size()+1, currentBPerson);
                        break;
                    case 3:
                        // Dodanie takiego samego elementu.
                        additionalPerson = currentPerson;
                        additionalBPerson = currentBPerson;
                        hMNewPerson.put(hMNewPerson.size()+1, additionalPerson);
                        tMNewPerson.put(hMNewPerson.size()+1, additionalPerson);
                        hMBetterPerson.put(hMBetterPerson.size()+1, additionalBPerson);
                        tMBetterPerson.put(hMBetterPerson.size()+1, additionalBPerson);
                        break;
                    case 0: return;
                }  // koniec instrukcji switch
        }
    }

}  // koniec klasy PersonConsoleApp
