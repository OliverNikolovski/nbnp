package mk.ukim.finki.nbnp.studentmanagementsystem.model.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentStudytypeId implements Serializable {
    private static final long serialVersionUID = 7740715339574234299L;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "study_type_id", nullable = false)
    private Long studyTypeId;

    public Long getStudyTypeId() {
        return studyTypeId;
    }

    public void setStudyTypeId(Long studyTypeId) {
        this.studyTypeId = studyTypeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studyTypeId, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentStudytypeId entity = (StudentStudytypeId) o;
        return Objects.equals(this.studyTypeId, entity.studyTypeId) &&
                Objects.equals(this.userId, entity.userId);
    }
}