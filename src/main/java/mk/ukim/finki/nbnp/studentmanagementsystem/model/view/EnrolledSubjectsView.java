package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "enrolled_subjects_view")
public class EnrolledSubjectsView {
    @EmbeddedId
    private EnrolledSubjectsViewId id;

    @Column(name = "student_id",
            insertable = false,
            updatable = false)
    private Long studentId;

    @Column(name = "subject_id",
            insertable = false,
            updatable = false)
    private Long subjectId;

    @Column(name = "professor_id")
    private Long professorId;

    @Column(name = "semester_id",
            insertable = false,
            updatable = false)
    private Long semesterId;

    @Column(name = "code", length = 9)
    private String code;

    @Column(name = "credits")
    private Integer credits;

    @Column(name = "subject")
    private String subject;

    @Column(name = "semester_number")
    private Integer semesterNumber;

    @Column(name = "program")
    private String program;

    @Column(name = "study_type")
    private String studyType;

    @Column(name = "prof_first_name")
    private String profFirstName;

    @Column(name = "prof_last_name")
    private String profLastName;

    @Column(name = "semester_type", length = 10)
    private String semesterType;

    @Column(name = "semester_start_date")
    private LocalDate semesterStartDate;

    @Column(name = "semester_end_date")
    private LocalDate semesterEndDate;

    public LocalDate getSemesterEndDate() {
        return semesterEndDate;
    }

    public LocalDate getSemesterStartDate() {
        return semesterStartDate;
    }

    public String getSemesterType() {
        return semesterType;
    }

    public String getProfLastName() {
        return profLastName;
    }

    public String getProfFirstName() {
        return profFirstName;
    }

    public String getStudyType() {
        return studyType;
    }

    public String getProgram() {
        return program;
    }

    public Integer getSemesterNumber() {
        return semesterNumber;
    }

    public String getSubject() {
        return subject;
    }

    public Integer getCredits() {
        return credits;
    }

    public String getCode() {
        return code;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public EnrolledSubjectsViewId getId() {
        return id;
    }

    public void setId(EnrolledSubjectsViewId id) {
        this.id = id;
    }

    protected EnrolledSubjectsView() {
    }
}