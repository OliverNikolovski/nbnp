package mk.ukim.finki.nbnp.studentmanagementsystem.api;

import mk.ukim.finki.nbnp.studentmanagementsystem.mapper.UserMapper;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.dto.UserDto;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.UserPersonalInfoView;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // this is just for testing if login works, should be removed later
    @GetMapping
    public String greeting() {
        return "Successfully Logged in!";
    }

    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable Long id) {
        return userMapper.findById(id);
    }

    @GetMapping("/info/{id}")
    public UserPersonalInfoView getPersonalInfoForUser(@PathVariable Long id) {
        return userMapper.getPersonalInfoForUser(id);
    }
}
