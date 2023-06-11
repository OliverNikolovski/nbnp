package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "request_request_type")
public class RequestRequestType {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "request_type_id")
    private Long requestTypeId;

    @Column(name = "request_date")
    private LocalDate requestDate;

    @Column(name = "status")
    private String status;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public Long getRequestTypeId() {
        return requestTypeId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    protected RequestRequestType() {
    }
}