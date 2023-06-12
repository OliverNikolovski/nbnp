package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "exams_view")
@Getter
@Setter
@NoArgsConstructor
public class ExamsView {
    @Id
    @Column(name = "exam_id")
    private Long examId;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "student_indeks")
    private String studentIndeks;

    @Column(name = "code", length = 9)
    private String code;

    @Column(name = "subject")
    private String subject;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "session")
    private String session;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "semester")
    private Integer semester;

    @Column(name = "credits")
    private Integer credits;

    @Column(name = "winter_summer")
    private String winterOrSummer;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "professor_id")
    private Long professorId;

    @Column(name = "prof_first_name")
    private String profFirstName;

    @Column(name = "prof_last_name")
    private String profLastName;
}