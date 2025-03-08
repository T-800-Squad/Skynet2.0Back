package edu.eci.cvds.Labtools.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "users")
public abstract class User {


    //protected String userId;

    //Cambio para asegurar funcionamiento
    @Id
    @Field("_id")
    protected String id;
    protected String name;
    protected String email;
    protected String password;
    protected boolean logged;
    protected List<Booking> bookings;

    public void addBooking(Booking booking) {

    }

    public void deleteBooking(String bookingId) {

    }

}
