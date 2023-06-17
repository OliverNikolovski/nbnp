package mk.ukim.finki.nbnp.studentmanagementsystem.api;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.dto.UserDto;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.EnrolledSemestersView;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.EnrolledSubjectsView;
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

    // this is just for testing if login works, should be removed later
    @GetMapping
    public String greeting() {
        return "Successfully Logged in!";
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
    public List<EnrolledSemestersView> getSemestersForStudent(@PathVariable Long id) {
        return userService.getSemestersForStudent(id);
    }

    @GetMapping("/{studentId}/subjects")
    public List<EnrolledSubjectsView> getSubjectsInSemesterForStudent(@PathVariable Long studentId,
                                                                      @RequestParam Long semesterId) {
        return userService.getSubjectsInSemesterForStudent(studentId, semesterId);
    }

    @GetMapping("/{id}/exams")
    public List<ExamsView> getAllPassedExamsForStudent(@PathVariable Long id) {
        return userService.getAllPassedExamsForStudent(id);
    }

}
