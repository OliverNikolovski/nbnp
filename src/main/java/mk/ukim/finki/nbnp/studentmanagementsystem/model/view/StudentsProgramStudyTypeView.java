package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "students_program_study_type_view")
public class StudentsProgramStudyTypeView {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Column(name = "nasoka")
    private String nasoka;

    @Column(name = "tip_na_studii")
    private String tipNaStudii;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "finished")
    private Boolean finished;

    public Boolean getFinished() {
        return finished;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getTipNaStudii() {
        return tipNaStudii;
    }

    public String getNasoka() {
        return nasoka;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getIme() {
        return ime;
    }

    public Long getId() {
        return id;
    }

    protected StudentsProgramStudyTypeView() {
    }
}