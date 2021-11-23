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

    public NewPerson(String first_name, String last_name, String job_name, int birth_year) throws PersonException {
        setRandomFirstName(first_name);
        setRandomLastName(last_name);
        setRandomJob(job_name);
        setRandomBirthYear(birth_year);
    }

    public String getFirstName() {
        return firstName;
    }

    public void  setRandomFirstName(String first_name) throws PersonException {
        String sFirstNames[] = {"Marcin" , "Kinga", "Stefan", "Alina", "Ewa", "Mariusz", "Mateusz", "Roman", "Emilia", "Arkadiusz", "Maria", "Julia"};
        first_name = sFirstNames[new Random().nextInt(sFirstNames.length)];
        this.firstName = first_name;
    }

    public void  setRandomLastName(String last_name) throws PersonException {
        String sLastNames[] = {"Gnap", "Nowak", "Kowalczyk", "Adamczuk", "Pytka", "Zasada", "Mielczarek", "Staniec", "Robak", "Gerlach", "Githab", "Implement"};
        last_name = sLastNames[new Random().nextInt(sLastNames.length)];
        this.lastName = last_name;
    }

    public void  setRandomJob(String job_name) throws PersonException {
        String sJobNames[] = {"Kucharz", "Stolarz", "Maszynista", "Piekarz", "Mechanik", "Hydraulik", "Ogrodnik", "Prawnik", "Pisarz", "Nauczyciel", "Lekarz", "Bezrobotny"};
        job_name = sJobNames[new Random().nextInt(sJobNames.length)];
        this.job = job_name;
    }

    public void  setRandomBirthYear(int birth_year) throws PersonException {
        birth_year = new Random().nextInt(1900 - 2002);
        this.birthYear = birth_year;
    }

}
