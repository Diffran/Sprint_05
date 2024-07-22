package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.User;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.PlayerService;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.impl.PlayerServiceImpl;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.utils.Role;
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
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @PostMapping
    public ResponseEntity<?> addPlayer(@RequestBody PlayerDTO playerDTO, Principal principal){
        AppValidations.verifyUserAccess(principal, null);

        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.create(playerDTO));
    }

    @PutMapping
    public ResponseEntity<?> updatePlayer(@RequestBody PlayerDTO playerDTO, Principal principal){
        AppValidations.verifyUserAccess(principal, playerDTO.getPlayer_id());

        return ResponseEntity.ok().body(playerService.update(playerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable("id") String playerID, Principal principal){
        AppValidations.verifyUserAccess(principal, null);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(playerService.delete(playerID));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> getOnePlayer(@PathVariable("id") String playerID){
        return ResponseEntity.ok().body(playerService.getOne(playerID));
    }

    @GetMapping("/")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers(){
        return ResponseEntity.ok().body(playerService.getAll());
    }

    @GetMapping("/ranking")
    public ResponseEntity<Double> getAverageWinrate(){
        return ResponseEntity.ok().body(playerService.getAverageWinrate());
    }

    @GetMapping("/ranking/loser")
    public ResponseEntity<?> getLoser(){
        return ResponseEntity.ok().body(playerService.getLoser());
    }

    @GetMapping("/ranking/winner")
    public ResponseEntity<?> getWinner(){
        return ResponseEntity.ok().body(playerService.getWinner());
    }

}
