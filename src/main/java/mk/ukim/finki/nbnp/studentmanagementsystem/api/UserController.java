package mk.ukim.finki.nbnp.studentmanagementsystem.api;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.dto.EnrolledSemesterDto;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.dto.EnrolledSubjectDto;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.dto.UserDto;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.ExamsView;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.UserPersonalInfoView;
import mk.ukim.finki.nbnp.studentmanagementsystem.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/{id}/info")
    public UserPersonalInfoView getPersonalInfoForUser(@PathVariable Long id) {
        return userService.getPersonalInfoForUser(id);
    }

    @GetMapping("/{id}/semesters")
    public List<EnrolledSemesterDto> getSemestersForStudent(@PathVariable Long id) {
        return userService.getSemestersForStudent(id);
    }

    @GetMapping("/{studentId}/subjects")
    public List<EnrolledSubjectDto> getSubjectsInSemesterForStudent(@PathVariable Long studentId,
                                                                    @RequestParam Long semesterId) {
        return userService.getSubjectsInSemesterForStudent(studentId, semesterId);
    }

    @GetMapping("/{id}/exams")
    public List<ExamsView> getAllPassedExamsForStudent(@PathVariable Long id) {
        return userService.getAllPassedExamsForStudent(id);
    }

}
