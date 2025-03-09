package edu.eci.cvds.Labtools.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@ToString

public class CreateBookingDTO {
    public String userName;
    public String labName;
    public String date;
}
