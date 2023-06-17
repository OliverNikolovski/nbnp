package mk.ukim.finki.nbnp.studentmanagementsystem.service;

import mk.ukim.finki.nbnp.studentmanagementsystem.exception.UserDoesNotExistException;
import mk.ukim.finki.nbnp.studentmanagementsystem.factory.DtoFactory;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.dto.UserDto;
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
    private final DtoFactory dtoFactory;

    public UserService(UserRepository userRepository, UserJdbcRepository userJdbcRepository, DtoFactory dtoFactory) {
        this.userRepository = userRepository;
        this.userJdbcRepository = userJdbcRepository;
        this.dtoFactory = dtoFactory;
    }

    public UserDto findUserByFacultyEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByFacultyEmail(email)
                .map(dtoFactory::toUserDto)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByFacultyEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(dtoFactory::toUserDto)
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
}
