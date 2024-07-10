package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @Column(name="player_id")
    private String playerId;
    private String nickname;
    private double winrate;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Game> games;


    public Player(String player_id, String nickname){
        this.playerId =player_id;
        this.nickname=nickname;
        this.winrate=0;
        games = new ArrayList<>();
    }
}
