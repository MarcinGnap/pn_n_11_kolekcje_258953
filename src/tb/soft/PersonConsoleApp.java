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
            "    M E N U   S E T  \n" +
                    "1 - Dodanie elementu                           \n" +
                    "2 - Usunięcie elementu                         \n" +
                    "3 - Wypisanie elementów                        \n" +
                    "4 - Różnice w metodach equals() i hashCod()    \n" +
                    "0 - Powrót                                     \n";

    private static final String ADD_MENU =
            "    M E N U   A D D  \n" +
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
    }


    /*
     * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej
     * przez obiekt klasy Person
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
            Set<NewPerson> hSNewPerson = new HashSet<>();
            Set<NewPerson> tSNewPerson = new TreeSet<>();
            showCurrentPerson();
                switch (UI.enterInt(MIDDLE_MENU + "==>> ")) {
                    case 1:
                        // Dodatkowe menu służące do dodawania elementów.
                        AddSetPosition(hSNewPerson, tSNewPerson);
                        break;
                    case 2:
                        // Usunięcie elementu.
                        break;
                    case 3:
                        // Wypisanie elementów.
                        for(NewPerson element: hSNewPerson) {
                            System.out.println(element + " ");
                        }
                        break;
                    case 4:
                        // Różnice w metodach equals() i hashCod().
                        break;
                    case 0: return;
                }  // koniec instrukcji switch
        }
    }

    private void AddSetPosition(Set hSNewPerson, Set tSNewPerson) throws PersonException {
        while (true) {
            UI.clearConsole();
            switch (UI.enterInt(ADD_MENU + "==>> ")) {
                case 1:
                    // Dodanie nowego elementu.
                    currentPerson = NewPerson.createNewPerson();
                    hSNewPerson.add(currentPerson);
                    tSNewPerson.add(currentPerson);
                    break;
                case 2:
                    // Dodanie tego samego elementu.
                    hSNewPerson.add(currentPerson);
                    tSNewPerson.add(currentPerson);
                    break;
                case 3:
                    // Dodanie takiego samego elementu.
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
            showCurrentPerson();
                switch (UI.enterInt(MIDDLE_MENU + "==>> ")) {
                    case 1:
                        // Dodatkowe menu służące do dodawania elementów.
                        AddListPosition(aLNewPerson, lLNewPerson);
                        break;
                    case 2:
                        // Usunięcie elementu.
                        break;
                    case 3:
                        // Wypisanie elementów.
                        break;
                    case 4:
                        // Różnice w metodach equals() i hashCod().
                        break;
                    case 0: return;
                }  // koniec instrukcji switch
        }
    }

    private void AddListPosition(ArrayList aLNewPerson, LinkedList lLNewPerson) throws PersonException {
        while (true) {
            UI.clearConsole();
            switch (UI.enterInt(ADD_MENU + "==>> ")) {
                case 1:
                    // Dodanie nowego elementu.
                    currentPerson = NewPerson.createNewPerson();
                    aLNewPerson.add(currentPerson);
                    lLNewPerson.add(currentPerson);
                    break;
                case 2:
                    // Dodanie tego samego elementu.
                    aLNewPerson.add(currentPerson);
                    lLNewPerson.add(currentPerson);
                    break;
                case 3:
                    // Dodanie takiego samego elementu.
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
            showCurrentPerson();
                switch (UI.enterInt(MIDDLE_MENU + "==>> ")) {
                    case 1:
                        // Dodatkowe menu służące do dodawania elementów.
                        AddMapPosition(hMNewPerson, tMNewPerson);
                        break;
                    case 2:
                        // Usunięcie elementu.
                        int key = UI.enterInt("Podaj pozycje, z ktorej ma zostac usuniety element: ");
                        hMNewPerson.remove(key);
                        break;
                    case 3:
                        // Wypisanie elementów.
                        break;
                    case 4:
                        // Różnice w metodach equals() i hashCod().
                        break;
                    case 0: return;
                }  // koniec instrukcji switch
        }
    }

    private void AddMapPosition(HashMap hMNewPerson, TreeMap tMNewPerson) throws PersonException {
        while (true) {
            UI.clearConsole();
                switch (UI.enterInt(ADD_MENU + "==>> ")) {
                    case 1:
                        // Dodanie nowego elementu.
                        currentPerson = NewPerson.createNewPerson();
                        hMNewPerson.put(hMNewPerson.size()+1, currentPerson);
                        tMNewPerson.put(hMNewPerson.size()+1, currentPerson);
                        break;
                    case 2:
                        // Dodanie tego samego elementu.
                        hMNewPerson.put(hMNewPerson.size()+1, currentPerson);
                        tMNewPerson.put(hMNewPerson.size()+1, currentPerson);
                        break;
                    case 3:
                        // Dodanie takiego samego elementu.
                        break;
                    case 0: return;
                }  // koniec instrukcji switch
        }
    }

}  // koniec klasy PersonConsoleApp
