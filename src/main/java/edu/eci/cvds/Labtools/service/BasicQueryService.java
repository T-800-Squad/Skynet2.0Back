package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.dto.BookingDTO;
import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.repository.MongoLabRepository;
import edu.eci.cvds.Labtools.repository.MongoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase BasicQueryService que implementa la interfaz QueryService.
 * Esta clase se encarga de gestionar consultas relacionadas con la disponibilidad de laboratorios
 * y las reservas de los usuarios.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
@Service
public class BasicQueryService implements QueryService {
    @Autowired
    private MongoUserRepository userRepository;

    @Autowired
    private MongoLabRepository labRepository;

    /**
     * Verifica la disponibilidad de laboratorios en una fecha específica.
     *
     * @param date La fecha para verificar la disponibilidad de los laboratorios.
     * @return Una lista de nombres de laboratorios que están disponibles en la fecha especificada.
     */
    public List<String> checkAvailability(String date) {
        List<Lab> labs = labRepository.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date,formatter);
        List<String> labList = new ArrayList<>();
        for (Lab lab : labs) {
            if(!lab.getIsAvailable().containsKey(dateTime)) {
                labList.add(lab.getName());
            }
        }
        return labList;
    }

    /**
     * Encuentra las reservas de un usuario por su nombre.
     *
     * @param name El nombre del usuario cuyas reservas se desean encontrar.
     * @return Una lista de objetos BookingDTO que representan las reservas del usuario.
     */
    public List<BookingDTO> findBookingsByName(String name){
        User user = userRepository.findByName(name);
        validateUser(user);
        List<Booking> bookings = user.getBookings();
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        for(Booking booking : bookings){
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setDate(booking.getDate());
            bookingDTO.setBookingId(booking.getBookingId());
            bookingDTO.setLabName(booking.getLab().getName());
            bookingDTOs.add(bookingDTO);
        }
        return bookingDTOs;
    }

    /**
     * Validar que un usuario exista y tenga reservas.
     *
     * @param user El usuario a validar.
     * @throws IllegalArgumentException Si el usuario no existe o no tiene reservas.
     */
    private void validateUser(User user) {

        if(user==null){
            throw new IllegalArgumentException("No user found");
        }
        if(user.getBookings().isEmpty()){
            throw new IllegalArgumentException("User don't have bookings");
        }

    }
}