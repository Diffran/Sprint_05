package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.GameService;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.impl.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/{id}/games")
    public ResponseEntity<GameDTO> rollDice(@PathVariable("id") String playerID){
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.roll(playerID));
    }
    @DeleteMapping("/{id}/games")
    public ResponseEntity<String> deleteAllGames(@PathVariable("id") String playerID){
        gameService.deleteAll(playerID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/games")
    public ResponseEntity<List<GameDTO>> getAll(@PathVariable("id") String playerID){
        gameService.getAllPlayer(playerID);
        return ResponseEntity.ok().body(gameService.getAllPlayer(playerID));
    }
}
