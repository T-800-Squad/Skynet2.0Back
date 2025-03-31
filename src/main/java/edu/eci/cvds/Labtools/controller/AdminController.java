package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.dto.CreateUserDTO;
import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.service.JwtService;
import edu.eci.cvds.Labtools.service.LabService;
import edu.eci.cvds.Labtools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private LabService labService;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody CreateUserDTO createUserDTO, @RequestHeader("Authorization") String token) {
        if(jwtService.validateUserIsAdmin(token)) {
            User user = userService.createUser(createUserDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(@RequestParam String userName, @RequestHeader("Authorization") String token) {
        if(jwtService.validateUserIsAdmin(token)) {
            userService.deleteUser(userName);
            return ResponseEntity.ok("User deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping("/lab")
    public ResponseEntity<Lab> createLab(@RequestParam String labName, @RequestHeader("Authorization") String token) {
        if(jwtService.validateUserIsAdmin(token)) {
            Lab lab = labService.createLab(labName);
            return ResponseEntity.status(HttpStatus.CREATED).body(lab);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @DeleteMapping("/lab")
    public ResponseEntity<String> deleteLab(@RequestParam String labName, @RequestHeader("Authorization") String token) {
        if(jwtService.validateUserIsAdmin(token)) {
            labService.deleteLab(labName);
            return ResponseEntity.ok("Lab deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

}
