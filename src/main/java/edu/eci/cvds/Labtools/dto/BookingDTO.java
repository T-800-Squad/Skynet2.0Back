package edu.eci.cvds.Labtools.dto;

/**
 * Clase BookingDTO (Data Transfer Object) que representa la información de una reserva.
 * Esta clase se utiliza para transferir datos entre la capa de servicio y la capa de presentación.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
public class BookingDTO {
    private String bookingId;
    private String labName;
    private String date;
    private int priority;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPriority() { return priority;}

    public void setPriority(int priority) { this.priority = priority;}
}
