package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.S05T01N02FrancitorraDiana.model.services.impl.FlowerServiceImpl;
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
    public ResponseEntity<?> addFruit(@RequestBody FlowerDTO flowerDTO){
        flowerService.create(flowerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(flowerDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateFruit(@RequestBody FlowerDTO flowerDTO){
        flowerService.update(flowerDTO);
        return ResponseEntity.ok().body(flowerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFruit(@PathVariable("id") Integer flowerId){
        flowerService.delete(flowerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getOneFruit(@PathVariable("id") Integer flowerId){
        return ResponseEntity.ok().body(flowerService.getOne(flowerId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FlowerDTO>> getAllFruits(){
        return ResponseEntity.ok().body(flowerService.getAll());
    }

}
