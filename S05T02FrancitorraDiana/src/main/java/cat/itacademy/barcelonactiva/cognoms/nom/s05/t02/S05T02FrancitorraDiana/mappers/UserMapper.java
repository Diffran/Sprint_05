package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.mappers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.User;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.UserDTO;

public class UserMapper {
    public static User toEntity(UserDTO userDTO){
        if(userDTO.getId()==null){
            return new User(userDTO.getEmail(),userDTO.getPassword());
        }
        return new User(userDTO.getId(),userDTO.getEmail(), userDTO.getPassword(),userDTO.getRole(), userDTO.getDate());
    }

    public static UserDTO toDTO(User user){
        return new UserDTO(user.getId(), user.getEmail(), user.getPassword(),user.getRole(),user.getDate());
    }
}
