package edu.eci.cvds.Labtools.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta User que representa un usuario en el sistema de reservas.
 * Esta clase está mapeada a la colección "users" en MongoDB.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */
@Document(collection = "users")
public abstract class User {

    @Id
    protected String userId;
    protected String name;
    protected String email;
    protected String password;
    protected boolean logged;
    protected List<Booking> bookings = new ArrayList<>();
    protected String rol;

    /**
     * Agrega una reserva a la lista de reservas del usuario.
     * Si el usuario ya tiene 200 reservas, se lanza una excepción.
     *
     * @param booking Reserva a agregar.
     * @throws IllegalArgumentException Si el usuario ya tiene 200 reservas.
     */
    public void addBooking(Booking booking) {

        if (bookings.size() == 200) {
            throw new IllegalArgumentException("User already have twenty bookings");
        }
        bookings.add(booking);
    }

    /**
     * Elimina una reserva de la lista de reservas del usuario.
     * Si el usuario no tiene reservas, se lanza una excepción.
     *
     * @param booking Reserva a eliminar.
     * @throws IllegalArgumentException Si el usuario no tiene reservas.
     */
    public void deleteBooking(Booking booking) {
        if (bookings.isEmpty()) {
            throw new IllegalArgumentException("User don't have bookings");
        }
        bookings.remove(booking);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}