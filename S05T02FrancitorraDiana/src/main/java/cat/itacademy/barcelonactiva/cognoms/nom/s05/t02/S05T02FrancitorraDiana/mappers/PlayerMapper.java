package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.mappers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.Player;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.PlayerDTO;

public class PlayerMapper {
    public static Player toEntity(PlayerDTO playerDTO){
        if(playerDTO.getNickname()==null||playerDTO.getNickname().isEmpty()){
            return new Player(playerDTO.getPlayer_id(), "ANÒNIM");
        }
        return new Player(playerDTO.getPlayer_id(),playerDTO.getNickname());
    }

    public static PlayerDTO toDTO(Player player){
        return new PlayerDTO(player.getPlayerId(),player.getNickname(), player.getWinrate());
    }
}
