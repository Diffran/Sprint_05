package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.impl;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.mappers.PlayerMapper;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.Player;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.PlayerIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.UserIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.PlayerService;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.utils.validations.AppValidations;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerIRepository playerIRepository;
    @Autowired
    private UserIRepository userIRepository;

    public PlayerDTO create(PlayerDTO playerDTO){
        AppValidations.nickNameExist(playerIRepository,playerDTO.getNickname());
        AppValidations.userExist(playerIRepository, userIRepository, playerDTO.getPlayer_id());

        return PlayerMapper.toDTO(playerIRepository.save(PlayerMapper.toEntity(playerDTO)));
    }

    public PlayerDTO update(PlayerDTO playerDTO){
        PlayerDTO playerToUpdate = getOne(playerDTO.getPlayer_id());
        AppValidations.nickNameExist(playerIRepository,playerDTO.getNickname());
        playerToUpdate.setNickname(playerDTO.getNickname());

        return PlayerMapper.toDTO(playerIRepository.save(PlayerMapper.toEntity(playerToUpdate)));
    }
    public String delete(String playerID){
        if(!playerIRepository.findById(playerID).isPresent()){
            throw new EntityNotFoundException("DELETE PLAYER failed: '"+playerID+"' -> DOESN'T EXIST in DataBase");
        }

        playerIRepository.deleteById(playerID);
        return "Player with ID: "+playerID+" has been successfully DELETED.";
    }
    public List<PlayerDTO> getAll(){
        List<Player> playerList =  playerIRepository.findAll();

        return playerList.stream()
                .map(PlayerMapper::toDTO)
                .collect(Collectors.toList());
    }
    public PlayerDTO getOne(String playerID){
          Player player = playerIRepository.findById(playerID)
                  .orElseThrow(() -> new NoResultException("Get One Player Failed: Invalid ID: "+ playerID
                          +" -> DOESN'T EXIST in DataBase"));
          return PlayerMapper.toDTO(player);
    }

    public double getAverageWinrate(){
        List<Player> playerList = playerIRepository.findAll();
        return playerList.stream()
                .mapToDouble(Player::getWinrate)
                .average()
                .orElse(0.0);
    }

    public PlayerDTO getLoser(){
        List<Player> playerList = playerIRepository.findAll();
        return playerList.stream()
                .min(Comparator.comparingDouble(Player::getWinrate))
                .map(PlayerMapper::toDTO)
                .orElseThrow(() -> new NoResultException("Get loser failed, there are no players in the database"));

    }

    public PlayerDTO getWinner(){
        List<Player> playerList = playerIRepository.findAll();
        return playerList.stream()
                .max(Comparator.comparingDouble(Player::getWinrate))
                .map(PlayerMapper::toDTO)
                .orElseThrow(() -> new NoResultException("Get loser failed, there are no players in the database"));

    }

}
