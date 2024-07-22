package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {
    PlayerDTO create(PlayerDTO playerDTO);
    List<PlayerDTO> getAll();
    PlayerDTO update(PlayerDTO playerDTO);
    String delete(String playerID);
    PlayerDTO getOne(String playerID);
    double getAverageWinrate();
    PlayerDTO getLoser();
    PlayerDTO getWinner();


}
