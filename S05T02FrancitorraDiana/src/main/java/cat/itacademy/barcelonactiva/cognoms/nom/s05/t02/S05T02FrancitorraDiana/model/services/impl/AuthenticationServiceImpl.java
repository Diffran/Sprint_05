package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.impl;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.dao.request.SignRequest;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.dao.response.JwtAuthenticationResponse;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.User;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.UserIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.AuthenticationService;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.JwtService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserIRepository userIRepository;
    @Autowired
    private JwtService jwtService;

    @Override
    public JwtAuthenticationResponse signUp(SignRequest request) {
        if (request.getEmail().isEmpty() || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Email and password: MUST NOT BE EMPTY");
        }
        userIRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new EntityExistsException("Auth User failed: '"+user.getEmail()+"' -> ALREADY EXIST in DataBase" );
                });
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userIRepository.save(user);

        return JwtAuthenticationResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignRequest request) {
        return null;
    }
}
