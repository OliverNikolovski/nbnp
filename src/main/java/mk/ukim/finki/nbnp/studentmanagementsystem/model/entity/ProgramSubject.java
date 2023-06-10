package mk.ukim.finki.nbnp.studentmanagementsystem.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "program_subject")
public class ProgramSubject {
    @EmbeddedId
    private ProgramSubjectId id;

    @MapsId("programId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    @MapsId("subjectId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "is_mandatory", nullable = false)
    private Boolean isMandatory = false;

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public ProgramSubjectId getId() {
        return id;
    }

    public void setId(ProgramSubjectId id) {
        this.id = id;
    }
}