package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnrolledSemestersViewId implements Serializable {
    private static final long serialVersionUID = 9052909295909771338L;
    @Column(name = "semester_id")
    private Long semesterId;
    @Column(name = "student_id")
    private Long studentId;

    public Long getStudentId() {
        return studentId;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, semesterId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EnrolledSemestersViewId entity = (EnrolledSemestersViewId) o;
        return Objects.equals(this.studentId, entity.studentId) &&
                Objects.equals(this.semesterId, entity.semesterId);
    }
}