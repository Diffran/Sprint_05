package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    void create(UserDTO userDTO);
    List<UserDTO> getAll();
    void update(UserDTO userDTO);
    void delete(String userID);
    UserDTO getOne(String userID);

}
