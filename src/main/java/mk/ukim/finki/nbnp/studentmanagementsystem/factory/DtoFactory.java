package mk.ukim.finki.nbnp.studentmanagementsystem.factory;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.dto.EnrolledSemesterDto;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.dto.EnrolledSubjectDto;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.dto.UserDto;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.User;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.EnrolledSemestersView;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.EnrolledSubjectsView;
import org.springframework.stereotype.Component;

@Component
public class DtoFactory {

    public UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getFacultyEmail(), user.getIndeks(),
                user.getThreeYearStudies(), user.getYearOfEnrollment(), user.getResume(), user.getAverageGrade(),
                user.getTotalCredits(), user.getRoles());
    }

    public EnrolledSemesterDto toEnrolledSemesterDto(EnrolledSemestersView view) {
        return new EnrolledSemesterDto(view.getId().getSemesterId(), view.getId().getStudentId(), view.getType(),
                view.getStartDate(), view.getEndDate(), view.getStatus());
    }

    public EnrolledSubjectDto toEnrolledSubjectDto(EnrolledSubjectsView view) {
        return new EnrolledSubjectDto(view.getId().getStudentId(), view.getId().getSubjectId(),
                view.getId().getSemesterId(), view.getProfessorId(), view.getCode(), view.getCredits(), view.getSubject(),
                view.getSemesterNumber(), view.getProgram(), view.getStudyType(), view.getProfFirstName(), view.getProfLastName(),
                view.getSemesterType(), view.getSemesterStartDate(), view.getSemesterEndDate());
    }

}
