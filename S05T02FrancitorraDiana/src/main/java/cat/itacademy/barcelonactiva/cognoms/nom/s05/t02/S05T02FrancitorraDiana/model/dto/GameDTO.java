package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.Player;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.utils.Result;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    private Integer pk_GameID;
    private int dice1;
    private int dice2;
    private Result result;
    @NotNull
    @JsonIgnore
    private Player player;

    public GameDTO(Integer pk_GameID, int dice1, int dice2, Result result) {
        this.pk_GameID = pk_GameID;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.result = result;
    }
}
