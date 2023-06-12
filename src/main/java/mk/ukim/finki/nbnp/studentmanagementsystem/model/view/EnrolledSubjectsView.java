package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "enrolled_subjects_view")
@Getter
@Setter
@NoArgsConstructor
public class EnrolledSubjectsView {
    @EmbeddedId
    private EnrolledSubjectsViewId id;

    @Column(name = "professor_id")
    private Long professorId;

    @Column(name = "code", length = 9)
    private String code;

    @Column(name = "credits")
    private Integer credits;

    @Column(name = "subject")
    private String subject;

    @Column(name = "semester_number")
    private Integer semesterNumber;

    @Column(name = "program")
    private String program;

    @Column(name = "study_type")
    private String studyType;

    @Column(name = "prof_first_name")
    private String profFirstName;

    @Column(name = "prof_last_name")
    private String profLastName;

    @Column(name = "semester_type", length = 10)
    private String semesterType;

    @Column(name = "semester_start_date")
    private LocalDate semesterStartDate;

    @Column(name = "semester_end_date")
    private LocalDate semesterEndDate;
}