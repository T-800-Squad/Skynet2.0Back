package edu.eci.cvds.Labtools.mapper;

import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.model.BookingDTO;
import edu.eci.cvds.Labtools.repository.MongoLabRepository;
import edu.eci.cvds.Labtools.repository.MongoUserRepository;

public class BookingMapper implements GenericMapper<Booking, BookingDTO> {

    private final MongoUserRepository userRepository;
    private final MongoLabRepository labRepository;

    public BookingMapper(MongoUserRepository userRepository, MongoLabRepository labRepository) {
        this.userRepository = userRepository;
        this.labRepository = labRepository;
    }

    @Override
    public BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setBookingId(booking.getBookingId());
        dto.setDay(booking.getDay());
        dto.setTimeLine(booking.getTimeLine());
        dto.setUserName(booking.getUser().getName());
        dto.setLabName(booking.getLab().getLabId());
        return dto;
    }

    @Override
    public Booking toEntity(BookingDTO bookingDTO){
        Booking booking = new Booking();
        booking.setBookingId(bookingDTO.getBookingId());
        booking.setDay(bookingDTO.getDay());
        booking.setTimeLine(bookingDTO.getTimeLine());
        booking.setLab(this.labRepository.getLab(bookingDTO.getLabName()));
        booking.setUser(this.userRepository.findByUserId(bookingDTO.getUserName()).orElse(null));
        return booking;
    }
}
