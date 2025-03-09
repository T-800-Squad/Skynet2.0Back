package edu.eci.cvds.Labtools.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.eci.cvds.Labtools.dto.CreateBookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.mongodb.client.model.Updates.push;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import jakarta.annotation.PostConstruct;
import static com.mongodb.client.model.Sorts.descending;

@Service
public class BasicBookingService implements BookingService {
    private final MongoClient mongoClient;
    private MongoCollection<Document> users;
    private MongoCollection<Document> labs;
    private MongoCollection<Document> bookings;
    private int bookingCount = 0;

    @Autowired
    public BasicBookingService(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @PostConstruct
    public void init() {
        MongoDatabase database = mongoClient.getDatabase("labTools");
        this.users = database.getCollection("users");
        this.labs = database.getCollection("laboratories");
        this.bookings = database.getCollection("bookings");


        Document lastBooking = bookings.find().sort(descending("bookingId")).first();
        this.bookingCount = (lastBooking != null) ? lastBooking.getInteger("bookingId") : 0;
    }

    public void createBooking(CreateBookingDTO createBookingDTO) {

        int bookingId = ++bookingCount;
        Document booking = new Document("bookingId", bookingId)
                .append("date", createBookingDTO.date)
                .append("user", createBookingDTO.userName)
                .append("lab", createBookingDTO.labName);

        bookings.insertOne(booking);
        users.updateOne(eq("name", createBookingDTO.userName), push("bookingsId", bookingId));

        System.out.println("Reserva creada exitosamente");
    }

    public String[] checkAvailability(String date) {
        return null;
    }

    public boolean deleteBooking(String bookingId) {
        return false;
    }

}
