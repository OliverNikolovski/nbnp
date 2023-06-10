package mk.ukim.finki.nbnp.studentmanagementsystem.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "enrolled_semester")
public class EnrolledSemester {
    @EmbeddedId
    private EnrolledSemesterId id;

    @MapsId("studentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @MapsId("semesterId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "semester_status_id", nullable = false)
    private SemesterStatus semesterStatus;

    public SemesterStatus getSemesterStatus() {
        return semesterStatus;
    }

    public void setSemesterStatus(SemesterStatus semesterStatus) {
        this.semesterStatus = semesterStatus;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public EnrolledSemesterId getId() {
        return id;
    }

    public void setId(EnrolledSemesterId id) {
        this.id = id;
    }
}