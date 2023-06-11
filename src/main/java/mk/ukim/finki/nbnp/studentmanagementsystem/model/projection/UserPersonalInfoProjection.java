package mk.ukim.finki.nbnp.studentmanagementsystem.model.projection;

import java.time.LocalDate;

public interface UserPersonalInfoProjection {
    Long getId();
    String getFirstName();
    String getmiddleName();
    String getlastName();
    String getembg();
    String getcity();
    String getcountry();
    String getnationality();
    String getaddress();
    String getgender();
    String getphoneNumber();
    String getpersonalEmail();
    LocalDate birthDate();
    String facultyEmail();
    String indeks();
    String resume();
    Integer yearOfEnrollment();
    Double averageGrade();
    Integer totalCredits();
}
