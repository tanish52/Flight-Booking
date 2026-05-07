package com.example.flightbook;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.flightbook.Configuration.JwtBuilder;
import com.example.flightbook.DTO.UserRegisterDto;
import com.example.flightbook.Model.UserModel;
import com.example.flightbook.Repository.Userrepo;
import com.example.flightbook.Service.userserv;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private userserv userService;

    @Mock
    private Userrepo userRepo;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private JwtBuilder jwtbuilder;

    @Mock
    private AuthenticationManager authManager;

    @Test
    void testRegisterUser() {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setEmail("test@abc.com");
        dto.setPassword("123");

        when(encoder.encode(any())).thenReturn("encoded");

        userService.registerUser(dto);

        verify(userRepo).save(any(UserModel.class));
    }
}
