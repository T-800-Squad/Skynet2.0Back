package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.dto.BookingDTO;
import edu.eci.cvds.Labtools.service.QueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de consultas relacionadas con la disponibilidad de laboratorios
 * y la búsqueda de reservas de usuarios. Este controlador proporciona endpoints para verificar
 * la disponibilidad de laboratorios en una fecha específica y para encontrar reservas asociadas a un usuario.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */

@RestController
@RequestMapping("/query")
public class QueryController {
    private QueryService queryService;

    /**
     * Constructor para inyectar el servicio de consultas.
     *
     * @param queryService Servicio de consultas a inyectar.
     */
    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    /**
     * Endpoint para verificar la disponibilidad de laboratorios en una fecha específica.
     *
     * @param date La fecha para la cual se desea verificar la disponibilidad de laboratorios.
     * @return List<String> que contiene los nombres de los laboratorios disponibles en la fecha especificada.
     */
    @GetMapping("/lab")
    public List<String> checkAvvailability(@RequestParam String date) {
        return queryService.checkAvailability(date);
    }

    /**
     * Endpoint para encontrar las reservas asociadas a un usuario específico.
     *
     * @param userName El nombre de usuario para el cual se desean encontrar las reservas.
     * @return List<BookingDTO> que contiene las reservas asociadas al usuario especificado.
     */
    @GetMapping
    public List<BookingDTO> findBookingsById(@RequestParam String userName) {
        return queryService.findBookingsByName(userName);
    }
}
