package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "subjects_by_times_passed_view")
public class SubjectsByTimesPassedView {
    @Id
    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "number_of_passed_exams")
    private Long numberOfPassedExams;

    public Long getNumberOfPassedExams() {
        return numberOfPassedExams;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    protected SubjectsByTimesPassedView() {
    }
}