package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.User;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.GameService;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.impl.GameServiceImpl;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.utils.validations.AppValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/players")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/{id}/games")
    public ResponseEntity<GameDTO> rollDice(@PathVariable("id") String playerID, Principal principal){
        AppValidations.verifyUserAccess(principal, playerID);

        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.roll(playerID));
    }
    @DeleteMapping("/{id}/games")
    public ResponseEntity<String> deleteAllGames(@PathVariable("id") String playerID, Principal principal){
        AppValidations.verifyUserAccess(principal, playerID);

        gameService.deleteAll(playerID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/games")
    public ResponseEntity<List<GameDTO>> getAll(@PathVariable("id") String playerID){
        return ResponseEntity.ok().body(gameService.getAllPlayer(playerID));
    }
}
