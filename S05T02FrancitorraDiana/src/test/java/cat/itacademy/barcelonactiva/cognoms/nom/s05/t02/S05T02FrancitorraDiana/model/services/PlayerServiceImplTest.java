package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.Player;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.User;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.UserDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.PlayerIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.repository.UserIRepository;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.impl.PlayerServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Player Service Unit Tests")
@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplTest {
    @Mock
    private PlayerIRepository playerIRepository;
    @Mock
    private UserIRepository userIRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private PlayerServiceImpl playerService;

    private PlayerDTO playerDTO;
    private Player player;

    @BeforeEach
    void setUp() {
        playerDTO = new PlayerDTO();
        playerDTO.setPlayer_id("1");
        playerDTO.setNickname("player1");

        player = new Player();
        player.setPlayerId("1");
        player.setNickname("player1");
    }

    @Nested
    @DisplayName("Player Create tests")
    class PlayerCreation{
        @Test
        @DisplayName("Create Player Failed User doesnt exist")
        void testCreatePlayer_Failed_User() {
            assertThrows(NoResultException.class, () -> {
                playerService.create(playerDTO);
            });
        }
    }


    @Test
    @DisplayName("Delete Player Success")
    void testDeletePlayer_Success() {
        when(playerIRepository.findById(anyString())).thenReturn(Optional.of(player));

        playerService.delete("1");

        verify(playerIRepository, times(1)).deleteById("1");
    }

    @Test
    @DisplayName("Get All Players")
    void testGetAllPlayers() {
        when(playerIRepository.findAll()).thenReturn(List.of(player));

        List<PlayerDTO> players = playerService.getAll();

        assertFalse(players.isEmpty());
        assertEquals(1, players.size());
    }

    @Test
    @DisplayName("Get One Player Success")
    void testGetOnePlayer_Success() {
        when(playerIRepository.findById(anyString())).thenReturn(Optional.of(player));

        PlayerDTO result = playerService.getOne("1");

        assertNotNull(result);
        assertEquals("1", result.getPlayer_id());
    }

    @Test
    @DisplayName("Get One Player Not Found")
    void testGetOnePlayer_NotFound() {
        when(playerIRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(NoResultException.class, () -> playerService.getOne("1"));
    }

    @Test
    @DisplayName("Get Average Winrate")
    void testGetAverageWinrate() {
        player.setWinrate(50.0);
        when(playerIRepository.findAll()).thenReturn(List.of(player));

        double winrate = playerService.getAverageWinrate();

        assertEquals(50.0, winrate);
    }

    @Test
    @DisplayName("Get Loser Player")
    void testGetLoserPlayer() {
        player.setWinrate(10.0);
        when(playerIRepository.findAll()).thenReturn(List.of(player));

        PlayerDTO loser = playerService.getLoser();

        assertNotNull(loser);
        assertEquals("1", loser.getPlayer_id());
    }

    @Test
    @DisplayName("Get Winner Player")
    void testGetWinnerPlayer() {
        player.setWinrate(90.0);
        when(playerIRepository.findAll()).thenReturn(List.of(player));

        PlayerDTO winner = playerService.getWinner();

        assertNotNull(winner);
        assertEquals("1", winner.getPlayer_id());
    }
}
