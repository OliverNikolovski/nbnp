package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "enrolled_semesters_view")
public class EnrolledSemestersView {
    @EmbeddedId
    private EnrolledSemestersViewId id;

    @Column(name = "type", length = 10)
    private String type;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "status")
    private String status;

    public String getStatus() {
        return status;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getType() {
        return type;
    }

    public EnrolledSemestersViewId getId() {
        return id;
    }

    public void setId(EnrolledSemestersViewId id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EnrolledSemestersView() {
    }
}