package mk.ukim.finki.nbnp.studentmanagementsystem.api;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.Subject;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.request.EnrollSubjectsForStudentRequest;
import mk.ukim.finki.nbnp.studentmanagementsystem.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/enroll")
    public void enrollStudentInSubject(@RequestParam Long studentId,
                                       @RequestParam Long semesterId,
                                       @RequestParam Long subjectId) {
        subjectService.enrollStudentInSubject(studentId, semesterId, subjectId);
    }

    @PostMapping("/enroll-subjects")
    public void enrollSubjectsForStudent(@RequestBody EnrollSubjectsForStudentRequest request) {
        subjectService.enrollSubjectsForStudent(request);
    }

    @GetMapping("/{semester}")
    public List<Subject> getSubjectsBySemesterType(@PathVariable String semester) {
        return subjectService.getSubjectsBySemesterType(semester);
    }

}
