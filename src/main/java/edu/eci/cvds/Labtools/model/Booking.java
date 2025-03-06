package edu.eci.cvds.Labtools.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "bookings")
public class Booking {

    private String bookingId;
    private int day;
    private int timeLine;
    private User user;
    private Lab lab;


}
