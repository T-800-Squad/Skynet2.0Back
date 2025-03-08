package edu.eci.cvds.Labtools.dto;

import lombok.Data;

@Data
public class BookingDTO {
    private String bookingId;
    private int day;
    private int timeLine;
    private String userName;
    private String labName;
}
