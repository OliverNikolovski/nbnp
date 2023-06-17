package mk.ukim.finki.nbnp.studentmanagementsystem.factory;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.dto.UserDto;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class DtoFactory {

    public UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getFacultyEmail(), user.getIndeks(),
                user.getThreeYearStudies(), user.getYearOfEnrollment(), user.getResume(), user.getAverageGrade(),
                user.getTotalCredits(), user.getRoles());
    }

}
