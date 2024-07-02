package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03FrancitorraDiana.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03FrancitorraDiana.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03FrancitorraDiana.model.services.impl.ClientFlowerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/ClientFlower")
@Tag(name="Flower Client Controller", description="Client endpoint for flower management")
public class ClientFlowerController {
    @Autowired
    private ClientFlowerServiceImpl flowerService;

    @PostMapping("/add")
    @Operation(summary = "Add a new flower from client",
            description = "Endpoint for clients to create a new flower entry using FlowerDTO.")
    public ResponseEntity<?> addFlower(@RequestBody FlowerDTO flowerDTO){
        flowerService.create(flowerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(flowerDTO);
    }

    @PutMapping("/update")
    @Operation(summary = "Update an existing flower from client",
            description = "Endpoint for clients to update an existing flower using FlowerDTO.")
    public ResponseEntity<?> updateFlower(@RequestBody FlowerDTO flowerDTO){
        flowerService.update(flowerDTO);
        return ResponseEntity.ok().body(flowerDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a flower from client",
            description = "Endpoint for clients to delete an existing flower by ID.")
    public ResponseEntity<Void> deleteFlower(@PathVariable("id") Integer flowerId){
        flowerService.delete(flowerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getOne/{id}")
    @Operation(summary = "Get a flower by ID from client",
            description = "Endpoint for clients to retrieve a flower by its ID.")
    public ResponseEntity<?> getOneFlower(@PathVariable("id") Integer flowerId){
        return ResponseEntity.ok().body(flowerService.getOne(flowerId));
    }
    @GetMapping("/getAll")
    @Operation(summary = "Get all flowers from client",
            description = "Endpoint for clients to retrieve all available flowers.")
    public ResponseEntity<List<FlowerDTO>> getAllFlowers(){
        return ResponseEntity.ok().body(flowerService.getAll());
    }
}
