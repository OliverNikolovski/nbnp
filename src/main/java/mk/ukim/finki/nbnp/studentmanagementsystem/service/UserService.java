package mk.ukim.finki.nbnp.studentmanagementsystem.service;

import mk.ukim.finki.nbnp.studentmanagementsystem.exception.UserDoesNotExistException;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.dto.UserDto;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.User;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.EnrolledSemestersView;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.EnrolledSubjectsView;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.ExamsView;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.UserPersonalInfoView;
import mk.ukim.finki.nbnp.studentmanagementsystem.repository.entity.UserRepository;
import mk.ukim.finki.nbnp.studentmanagementsystem.repository.jdbc.UserJdbcRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserJdbcRepository userJdbcRepository;

    public UserService(UserRepository userRepository, UserJdbcRepository userJdbcRepository) {
        this.userRepository = userRepository;
        this.userJdbcRepository = userJdbcRepository;
    }

    public UserDto findUserByFacultyEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByFacultyEmail(email)
                .map(this::toUserDto)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByFacultyEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(this::toUserDto)
                .orElseThrow(() -> new UserDoesNotExistException(id));
    }

    public UserPersonalInfoView getPersonalInfoForUser(Long id) {
        Optional<UserPersonalInfoView> personalInfo = userJdbcRepository.getPersonalInfoForUser(id);
        return personalInfo.orElseThrow(() -> new UserDoesNotExistException(id));
    }

    public List<EnrolledSemestersView> getSemestersForStudent(Long id) {
        return userJdbcRepository.getSemestersForStudent(id);
    }

    public List<EnrolledSubjectsView> getSubjectsInSemesterForStudent(Long studentId, Long semesterId) {
        return userJdbcRepository.getSubjectsInSemesterForStudent(studentId, semesterId);
    }

    public List<ExamsView> getAllPassedExamsForStudent(Long id) {
        return userJdbcRepository.getAllPassedExamsForStudent(id);
    }

    private UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getFacultyEmail(), user.getIndeks(),
                user.getThreeYearStudies(), user.getYearOfEnrollment(), user.getResume(), user.getAverageGrade(),
                user.getTotalCredits(), user.getRoles());
    }
}
