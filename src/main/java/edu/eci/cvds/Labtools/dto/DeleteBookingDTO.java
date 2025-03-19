package edu.eci.cvds.Labtools.dto;

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
