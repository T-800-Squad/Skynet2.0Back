package edu.eci.cvds.Labtools.mapper;

import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.model.BookingDTO;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.repository.LabRepository;

public class BookingMapper implements GenericMapper<Booking, BookingDTO> {

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
        booking.setLab(LabRepository.getLab());
        booking.setUser(Optional< User > findByUserId(String userId));
        return booking;
    }
}
