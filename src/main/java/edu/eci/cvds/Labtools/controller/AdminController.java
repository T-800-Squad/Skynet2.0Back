package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.dto.CreateUserDTO;
import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.service.BookingService;
import edu.eci.cvds.Labtools.service.JwtService;
import edu.eci.cvds.Labtools.service.LabService;
import edu.eci.cvds.Labtools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de usuarios y laboratorios en la aplicación.
 * Este controlador proporciona endpoints para crear y eliminar usuarios y laboratorios,
 * así como para obtener prioridades de reservas, asegurando que solo los administradores
 * puedan acceder a estas funcionalidades.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private LabService labService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BookingService bookingService;

    /**
     * Endpoint para crear un nuevo usuario.
     *
     * @param createUserDTO DTO que contiene la información del nuevo usuario a crear.
     * @param token Token de autorización del usuario que realiza la solicitud.
     * @return ResponseEntity con el usuario creado y un código de estado 201 (CREATED) si el usuario es creado exitosamente,
     *         o un código de estado 401 (UNAUTHORIZED) si el token no es válido.
     */
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody CreateUserDTO createUserDTO, @RequestHeader("Authorization") String token) {
        if(jwtService.validateUserIsAdmin(token)) {
            User user = userService.createUser(createUserDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    /**
     * Endpoint para eliminar un usuario existente.
     *
     * @param userName Nombre del usuario a eliminar.
     * @param token Token de autorización del usuario que realiza la solicitud.
     * @return ResponseEntity con un mensaje de éxito y un código de estado 200 (OK) si el usuario es eliminado exitosamente,
     *         o un código de estado 401 (UNAUTHORIZED) si el token no es válido.
     */
    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(@RequestParam String userName, @RequestHeader("Authorization") String token) {
        if(jwtService.validateUserIsAdmin(token)) {
            userService.deleteUser(userName);
            return ResponseEntity.ok("User deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    /**
     * Endpoint para crear un nuevo laboratorio.
     *
     * @param labName Nombre del laboratorio a crear.
     * @param token Token de autorización del usuario que realiza la solicitud.
     * @return ResponseEntity con el laboratorio creado y un código de estado 201 (CREATED) si el laboratorio es creado exitosamente,
     *         o un código de estado 401 (UNAUTHORIZED) si el token no es válido.
     */
    @PostMapping("/lab")
    public ResponseEntity<Lab> createLab(@RequestParam String labName, @RequestHeader("Authorization") String token) {
        if(jwtService.validateUserIsAdmin(token)) {
            Lab lab = labService.createLab(labName);
            return ResponseEntity.status(HttpStatus.CREATED).body(lab);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    /**
     * Endpoint para eliminar un laboratorio existente.
     *
     * @param labName Nombre del laboratorio a eliminar.
     * @param token Token de autorización del usuario que realiza la solicitud.
     * @return ResponseEntity con un mensaje de éxito y un código de estado 200 (OK) si el laboratorio es eliminado exitosamente,
     *         o un código de estado 401 (UNAUTHORIZED) si el token no es válido.
     */
    @DeleteMapping("/lab")
    public ResponseEntity<String> deleteLab(@RequestParam String labName, @RequestHeader("Authorization") String token) {
        if(jwtService.validateUserIsAdmin(token)) {
            labService.deleteLab(labName);
            return ResponseEntity.ok("Lab deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }



}
