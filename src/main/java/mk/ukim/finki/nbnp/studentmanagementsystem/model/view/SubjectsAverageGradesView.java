package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Immutable
@Table(name = "subjects_average_grades_view")
public class SubjectsAverageGradesView {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "predmet")
    private String predmet;

    @Column(name = "prosek", precision = 4, scale = 2)
    private BigDecimal prosek;

    public BigDecimal getProsek() {
        return prosek;
    }

    public String getPredmet() {
        return predmet;
    }

    public Long getId() {
        return id;
    }

    protected SubjectsAverageGradesView() {
    }
}