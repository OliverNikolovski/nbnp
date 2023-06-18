package mk.ukim.finki.nbnp.studentmanagementsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnrolledSemesterDto {
    private Long semesterId;
    private Long studentId;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}
