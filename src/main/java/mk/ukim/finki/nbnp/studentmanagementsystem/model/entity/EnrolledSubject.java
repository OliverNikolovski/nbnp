package mk.ukim.finki.nbnp.studentmanagementsystem.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "enrolled_subject")
public class EnrolledSubject {
    @EmbeddedId
    private EnrolledSubjectId id;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false),
            @JoinColumn(name = "semester_id", referencedColumnName = "semester_id", nullable = false)
    })
    private EnrolledSemester enrolledSemester;

    @MapsId("subjectId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "professor_id", nullable = false)
    private User professor;

    public User getProfessor() {
        return professor;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public EnrolledSemester getEnrolledSemester() {
        return enrolledSemester;
    }

    public void setEnrolledSemester(EnrolledSemester enrolledSemester) {
        this.enrolledSemester = enrolledSemester;
    }

    public EnrolledSubjectId getId() {
        return id;
    }

    public void setId(EnrolledSubjectId id) {
        this.id = id;
    }
}