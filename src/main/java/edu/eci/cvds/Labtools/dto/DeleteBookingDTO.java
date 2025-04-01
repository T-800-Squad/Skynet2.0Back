package edu.eci.cvds.Labtools.dto;

/**
 * Clase DeleteBookingDTO (Data Transfer Object) que representa la información necesaria
 * para eliminar una reserva. Esta clase se utiliza para transferir datos desde la capa
 * de presentación a la capa de servicio.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
public class DeleteBookingDTO {
    private String userName;
    private String bookingId;

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getBookingId() {
        return bookingId;
    }
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

}
