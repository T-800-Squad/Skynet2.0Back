package edu.eci.cvds.Labtools.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Clase Booking que representa una reserva en el sistema.
 * Esta clase está mapeada a la colección "bookings" en MongoDB.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
@Document(collection = "bookings")
public class Booking {

    @Id
    private String bookingId;
    private String date;
    private Lab lab;
    private int priority;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public int getPriority() { return  priority;}

    public void setPriority(int priority) { this.priority = priority;}

    @Override
    public boolean equals(Object booking) {
        if(booking instanceof Booking book) {
            return this.bookingId.equals(book.getBookingId());
        }
        return false;
    }
}
