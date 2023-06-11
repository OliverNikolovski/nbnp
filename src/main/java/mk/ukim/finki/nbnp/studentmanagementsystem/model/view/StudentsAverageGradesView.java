package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Immutable
@Table(name = "students_average_grades_view")
public class StudentsAverageGradesView {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "indeks")
    private String indeks;

    @Column(name = "ime_prezime")
    private String imePrezime;

    @Column(name = "prosek", precision = 4, scale = 2)
    private BigDecimal prosek;

    public BigDecimal getProsek() {
        return prosek;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public String getIndeks() {
        return indeks;
    }

    public Long getId() {
        return id;
    }

    protected StudentsAverageGradesView() {
    }
}