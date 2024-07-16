package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.controllers;


import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.dao.request.SignRequest;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.dao.response.JwtAuthenticationResponse;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)


public class AuthenticationControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @Mock
        private AuthenticationService authenticationService;

        @Autowired
        private ObjectMapper objectMapper;

        private SignRequest signRequest;
        private JwtAuthenticationResponse jwtResponse;

        @BeforeEach
        void setUp() {
            signRequest = new SignRequest();
            signRequest.setEmail("testuser");
            signRequest.setPassword("testpass");

            jwtResponse = new JwtAuthenticationResponse();
            jwtResponse.setToken("jwt-token");
        }

        @Test
        void testLogin() throws Exception {
            when(authenticationService.signIn(any(SignRequest.class))).thenReturn(jwtResponse);

            mockMvc.perform(MockMvcRequestBuilders.post("/auth/signin")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(signRequest)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(objectMapper.writeValueAsString(jwtResponse)));
        }

        @Test
        void testRegister() throws Exception {
            when(authenticationService.signUp(any(SignRequest.class))).thenReturn(jwtResponse);

            mockMvc.perform(MockMvcRequestBuilders.post("/auth/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(signRequest)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(objectMapper.writeValueAsString(jwtResponse)));
        }
}
