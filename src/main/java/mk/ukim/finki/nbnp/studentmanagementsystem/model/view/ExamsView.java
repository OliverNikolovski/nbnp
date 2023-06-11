package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "exams_view")
public class ExamsView {
    @Id
    @Column(name = "exam_id")
    private Long examId;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "student_indeks")
    private String studentIndeks;

    @Column(name = "code", length = 9)
    private String code;

    @Column(name = "subject")
    private String subject;

    @Column(name = "subject_id")
    private Long subjectId;

    @Lob
    @Column(name = "session")
    private String session;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "semester")
    private Integer semester;

    @Column(name = "credits")
    private Integer credits;

    @Column(name = "\"winter/summer\"")
    private String winterOrSummer;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "professor_id")
    private Long professorId;

    @Column(name = "prof_first_name")
    private String profFirstName;

    @Column(name = "prof_last_name")
    private String profLastName;

    public String getProfLastName() {
        return profLastName;
    }

    public String getProfFirstName() {
        return profFirstName;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public Integer getGrade() {
        return grade;
    }

    public String getWinterOrSummer() {
        return winterOrSummer;
    }

    public Integer getCredits() {
        return credits;
    }

    public Integer getSemester() {
        return semester;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSession() {
        return session;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public String getSubject() {
        return subject;
    }

    public String getCode() {
        return code;
    }

    public String getStudentIndeks() {
        return studentIndeks;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getExamId() {
        return examId;
    }

    protected ExamsView() {
    }
}