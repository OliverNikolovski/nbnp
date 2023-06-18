package mk.ukim.finki.nbnp.studentmanagementsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnrolledSubjectDto {
    private Long studentId;
    private Long subjectId;
    private Long semesterId;
    private Long professorId;
    private String code;
    private Integer credits;
    private String subject;
    private Integer semesterNumber;
    private String program;
    private String studyType;
    private String profFirstName;
    private String profLastName;
    private String semesterType;
    private LocalDate semesterStartDate;
    private LocalDate semesterEndDate;
}
