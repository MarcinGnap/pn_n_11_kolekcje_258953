package tb.soft;

import java.util.Comparator;
import java.util.Objects;

public class BetterPerson extends NewPerson implements Comparable{

    private String firstName;
    private String lastName;
    private int birthYear;
    private String job;

    public BetterPerson(String first_name, String last_name, String job_name) throws PersonException {
        super(first_name, last_name, job_name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetterPerson that = (BetterPerson) o;
        return getBirthYear() == that.getBirthYear() && getFirstName().equals(that.getFirstName()) && getLastName().equals(that.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getBirthYear());
    }

    @Override
    public int compareTo(Object o) {
        return getLastName().compareTo(((BetterPerson) o).getLastName());
    }


}



class FNComparator implements Comparator<BetterPerson> {


    @Override
    public int compare(BetterPerson o1, BetterPerson o2) {
        return o1.getFirstName().compareTo(o2.getFirstName());
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
