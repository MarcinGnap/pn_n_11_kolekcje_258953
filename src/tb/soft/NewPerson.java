package tb.soft;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class NewPerson {

    private String firstName;
    private String lastName;
    private int birthYear;
    private String job;

    public NewPerson(String first_name, String last_name, String job_name) throws PersonException {
        setRandomFirstName(first_name);
        setRandomLastName(last_name);
        setRandomJob(job_name);
    }

    public String getFirstName() {
        return firstName;
    }

    public String geFirstName() {
        return firstName;
    }

    public void  setRandomFirstName(String first_name) throws PersonException {
        String sFirstNames[] = {"Marcin" , "Kinga", "Stefan", "Alina", "Ewa", "Mariusz", "Mateusz", "Roman", "Emilia", "Arkadiusz", "Maria", "Julia"};
        first_name = sFirstNames[new Random().nextInt(sFirstNames.length)];
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void  setRandomLastName(String last_name) throws PersonException {
        String sLastNames[] = {"Gnap", "Nowak", "Kowalczyk", "Adamczuk", "Pytka", "Zasada", "Mielczarek", "Staniec", "Robak", "Gerlach", "Githab", "Implement"};
        last_name = sLastNames[new Random().nextInt(sLastNames.length)];
        this.lastName = last_name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void  setRandomBirthYear(int birth_year) throws PersonException {
        birth_year = new Random().nextInt(1900 - 2002);
        this.birthYear = birth_year;
    }

    public String getJob() {
        return job;
    }

    public void  setRandomJob(String job_name) throws PersonException {
        String sJobNames[] = {"Kucharz", "Stolarz", "Maszynista", "Piekarz", "Mechanik", "Hydraulik", "Ogrodnik", "Prawnik", "Pisarz", "Nauczyciel", "Lekarz", "Bezrobotny"};
        job_name = sJobNames[new Random().nextInt(sJobNames.length)];
        this.job = job_name;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public static void printToFile(PrintWriter writer, NewPerson person){
        writer.println(person.firstName + "#" + person.lastName +
                "#" + person.birthYear + "#" + person.job);
    }

    public static void printToFile(String file_name, NewPerson person) throws PersonException {
        try (PrintWriter writer = new PrintWriter(file_name)) {
            printToFile(writer, person);
        } catch (FileNotFoundException e){
            throw new PersonException("Nie odnaleziono pliku " + file_name);
        }
    }

    public static NewPerson readFromFile(BufferedReader reader) throws PersonException{
        try {
            String line = reader.readLine();
            String[] txt = line.split("#");
            NewPerson person = new NewPerson(txt[0], txt[1], txt[3]);
            person.setRandomBirthYear(Integer.parseInt(txt[2]));
            return person;
        } catch(IOException e){
            throw new PersonException("Wystąpił błąd podczas odczytu danych z pliku.");
        }
    }

    public static NewPerson readFromFile(String file_name) throws PersonException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(file_name)))) {
            return NewPerson.readFromFile(reader);
        } catch (FileNotFoundException e){
            throw new PersonException("Nie odnaleziono pliku " + file_name);
        } catch(IOException e){
            throw new PersonException("Wystąpił błąd podczas odczytu danych z pliku.");
        }
    }

    static NewPerson createNewPerson(String first_name, String last_name, String job_name, int birth_year) throws PersonException {
        NewPerson person = null;
        try {
            person = new NewPerson(first_name, last_name, job_name);
            person.setRandomBirthYear(birth_year);
        } catch (PersonException e) {
            throw new PersonException("Wystąpił błąd podczas tworzenia obiaktu klasy NewPerson.");
        }
        return person;
    }

}
