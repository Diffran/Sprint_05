package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.domain;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.exceptions.CountryNotFoundException;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.util.Countries;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk_FlowerID;
    @Column(name="name")
    private String flowerName;
    @Column(name="country")
    private String flowerCountry;

    public Flower(String flowerName, String flowerCountry) {
        this.flowerName = flowerName;
        this.flowerCountry = validCountry(flowerCountry);
    }
    private String validCountry(String country){
        return Countries.ALL_COUNTRIES.stream()
                .filter(c -> c.equalsIgnoreCase(country))
                .findFirst()
                .orElseThrow(() -> new CountryNotFoundException("Not Valid Country: " + country
                        + " -PLEASE ADD A VALID COUNTRYY"));
    }
}
