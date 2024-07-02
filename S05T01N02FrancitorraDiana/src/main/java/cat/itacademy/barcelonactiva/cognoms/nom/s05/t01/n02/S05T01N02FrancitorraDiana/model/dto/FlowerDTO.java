package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Arrays;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlowerDTO {

    private Integer pk_FlowerID;
    @NonNull
    private String flowerName;
    private String flowerCountry;
    private String flowerType;

    private static final List<String> UE_COUNTRIES = Arrays.asList(
            "Austria", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czech Republic",
            "Denmark", "Estonia", "Finland", "France", "Germany", "Greece",
            "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg",
            "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia",
            "Slovenia", "Spain", "Sweden"
    );


    public FlowerDTO(Integer pk_FlowerID, String flowerName, String flowerCountry) {
        this.pk_FlowerID = pk_FlowerID;
        this.flowerName = flowerName;
        this.flowerCountry =flowerCountry;
        setFlowerType(this.flowerCountry);
    }

    private void setFlowerType(String flowerCountry){
        this.flowerType = UE_COUNTRIES.stream()
                .anyMatch(country -> country.equalsIgnoreCase(flowerCountry)) ? "UE" : "NON UE";
    }
}


