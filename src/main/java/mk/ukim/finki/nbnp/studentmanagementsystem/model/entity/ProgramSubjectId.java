package mk.ukim.finki.nbnp.studentmanagementsystem.model.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProgramSubjectId implements Serializable {
    private static final long serialVersionUID = -2380046582642276213L;
    @Column(name = "program_id", nullable = false)
    private Long programId;
    @Column(name = "subject_id", nullable = false)
    private Long subjectId;

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(programId, subjectId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProgramSubjectId entity = (ProgramSubjectId) o;
        return Objects.equals(this.programId, entity.programId) &&
                Objects.equals(this.subjectId, entity.subjectId);
    }
}