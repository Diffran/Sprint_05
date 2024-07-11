package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services;


import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String getToken(UserDetails userDetails);
    //String getEmail(String token);
    //boolean isTokenValid(String token, UserDetails userDetails);
}
