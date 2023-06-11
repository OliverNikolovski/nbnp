package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProfessorsSubjectsViewId implements Serializable {
    private static final long serialVersionUID = 2148506273716976216L;
    @Column(name = "professor_id")
    private Long professorId;
    @Column(name = "subject_id")
    private Long subjectId;

    public Long getSubjectId() {
        return subjectId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(professorId, subjectId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProfessorsSubjectsViewId entity = (ProfessorsSubjectsViewId) o;
        return Objects.equals(this.professorId, entity.professorId) &&
                Objects.equals(this.subjectId, entity.subjectId);
    }
}