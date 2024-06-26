package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03FrancitorraDiana.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flower {

    private Integer pk_FlowerID;
    private String flowerName;
    private String flowerCountry;

    public Flower(String flowerName, String flowerCountry) {
        this.flowerName = flowerName;
        this.flowerCountry = flowerCountry;
    }

}
