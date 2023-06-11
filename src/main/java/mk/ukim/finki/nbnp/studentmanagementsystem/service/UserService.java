package mk.ukim.finki.nbnp.studentmanagementsystem.service;

import mk.ukim.finki.nbnp.studentmanagementsystem.exception.UserDoesNotExistException;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.User;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.UserPersonalInfoView;
import mk.ukim.finki.nbnp.studentmanagementsystem.repository.entity.UserRepository;
import mk.ukim.finki.nbnp.studentmanagementsystem.repository.view.UserPersonalInfoViewRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserPersonalInfoViewRepository userPersonalInfoViewRepository;

    public UserService(UserRepository userRepository, UserPersonalInfoViewRepository userPersonalInfoViewRepository) {
        this.userRepository = userRepository;
        this.userPersonalInfoViewRepository = userPersonalInfoViewRepository;
    }

    public User findUserByFacultyEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByFacultyEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByFacultyEmail(username);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserDoesNotExistException(id));
    }

    public UserPersonalInfoView getPersonalInfoForUser(Long id) {
        Optional<UserPersonalInfoView> personalInfo = userPersonalInfoViewRepository.getPersonalInfoById(id);
        return personalInfo.orElseThrow(() -> new UserDoesNotExistException(id));
    }
}
