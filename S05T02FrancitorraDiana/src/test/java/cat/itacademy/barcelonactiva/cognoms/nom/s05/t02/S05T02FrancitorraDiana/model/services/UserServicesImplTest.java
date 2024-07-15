package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.User;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.UserDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.PlayerIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.UserIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.impl.UserServiceImpl;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.utils.Role;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("User Service Unit Tests")
@ExtendWith(MockitoExtension.class)
public class UserServicesImplTest {
        @Mock
        private UserIRepository userRepository;
        @Mock
        private PlayerIRepository playerRepository;
        @InjectMocks
        private UserServiceImpl userService;

        @Nested
        @DisplayName("Create User Tests")
        class CreateUserTests {

            @Test
            @DisplayName("Create User Success")
            void testCreateUser_Success() {
                UserDTO userDTO = new UserDTO();
                userDTO.setId("1");
                userDTO.setEmail("test@example.com");

                userService.create(userDTO);

                verify(userRepository, times(1)).save(any(User.class));
            }
        }

        @Nested
        @DisplayName("Update User Tests")
        class UpdateUserTests {

            @Test
            @DisplayName("Update User Success")
            void testUpdateUser_Success() {
                UserDTO userDTO = new UserDTO();
                userDTO.setId("1");
                userDTO.setEmail("test@example.com");

                when(userRepository.findById("1")).thenReturn(Optional.of(new User()));

                userService.update(userDTO);

                verify(userRepository, times(1)).save(any(User.class));
            }

            @Test
            @DisplayName("Update User Not Found")
            void testUpdateUser_NotFound() {
                UserDTO userDTO = new UserDTO();
                userDTO.setId("1");
                userDTO.setEmail("test@example.com");

                when(userRepository.findById("1")).thenReturn(Optional.empty());

                EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
                    userService.update(userDTO);
                });

                assertEquals("Update User Failed: Invalid ID: 1 -> DOESN'T EXIST in DataBase", exception.getMessage());
            }
        }

        @Nested
        @DisplayName("Delete User Tests")
        class DeleteUserTests {

            @Test
            @DisplayName("Delete User Success")
            void testDeleteUser_Success() {
                String userId = "1";

                when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));

                userService.delete(userId);

                verify(userRepository, times(1)).deleteById(userId);
            }

            @Test
            @DisplayName("Delete User Not Found")
            void testDeleteUser_NotFound() {
                String userId = "1";

                when(userRepository.findById(userId)).thenReturn(Optional.empty());

                EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
                    userService.delete(userId);
                });

                assertEquals("Delete User Failed: Invalid ID: 1 -> DOESN'T EXIST in DataBase", exception.getMessage());
            }
        }

        @Nested
        @DisplayName("Get One User Tests")
        class GetOneUserTests {

            @Test
            @DisplayName("Get One User Success")
            void testGetOneUser_Success() {
                String userId = "1";
                User user = new User();
                user.setId(userId);

                when(userRepository.findById(userId)).thenReturn(Optional.of(user));

                UserDTO result = userService.getOne(userId);

                assertEquals(userId, result.getId());
            }

            @Test
            @DisplayName("Get One User Not Found")
            void testGetOneUser_NotFound() {
                String userId = "1";

                when(userRepository.findById(userId)).thenReturn(Optional.empty());

                NoResultException exception = Assertions.assertThrows(NoResultException.class, () -> {
                    userService.getOne(userId);
                });

                assertEquals("Get One User Failed: Invalid ID: 1 -> DOESN'T EXIST in DataBase", exception.getMessage());
            }
        }

        @Nested
        @DisplayName("Get All Users Tests")
        class GetAllUsersTests {
            @Test
            @DisplayName("Get All Users Success")
            void testGetAllUsers_Success() {
                User user1 = new User();
                user1.setId("1");
                User user2 = new User();
                user2.setId("2");

                when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

                List<UserDTO> result = userService.getAll();

                assertEquals(2, result.size());
            }
        }

        @Nested
        @DisplayName("User Details Service Tests")
        class UserDetailsServiceTests {
            @Test
            @DisplayName("Load User By Username Success")
            void testLoadUserByUsername_Success() {
                String username = "test@example.com";
                User user = new User();
                user.setEmail(username);

                when(userRepository.findByEmail(username)).thenReturn(Optional.of(user));

                UserDetails userDetails = userService.userDetailsService().loadUserByUsername(username);

                assertEquals(username, userDetails.getUsername());
            }

            @Test
            @DisplayName("Load User By Username Not Found")
            void testLoadUserByUsername_NotFound() {
                String username = "nonexistent@example.com";

                when(userRepository.findByEmail(username)).thenReturn(Optional.empty());

                UsernameNotFoundException exception = Assertions.assertThrows(UsernameNotFoundException.class, () -> {
                    userService.userDetailsService().loadUserByUsername(username);
                });

                assertEquals("User not found with email: " + username, exception.getMessage());
            }
        }


}
