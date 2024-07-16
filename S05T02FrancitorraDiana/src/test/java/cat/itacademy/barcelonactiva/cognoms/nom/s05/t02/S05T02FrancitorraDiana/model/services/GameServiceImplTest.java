package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.Game;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.Player;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.GameIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.PlayerIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.impl.GameServiceImpl;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.utils.Result;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class GameServiceImplTest {

        @Mock
        private GameIRepository gameIRepository;

        @Mock
        private PlayerIRepository playerIRepository;

        @InjectMocks
        private GameServiceImpl gameService;

        private Player player;
        private Game game;
        private GameDTO gameDTO;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            player = new Player();
            player.setPlayerId("1");
            player.setNickname("player1");
            player.setGames(new ArrayList<>());

            game = new Game();
            game.setPk_GameID(1);
            game.setDice1(3);
            game.setDice2(4);
            game.setResult(Result.WIN);
            game.setPlayer(player);

            gameDTO = new GameDTO();
            gameDTO.setPk_GameID(1);
            gameDTO.setDice1(3);
            gameDTO.setDice2(4);
            gameDTO.setResult(Result.WIN);
            gameDTO.setPlayer(player);
        }

        @Nested
        @DisplayName("Roll Tests")
        class RollTests {

            @Test
            @DisplayName("Roll success")
            void testRoll_Success() {
                when(playerIRepository.findById("1")).thenReturn(Optional.of(player));
                when(gameIRepository.save(any(Game.class))).thenReturn(game);

                GameDTO result = gameService.roll("1");

                assertNotNull(result);
                assertEquals("1", result.getPlayer().getPlayerId());
                verify(gameIRepository, times(1)).save(any(Game.class));
            }

            @Test
            @DisplayName("Roll player not found")
            void testRoll_PlayerNotFound() {
                when(playerIRepository.findById("1")).thenReturn(Optional.empty());

                assertThrows(EntityNotFoundException.class, () -> gameService.roll("1"));
            }
        }

        @Nested
        @DisplayName("Delete All Tests")
        class DeleteAllTests {

            @Test
            @DisplayName("Delete all games success")
            void testDeleteAll_Success() {
                when(playerIRepository.findById("1")).thenReturn(Optional.of(player));

                gameService.deleteAll("1");

                verify(gameIRepository, times(1)).deleteByPlayer_PlayerId("1");
                assertEquals(0, player.getWinrate());
            }

            @Test
            @DisplayName("Delete all games player not found")
            void testDeleteAll_PlayerNotFound() {
                when(playerIRepository.findById("1")).thenReturn(Optional.empty());

                assertThrows(EntityNotFoundException.class, () -> gameService.deleteAll("1"));
            }
        }

        @Nested
        @DisplayName("Get All Games Tests")
        class GetAllGamesTests {

            @Test
            @DisplayName("Get all games success")
            void testGetAll_Success() {
                List<Game> games = new ArrayList<>();
                games.add(game);
                when(gameIRepository.findAll()).thenReturn(games);

                List<GameDTO> result = gameService.getAll();

                assertEquals(1, result.size());
                assertEquals(1, result.get(0).getPk_GameID());
            }
        }

        @Nested
        @DisplayName("Get All Games by Player Tests")
        class GetAllGamesByPlayerTests {

            @Test
            @DisplayName("Get all games by player success")
            void testGetAllPlayer_Success() {
                player.getGames().add(game);
                when(playerIRepository.findById("1")).thenReturn(Optional.of(player));

                List<GameDTO> result = gameService.getAllPlayer("1");

                assertEquals(1, result.size());
                assertEquals(1, result.get(0).getPk_GameID());
            }

            @Test
            @DisplayName("Get all games by player not found")
            void testGetAllPlayer_PlayerNotFound() {
                when(playerIRepository.findById("1")).thenReturn(Optional.empty());

                assertThrows(EntityNotFoundException.class, () -> gameService.getAllPlayer("1"));
            }
        }


}
