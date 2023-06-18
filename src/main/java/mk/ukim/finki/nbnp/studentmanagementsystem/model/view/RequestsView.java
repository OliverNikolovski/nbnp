package mk.ukim.finki.nbnp.studentmanagementsystem.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "request_request_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestsView {
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
}