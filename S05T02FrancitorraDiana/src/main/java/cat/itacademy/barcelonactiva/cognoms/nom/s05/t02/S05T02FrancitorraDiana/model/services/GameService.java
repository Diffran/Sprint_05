package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.GameDTO;

import java.util.List;

public interface GameService {
    GameDTO roll(String playerID);
    void deleteAll(String playerID);

    List<GameDTO> getAll();
    List<GameDTO> getAllPlayer(String playerID);



}
