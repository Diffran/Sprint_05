package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.impl;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.mappers.GameMapper;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.Game;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.Player;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.GameIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.PlayerIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.GameService;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.utils.Result;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameIRepository gameIRepository;
    @Autowired
    private PlayerIRepository playerIRepository;
    private Random roll = new Random();

    public GameDTO roll(String playerID){
        Player player = playerIRepository.findById(playerID)
                .orElseThrow(() -> new EntityNotFoundException("ROLL failed: user '" + playerID + "' DOESN'T EXIST in DataBase"));

        GameDTO gameDTO=new GameDTO();
        gameDTO.setPk_GameID(null);
        gameDTO.setDice1((roll.nextInt(6) + 1));
        gameDTO.setDice2((roll.nextInt(6) + 1));
        gameDTO.setResult(gameResult(gameDTO.getDice1(), gameDTO.getDice2()));
        gameDTO.setPlayer(player);

        gameIRepository.save(GameMapper.toEntity(gameDTO, player));
        updateWinrate(player);
        return  gameDTO;
    }

    private Result gameResult(int dice1, int dice2){
        return (dice1 + dice2 == 7) ? Result.WIN : Result.LOSE;
    }
    private void updateWinrate(Player player){
        if (player.getGames().size() > 0) {
            long wins = player.getGames().stream()
                    .filter(game -> game.getResult() == Result.WIN)
                    .count();
            player.setWinrate(((double)wins/player.getGames().size())*100);
            playerIRepository.save(player);
        } else {
            player.setWinrate(0);
        }
    }

    @Transactional
    public String deleteAll(String playerID){
        Player player = playerIRepository.findById(playerID)
                .orElseThrow(() -> new EntityNotFoundException("Delete all failed -> Player with ID '" + playerID +
                        "' DOESN'T EXIST in DataBase"));

        gameIRepository.deleteByPlayer_PlayerId(playerID);
        player.setWinrate(0);

        return "all games from: "+playerID+" have been successfully DELETED";
    }


    public List<GameDTO> getAll(){
        List<Game> gameList =  gameIRepository.findAll();

        return gameList.stream()
                .map(GameMapper::toDTO)
                .collect(Collectors.toList());
    }
    public List<GameDTO> getAllPlayer(String playerID){
        Player player = playerIRepository.findById(playerID)
                .orElseThrow(() -> new EntityNotFoundException("Get all Games failed -> Player with ID '" + playerID +
                        "' DOESN'T EXIST in DataBase"));

        return player.getGames().stream()
                .map(GameMapper::toDTO)
                .collect(Collectors.toList());
    }
}
