package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.User;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.UserDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.UserService;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.utils.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.security.Principal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
        @Mock
        private UserService userService;

        @Mock
        private Authentication authentication;
        @InjectMocks
        private UserController userController;

    @BeforeEach
    void setUp() {
        User principal = new User("username", "TEST@TEST.COM","password", Role.USER, new Date());
        authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(principal.getUsername());

        List<GrantedAuthority> authorities = Arrays.asList(authority);
        when(authentication.getAuthorities()).thenReturn(authorities);
    }

        @Test
        @DisplayName("Add User - Success")
        void testAddUser_Success() {
            Principal mockPrincipal = mock(Principal.class, withSettings().defaultAnswer(invocation -> null));

            UserDTO userDTO = new UserDTO();
            userDTO.setId("1");
            userDTO.setEmail("test@example.com");

            ResponseEntity<?> response = userController.addUser(userDTO, mock(Principal.class));

            verify(userService, times(1)).create(userDTO);

            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertEquals(userDTO, response.getBody());
        }

        @Test
        @DisplayName("Get All Users - Success")
        void testGetAllUsers_Success() {
            List<UserDTO> userDTOList = new ArrayList<>();
            userDTOList.add(new UserDTO("1", "test1@example.com","password",Role.USER,new Date()));
            userDTOList.add(new UserDTO("2", "test2@example.com","password",Role.USER,new Date()));

            when(userService.getAll()).thenReturn(userDTOList);

            ResponseEntity<List<UserDTO>> response = userController.getAllUsers(mock(Principal.class));

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(userDTOList, response.getBody());
            verify(userService, times(1)).getAll();
        }

        @Test
        @DisplayName("Delete User - Success")
        void testDeleteUser_Success() {
            ResponseEntity<?> response = userController.delete("1", mock(Principal.class));

            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            verify(userService, times(1)).delete("1");
        }

        @Test
        @DisplayName("Update User - Success")
        void testUpdateUser_Success() {
            UserDTO userDTO = new UserDTO();
            userDTO.setId("1");
            userDTO.setEmail("updated@example.com");

            ResponseEntity<?> response = userController.update(userDTO, mock(Principal.class));

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(userDTO, response.getBody());
            verify(userService, times(1)).update(userDTO);
        }

        @Test
        @DisplayName("Get One User - Success")
        void testGetOneUser_Success() {
            UserDTO userDTO = new UserDTO("1", "test@example.com","password", Role.USER, new Date());

            when(userService.getOne("1")).thenReturn(userDTO);

            ResponseEntity<?> response = userController.getOne("1", mock(Principal.class));

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(userDTO, response.getBody());
            verify(userService, times(1)).getOne("1");
        }


}
