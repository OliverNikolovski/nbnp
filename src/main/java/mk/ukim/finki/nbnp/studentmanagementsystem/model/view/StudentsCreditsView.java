package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "students_credits_view")
public class StudentsCreditsView {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "indeks")
    private String indeks;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Column(name = "krediti")
    private Long krediti;

    public Long getKrediti() {
        return krediti;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getIme() {
        return ime;
    }

    public String getIndeks() {
        return indeks;
    }

    public Long getId() {
        return id;
    }

    protected StudentsCreditsView() {
    }
}