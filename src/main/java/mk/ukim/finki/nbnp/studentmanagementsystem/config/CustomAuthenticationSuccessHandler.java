package mk.ukim.finki.nbnp.studentmanagementsystem.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import mk.ukim.finki.nbnp.studentmanagementsystem.factory.DtoFactory;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.dto.UserDto;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final DtoFactory dtoFactory;
    private final ObjectMapper objectMapper;

    public CustomAuthenticationSuccessHandler(DtoFactory dtoFactory, ObjectMapper objectMapper) {
        this.dtoFactory = dtoFactory;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        UserDto userDto = dtoFactory.toUserDto(user);
        String userDtoJson = objectMapper.writeValueAsString(userDto);
        response.getWriter().write(userDtoJson);
        response.setStatus(HttpServletResponse.SC_OK);
    }


}
