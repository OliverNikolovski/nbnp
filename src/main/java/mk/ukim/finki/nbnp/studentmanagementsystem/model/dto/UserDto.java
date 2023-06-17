package mk.ukim.finki.nbnp.studentmanagementsystem.model.dto;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.Role;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
    private Long id;

    private String facultyEmail;

    private String indeks;

    private Boolean threeYearStudies;

    private Integer yearOfEnrollment;

    private String resume;

    private Double averageGrade;

    private Integer totalCredits;

    private List<String> roles;

    public UserDto(Long id, String facultyEmail, String indeks, Boolean threeYearStudies,
                   Integer yearOfEnrollment, String resume, Double averageGrade, Integer totalCredits, List<Role> roles) {
        this.id = id;
        this.facultyEmail = facultyEmail;
        this.indeks = indeks;
        this.threeYearStudies = threeYearStudies;
        this.yearOfEnrollment = yearOfEnrollment;
        this.resume = resume;
        this.averageGrade = averageGrade;
        this.totalCredits = totalCredits;
        this.roles = roles.stream().map(Role::getAuthority).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacultyEmail() {
        return facultyEmail;
    }

    public void setFacultyEmail(String facultyEmail) {
        this.facultyEmail = facultyEmail;
    }

    public String getIndeks() {
        return indeks;
    }

    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }

    public Boolean getThreeYearStudies() {
        return threeYearStudies;
    }

    public void setThreeYearStudies(Boolean threeYearStudies) {
        this.threeYearStudies = threeYearStudies;
    }

    public Integer getYearOfEnrollment() {
        return yearOfEnrollment;
    }

    public void setYearOfEnrollment(Integer yearOfEnrollment) {
        this.yearOfEnrollment = yearOfEnrollment;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public Integer getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(Integer totalCredits) {
        this.totalCredits = totalCredits;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
