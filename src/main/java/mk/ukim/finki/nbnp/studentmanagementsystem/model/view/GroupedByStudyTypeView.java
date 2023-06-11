package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "grouped_by_study_type_view")
public class GroupedByStudyTypeView {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "tip_na_studii")
    private String tipNaStudii;

    @Column(name = "broj_na_studenti")
    private Long brojNaStudenti;

    public Long getBrojNaStudenti() {
        return brojNaStudenti;
    }

    public String getTipNaStudii() {
        return tipNaStudii;
    }

    public Long getId() {
        return id;
    }

    protected GroupedByStudyTypeView() {
    }
}