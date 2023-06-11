package mk.ukim.finki.nbnp.studentmanagementsystem.mapper;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.dto.UserDto;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.User;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.UserPersonalInfoView;
import mk.ukim.finki.nbnp.studentmanagementsystem.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final UserService userService;

    public UserMapper(UserService userService) {
        this.userService = userService;
    }

    public UserDto findByFacultyEmail(String email) throws UsernameNotFoundException {
        return toUserDto(userService.findUserByFacultyEmail(email));
    }

    public UserDto findById(Long id) {
        return toUserDto(userService.findById(id));
    }

    public UserPersonalInfoView getPersonalInfoForUser(Long id) {
        return userService.getPersonalInfoForUser(id);
    }

    private UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getFacultyEmail(), user.getIndeks(),
                user.getThreeYearStudies(), user.getYearOfEnrollment(), user.getResume(), user.getAverageGrade(),
                user.getTotalCredits(), user.getRoles());
    }

}
