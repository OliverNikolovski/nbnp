package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "user_personal_info_view")
public class UserPersonalInfoView {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "embg", length = 13)
    private String embg;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "personal_email")
    private String personalEmail;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "faculty_email")
    private String facultyEmail;

    @Column(name = "indeks")
    private String indeks;

    @Column(name = "resume", length = 1024)
    private String resume;

    @Column(name = "year_of_enrollment")
    private Integer yearOfEnrollment;

    @Column(name = "average_grade")
    private Double averageGrade;

    @Column(name = "total_credits")
    private Integer totalCredits;

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalCredits() {
        return totalCredits;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public Integer getYearOfEnrollment() {
        return yearOfEnrollment;
    }

    public String getResume() {
        return resume;
    }

    public String getIndeks() {
        return indeks;
    }

    public String getFacultyEmail() {
        return facultyEmail;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getNationality() {
        return nationality;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getEmbg() {
        return embg;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmbg(String embg) {
        this.embg = embg;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setFacultyEmail(String facultyEmail) {
        this.facultyEmail = facultyEmail;
    }

    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setYearOfEnrollment(Integer yearOfEnrollment) {
        this.yearOfEnrollment = yearOfEnrollment;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void setTotalCredits(Integer totalCredits) {
        this.totalCredits = totalCredits;
    }

    public UserPersonalInfoView() {
    }
}