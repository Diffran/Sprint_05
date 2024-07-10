package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.utils.validations;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.PlayerIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.UserIRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.NoResultException;

public class PlayerValidation {
    public static void userExist(PlayerIRepository playerIRepository,UserIRepository userIRepository, String userID){
        if (playerIRepository.existsById(userID)) {
            throw new EntityExistsException("Player with ID '" + userID + "' -> ALREADY" +
                    " EXISTS in DataBase");
        }
        if (!userIRepository.existsById(userID)) {
            throw new NoResultException("User with ID '" + userID + "' does not exist.");
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
