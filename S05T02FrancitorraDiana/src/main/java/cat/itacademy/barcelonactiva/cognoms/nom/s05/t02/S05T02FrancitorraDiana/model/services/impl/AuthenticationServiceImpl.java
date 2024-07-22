package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.impl;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dao.request.SignRequest;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dao.response.JwtAuthenticationResponse;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.mappers.UserMapper;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.User;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.UserDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.UserIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.AuthenticationService;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.JwtService;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.PlayerService;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.utils.Role;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserIRepository userIRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final PlayerService playerService;

    @Override
    public JwtAuthenticationResponse signUp(SignRequest request) {
        if (request.getEmail().isEmpty() || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Email and password: MUST NOT BE EMPTY");
        }
        userIRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new EntityExistsException("Auth User failed: '"+user.getEmail()+"' -> ALREADY EXIST in DataBase" );
                });

        UserDTO userDTO = UserDTO.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        User user = UserMapper.toEntity(userDTO);
        if(userIRepository.findAll().isEmpty()){
            user.setRole(Role.ADMIN);
        }

        userIRepository.save(user);
        PlayerDTO defaultPlayer = new PlayerDTO(user.getId(),null,0);
        playerService.create(defaultPlayer);

        return JwtAuthenticationResponse.builder()
                .token(jwtService.getToken(user))
                .userId(user.getId())
                .build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userIRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("USER EMAIL IS NOT IN THE DATABASE"));

        return JwtAuthenticationResponse.builder()
                .token(jwtService.getToken(user))
                .userId(user.getId())
                .build();
    }
}
