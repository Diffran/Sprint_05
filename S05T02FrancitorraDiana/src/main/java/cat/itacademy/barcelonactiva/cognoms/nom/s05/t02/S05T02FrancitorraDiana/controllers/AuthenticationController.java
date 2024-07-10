package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.dao.request.SignRequest;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.dao.response.JwtAuthenticationResponse;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody SignRequest request){//TODO: canviar el String per JwtAuthenticationResponse
        //TODO: return ResponseEntity.ok(authenticationService.signup(request));
        return ResponseEntity.ok().body("hola?");
    }
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignRequest request){//TODO: canviar el String per JwtAuthenticationResponse
        //TODO: return ResponseEntity.ok(authenticationService.signin(request));
        return ResponseEntity.ok().body("adeu?");
    }
}
