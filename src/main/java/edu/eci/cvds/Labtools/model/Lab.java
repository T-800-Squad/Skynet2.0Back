package edu.eci.cvds.Labtools.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * Clase Lab que representa un laboratorio en el sistema de reservas.
 * Esta clase está mapeada a la colección "laboratories" en MongoDB.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
@Document(collection = "laboratories")
public class Lab {
    @Id
    private String labId;
    private String name;
    private HashMap<LocalDateTime, Boolean> isAvailable = new HashMap<>();

    public String getLabId() {
        return labId;
    }
    public void setLabId(String labId) {
        this.labId = labId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<LocalDateTime, Boolean> getIsAvailable() {
        return isAvailable;
    }

    /**
     * Establece la disponibilidad del laboratorio en una fecha y hora específicas.
     * Si ya existe una reserva en esa fecha y hora, se lanza una excepción.
     *
     * @param localDateTime Fecha y hora para establecer la disponibilidad.
     * @throws IllegalArgumentException Si ya hay una reserva en esa fecha y hora.
     */
    public void setIsAvailable(LocalDateTime localDateTime) {
        if(isAvailable.containsKey(localDateTime)) {
            throw new IllegalArgumentException("this lab already has a booking on this date");
        }
        isAvailable.put(localDateTime, true);
    }

    /**
     * Elimina la disponibilidad del laboratorio en una fecha y hora específicas.
     * Si no hay una reserva en esa fecha y hora, se lanza una excepción.
     *
     * @param localDateTime Fecha y hora para eliminar la disponibilidad.
     * @throws IllegalArgumentException Si no hay una reserva en esa fecha y hora.
     */
    public void deleteIsAvailable(LocalDateTime localDateTime) {
        if(!isAvailable.containsKey(localDateTime)) {
            throw new IllegalArgumentException("this lab doesn't have a booking on this date");
        }
        isAvailable.remove(localDateTime);
    }
}
