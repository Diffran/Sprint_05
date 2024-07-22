package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dao.request.SignRequest;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dao.response.JwtAuthenticationResponse;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.User;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.UserIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.impl.AuthenticationServiceImpl;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthenticationServiceImplTest {
        @Mock
        private UserIRepository userIRepository;

        @Mock
        private JwtService jwtService;

        @Mock
        private PasswordEncoder passwordEncoder;

        @Mock
        private AuthenticationManager authenticationManager;

        @Mock
        private PlayerService playerService;

        @InjectMocks
        private AuthenticationServiceImpl authenticationService;

        private SignRequest signUpRequest;
        private SignRequest signInRequest;
        private User user;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);

            signUpRequest = new SignRequest();
            signUpRequest.setEmail("test@example.com");
            signUpRequest.setPassword("password");

            signInRequest = new SignRequest();
            signInRequest.setEmail("test@example.com");
            signInRequest.setPassword("password");

            user = new User();
            user.setId("1");
            user.setEmail("test@example.com");
            user.setPassword("encodedPassword");
        }

        @Nested
        @DisplayName("Sign Up Tests")
        class SignUpTests {

            @Test
            @DisplayName("Sign Up success")
            void testSignUp_Success() {
                when(userIRepository.findByEmail(signUpRequest.getEmail())).thenReturn(Optional.empty());
                when(passwordEncoder.encode(signUpRequest.getPassword())).thenReturn("encodedPassword");
                when(jwtService.getToken(any(User.class))).thenReturn("jwtToken");
                when(userIRepository.save(any(User.class))).thenReturn(user);

                JwtAuthenticationResponse response = authenticationService.signUp(signUpRequest);

                assertNotNull(response);
                assertEquals("jwtToken", response.getToken());
                verify(userIRepository, times(1)).save(any(User.class));
                verify(playerService, times(1)).create(any(PlayerDTO.class));
            }

            @Test
            @DisplayName("Sign Up user already exists")
            void testSignUp_UserAlreadyExists() {
                when(userIRepository.findByEmail(signUpRequest.getEmail())).thenReturn(Optional.of(user));

                EntityExistsException exception = assertThrows(EntityExistsException.class, () -> {
                    authenticationService.signUp(signUpRequest);
                });

                assertEquals("Auth User failed: 'test@example.com' -> ALREADY EXIST in DataBase", exception.getMessage());
                verify(userIRepository, times(0)).save(any(User.class));
                verify(playerService, times(0)).create(any(PlayerDTO.class));
            }

            @Test
            @DisplayName("Sign Up empty email or password")
            void testSignUp_EmptyEmailOrPassword() {
                signUpRequest.setEmail("");
                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                    authenticationService.signUp(signUpRequest);
                });
                assertEquals("Email and password: MUST NOT BE EMPTY", exception.getMessage());

                signUpRequest.setEmail("test@example.com");
                signUpRequest.setPassword("");
                exception = assertThrows(IllegalArgumentException.class, () -> {
                    authenticationService.signUp(signUpRequest);
                });
                assertEquals("Email and password: MUST NOT BE EMPTY", exception.getMessage());

                verify(userIRepository, times(0)).save(any(User.class));
                verify(playerService, times(0)).create(any(PlayerDTO.class));
            }
        }

        @Nested
        @DisplayName("Sign In Tests")
        class SignInTests {

            @Test
            @DisplayName("Sign In success")
            void testSignIn_Success() {
                when(userIRepository.findByEmail(signInRequest.getEmail())).thenReturn(Optional.of(user));
                when(jwtService.getToken(any(User.class))).thenReturn("jwtToken");

                JwtAuthenticationResponse response = authenticationService.signIn(signInRequest);

                assertNotNull(response);
                assertEquals("jwtToken", response.getToken());
                assertEquals("1", response.getUserId());
                verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
            }

            @Test
            @DisplayName("Sign In user not found")
            void testSignIn_UserNotFound() {
                when(userIRepository.findByEmail(signInRequest.getEmail())).thenReturn(Optional.empty());

                EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
                    authenticationService.signIn(signInRequest);
                });

                assertEquals("USER EMAIL IS NOT IN THE DATABASE", exception.getMessage());
                verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
            }
        }


}
