package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.services.impl.FlowerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flower")
public class FlowerController {
    @Autowired
    private FlowerServiceImpl flowerService;


    @PostMapping("/add")
    @Operation(summary = "add flower", description="add a new flower entity to the database, all data must be entered" +
            "but only flowerName and flowerCountry matter, pk_FlowerID and flowerType are added automatically by the" +
            "program logic. The flowerCountry must be an existing country, or it will throw an exception")
    public ResponseEntity<?> addFlower(@RequestBody FlowerDTO flowerDTO){
        flowerService.create(flowerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(flowerDTO);
    }

    @PutMapping("/update")
    @Operation(summary= "update flower", description= "upload an existing flower entity, like in the /add method, but " +
            "pk_FlowerID must exist in the database for the method to work properly, if not it throws an exception. " +
            "The flowerCountry must be an existing country, or it will also throw an exception")
    public ResponseEntity<?> updateFlower(@RequestBody FlowerDTO flowerDTO){
        flowerService.update(flowerDTO);
        return ResponseEntity.ok().body(flowerDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete a flower", description = "delete a flower entity by entering its pk_FlowerID, if the " +
            "pk_FlowerID doesn't exist the program throws an exception")
    public ResponseEntity<?> deleteFlower(@PathVariable("id") Integer flowerId){
        flowerService.delete(flowerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getOne/{id}")
    @Operation(summary= "get one flower by its ID", description = "get the values of one flower entity in the database by" +
            " its pk_FlowerID. If the pk_FlowerID doesn't exist it throws an exception")
    public ResponseEntity<?> getOneFlower(@PathVariable("id") Integer flowerId){
        return ResponseEntity.ok().body(flowerService.getOne(flowerId));
    }

    @GetMapping("/getAll")
    @Operation(summary = "get all the flowers entities", description = "get all the flower entities saved in the database")
    public ResponseEntity<List<FlowerDTO>> getAllFlowers(){
        return ResponseEntity.ok().body(flowerService.getAll());
    }

}
