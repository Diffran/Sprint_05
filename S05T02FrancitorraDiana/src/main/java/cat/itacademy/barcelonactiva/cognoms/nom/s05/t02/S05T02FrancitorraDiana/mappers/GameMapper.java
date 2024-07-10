package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.mappers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.Game;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.Player;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.User;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.UserDTO;

public class GameMapper {
    public static Game toEntity(GameDTO gameDTO, Player player){
        return new Game(gameDTO.getPk_GameID(), gameDTO.getDice1(), gameDTO.getDice2(), gameDTO.getResult(), player);
    }

    public static GameDTO toDTO(Game game){
        return new GameDTO(game.getPk_GameID(),game.getDice1(), game.getDice2(), game.getResult());
    }
}
