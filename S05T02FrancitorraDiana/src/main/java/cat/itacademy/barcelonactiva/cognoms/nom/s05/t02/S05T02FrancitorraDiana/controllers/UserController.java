package cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.domain.User;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.dto.UserDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.model.services.impl.UserServiceImpl;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t02.S05T02FrancitorraDiana.utils.validations.AppValidations;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO, Principal principal){
        AppValidations.verifyUserAccess(principal,null);

        userService.create(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers(Principal principal){
        AppValidations.verifyUserAccess(principal,null);

        return ResponseEntity.ok().body(userService.getAll());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable("id") String userID, Principal principal){
        AppValidations.verifyUserAccess(principal,null);

        userService.delete(userID);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody UserDTO userDTO, Principal principal){
        AppValidations.verifyUserAccess(principal,null);

        userService.update(userDTO);
        return ResponseEntity.ok().body(userDTO);
    }

    @GetMapping("/{id}/getOne")
    public ResponseEntity<?> getOne(@PathVariable("id") String userID, Principal principal){
        AppValidations.verifyUserAccess(principal, userID);

        return ResponseEntity.ok().body(userService.getOne(userID));
    }
}
