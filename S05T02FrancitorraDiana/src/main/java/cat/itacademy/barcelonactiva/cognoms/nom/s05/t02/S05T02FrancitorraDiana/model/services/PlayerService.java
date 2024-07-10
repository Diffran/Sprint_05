package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {
    void create(PlayerDTO playerDTO);
    List<PlayerDTO> getAll();
    void update(PlayerDTO playerDTO);
    void delete(String playerID);
    PlayerDTO getOne(String playerID);
    double getAverageWinrate();
    PlayerDTO getLoser();
    PlayerDTO getWinner();


}
