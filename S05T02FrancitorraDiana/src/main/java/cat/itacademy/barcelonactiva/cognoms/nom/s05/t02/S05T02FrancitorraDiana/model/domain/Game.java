package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.utils.Result;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk_GameID;
    @Column(name="dice_1")
    private int dice1;
    @Column(name="dice_2")
    private int dice2;
    @Enumerated(EnumType.STRING)
    private Result result;
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
}
