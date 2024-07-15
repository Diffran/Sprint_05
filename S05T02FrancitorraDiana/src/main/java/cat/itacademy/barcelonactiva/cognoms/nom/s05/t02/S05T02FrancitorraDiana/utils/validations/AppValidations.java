package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.utils.validations;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.User;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.PlayerIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.UserIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.utils.Role;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.NoResultException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;

import java.security.Principal;

public class AppValidations {

    public static void verifyUserAccess(Principal principal, String id){
        User authenticatedUser = (User) ((Authentication) principal).getPrincipal();

        if(authenticatedUser.getRole() == Role.ADMIN){
            return;
        }else if(id==null){
            throw new AccessDeniedException("Only ADMIN have Access");
        }else if(!authenticatedUser.getId().equals(id)){
            throw new AccessDeniedException("YOU DON'T HAVE ACCESS to this id... ONLY use YOUR user ID");
        }
    }

    public static void userExist(PlayerIRepository playerIRepository,UserIRepository userIRepository, String userID){
        if (playerIRepository.existsById(userID)) {
            throw new EntityExistsException("Player with ID '" + userID + "' -> ALREADY" +
                    " EXISTS in DataBase");
        }
        if (!userIRepository.existsById(userID)) {
            throw new NoResultException("User with ID '" + userID + "' DOESN'T EXIST in DataBase.");
        }
    }

    public static void nickNameExist(PlayerIRepository playerIRepository,String nickname){
        if(nickname!=null) {
            if (playerIRepository.existsByNickname(nickname) && !nickname.equals("ANÒNiM")) {
                throw new EntityExistsException("Player nickname: '" + nickname
                        + "' ALREADY EXIST in the DataBase, Player nickname must be unique");
            }
        }
    }
}
