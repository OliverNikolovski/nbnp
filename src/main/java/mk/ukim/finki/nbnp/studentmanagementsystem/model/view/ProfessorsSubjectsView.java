package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "professors_subjects_view")
public class ProfessorsSubjectsView {
    @EmbeddedId
    private ProfessorsSubjectsViewId id;

    @Column(name = "professor_id",
            insertable = false,
            updatable = false)
    private Long professorId;

    @Column(name = "professor_name")
    private String professorName;

    @Column(name = "subject_id",
            insertable = false,
            updatable = false)
    private Long subjectId;

    @Column(name = "subject_name")
    private String subjectName;

    public String getSubjectName() {
        return subjectName;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public String getProfessorName() {
        return professorName;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public ProfessorsSubjectsViewId getId() {
        return id;
    }

    public void setId(ProfessorsSubjectsViewId id) {
        this.id = id;
    }

    protected ProfessorsSubjectsView() {
    }
}