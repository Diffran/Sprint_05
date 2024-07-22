package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.impl;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.mappers.UserMapper;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.User;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.UserDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.PlayerIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.UserIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.UserService;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.utils.Role;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserIRepository userIRepository;
    private final PlayerIRepository playerIRepository;

    public UserDTO create(UserDTO userDTO){
        userDTO.setId(null);
        User  user = UserMapper.toEntity(userDTO);
        userIRepository.save(user);

        //el primer user sempre es admin
        if(userIRepository.findAll().size()==1){
            user.setRole(Role.ADMIN);
            userIRepository.save(user);
        }

        return UserMapper.toDTO(user);
    }

    public UserDTO update(UserDTO userDTO){
        userIRepository.findById(userDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Update User Failed: Invalid ID: "+ userDTO.getId()
                        +" -> DOESN'T EXIST in DataBase"));

        userIRepository.save(UserMapper.toEntity(userDTO));
        return userDTO;
    }

    public String delete(String userID){
        if(!userIRepository.findById(userID).isPresent()){
            throw new EntityNotFoundException("Delete User Failed: Invalid ID: "+ userID+
                    " -> DOESN'T EXIST in DataBase");
        }

        userIRepository.deleteById(userID);
        playerIRepository.deleteById(userID);

        return "User with ID: "+userID+" has been successfully DELETED.";
    }

    public UserDTO getOne(String userID){
        User user = userIRepository.findById(userID)
                .orElseThrow(() -> new NoResultException("Get One User Failed: Invalid ID: "+ userID
                        +" -> DOESN'T EXIST in DataBase"));

        return UserMapper.toDTO(user);
    }

    public List<UserDTO> getAll(){
        List<User> userList = userIRepository.findAll();
        return userList.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userIRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
            }
        };
    }
}
