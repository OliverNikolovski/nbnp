package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "subjects_view")
public class SubjectsView {
    @Id
    @Column(name = "predmet_id")
    private Long predmetId;

    @Column(name = "predmet")
    private String predmet;

    @Column(name = "kod", length = 9)
    private String kod;

    @Column(name = "krediti")
    private Integer krediti;

    @Column(name = "broj_semestar")
    private Integer brojSemestar;

    @Column(name = "nasoka")
    private String nasoka;

    @Column(name = "tip_na_studii")
    private String tipNaStudii;

    public String getTipNaStudii() {
        return tipNaStudii;
    }

    public String getNasoka() {
        return nasoka;
    }

    public Integer getBrojSemestar() {
        return brojSemestar;
    }

    public Integer getKrediti() {
        return krediti;
    }

    public String getKod() {
        return kod;
    }

    public String getPredmet() {
        return predmet;
    }

    public Long getPredmetId() {
        return predmetId;
    }

    protected SubjectsView() {
    }
}