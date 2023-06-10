package mk.ukim.finki.nbnp.studentmanagementsystem.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "faculty_email", nullable = false)
    private String facultyEmail;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "indeks")
    private String indeks;

    @Column(name = "three_year_studies")
    private Boolean threeYearStudies;

    @Column(name = "year_of_enrollment")
    private Integer yearOfEnrollment;

    @Column(name = "resume", length = 1024)
    private String resume;

    @Column(name = "average_grade")
    private Double averageGrade;

    @Column(name = "total_credits")
    private Integer totalCredits;

    public Integer getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(Integer totalCredits) {
        this.totalCredits = totalCredits;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Integer getYearOfEnrollment() {
        return yearOfEnrollment;
    }

    public void setYearOfEnrollment(Integer yearOfEnrollment) {
        this.yearOfEnrollment = yearOfEnrollment;
    }

    public Boolean getThreeYearStudies() {
        return threeYearStudies;
    }

    public void setThreeYearStudies(Boolean threeYearStudies) {
        this.threeYearStudies = threeYearStudies;
    }

    public String getIndeks() {
        return indeks;
    }

    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFacultyEmail() {
        return facultyEmail;
    }

    public void setFacultyEmail(String facultyEmail) {
        this.facultyEmail = facultyEmail;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}