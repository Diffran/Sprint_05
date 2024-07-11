package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.impl;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.dao.request.SignRequest;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.dao.response.JwtAuthenticationResponse;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.User;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.UserDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.UserIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.AuthenticationService;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.UserService;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserIRepository userIRepository;
    private UserService userService;

    @Override
    public JwtAuthenticationResponse signUp(SignRequest request) {
        if (request.getEmail().isEmpty() || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Email and password: MUST NOT BE EMPTY");
        }
        userIRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new EntityExistsException("Auth User failed: '"+user.getEmail()+"' -> ALREADY EXIST in DataBase" );
                });
        UserDTO user = UserDTO.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userService.create(user);//aixi em fa la date i el rol automatic, per aixo service i o repository
    }

    @Override
    public JwtAuthenticationResponse signIn(SignRequest request) {
        return null;
    }
}
