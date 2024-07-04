package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.impl;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.User;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.UserDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.UserIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserIRepository userIRepository;
    private User toEntity(UserDTO userDTO){
        if(userDTO.getId()==null){
            return new User(userDTO.getEmail(),userDTO.getPassword());
        }
        return new User(userDTO.getId(),userDTO.getEmail(), userDTO.getPassword(), userDTO.getDate());
    }

    private UserDTO toDTO(User user){
        return new UserDTO(user.getId(), user.getEmail(), user.getPassword(),user.getDate());
    }
    public void create(UserDTO userDTO){
        userIRepository.save(toEntity(userDTO));
    }

    public List<UserDTO> getAll(){
        List<User> userList = userIRepository.findAll();
        return userList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
