package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDTO create(UserDTO userDTO);
    List<UserDTO> getAll();
    UserDTO update(UserDTO userDTO);
    String delete(String userID);
    UserDTO getOne(String userID);
    UserDetailsService userDetailsService();

}
