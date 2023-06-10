package mk.ukim.finki.nbnp.studentmanagementsystem.model.entity;

import lombok.Getter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnrolledSubjectId implements Serializable {
    private static final long serialVersionUID = -1935660183795443781L;
    @Column(name = "student_id", nullable = false)
    private Long studentId;
    @Column(name = "semester_id", nullable = false)
    private Long semesterId;
    @Column(name = "subject_id", nullable = false)
    private Long subjectId;

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, semesterId, subjectId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EnrolledSubjectId entity = (EnrolledSubjectId) o;
        return Objects.equals(this.studentId, entity.studentId) &&
                Objects.equals(this.semesterId, entity.semesterId) &&
                Objects.equals(this.subjectId, entity.subjectId);
    }
}