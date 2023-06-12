package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnrolledSubjectsViewId implements Serializable {
    private static final long serialVersionUID = -2339117454130271441L;
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "subject_id")
    private Long subjectId;
    @Column(name = "semester_id")
    private Long semesterId;

    public Long getSemesterId() {
        return semesterId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, semesterId, subjectId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EnrolledSubjectsViewId entity = (EnrolledSubjectsViewId) o;
        return Objects.equals(this.studentId, entity.studentId) &&
                Objects.equals(this.semesterId, entity.semesterId) &&
                Objects.equals(this.subjectId, entity.subjectId);
    }
}