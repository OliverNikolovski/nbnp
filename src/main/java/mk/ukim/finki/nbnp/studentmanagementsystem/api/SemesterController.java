package mk.ukim.finki.nbnp.studentmanagementsystem.api;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.Semester;
import mk.ukim.finki.nbnp.studentmanagementsystem.service.SemesterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/semesters")
public class SemesterController {
    private final SemesterService semesterService;

    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @GetMapping("/active")
    public ResponseEntity<Semester> getActiveSemester() {
        return semesterService.findActiveSemester()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/enroll-student")
    public void enrollStudentInSemester(@RequestParam Long studentId, @RequestParam Long semesterId) {
        semesterService.enrollStudentInSemester(studentId, semesterId);
    }
}
