package mk.ukim.finki.nbnp.studentmanagementsystem.model.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentProgramId implements Serializable {
    private static final long serialVersionUID = -2313046440464551545L;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "program_id", nullable = false)
    private Long programId;

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, programId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentProgramId entity = (StudentProgramId) o;
        return Objects.equals(this.userId, entity.userId) &&
                Objects.equals(this.programId, entity.programId);
    }
}