package mk.ukim.finki.nbnp.studentmanagementsystem.model.request;

import java.util.List;

public class EnrollSubjectsForStudentRequest {
    public Long studentId;
    public Long semesterId;
    public List<Long> subjectIds;
}
