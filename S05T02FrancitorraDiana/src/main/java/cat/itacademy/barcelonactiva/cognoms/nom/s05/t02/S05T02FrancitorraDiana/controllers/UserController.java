package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.UserDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        userService.create(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAll());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable("id") String userID){
        userService.delete(userID);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO){
        userService.update(userDTO);
        return ResponseEntity.ok().body(userDTO);
    }

    @GetMapping("/{id}/getOne")
    public ResponseEntity<?> getOne(@PathVariable("id") String userID){
        return ResponseEntity.ok().body(userService.getOne(userID));
    }
}
